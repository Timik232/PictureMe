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

import com.example.coursework.databinding.ActivityMainBinding;
import com.example.coursework.databinding.MainFragmentBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView menu = binding.navigationMenu;
        View navHost = findViewById(R.id.fragmentContainerView);
        NavController navController = Navigation.findNavController(navHost);
        Log.d("Tag", "Работает");

        NavigationUI.setupWithNavController(menu, navController);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.mainFragment, R.id.chatsFragment, R.id.listFragment)
                .setFallbackOnNavigateUpListener(null)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        Log.d("Tag", "Прошло");
        menu.setSelectedItemId(R.id.mainFragment);
        navController.addOnDestinationChangedListener((controller, destination,arguments) -> {
            if (appBarConfiguration.getTopLevelDestinations().contains(destination.getId())){
                menu.setVisibility(View.VISIBLE);
            }
            else {
                menu.setVisibility(View.GONE);
            }
        });
    }

}