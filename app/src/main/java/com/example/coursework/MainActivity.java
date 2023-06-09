package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.coursework.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        return navController.navigateUp() || super.onSupportNavigateUp();
    }


}