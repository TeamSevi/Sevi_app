package com.teamsevi.sevi.Login_Signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.teamsevi.sevi.Home.HomePage;
import com.teamsevi.sevi.R;

public class SignupScreen extends AppCompatActivity {

    EditText lastname,phoneno,email,password,confirmpass;
    TextInputEditText firstname;
    Button next;
    boolean check =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signup_screen);

        firstname = findViewById(R.id.signup_firstname);
        lastname = findViewById(R.id.signup_lastname);
        phoneno = findViewById(R.id.signup_phone);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_pass);
        confirmpass = findViewById(R.id.signup_confrm_pass);
        next = findViewById(R.id.signup_next_button);

    }

    private Boolean validatefirstname(){
        String val = firstname.getText().toString();
        if(val.isEmpty()) {
            firstname.setError("Field can not be Empty");
            return false;
        }else if(!val.matches("[a-zA-Z]+")){
            firstname.setError("Invalid name");
            return false;
        }
        else{
            firstname.setError(null);
            return true;
        }
    }
    private Boolean validatelastname(){
        String val = lastname.getText().toString();
        if(val.isEmpty()){
            lastname.setError("Field can not be Empty");
            return false;
        }else if(!val.matches("[a-zA-Z]+")){
            firstname.setError("Invalid name");
            return false;
        }
        else{
            lastname.setError(null);
            return true;
        }
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
    private Boolean validateemail(){
        String val = email.getText().toString();
        if(val.isEmpty()){
            email.setError("Field can not be Empty");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(val).matches())
        {
            email.setError("Invalid Input");
            return false;
        }
        else{
            email.setError(null);
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
    private Boolean validateconfpass(){
        String val = confirmpass.getText().toString();
        String val2 = password.getText().toString();
        if(val.isEmpty()){
            confirmpass.setError("Field can not be Empty");
            return false;
        }else if(!val.matches(val2)){
            confirmpass.setError("Password must be same");
            return false;
        }
        else{
            confirmpass.setError(null);
            return true;
        }
    }
    public void callSignupOTP(View view){
        final String _fname = firstname.getText().toString();
        final String _lname = lastname.getText().toString();
        final String _phone = phoneno.getText().toString();
        final String _email = email.getText().toString();
        final String _pass = password.getText().toString();
        if(!validatefirstname() | !validatelastname() | !validatephone() | !validateemail() | !validatepass() | !validateconfpass() ){
            return;
        }
        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneno").equalTo(_phone);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    phoneno.setError(null);

                    Toast.makeText(SignupScreen.this, "User Already exist", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getApplicationContext(), Signup_OTP.class);
                    intent.putExtra("firstname", _fname);
                    intent.putExtra("lastname", _lname);
                    intent.putExtra("phone", _phone);
                    intent.putExtra("email", _email);
                    intent.putExtra("password", _pass);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SignupScreen.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

//
////        ;
////        if(_fname.isEmpty() || _lname.isEmpty() || _phone.isEmpty() || _email.isEmpty() || _pass.isEmpty() || _cpass.isEmpty()){
////            firstname.setError("Field is empty");
////        }else if(_pass != _cpass){
////            confirmpass.setError("Enter same password");
////        }else {
//            Intent intent = new Intent(getApplicationContext(), Signup_OTP.class);
////            intent.putExtra("firstname", _fname);
////            intent.putExtra("lastname", _lname);
////            intent.putExtra("phone", _phone);
////            intent.putExtra("email", _email);
////            intent.putExtra("password", _pass);
//
//            startActivity(intent);
////        }
    }




    public void callLoginScreen(View view){
        Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
        startActivity(intent);
    }
}
