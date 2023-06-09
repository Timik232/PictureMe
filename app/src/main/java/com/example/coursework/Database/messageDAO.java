package com.example.coursework.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface messageDAO {
    @Insert
    void saveMessage(Message message);

    @Update()
    void updateMessage(Message message);

    @Query("SELECT * from message where chatId=:chatId")
    LiveData<List<Message>> getChatMessages(Integer chatId);
    @Query("SELECT * from chats where `name`=(:name)")
    Message getMessageByName(String name);

}
