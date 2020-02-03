package com.example.sciwizprojectmvvm.Viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.sciwizprojectmvvm.Repositories.FilmRepository;
import com.example.sciwizprojectmvvm.models.Result;
import com.example.sciwizprojectmvvm.util.Resource;

import java.util.List;

public class FilmListViewModel extends AndroidViewModel {

    private MediatorLiveData<Resource<List<Result>>> recipes = new MediatorLiveData<>();
    private FilmRepository mRecipeRepository;
    public static final String QUERY_EXHAUSTED = "No more results.";


    public FilmListViewModel(@NonNull Application application) {
        super(application);
        mRecipeRepository = FilmRepository.getInstance(application);

    }

    public LiveData<Resource<List<Result>>> getRecipes(){
        return recipes;
    }


    public void searchRecipesApi(){
        final LiveData<Resource<List<Result>>> repositorySource = mRecipeRepository.searchFilmsApi();

        recipes.addSource(repositorySource, new Observer<Resource<List<Result>>>() {
            @Override
            public void onChanged(Resource<List<Result>> listResource) {
                recipes.setValue(listResource);
            }
        });
    }

}
