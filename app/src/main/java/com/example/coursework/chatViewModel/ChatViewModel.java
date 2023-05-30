package com.example.coursework.chatViewModel;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.coursework.Database.DataChatPerson;
import com.example.coursework.Repository.ChatRepository;
import com.example.coursework.Repository.Chat_Person;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ChatViewModel extends ViewModel {
    private ChatRepository chatRepository;
    private Handler mainHandler;

    public ChatViewModel() {
        mainHandler = new Handler(Looper.getMainLooper());
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
}

