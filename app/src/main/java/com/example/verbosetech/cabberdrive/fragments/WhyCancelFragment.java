package com.example.verbosetech.cabberdrive.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.verbosetech.cabberdrive.R;
import com.example.verbosetech.cabberdrive.activities.MainActivity;

public class WhyCancelFragment extends android.support.v4.app.Fragment {

    TextView tV_cancel_other;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_why_cancel,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tV_cancel_other=view.findViewById(R.id.tv_other_cancel);
        tV_cancel_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
    }
}
