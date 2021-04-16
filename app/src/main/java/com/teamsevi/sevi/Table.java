package com.teamsevi.sevi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.teamsevi.sevi.Adapter.Adapter_Table;
import com.teamsevi.sevi.Adapter.Adapter_menu;
import com.teamsevi.sevi.Model.Model_Table;
import com.teamsevi.sevi.Model.Model_menu;

public class Table extends AppCompatActivity {
RecyclerView recyclerView;
Adapter_Table adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        SharedPreferences shared = getSharedPreferences("HotelSession", MODE_PRIVATE);
        String hotelid = shared.getString("hotelid", "");
        recyclerView = (RecyclerView)findViewById(R.id.rev2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);

        FirebaseRecyclerOptions<Model_Table> options =
                new FirebaseRecyclerOptions.Builder<Model_Table>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Hotel").child(hotelid).child("tables"), Model_Table.class)
                        .build();
        adapter = new Adapter_Table(options,this);
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}