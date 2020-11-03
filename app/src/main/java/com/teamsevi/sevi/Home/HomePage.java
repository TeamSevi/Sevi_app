 package com.teamsevi.sevi.Home;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.teamsevi.sevi.R;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerlayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        drawerlayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        Menu menu = navigationView.getMenu();
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (this,drawerlayout,toolbar,R.string.n_d_c,R.string.n_d_o);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }
     public  void onBackPressed() {

        if(drawerlayout.isDrawerOpen(GravityCompat.START)){
            drawerlayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
     }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.profile:
                Intent i1 = new Intent(HomePage.this,UserProfile.class);
                startActivity(i1);
                break;
            case R.id.myorder:
                Intent intent = new Intent(HomePage.this,MyOrder.class);
                startActivity(intent);
                break;
            case R.id.feedback:
                Intent i = new Intent(HomePage.this,UserFeedback.class);
                startActivity(i);
                break;
            case R.id.Cotact:
                Intent I = new Intent(HomePage.this,ContactUs.class);
                startActivity(I);
                break;

        }
        drawerlayout.closeDrawer(GravityCompat.START);
        return false;
    }
}
