package com.teamsevi.sevi.Login_Signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.teamsevi.sevi.Home.HomePage;
import com.teamsevi.sevi.R;

public class Signup_OTP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signup__otp);
    }
    public void callHomeScreen(View view){

        Intent intent = new Intent(getApplicationContext(), HomePage.class);
        startActivity(intent);

    }
}
