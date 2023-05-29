package com.example.coursework.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "texts")
public class DataChatPerson {
    @NotNull
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "photo")
    private int photo;
    @ColumnInfo(name = "last_message")
    private String last_message;
    @ColumnInfo(name = "last_data")
    private String last_date;

    public void setId(@NotNull Integer id) {
        this.id = id;
    }

    @NotNull
    public Integer getId() {
        return id;
    }

    public DataChatPerson(String name, int photo, String last_message, String last_date) {
        this.name = name;
        this.photo = photo;
        this.last_date = last_date;
        this.last_message = last_message;
    }

    public void setLast_data(String last_date) {
        this.last_date = last_date;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }

    public String getName() {
        return name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_date() {
        return last_date;
    }

    public String getLast_message() {
        return last_message;
    }
}
