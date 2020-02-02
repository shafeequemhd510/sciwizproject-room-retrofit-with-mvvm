package com.example.sciwizprojectmvvm.persistance;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.sciwizprojectmvvm.models.Result;


@Database(entities = {Result.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class FilmsDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "films_db";

    private static FilmsDatabase instance;

    public static FilmsDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    FilmsDatabase.class,
                    DATABASE_NAME
            ).allowMainThreadQueries()
                    .setJournalMode(JournalMode.TRUNCATE)
                    .build();
        }
        return instance;
    }

    public abstract FilmDao getFilmDao();


}