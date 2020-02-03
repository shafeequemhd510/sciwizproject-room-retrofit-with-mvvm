package com.example.sciwizprojectmvvm.Repositories;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;


import com.example.sciwizprojectmvvm.AppExecutors;
import com.example.sciwizprojectmvvm.Requests.ApiResponse;
import com.example.sciwizprojectmvvm.Requests.Constants;
import com.example.sciwizprojectmvvm.Requests.FilimsResponse;
import com.example.sciwizprojectmvvm.Requests.ServiceGenerator;
import com.example.sciwizprojectmvvm.models.Result;
import com.example.sciwizprojectmvvm.persistance.FilmDao;
import com.example.sciwizprojectmvvm.persistance.FilmsDatabase;
import com.example.sciwizprojectmvvm.util.NetworkBoundResource;
import com.example.sciwizprojectmvvm.util.Resource;

import java.util.ArrayList;
import java.util.List;


public class FilmRepository {


    private static final String TAG = "FilmRepository";

    private static FilmRepository instance;
    private FilmDao filmDao;

    public static FilmRepository getInstance(Context context) {
        if (instance == null) {
            instance = new FilmRepository(context);
        }
        return instance;
    }


    private FilmRepository(Context context) {
        filmDao = FilmsDatabase.getInstance(context).getFilmDao();
    }

    public LiveData<Resource<List<Result>>> searchFilmsApi() {
        return new NetworkBoundResource<List<Result>, FilimsResponse>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull FilimsResponse item) {

                    filmDao.insertFilm(item.getResults());

                }
            @Override
            protected boolean shouldFetch(@Nullable List<Result> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Result>> loadFromDb() {
                return filmDao.searchFilims();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<FilimsResponse>> createCall() {
                return ServiceGenerator.getFilimApiClient()
                        .getfilims();
            }
        }.getAsLiveData();
    }


}













