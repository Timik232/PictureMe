package com.example.coursework.Repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.coursework.Database.ChatsDataBase;
import com.example.coursework.Database.DataChatPerson;
import com.example.coursework.Database.chatDAO;
import com.example.coursework.R;

import java.util.ArrayList;
import java.util.List;

public class ChatRepository {
    private final chatDAO chatPersonDao;
    private MutableLiveData<List<DataChatPerson>> allChatPersonsLiveData;

    public ChatRepository(Context context) {
        ChatsDataBase db = ChatsDataBase.getDatabase(context);
        chatPersonDao = db.chatDao();
        chatPersonDao.saveChat(new DataChatPerson("Кирилл Евдокимов", R.drawable.ava,  "Спасибо большое!!!", "10:00"));
        chatPersonDao.saveChat(new DataChatPerson("Ярослав Акатьев", R.drawable.rss,  "Здравствуйте!", "11:30"));
        chatPersonDao.saveChat(new DataChatPerson("Валера Чечня", R.drawable.valera,  "Ну как там с деньгами?", "12:45"));
        chatPersonDao.saveChat(new DataChatPerson("Бретт Оутри", R.drawable.stahli,  "Классно получилось", "22:40"));
        chatPersonDao.saveChat(new DataChatPerson("Дарья Аникова", R.drawable.dasha,  "Рад помочь) ", "00:00"));
        updateChatPersonsLiveData();
    }

    public LiveData<List<DataChatPerson>> getAllChatPersonsDataLiveData() {
        return allChatPersonsLiveData;
    }
    public void addChat(DataChatPerson chat) {
        chatPersonDao.saveChat(chat);
        updateChatPersonsLiveData();
    }

    private void updateChatPersonsLiveData() {
        // Получаем данные из базы данных и устанавливаем их в LiveData
        List<DataChatPerson> chatPersons = chatPersonDao.getAllChatPersons();
        allChatPersonsLiveData.setValue(chatPersons);
    }
    public List<DataChatPerson> getAllChatPersons() {
        return chatPersonDao.getAllChatPersons();
    }
    public void updateChat(DataChatPerson chat) {
        chatPersonDao.updateLastMessage(chat);
        updateChatPersonsLiveData();
    }
}