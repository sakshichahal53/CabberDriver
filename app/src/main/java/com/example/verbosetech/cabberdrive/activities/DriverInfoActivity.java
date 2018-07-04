package com.example.verbosetech.cabberdrive.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.verbosetech.cabberdrive.R;
import com.example.verbosetech.cabberdrive.fragments.BottomNavBarFragment;

public class DriverInfoActivity extends AppCompatActivity {
    CardView cv_help, cv_documents, cv_share, cv_about, cv_settings, cv_mytrips;
    Toolbar toolbar;
    Switch switch_;
    TextView tv_offline,tv_online,tv_driver_view_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_profile);

        Toolbar toolbar=findViewById(R.id.toolbar_driver_profile);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);


        toolbar=findViewById(R.id.toolbar_driver_profile);

        cv_help=findViewById(R.id.cv_help);
        cv_documents=findViewById(R.id.cv_documents);
        cv_settings=findViewById(R.id.cv_settings);
        cv_mytrips=findViewById(R.id.cv_mytrips);
        cv_share=findViewById(R.id.cv_share);
        cv_about=findViewById(R.id.cv_about);

        switch_=findViewById(R.id.switch_driver_profile);
        tv_offline=findViewById(R.id.tv_driver_offline);
        tv_online=findViewById(R.id.tv_driver_online);

        tv_offline.setTextColor(getResources().getColor(R.color.white));
        tv_online.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        tv_driver_view_profile=findViewById(R.id.tv_driver_view_profile);
        tv_driver_view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverInfoActivity.this, ProfileActivity.class));

            }
        });

        switch_.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    tv_offline.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    tv_online.setTextColor(getResources().getColor(R.color.white));
                    Log.e("DIRVER"," checked");
                }
                else {
                    Log.e("DIRVER", "not checked");
                    tv_online.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    tv_offline.setTextColor(getResources().getColor(R.color.white));
                }


            }
        });

        cv_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverInfoActivity.this, HelpActivity.class));
            }
        });




        cv_documents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverInfoActivity.this, DocumentsActivity.class));
            }
        });


        cv_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverInfoActivity.this, SettingsActivity.class));
            }
        });


        cv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverInfoActivity.this, AboutActivity.class));
            }
        });


        cv_mytrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverInfoActivity.this, MyTripsActivity.class));
            }
        });


        cv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverInfoActivity.this, SettingsActivity.class));
            }
        });


        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_nav_bar, new BottomNavBarFragment(), "Navbar");
        fragmentTransaction.commit();

    }
}
