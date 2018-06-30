package com.example.verbosetech.cabberdrive.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.verbosetech.cabberdrive.R;
import com.example.verbosetech.cabberdrive.helpers.BottomNavigationViewHelper;

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

       // navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        //navigationView.setOnNavigationItemSelectedListener(this);
        //BottomNavigationViewHelper.removeShiftMode(navigationView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }

    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    @Override
    public void onPause() {
        super.onPause();
       // overridePendingTransition(0, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {

            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(this, MainActivity.class));
            } else if (itemId == R.id.navigation_ratings) {
                startActivity(new Intent(this, MainActivity.class));
            } else if (itemId == R.id.navigation_earnings) {
                startActivity(new Intent(this, EarningsActivity.class));
            }
            else if(itemId==R.id.navigation_profile)
                startActivity(new Intent(this,ProfileActivity.class));
            finish();


        return true;
    }

    private void updateNavigationBarState(){
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    void selectBottomNavigationBarItem(int itemId) {
        MenuItem item = navigationView.getMenu().findItem(itemId);
        item.setChecked(true);
    }

    abstract int getContentViewId();

    abstract int getNavigationMenuItemId();

}
