package com.example.sciwizprojectmvvm.persistance;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sciwizprojectmvvm.models.Result;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface FilmDao {

    @Insert(onConflict = IGNORE)
    long[] insertFilms(Result... result);

    @Insert(onConflict = IGNORE)
    void insertFilm(List<Result>result);

    @Query("SELECT * FROM filmsList ORDER BY title desc ")
    LiveData<List<Result>> searchFilims();

}
