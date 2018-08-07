package com.example.verbosetech.cabberdrive.fragments;

import android.app.Fragment;
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
import com.example.verbosetech.cabberdrive.others.GlobalVariables;

public class AcceptRiderCopyFragment extends android.support.v4.app.Fragment {

    Button btn_arrived, btn_cancel;
    TextView tv_info_rider;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_accept_rider_copy,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_arrived=view.findViewById(R.id.btn_arrived_rider);
        btn_cancel=view.findViewById(R.id.btn_cancel_ride);

        boolean is_cancelled_ride = ((GlobalVariables) getActivity().getApplication()).isIs_cancelled();
        if(is_cancelled_ride)
        {

            btn_cancel.setVisibility(View.VISIBLE);
            btn_arrived.setVisibility(View.GONE);

            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_nav_bar, new WhyCancelFragment(), "Rider Request");
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }
        else
        {

            btn_arrived.setVisibility(View.VISIBLE);
            btn_cancel.setVisibility(View.GONE);
            btn_arrived.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_nav_bar, new ArrivedToNavigateFragment(), "Rider Request");
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });

        }

        tv_info_rider=view.findViewById(R.id.tv_info_rrider);
        tv_info_rider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_nav_bar, new RiderInfoFragment(), "Rider Request");
                fragmentTransaction.addToBackStack("info_rider");
                fragmentTransaction.commit();


            }
        });
    }
}
