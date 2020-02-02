package com.example.sciwizprojectmvvm.Requests;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sciwizprojectmvvm.AppExecutors;
import com.example.sciwizprojectmvvm.models.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.example.sciwizprojectmvvm.Requests.Constants.NETWORK_TIMEOUT;

public class FilmApiClienttt {


        private RetrieveFilmsRunnable retrieveFilmsRunnable;
        private static FilmApiClienttt instance;
        private MutableLiveData<List<Result>> mFilms;

        public static FilmApiClienttt getInstance(){
            if(instance == null){
                instance = new FilmApiClienttt();
            }
            return instance;
        }

        private FilmApiClienttt() {

            mFilms = new MutableLiveData<>();
        }

        public LiveData<List<Result>> getfilms(){
            return mFilms;
        }


        public void searchFilmsApi(){
            retrieveFilmsRunnable = new RetrieveFilmsRunnable();
            final Future handler = AppExecutors.getInstance().networkIO().submit(retrieveFilmsRunnable);
                AppExecutors.getInstance().networkIO().schedule(new Runnable() {
                    @Override
                    public void run() {
                        handler.cancel(false);
                    }
                },NETWORK_TIMEOUT,TimeUnit.MILLISECONDS);
        }

        private class RetrieveFilmsRunnable implements Runnable{

            @Override
            public void run() {

                try {
                    Response response = getFilms().execute();

                    int code=response.code();
                    if (code==200){

                    }

                    if (response.code()==200){
                        List<Result>list= new ArrayList<>(((FilimsResponse)response.body()).getResults());


                        mFilms.postValue(list);
                    }
                    else {

                        String error = response.errorBody().string();
                        Log.e("vw", "run: "+error );
                        mFilms.postValue(null);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    mFilms.postValue(null);
                }

            }

            private Call<FilimsResponse>getFilms(){

                return ServiceGenerator.getFilimApiClient().getfilims();
            }
        }
    }


