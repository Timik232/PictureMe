package com.example.coursework.Database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface chatDAO {
    @Insert
    void saveChat(DataChatPerson chatPerson);

    @Update()
    void updateLastMessage(DataChatPerson chatPerson);

    @Query("SELECT * from chats where `name`=(:name)")
    DataChatPerson getChat(String name);

    @Query("SELECT * FROM chats")
    LiveData<List<DataChatPerson>> getAllChatPersons();

}
