package com.example.coursework;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.coursework.chatViewModel.AppViewModel;
import com.example.coursework.databinding.MainFragmentBinding;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.location.Location;
import com.yandex.mapkit.location.LocationListener;
import com.yandex.mapkit.location.LocationManager;
import com.yandex.mapkit.location.LocationStatus;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;


public class MainFragment extends Fragment {
    private MapView mapview;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private AppViewModel appViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appViewModel = new ViewModelProvider(this).get(AppViewModel.class);
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("username", "").equals("")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", "Комолов Тимур");
            editor.putString("email", "photographer@email.com");
            editor.apply();
        }
        appViewModel.setPreferences(sharedPreferences);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        MainFragmentBinding binding = MainFragmentBinding.inflate(getLayoutInflater());
        mapview = view.findViewById(R.id.mapview);
        MapKitFactory.initialize(requireContext());
        mapview = (MapView)view.findViewById(R.id.mapview);
        mapview.getMap().move(
                new CameraPosition(new Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 2),
                null);
        return view;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        LocationManager locationManager = MapKitFactory.getInstance().createLocationManager();
        Log.d("Tag", "Запустил фрагмент");
        MapKitFactory.getInstance().onStart();
        mapview.onStart();
        Button find = view.findViewById(R.id.findPhotographer);
        find.setOnClickListener(v -> {
            Toast.makeText(requireContext().getApplicationContext(), "Нет доступных фотографов", Toast.LENGTH_SHORT).show();
            Log.d("Pressed", "Button was pressed");
        });
        ImageView profile = view.findViewById(R.id.profile_icon);
        profile.setOnClickListener(v ->{
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.profile);
        });
        ImageView nearMe = view.findViewById(R.id.nearMe);
        nearMe.setOnClickListener(v ->{
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {                Log.d("move", "kuda");

                locationManager.requestSingleUpdate(new LocationListener() {
                    @Override
                    public void onLocationUpdated(@NonNull Location location) {
                        Log.d("move", "tudaa");
                        double latitude = location.getPosition().getLatitude();
                        double longitude = location.getPosition().getLongitude();
                        mapview.getMap().move(
                                new CameraPosition(new Point(latitude, longitude), 14.0f, 0.0f, 0.0f),
                                new Animation(Animation.Type.SMOOTH, 1),
                                null);
                    }

                    @Override
                    public void onLocationStatusUpdated(@NonNull LocationStatus locationStatus) {

                    }
                });
            }
            else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    builder.setTitle("Разрешение доступа к местоположению");
                    builder.setMessage("Для определения вашего местоположения необходимо предоставить разрешение. Хотите предоставить разрешение?");
                    builder.setPositiveButton("Да", (dialog, which) -> {
                        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
                        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
                    });
                    builder.setNegativeButton("Нет", (dialog, which) -> {
                    Toast.makeText(requireContext(),"Без разрешения мы не сможем отслеживать ваше местоположение", Toast.LENGTH_LONG).show();
                    });
                    builder.show();
                } else {
                    ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
                }
            }
        });
    }
    @Override
    public void onStop(){
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapview.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        MapKitFactory.getInstance().onStart();
        mapview.onStart();
    }
}
