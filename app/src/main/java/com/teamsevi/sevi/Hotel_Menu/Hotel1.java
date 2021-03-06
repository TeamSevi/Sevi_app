package com.teamsevi.sevi.Hotel_Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.teamsevi.sevi.Adapter.Adapter_category;
import com.teamsevi.sevi.Adapter.Adapter_menu;
import com.teamsevi.sevi.Model.Model_category;
import com.teamsevi.sevi.Model.Model_menu;
import com.teamsevi.sevi.R;

public class Hotel1 extends AppCompatActivity {
    RecyclerView recyclerView,recyclerView1;
    Adapter_menu adapter;
    Adapter_category adapter1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel1);

        SharedPreferences shared = getSharedPreferences("HotelSession", MODE_PRIVATE);
        String hotelid = shared.getString("hotelid", "");
        //System.out.println(hotelid);

        recyclerView = (RecyclerView)findViewById(R.id.rev);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model_menu> options =
                new FirebaseRecyclerOptions.Builder<Model_menu>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Hotel").child(hotelid).child("items"), Model_menu.class)
                        .build();
        adapter = new Adapter_menu(options);
        recyclerView.setAdapter(adapter);

        recyclerView1 = (RecyclerView)findViewById(R.id.rev1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        FirebaseRecyclerOptions<Model_category> options1 =
                new FirebaseRecyclerOptions.Builder<Model_category>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Hotel").child(hotelid).child("items"), Model_category.class)
                        .build();
        adapter1 = new Adapter_category(options1);
        recyclerView1.setAdapter(adapter1);



    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter1.startListening();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter1.stopListening();
        adapter.stopListening();
    }

}