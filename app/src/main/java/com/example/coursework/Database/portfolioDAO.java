package com.example.coursework.Database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface portfolioDAO {
    @Insert
    void savePortfolio(PortfolioPerson portfolioPerson);

    @Update()
    void updatePortfolio(PortfolioPerson portfolioPerson);

    @Query("SELECT * from portfolio where `name`=(:name)")
    PortfolioPerson getPortfolio(String name);

    @Query("SELECT * FROM portfolio")
    LiveData<List<PortfolioPerson>> getAllPortfolio();

}

