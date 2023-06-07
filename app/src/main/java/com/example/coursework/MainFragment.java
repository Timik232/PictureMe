package com.example.coursework;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.graphics.PointF;
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

import com.bumptech.glide.Glide;
import com.example.coursework.databinding.MainFragmentBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.shape.MaterialShapeDrawable;
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
import com.yandex.runtime.image.ImageProvider;



public class MainFragment extends Fragment {
    private MapView mapview;
    private MapKit mapKit;
    private PlacemarkMapObject currentPlacemark;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
//    private void createPlacemark(Point point) {
//        // Создание стиля отметки
//        PlacemarkStyle placemarkStyle = new PlacemarkStyle();
//        placemarkStyle.setIcon(ImageProvider.fromResource(getApplicationContext(), R.drawable.icon_placemark));
//
//        // Создание отметки с использованием стиля
//        currentPlacemark = yandexMap.getMapObjects().addPlacemark(point, placemarkStyle);
//    }


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
        ImageView nearMe = view.findViewById(R.id.nearMe);

//        mapview.getMap().addTapListener(new MapView.MapTapListener() {
//            @Override
//            public boolean onMapTapped(@NonNull final PointF point) {
//                PlacemarkMapObject placemark = mapObjects.addPlacemark(new Point(point.x, point.y));
//                placemark.setIcon(ImageProvider.fromResource(requireContext(), R.drawable.icon));
//                return true;
//            }
//        });
        //MaterialShapeDrawable shapeDrawable = MaterialShapeDrawable.createWithElevation(this, 8f);

        //nearMe
        nearMe.setOnClickListener(v ->{
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Log.d("move", "kuda");
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
