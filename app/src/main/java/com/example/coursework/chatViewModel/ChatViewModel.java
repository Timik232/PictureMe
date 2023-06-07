package com.example.coursework.chatViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.coursework.Database.DataChatPerson;
import com.example.coursework.Database.PortfolioPerson;
import com.example.coursework.Repository.ChatRepository;

import java.util.List;

public class ChatViewModel extends ViewModel {
    private ChatRepository chatRepository;

    public ChatViewModel() {
        Log.d("VIEWMODEL", "Запущена модель");
        chatRepository = ChatRepository.getInstance();
    }

    public LiveData<List<DataChatPerson>> getAllChatPersonsLiveData() {
        return chatRepository.getAllChatPersonsDataLiveData();
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
    public DataChatPerson getChatPerson(String name){
        return chatRepository.getChat(name);
    }
    public LiveData<List<PortfolioPerson>> getAllPortfolioLiveData(){
        return chatRepository.getAllPortfolioLiveData();
    }
}

