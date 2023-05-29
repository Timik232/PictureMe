package com.example.coursework.chatViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.coursework.Database.DataChatPerson;

import java.util.List;

public class DataChatLiveData {
    private MutableLiveData<List<DataChatPerson>> chatPersonsLiveData;

    public DataChatLiveData() {
        chatPersonsLiveData = new MutableLiveData<>();
    }

    public LiveData<List<DataChatPerson>> getChatPersonsLiveData() {
        return chatPersonsLiveData;
    }

    public void setChatPersons(List<DataChatPerson> chatPersons) {
        chatPersonsLiveData.setValue(chatPersons);
    }
}

