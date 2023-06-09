package com.example.coursework.Repository;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.coursework.Database.AppDataBase;
import com.example.coursework.Database.DataChatPerson;
import com.example.coursework.Database.Message;
import com.example.coursework.Database.PortfolioPerson;
import com.example.coursework.Database.chatDAO;
import com.example.coursework.Database.messageDAO;
import com.example.coursework.Database.portfolioDAO;
import com.example.coursework.R;

import java.util.ArrayList;
import java.util.List;

public class ChatRepository {
    private final chatDAO chatPersonDao;
    private final portfolioDAO portfolioDao;
    private final messageDAO messageDao;
    private SharedPreferences user;
    private LiveData<List<DataChatPerson>> allChatPersonsLiveData;
    private LiveData<List<PortfolioPerson>> allPortfolioLiveData;

    private static ChatRepository instance;

    public static ChatRepository getInstance(){
        if (instance == null){
            instance = new ChatRepository();
        }
        return instance;
    }

    private ChatRepository() {

        AppDataBase db = AppDataBase.getDatabase();
        this.chatPersonDao = db.chatDao();
        this.portfolioDao = db.portfolioDao();
        this.messageDao = db.messageDao();
        this.allPortfolioLiveData = portfolioDao.getAllPortfolio();
        this.allChatPersonsLiveData = chatPersonDao.getAllChatPersons();

        //updateChatPersonsLiveData();


    }

    public LiveData<List<DataChatPerson>> getAllChatPersonsDataLiveData() {
        return allChatPersonsLiveData;
    }
    public void addChat(DataChatPerson chat) {
        chatPersonDao.saveChat(chat);
    }

    private void updateChatPersonsLiveData() {
        allChatPersonsLiveData = chatPersonDao.getAllChatPersons();
    }
    public LiveData<List<DataChatPerson>> getAllChatPersons() {
        return chatPersonDao.getAllChatPersons();
    }
    public void updateChat(DataChatPerson chat) {
        chatPersonDao.updateLastMessage(chat);
    }
    public DataChatPerson getChat(String name){
        return chatPersonDao.getChat(name);
    }
    public PortfolioPerson getPortfolio(String name){
        return portfolioDao.getPortfolio(name);
    }
    public LiveData<List<PortfolioPerson>> getAllPortfolioLiveData(){
        return portfolioDao.getAllPortfolio();
    }
    public LiveData<List<Message>> getChatMessages(Integer chatId){
        return messageDao.getChatMessages(chatId);
    }
    public void saveMessage(Message message){
        messageDao.saveMessage(message);
    }
    public void setPreferences(SharedPreferences user){
        this.user = user;
    }
    public SharedPreferences getUser(){
        return user;
    }
    public void setUserName(String name){
        SharedPreferences.Editor editor = user.edit();
        editor.putString("username",name);
        editor.apply();
    }
    public void setUserEmail(String email){
        SharedPreferences.Editor editor = user.edit();
        editor.putString("email",email);
        editor.apply();
    }
    public String getLastMessage(Integer id){
        List<Message> message = chatPersonDao.getLastMessage(id);
        return message.get(0).getText();
    }
}
