package com.teamsevi.sevi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.teamsevi.sevi.Login_Signup.LoginScreen;

public class SplashScreen extends AppCompatActivity {
    private  static int SPLASH_TIMER =2000;
    ImageView splashBg;
    Animation splashAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        //Hooks
        splashBg = findViewById(R.id.splash_bg);
        splashAnim = AnimationUtils.loadAnimation(this,R.anim.splash_anim);

        splashBg.setAnimation(splashAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIMER);

    }
}
