package com.example.verbosetech.cabberdrive.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.verbosetech.cabberdrive.R;

public class SignInActivity extends AppCompatActivity {

    private Button btn_continue_login;
    private TextView tv_nav_signup, tv_fgot_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Toolbar toolbar=findViewById(R.id.toolbar_signin);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tv_fgot_pass = findViewById(R.id.tv_forgot_password);
        tv_nav_signup = findViewById(R.id.tv_nav_signup);

        Spannable forgot = new SpannableString("Forgot ");
        forgot.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.text_Color)), 0, forgot.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_fgot_pass.setText(forgot);
        Spannable password = new SpannableString("Password?");

        password.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), 0, password.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_fgot_pass.append(password);


        Spannable new_user = new SpannableString("New user? ");
        new_user.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.text_Color)), 0, new_user.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_nav_signup.setText(new_user);
        Spannable signup = new SpannableString("Sign up");

        signup.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), 0, signup.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_nav_signup.append(signup);



        btn_continue_login=findViewById(R.id.btn_log_in);
        btn_continue_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this,MainActivity.class));
            }
        });

    }
}
