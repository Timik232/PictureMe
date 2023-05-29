package com.example.coursework.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataChatPerson.class}, version = 4)
public abstract class ChatsDataBase extends RoomDatabase {
    private static final String dbName = "texts";
    private static ChatsDataBase db;

    public static synchronized ChatsDataBase getDatabase(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context, ChatsDataBase.class, dbName)
                    .fallbackToDestructiveMigration().allowMainThreadQueries()
                    .build();
        }
        return db;
    }

    public abstract chatDAO chatDao();
}
