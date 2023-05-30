package com.example.coursework.Database;

import static com.example.coursework.PictureMe.getAppContext;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataChatPerson.class}, version = 4)
public abstract class AppDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "PictureMe";
    private static AppDataBase instance;

    public static synchronized AppDataBase getDatabase() {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            getAppContext(),
                            AppDataBase.class,
                            DATABASE_NAME)
                    .fallbackToDestructiveMigration().allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract chatDAO chatDao();
}
