package com.example.verbosetech.cabberdrive.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.verbosetech.cabberdrive.R;
import com.example.verbosetech.cabberdrive.activities.AboutActivity;
import com.example.verbosetech.cabberdrive.activities.DocumentsActivity;
import com.example.verbosetech.cabberdrive.activities.HelpActivity;
import com.example.verbosetech.cabberdrive.activities.MyTripsActivity;
import com.example.verbosetech.cabberdrive.activities.ProfileActivity;
import com.example.verbosetech.cabberdrive.activities.SettingsActivity;

public class DriverInfoFragment extends android.support.v4.app.Fragment {
    CardView cv_help, cv_documents, cv_share, cv_about, cv_settings, cv_mytrips;
    Switch switch_;
    TextView tv_offline,tv_online,tv_driver_view_profile;


    public DriverInfoFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driver_profile,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cv_help=view.findViewById(R.id.cv_help);
        cv_documents=view.findViewById(R.id.cv_documents);
        cv_settings=view.findViewById(R.id.cv_settings);
        cv_mytrips=view.findViewById(R.id.cv_mytrips);
        cv_share=view.findViewById(R.id.cv_share);
        cv_about=view.findViewById(R.id.cv_about);



        cv_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HelpActivity.class));
            }
        });




        cv_documents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DocumentsActivity.class));
            }
        });


        cv_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
            }
        });


        cv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AboutActivity.class));
            }
        });


        cv_mytrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MyTripsActivity.class));
            }
        });


        cv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
            }
        });






    }
}
