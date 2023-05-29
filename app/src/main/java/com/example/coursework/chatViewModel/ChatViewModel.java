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
    private LiveData<List<DataChatPerson>> allChatPersonsLiveData;
    private Executor executor;
    private Handler mainHandler;

    public ChatViewModel(Context context) {
        executor = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());
        Log.d("VIEWMODEL", "Запущена модель");
//        chatRepository = new ChatRepository(context);
//        loadChats();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                chatRepository = new ChatRepository(context);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loadChats();
                    }
                });
            }
        });
    }

    public LiveData<List<DataChatPerson>> getAllChatPersonsLiveData() {
        return allChatPersonsLiveData;
    }
    public List<DataChatPerson> getAllChatPersons() {
        return chatRepository.getAllChatPersons().getValue();
    }
    public void addChatPerson(DataChatPerson chatPerson) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                chatRepository.addChat(chatPerson);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loadChats();
                    }
                });
            }
        });
    }
    public void updateChatPerson(DataChatPerson chatPerson) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                chatRepository.updateChat(chatPerson);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loadChats();
                    }
                });
            }
        });
    }
    protected void loadChats(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                allChatPersonsLiveData = chatRepository.getAllChatPersonsDataLiveData();
            }
        });

    }
}

