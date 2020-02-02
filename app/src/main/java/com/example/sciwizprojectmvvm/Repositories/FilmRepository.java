package com.example.sciwizprojectmvvm.Repositories;


import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.sciwizprojectmvvm.Requests.FilmApiClienttt;
import com.example.sciwizprojectmvvm.models.Result;
import com.example.sciwizprojectmvvm.persistance.FilmsDatabase;

import java.util.List;


public class FilmRepository {

    //    private MutableLiveData<List<Result>> mFilms;
    private FilmApiClienttt filmApiClienttt;
    FilmsDatabase filmsDatabase;


    private static FilmRepository instance;

    public static FilmRepository getInstance() {
        if (instance == null) {
            instance = new FilmRepository();
        }
        return instance;
    }

    public FilmRepository() {
//        mFilms = new MutableLiveData<>();
        filmApiClienttt = FilmApiClienttt.getInstance();

//        filmsDatabase=FilmsDatabae.getInstance();
    }


    public void searchFilmsApi() {
        filmApiClienttt.searchFilmsApi();
    }

    public LiveData<List<Result>> getFilms(LifecycleOwner context) {
        filmsDatabase= FilmsDatabase.getInstance((Context) context);
        if( filmsDatabase.getFilmDao().searchFilims().getValue()!=null){
            return  filmsDatabase.getFilmDao().searchFilims();
        }

        else {
        LiveData<List<Result>> filmsLive = filmApiClienttt.getfilms();

        filmsLive.observe(context, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {

                if (results!=null){
                filmsDatabase.getFilmDao().insertFilm(results);

            }}
        });
        return filmsDatabase.getFilmDao().searchFilims();
    }}}
















