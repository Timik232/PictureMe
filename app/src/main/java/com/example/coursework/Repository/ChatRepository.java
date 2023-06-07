package com.example.coursework.Repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.coursework.Database.AppDataBase;
import com.example.coursework.Database.DataChatPerson;
import com.example.coursework.Database.PortfolioPerson;
import com.example.coursework.Database.chatDAO;
import com.example.coursework.Database.portfolioDAO;
import com.example.coursework.R;

import java.util.ArrayList;
import java.util.List;

public class ChatRepository {
    private final chatDAO chatPersonDao;
    private final portfolioDAO portfolioDao;
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
}
