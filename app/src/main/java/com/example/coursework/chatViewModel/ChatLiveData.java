package com.example.coursework.chatViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.coursework.Repository.Chat_Person;

public class ChatLiveData {
    private MutableLiveData<Chat_Person> chatLiveData;

    public LiveData<Chat_Person> getChatLiveData() {
        if (chatLiveData == null) {
            chatLiveData = new MutableLiveData<>();
        }
        return chatLiveData;
    }

    public void setChat(Chat_Person chat) {
        if (chatLiveData != null) {
            chatLiveData.setValue(chat);
        }
    }

    public void updateLastMessage(String message) {
        if (chatLiveData != null) {
            Chat_Person currentChat = chatLiveData.getValue();
            if (currentChat != null) {
                currentChat.setLast_message(message);
                chatLiveData.setValue(currentChat);
            }
        }
    }
    public void updateLastTime(String time) {
        if (chatLiveData != null) {
            Chat_Person currentChat = chatLiveData.getValue();
            if (currentChat != null) {
                currentChat.setLast_data(time);
                chatLiveData.setValue(currentChat);
            }
        }
    }
}

