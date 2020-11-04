package com.teamsevi.sevi.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.teamsevi.sevi.Database.SessionManager;
import com.teamsevi.sevi.R;

import java.util.HashMap;

public class UserProfile extends AppCompatActivity {
    EditText first_name,last_name,user_mail,user_phone;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_user_profile);

        first_name = findViewById(R.id.fe_name);
        last_name = findViewById(R.id.le_name);
        user_phone = findViewById(R.id.E_mobile);
        user_mail = findViewById(R.id.E_Email);
        name =findViewById(R.id.name);

        SessionManager sessionManager = new SessionManager(this);
        HashMap usersDetails = sessionManager.getUserDetailFromSession();
        String firstname = (String) usersDetails.get(SessionManager.KEY_FIRSTNAME);
        String lastname = (String) usersDetails.get(SessionManager.KEY_LASTNAME);
        String phone = (String) usersDetails.get(SessionManager.KEY_PHONENO);
        String email = (String) usersDetails.get(SessionManager.KEY_EMAIL);
        first_name.setText(firstname);
        last_name.setText(lastname);
        user_phone.setText(phone);
        user_mail.setText(email);
        name.setText(firstname + " " + lastname);
    }
}