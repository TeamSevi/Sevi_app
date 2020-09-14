package com.teamsevi.sevi.Login_Signup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.teamsevi.sevi.Home.HomePage;
import com.teamsevi.sevi.R;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_screen);


    }
    public void callHomeScreen(View view){
        Intent intent = new Intent(getApplicationContext(), HomePage.class);
        startActivity(intent);
    }

    public void callSignupScreen(View view){
        Intent intent = new Intent(getApplicationContext(), SignupScreen.class);
        startActivity(intent);
    }
    public void callForgetPassword(View view){
        Intent intent = new Intent(getApplicationContext(), Forget_Password.class);
        startActivity(intent);
    }
}

