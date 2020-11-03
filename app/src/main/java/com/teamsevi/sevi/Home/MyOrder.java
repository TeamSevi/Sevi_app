package com.teamsevi.sevi.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.teamsevi.sevi.R;

public class MyOrder extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_my_order);
         recyclerView = (RecyclerView)findViewById(R.id.list);
    }
}