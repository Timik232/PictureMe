package com.example.coursework;

import android.app.Application;
import android.content.Context;

public class PictureMe extends Application {
    private static PictureMe instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getAppContext(){
        return instance.getApplicationContext();
    }
}
