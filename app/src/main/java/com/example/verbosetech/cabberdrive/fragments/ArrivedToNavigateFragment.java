package com.example.verbosetech.cabberdrive.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.verbosetech.cabberdrive.R;
import com.example.verbosetech.cabberdrive.activities.Fare_and_RateActivity;

public class ArrivedToNavigateFragment extends android.support.v4.app.Fragment {

    TextView  tv_overview_ride;
    Button btn_final_arrived;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_accept_rider_navigate, container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_overview_ride=view.findViewById(R.id.over_view_rides);
        tv_overview_ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_nav_bar, new ShowLocationsFragment(), "Rider Request");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btn_final_arrived=view.findViewById(R.id.btn_final_arrived);
        btn_final_arrived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Fare_and_RateActivity.class));
            }
        });
    }
}
