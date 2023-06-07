package com.example.coursework.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "portfolio")
public class PortfolioPerson {
    @NotNull
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "photo")
    private int photo;
    @ColumnInfo(name = "rate")
    private String rate;
    @ColumnInfo(name = "text")
    private String text;
    @ColumnInfo(name = "icon")
    private int icon;
    @ColumnInfo(name = "cost")
    private String cost;

    public void setId(@NotNull Integer id) {
        this.id = id;
    }

    @NotNull
    public Integer getId() {
        return id;
    }

    public PortfolioPerson(String name, int photo, int icon, String text, String rate, String cost) {
        this.name = name;
        this.photo = photo;
        this.text = text;
        this.icon = icon;
        this.rate = rate;
        this.cost = cost;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(int icon) {
        this.icon = icon;
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

    public String getText() {
        return text;
    }

    public int getIcon() {
        return icon;
    }

    public String getCost() {
        return cost;
    }

    public String getRate() {
        return rate;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
