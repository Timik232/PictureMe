package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.coursework.databinding.MainFragmentBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View navHost = findViewById(R.id.fragmentContainerView);
        NavController navController = Navigation.findNavController(navHost);
        BottomNavigationView menu = findViewById(R.id.navigation_menu);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration
                .Builder(R.id.main_fragment, R.id.chats_fragment, R.id.photographer_fragment)
                .build();

        NavigationUI.setupWithNavController(menu, navController);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }
}