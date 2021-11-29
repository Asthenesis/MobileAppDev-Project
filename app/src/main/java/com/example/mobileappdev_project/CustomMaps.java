package com.example.mobileappdev_project;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CustomMaps extends AppCompatActivity implements OnMapReadyCallback {
    Button  mylocation;
    GoogleMap map;
    LatLng home, position;
    Double latitude, longitude, x, y;
    int PLACE_AUTO = 1;

    @Override
    public void onMapReady(GoogleMap googleMap){
        map = googleMap;
        home = new LatLng(-6.257385, 106.618320);
        map.addMarker(new MarkerOptions().position(home).title("Lokasi Anda!")).showInfoWindow();
        map.moveCamera(CameraUpdateFactory.newLatLng(home));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(home,16));
        map.setTrafficEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_maps);
        getSupportActionBar().hide();

        try{
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
        }


        mylocation = findViewById(R.id.btnMyLocation);
        mylocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(getApplicationContext(),"Error: Tidak ada akses ke GPS!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    map.setMyLocationEnabled(true);
                    LocationManager locationManager= (LocationManager) getSystemService(LOCATION_SERVICE);
                    Criteria criteria = new Criteria();
                    //Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    position = new LatLng(latitude,longitude);
                    map.addMarker(new MarkerOptions().position(position).title("My Location Now")).showInfoWindow();
                    map.animateCamera(CameraUpdateFactory.newLatLng(position));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(position,18));
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}