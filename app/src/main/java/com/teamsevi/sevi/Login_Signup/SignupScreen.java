package com.teamsevi.sevi.Login_Signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.teamsevi.sevi.Home.HomePage;
import com.teamsevi.sevi.R;

public class SignupScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signup_screen);
    }
    public void callSignupScreen2(View view){

        Intent intent = new Intent(getApplicationContext(), Signup_OTP.class);
        startActivity(intent);

    }

    public void callLoginScreen(View view){
        Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
        startActivity(intent);
    }
}
