package com.example.verbosetech.cabberdrive.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.verbosetech.cabberdrive.R;
import com.example.verbosetech.cabberdrive.adapters.PastTripsAdapter;
import com.example.verbosetech.cabberdrive.models.SingleTrip;

import java.util.ArrayList;
import java.util.List;

public class MyTripsActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private PastTripsAdapter m_adapter;
    private List<SingleTrip> my_trips = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips);
        Toolbar toolbar=findViewById(R.id.toolbar_trips);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recycler_view = (RecyclerView)findViewById(R.id.recycler_view_past_trips);

        m_adapter = new PastTripsAdapter(my_trips);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(m_adapter);

        prepareMovieData();
        }

    public void prepareMovieData() {

        SingleTrip trip1 = new SingleTrip("12:00 am", "Rolls Royce-X", "$100-$110", true);
        my_trips.add(trip1);

        SingleTrip trip2 = new SingleTrip("08:21 am", "Maruti Swift Desire", "$65-$85", true);
        my_trips.add(trip2);

        SingleTrip trip3 = new SingleTrip("09:00 am", "Maruti Nano", "$20-$30", true);
        my_trips.add(trip3);

        SingleTrip trip4 = new SingleTrip("11:26 am", "Maruti Suzuki Alto", "$23-43", true);
        my_trips.add(trip4);
    }

}
