package com.teamsevi.sevi.Login_Signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.teamsevi.sevi.Home.HomePage;
import com.teamsevi.sevi.R;

public class Signup_OTP extends AppCompatActivity {
    EditText otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signup_otp);

        otp = findViewById(R.id.otp);
//        String _fname = getIntent().getStringExtra("firstname");
//        String _lname = getIntent().getStringExtra("lastname");
//        String _phone = getIntent().getStringExtra("phone");
//        String _email = getIntent().getStringExtra("email");
//        String _pass = getIntent().getStringExtra("password");

    }
    public void callHomeScreen(View view){

        Intent intent = new Intent(getApplicationContext(), HomePage.class);
        startActivity(intent);

    }
}
