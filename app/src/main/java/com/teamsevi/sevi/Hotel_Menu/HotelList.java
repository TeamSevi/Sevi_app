package com.teamsevi.sevi.Hotel_Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.teamsevi.sevi.Home.HomePage;
import com.teamsevi.sevi.R;

public class HotelList extends AppCompatActivity {
    TextView name,address,city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_list);

        name = findViewById(R.id.hotelname);
        address = findViewById(R.id.hoteladdress);
        city = findViewById(R.id.hotelcity);
        SharedPreferences shared = getSharedPreferences("HotelMenuSession", MODE_PRIVATE);

        String hotelname = shared.getString("hotelname", "");
        name.setText(hotelname);

        String hoteladdress = shared.getString("hoteladdress","");
        address.setText(hoteladdress);

        String hotelcity = shared.getString("hotelcity","");
        city.setText(hotelcity);
    }


    public void callHomeScreen(View view){
        super.onBackPressed();
    }
}