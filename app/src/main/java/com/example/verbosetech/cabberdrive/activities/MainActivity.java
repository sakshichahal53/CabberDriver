package com.example.verbosetech.cabberdrive.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.verbosetech.cabberdrive.R;
import com.example.verbosetech.cabberdrive.adapters.ViewPagerAdapter;
import com.example.verbosetech.cabberdrive.fragments.DriverInfoFragment;
import com.example.verbosetech.cabberdrive.fragments.EarningsFragment;
import com.example.verbosetech.cabberdrive.fragments.HomeFragment;
import com.example.verbosetech.cabberdrive.fragments.RatingsFragment;
import com.example.verbosetech.cabberdrive.helpers.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    Switch switch_;
    TextView tv_offline, tv_online;
    private ViewPager viewPager;
    private BottomNavigationView bottom_navigation_view;

    //Fragments

    HomeFragment home_fragment;
    DriverInfoFragment profile_fragment;
    EarningsFragment earnings_fragment;
    RatingsFragment ratings_fragment;

    MenuItem prevMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch_ = findViewById(R.id.switch_home_off_on);
        tv_offline = findViewById(R.id.tv_home_off);
        tv_online = findViewById(R.id.tv_home_online);


        tv_offline.setTextColor(getResources().getColor(R.color.white));
        tv_online.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        switch_.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    tv_offline.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    tv_online.setTextColor(getResources().getColor(R.color.white));


                } else {

                    tv_online.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    tv_offline.setTextColor(getResources().getColor(R.color.white));


                }


            }
        });


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottom_navigation_view = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        BottomNavigationViewHelper.removeShiftMode(bottom_navigation_view);
        bottom_navigation_view.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.navigation_ratings:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.navigation_earnings:
                                viewPager.setCurrentItem(2);
                                break;
                           case R.id.navigation_profile:
                                viewPager.setCurrentItem(3);
                                break;

                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottom_navigation_view.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottom_navigation_view.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottom_navigation_view.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });



        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        home_fragment=new HomeFragment();
        ratings_fragment=new RatingsFragment();
        earnings_fragment=new EarningsFragment();
        profile_fragment=new DriverInfoFragment();

        adapter.addFragment(home_fragment);
        adapter.addFragment(ratings_fragment);
        adapter.addFragment(earnings_fragment);
        adapter.addFragment(profile_fragment);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return false;
    }
}
