package com.example.sciwizprojectmvvm.Viewmodel;


import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sciwizprojectmvvm.Repositories.FilmRepository;
import com.example.sciwizprojectmvvm.models.Result;

import java.util.List;

public class FilimListViewmodel extends ViewModel {

    private FilmRepository filmRepository;

    private MutableLiveData<List<Result>> mFilms = new MutableLiveData<>();

    public FilimListViewmodel() {
        filmRepository = FilmRepository.getInstance();
    }

    public void searchFilmsApi(){
        filmRepository.searchFilmsApi();
    }


    public LiveData<List<Result>> getRecipes(LifecycleOwner lifecycleOwner) {

        return filmRepository.getFilms(lifecycleOwner);
    }

}




