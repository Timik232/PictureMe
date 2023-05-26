package com.example.coursework;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.coursework.databinding.MainFragmentBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View navHost = findViewById(R.id.fragmentContainerView);
        NavController navController = Navigation.findNavController(navHost);
        Log.d("Tag", "Нашёл навигацию");
        BottomNavigationView menu = findViewById(R.id.navigation_menu);
        Log.d("Tag", "Нашёл меню навигации");
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.main_fragment_menu, R.id.chats_fragment_menu, R.id.photographer_fragment_menu)
                .setFallbackOnNavigateUpListener(null)
                .build();


        Log.d("Tag", "Работает");
        NavigationUI.setupWithNavController(menu, navController);
        menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main_fragment_menu:
                        navController.navigate(R.id.action_toHomeFragment);
                        break;
                    case R.id.chats_fragment_menu:
                        Log.d("Tag", "Переход во фрагмент");
                        navController.navigate(R.id.action_toChats);

                        break;
                    case R.id.photographer_fragment_menu:
                        navController.navigate(R.id.action_toPhotographers);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        Log.d("Tag", "Прошло");
    }

}