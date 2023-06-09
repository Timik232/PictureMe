package com.example.coursework.chatViewModel;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.coursework.Database.DataChatPerson;
import com.example.coursework.Database.Message;
import com.example.coursework.Database.PortfolioPerson;
import com.example.coursework.Repository.ChatRepository;

import java.util.List;

public class AppViewModel extends ViewModel {
    private ChatRepository chatRepository;

    public AppViewModel() {
        Log.d("VIEWMODEL", "Запущена модель");
        chatRepository = ChatRepository.getInstance();
    }

    public LiveData<List<DataChatPerson>> getAllChatPersonsLiveData() {
        return chatRepository.getAllChatPersonsDataLiveData();
    }
    public DataChatPerson getChatPerson(String name){
        return chatRepository.getChat(name);
    }
    public LiveData<List<PortfolioPerson>> getAllPortfolioLiveData(){
        return chatRepository.getAllPortfolioLiveData();
    }
    public PortfolioPerson getPortfolio(String name){
        return chatRepository.getPortfolio(name);
    }
    public LiveData<List<Message>> getChatMessages(Integer chatId){
        return chatRepository.getChatMessages(chatId);
    }
    public void saveMessage(Message message){
        new Thread(() -> {
            chatRepository.saveMessage(message);
        }).start();
    }
    public void setPreferences(SharedPreferences preferences){
        new Thread(() -> {
            chatRepository.setPreferences(preferences);
        }).start();
    }
    public String getUserName(){
        SharedPreferences user = chatRepository.getUser();
        return user.getString("username","");
    }
    public String getEmailName(){
        SharedPreferences user = chatRepository.getUser();
        return user.getString("email","");
    }
    public void setUserName(String name){
        new Thread(() -> {
            chatRepository.setUserName(name);
        }).start();
    }
    public void setUserEmail(String email){
        new Thread(() -> {
            chatRepository.setUserEmail(email);
        }).start();
    }
    public List<DataChatPerson> getAllChatPersons() {
        return chatRepository.getAllChatPersons().getValue();
    }
    public void addChatPerson(DataChatPerson chatPerson) {
        new Thread(() -> {
            chatRepository.addChat(chatPerson);
        }).start();
    }
    public void updateChatPerson(DataChatPerson chatPerson) {
        new Thread(() -> {
            chatRepository.updateChat(chatPerson);
        }).start();
    }
    public void setLastMessage(String name, String text){
        DataChatPerson person = chatRepository.getChat(name);
        person.setLast_message(text);
        chatRepository.updateChat(person);
    }
    public void setLastDate(String name, String time){
        DataChatPerson person = chatRepository.getChat(name);
        person.setLast_data(time);
        chatRepository.updateChat(person);
    }
    public String getLastMessage(Integer id){
        return chatRepository.getLastMessage(id);
    }

}

