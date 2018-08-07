package com.example.verbosetech.cabberdrive.others;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.verbosetech.cabberdrive.R;
import com.example.verbosetech.cabberdrive.activities.ProfileActivity;
import com.example.verbosetech.cabberdrive.activities.SignInActivity;
import com.example.verbosetech.cabberdrive.helpers.CustomStatusBar;

import javax.security.auth.login.LoginException;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    private int ACCESS_LOCATION_STORAGE = 100;

    private View statusbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        statusbar = findViewById(R.id.statusBarBackground);
        CustomStatusBar customStatusBar = new CustomStatusBar(SplashScreen.this, this);
        customStatusBar.setStatusBarColor(statusbar, getResources().getColor(R.color.transparent_black));


        runthread();
    }


    public void runthread() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(SplashScreen.this, SignInActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
