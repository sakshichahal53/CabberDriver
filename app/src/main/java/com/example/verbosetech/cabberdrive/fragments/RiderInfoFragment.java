package com.example.verbosetech.cabberdrive.fragments;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.verbosetech.cabberdrive.R;
import com.example.verbosetech.cabberdrive.others.GlobalVariables;


public class RiderInfoFragment extends android.app.Fragment {


    private GlobalVariables global_var;
    Button btn_cancel_ride;
    public RiderInfoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_rider_info, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        global_var=new GlobalVariables();

        btn_cancel_ride= view.findViewById(R.id.btn_cancel_ride_rider_info);
        btn_cancel_ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getActivity().getFragmentManager();
                ((GlobalVariables) getActivity().getApplication()).setIs_cancelled(true);

                fm.popBackStack ("info_rider", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });
    }
}
