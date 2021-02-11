package com.teamsevi.sevi.Login_Signup;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.teamsevi.sevi.Database.SessionManager;
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
        final String _phone = phoneno.getText().toString();
        final String _pass = password.getText().toString();
        if(!validatephone() | !validatepass() ){
            return;
        }
        //database
        Query checkUser = FirebaseDatabase.getInstance().getReference("App/users").orderByChild("phoneno").equalTo(_phone);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    phoneno.setError(null);

                    String systemPassword = dataSnapshot.child(_phone).child("password").getValue(String.class);
                    if(systemPassword.equals(_pass)){
                        password.setError(null);
                        String _firstname = dataSnapshot.child(_phone).child("firstname").getValue(String.class);
                        String _lastname = dataSnapshot.child(_phone).child("lastname").getValue(String.class);
                        String _phoneno = dataSnapshot.child(_phone).child("phoneno").getValue(String.class);
                        String _email = dataSnapshot.child(_phone).child("email").getValue(String.class);
                        String _password = dataSnapshot.child(_phone).child("password").getValue(String.class);

                        //creaate a session
                        SessionManager sessionManager = new SessionManager(LoginScreen.this);
                        sessionManager.createLoginSession(_firstname,_lastname,_phoneno,_email,_password);

                        startActivity(new Intent(getApplicationContext(), HomePage.class));

                    }
                    else {
                        Toast.makeText(LoginScreen.this, "Password does not match!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(LoginScreen.this, "No User exist!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LoginScreen.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

//        Intent intent = new Intent(getApplicationContext(), HomePage.class);
//        intent.putExtra("phone", _phone);
//        startActivity(intent);
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

