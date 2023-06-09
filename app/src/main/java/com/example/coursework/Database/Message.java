package com.example.coursework.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "message")
public class Message {
    @NotNull
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(name = "chatId")
    Integer chatId;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "text")
    private String text;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "time")
    private String time;
    public void setId(@NotNull Integer id) {
        this.id = id;
    }

    @NotNull
    public Integer getId() {
        return id;
    }
    public void setChatId(@NotNull Integer id) {
        this.chatId = id;
    }

    public Integer getChatId() {
        return chatId;
    }

    public Message(String name, Integer chatId, String text, String date, String time) {
        this.name = name;
        this.text = text;
        this.date = date;
        this.time = time;
        this.chatId = chatId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }
}
