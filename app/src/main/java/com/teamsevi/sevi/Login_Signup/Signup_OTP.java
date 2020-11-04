package com.teamsevi.sevi.Login_Signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teamsevi.sevi.Database.SessionManager;
import com.teamsevi.sevi.Database.UserHelperClass;
import com.teamsevi.sevi.Home.HomePage;
import com.teamsevi.sevi.R;

import java.util.concurrent.TimeUnit;

public class Signup_OTP extends AppCompatActivity {
    EditText otp;
    String codeBySystem;
    String firstname,lastname,email,phoneno,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signup_otp);

        otp = findViewById(R.id.otp);
        firstname = getIntent().getStringExtra("firstname");
        lastname = getIntent().getStringExtra("lastname");
        phoneno = getIntent().getStringExtra("phone");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        String Phoneno = "+91"+phoneno;

        sendVerificationCodeToUser(Phoneno);
    }

    private void sendVerificationCodeToUser(String phoneno) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneno,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if(code!=null){
                        otp.setText(code);
                        verfyCode(code);
                    }
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Toast.makeText(Signup_OTP.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            };

    private void verfyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem,code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Signup_OTP.this,"Verification Completed",Toast.LENGTH_SHORT).show();

                            //creaate a session
                            SessionManager sessionManager = new SessionManager(Signup_OTP.this);
                            sessionManager.createLoginSession(firstname,lastname,phoneno,email,password);
                            Intent intent = new Intent(getApplicationContext(), HomePage.class);
                            startActivity(intent);
                            storeUserData();

                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(Signup_OTP.this,"Verification not completed! Try Again",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void storeUserData() {
        FirebaseDatabase rootnode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootnode.getReference("Users");

        UserHelperClass addNewUser = new UserHelperClass(firstname,lastname,phoneno,email,password);

        reference.child(phoneno).setValue(addNewUser);
    }


    public void callHomeScreen(View view){
            String code = otp.getText().toString();
            if(!code.isEmpty()){
                verfyCode(code);
            }


    }
}
