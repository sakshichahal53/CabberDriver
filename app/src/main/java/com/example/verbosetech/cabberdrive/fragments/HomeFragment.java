package com.example.verbosetech.cabberdrive.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.verbosetech.cabberdrive.R;
import com.example.verbosetech.cabberdrive.activities.StartRidesActivity;
import com.example.verbosetech.cabberdrive.others.GlobalVariables;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class HomeFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback {

    private static final String TAG = "HomeActivity";
    private Toolbar toolbar;
    Switch switch_;
    TextView tv_offline, tv_online;
    GoogleMap google_map_global;
    Location my_location;
    private LatLng my_location_latlng;

    private CardView cardview_home_offline;
    LocationManager locationManager;
    String provider;

    private static final int ACCESS_LOCATION_STORAGE = 100;
    private FusedLocationProviderClient fused_client;


    private MapView map_view;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv_offline = getActivity().findViewById(R.id.tv_home_off);
        tv_online = getActivity().findViewById(R.id.tv_home_online);
        cardview_home_offline = getActivity().findViewById(R.id.cardview_offline);
        switch_ = getActivity().findViewById(R.id.switch_home_off_on);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);

        switch_.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    tv_offline.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    tv_online.setTextColor(getResources().getColor(R.color.white));
                    Log.e("DIRVER", " online");

                    cardview_home_offline.setVisibility(View.GONE);
                    set_my_location();


                } else {

                    Log.e("DIRVER", "offline");

                    tv_online.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    tv_offline.setTextColor(getResources().getColor(R.color.white));
                    cardview_home_offline.setVisibility(View.VISIBLE);


                }


            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ((GlobalVariables) getActivity().getApplication()).setIs_cancelled(false);

        map_view = (MapView) view.findViewById(R.id.map_view);
        map_view.onCreate(savedInstanceState);

        map_view.onResume();   // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

         map_view.getMapAsync(this);


        fused_client = LocationServices.getFusedLocationProviderClient(getActivity());



    }

    @Override
    public void onMapReady(GoogleMap google_map) {

        google_map_global=google_map;

        try {

            boolean success = google_map.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                    getActivity(), R.raw.styles));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }


        google_map_global.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                startActivity(new Intent(getActivity(), StartRidesActivity.class));
                return true;
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        map_view.onResume();
        if (!isLocationEnabled(getActivity())) {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(getActivity());
            }
            builder.setTitle("Location Services Disabled")
                    .setMessage("Please Turn Your GPS Location On!")
                    .show();


        }
        if (
                ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            android.Manifest.permission.ACCESS_FINE_LOCATION }, ACCESS_LOCATION_STORAGE
            );
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        map_view.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        map_view.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        map_view.onLowMemory();
    }

    public void set_my_location() {

        Log.e(TAG, "Isnide set loction");
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Log.e(TAG, "Looking for loction");
        google_map_global.setMyLocationEnabled(true);
        google_map_global.getUiSettings().setMyLocationButtonEnabled(false);
        fused_client.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Log.e(TAG, "My Location is " + String.valueOf(location.getLatitude()));
                    my_location = location;
                    my_location_latlng = new LatLng(my_location.getLatitude(), my_location.getLongitude());


                    google_map_global.setMinZoomPreference(10.0f);
                    google_map_global.moveCamera(CameraUpdateFactory.newLatLngZoom(my_location_latlng, 5));
                    google_map_global.clear();

                    int height = 50;
                    int width = 100;
                    BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.car_);
                    Bitmap b=bitmapdraw.getBitmap();
                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                    MarkerOptions markerOptions = new MarkerOptions().position(my_location_latlng)
                            .title("Current Location")
                            .icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                    Marker m_marker = google_map_global.addMarker(markerOptions);




                }
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Getting location failed");
                Toast.makeText(getActivity(), "Getting Location failed", Toast.LENGTH_SHORT).show();
            }
        });


    }




    public static boolean isLocationEnabled (Context context){
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        Log.e(TAG, "access code is "+requestCode);
        switch (requestCode) {


            case ACCESS_LOCATION_STORAGE: {

                if (grantResults.length > 0
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "Granted");

                } else {
                    Log.e(TAG, "Not Granted");
                    Toast.makeText(getActivity(), "Please grant location permissions in Settings", Toast.LENGTH_SHORT).show();
                }
                return;

            }

        }
    }

}
