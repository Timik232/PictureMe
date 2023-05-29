package com.example.coursework.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataChatPerson.class}, version = 4)
public abstract class AppDataBase extends RoomDatabase {
    private static final String dbName = "texts";
    private static AppDataBase db;

    public static synchronized AppDataBase getDatabase(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context, AppDataBase.class, dbName)
                    .fallbackToDestructiveMigration().allowMainThreadQueries()
                    .build();
        }
        return db;
    }

    public abstract chatDAO chatDao();
}
