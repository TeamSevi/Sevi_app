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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.teamsevi.sevi.Database.SessionManager;
import com.teamsevi.sevi.Login_Signup.LoginScreen;
import com.teamsevi.sevi.R;
import com.teamsevi.sevi.Scan_Order.ScanOrder;
import com.teamsevi.sevi.SplashScreen;

import java.util.HashMap;

 public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerlayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button scnbtn;
//    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        drawerlayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        scnbtn=findViewById(R.id.scn_btn);
//        username = findViewById(R.id.username);

        SessionManager sessionManager = new SessionManager(this);
        HashMap usersDetails = sessionManager.getUserDetailFromSession();
        String firstname = (String) usersDetails.get(SessionManager.KEY_FIRSTNAME);
        Toast.makeText(this, "Welcome "+firstname, Toast.LENGTH_SHORT).show();
//        username.setText(firstname);

        setSupportActionBar(toolbar);
        Menu menu = navigationView.getMenu();
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (this,drawerlayout,toolbar,R.string.n_d_c,R.string.n_d_o);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        scnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,ScanOrder.class);
                startActivity(intent);
            }
        });

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
            case R.id.Contact:
                Intent I = new Intent(HomePage.this,ContactUs.class);
                startActivity(I);
                break;
            case R.id.logout:
                SessionManager sessionManager = new SessionManager(this);
                sessionManager.logoutUserFromSession();
                Intent in = new Intent(HomePage.this, SplashScreen.class);
                startActivity(in);
                break;

        }
        drawerlayout.closeDrawer(GravityCompat.START);
        return false;
    }
}
