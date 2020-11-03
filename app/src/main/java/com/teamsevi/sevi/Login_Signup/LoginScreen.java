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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.teamsevi.sevi.Home.HomePage;
import com.teamsevi.sevi.R;

public class LoginScreen extends AppCompatActivity {

    EditText phoneno,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_screen);

        phoneno = findViewById(R.id.login_phoneno);
        password = findViewById(R.id.login_password);

    }
    private Boolean validatephone(){
        String val = phoneno.getText().toString();
        if(val.isEmpty()){
            phoneno.setError("Field can not be Empty");
            return false;
        }else if(val.length()!=10)
        {
            phoneno.setError("Invalid Input");
            return false;
        }
        else{
            phoneno.setError(null);
            return true;
        }
    }

    private Boolean validatepass(){
        String val = password.getText().toString();
        if(val.isEmpty()){
            password.setError("Field can not be Empty");
            return false;
        }else{
            password.setError(null);
            return true;
        }
    }

    public void callHomeScreen(View view){
        String _phone = phoneno.getText().toString();
        if(!validatephone() | !validatepass() ){
            return;
        }
        Intent intent = new Intent(getApplicationContext(), HomePage.class);
        intent.putExtra("phone", _phone);
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

