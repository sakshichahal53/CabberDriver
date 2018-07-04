package com.example.verbosetech.cabberdrive.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.verbosetech.cabberdrive.R;

public class DriverInfoFragment extends Fragment {
    CardView cv_help, cv_documents, cv_share, cv_about, cv_settings, cv_mytrips;
    Toolbar toolbar;

    public DriverInfoFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.driver_profile,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar=(Toolbar) getActivity().findViewById(R.id.toolbar_driver_profile);

        cv_help=view.findViewById(R.id.cv_help);
        cv_documents=view.findViewById(R.id.cv_documents);
        cv_settings=view.findViewById(R.id.cv_settings);
        cv_mytrips=view.findViewById(R.id.cv_mytrips);
        cv_share=view.findViewById(R.id.cv_share);
        cv_about=view.findViewById(R.id.cv_about);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);





    }
}
