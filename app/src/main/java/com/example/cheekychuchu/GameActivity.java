package com.example.cheekychuchu;

import androidx.fragment.app.FragmentActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Random;

public class GameActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
         mMap.getUiSettings().setAllGesturesEnabled(false);
         Intent intent = getIntent();
         float bearing = intent.getFloatExtra("bearing", 0);
         double lat = intent.getDoubleExtra("Lat", 0);
         double lon = intent.getDoubleExtra("Lon", 0);
         float tilt = intent.getFloatExtra("tilt", 0);
         float zoom = intent.getFloatExtra("zoom", 0);
         CameraPosition cameraPosition = new CameraPosition(new LatLng(lat, lon), zoom, tilt, bearing);
         mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
         mMap.setMyLocationEnabled(true);
        LatLng bottomLeft =
                mMap.getProjection().getVisibleRegion().nearLeft;
        Log.e("AAA", "AAAA");

        LatLng topRight =
                mMap.getProjection().getVisibleRegion().farRight;
        double top = topRight.latitude;
        double bottom = bottomLeft.latitude;
        double right = topRight.longitude;
        double left = bottomLeft.longitude;
        double ranLat = bottom;
        double ranLong = left;
        Log.e("top",  top + "");
        Log.e("bottom",  bottom + "");
        call(ranLat, ranLong, (top - bottom) / 50);


        /*
                mMap.setMinZoomPreference(16.8f);
        mMap.setMaxZoomPreference(17.2f);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                Toast.makeText(GameActivity.this, "h8fw", Toast.LENGTH_SHORT).show();
                if (location != null) {
                    Toast.makeText(GameActivity.this, "YEAHHh", Toast.LENGTH_SHORT).show();
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
                    mMap.setMyLocationEnabled(true);
                }
            }
        });
         */

    }
    public void call(final double a, final double b, final double stepLength) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng bottomLeft =
                            mMap.getProjection().getVisibleRegion().nearLeft;

                    LatLng topRight =
                            mMap.getProjection().getVisibleRegion().farRight;
                    double top = topRight.latitude;
                    double bottom = bottomLeft.latitude;
                    double right = topRight.longitude;
                    double left = bottomLeft.longitude;
                    double r1 = Math.random();
                    double r2 = Math.random();
                    final double ranLat = a;
                    final double ranLong = b;
                    final double x;
                    final double y;
                    //double ranLat = (top - bottom) * r1;
                    //double ranLong = (right - left) * r2;
                    if (r1 < 0.5) {
                        if (ranLat + stepLength < top) {
                            y = ranLat +  stepLength;
                        } else if (ranLat - stepLength > bottom) {
                            y = ranLat - stepLength;
                        } else {
                            y = ranLat;
                        }
                    } else {
                        if (ranLat - stepLength > bottom) {
                            y = ranLat - stepLength;
                        } else if (ranLat + stepLength < top) {
                            y = ranLat + stepLength;
                        } else {
                            y = ranLat;
                        }
                    }
                    if (r2 < 0.5) {
                        if (ranLong + stepLength < right) {
                            x = ranLong +  stepLength;
                        } else if (ranLong - stepLength > left) {
                            x = ranLong - stepLength;
                        } else {
                            x = ranLong;
                        }
                    } else {
                        if (ranLong - stepLength > left) {
                            x = ranLong - stepLength;
                        } else if (ranLong + stepLength < right) {
                            x = ranLong + stepLength;
                        } else {
                            x = ranLong;
                        }
                    }

                    LatLng randomLocation = new LatLng((ranLat), (ranLong));
                    MarkerOptions markerOptions = new MarkerOptions().position(randomLocation).icon(BitmapDescriptorFactory.fromResource(R.drawable.chuchuling));
                    Marker chuchOnMap = mMap.addMarker(markerOptions);
                    float[] f = new float[2];
                    Location.distanceBetween(chuchOnMap.getPosition().latitude,
                            chuchOnMap.getPosition().longitude, location.getLatitude(),
                            location.getLongitude(), f);
                    float t = f[0];
                    //Toast.makeText(GameActivity.this,t + "", Toast.LENGTH_SHORT).show();
                    if (f[0] < 100) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(GameActivity.this);
                        builder1.setCancelable(false);
                        builder1.setTitle("You Won!");

                        builder1.setPositiveButton(
                                "Return to Home Screen",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    } else {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                // Actions to do after 10 seconds
                                //Toast.makeText(GameActivity.this, "YEAHNNN", Toast.LENGTH_SHORT).show();
                                mMap.clear();
                                    Handler handler1 = new Handler();
                                    handler1.postDelayed(new Runnable() {
                                        public void run() {
                                            call(y, x, stepLength);

                                        }
                                    }, 1);
                                }
                        }, 50);
                    }

                }
            }
        });

    }
}
