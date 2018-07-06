package com.example.verbosetech.cabberdrive.activities;

import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.verbosetech.cabberdrive.R;
import com.example.verbosetech.cabberdrive.fragments.ArrivedToNavigateFragment;
import com.example.verbosetech.cabberdrive.fragments.ShowLocationsFragment;
import com.example.verbosetech.cabberdrive.helpers.DrawRoutes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class StartRidesActivity extends AppCompatActivity implements OnMapReadyCallback {
    private DrawRoutes draw_routes;
    private GoogleMap google_map_g ;

    private Marker source_marker;
    private Marker dest_marker;

 private static final String TAG="StartRidesActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_rides);

        draw_routes=new DrawRoutes(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_to_go);
        mapFragment.getMapAsync(this);



        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_nav_bar, new ShowLocationsFragment(), "Rider Request");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

    @Override
    public void onMapReady(GoogleMap google_map) {

        google_map_g=google_map;
        try {

            boolean success = google_map.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                    this, R.raw.styles));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }



        LatLng source_latlng= new LatLng(40.6892, 74.0445);
        LatLng dest_latlng=new LatLng(40.7484, 73.9857);


        MarkerOptions s_markerOptions = new MarkerOptions().position(source_latlng).title("Source Location");

        MarkerOptions d_marker_options = new MarkerOptions().position(dest_latlng).title("Destination Location");

        source_marker=google_map.addMarker(s_markerOptions);
        dest_marker=google_map.addMarker(d_marker_options);

        google_map.moveCamera(CameraUpdateFactory.newLatLngZoom(source_latlng, 10));


        //Log.e(TAG, String.valueOf(s_markerOptions)+String.valueOf(s_markerOptions));
       draw_routes.set_markers(source_marker,dest_marker,google_map);

    }

}
