 package com.teamsevi.sevi.Home;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.skyfishjy.library.RippleBackground;
import com.teamsevi.sevi.Database.SessionManager;
import com.teamsevi.sevi.Hotel_Menu.Hotel1;
import com.teamsevi.sevi.Login_Signup.LoginScreen;
import com.teamsevi.sevi.R;
import com.teamsevi.sevi.Scan_Order.ScanOrder;
import com.teamsevi.sevi.SplashScreen;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

 public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
         private int PERMISSION_ALL = 1;
         private String[] PERMISSIONS = {
                 Manifest.permission.WRITE_EXTERNAL_STORAGE,
                 Manifest.permission.READ_EXTERNAL_STORAGE,
                 Manifest.permission.CAMERA
         };
    DrawerLayout drawerlayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FloatingActionButton scnbtn;
    TextView name;
    CircleImageView image;
//    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        drawerlayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        scnbtn=findViewById(R.id.scan_btn);
        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        final RippleBackground rippleBackground=(RippleBackground) findViewById(R.id.rippleBackground);
        rippleBackground.startRippleAnimation();

        SessionManager sessionManager = new SessionManager(this);
        HashMap usersDetails = sessionManager.getUserDetailFromSession();
        String firstname = (String) usersDetails.get(SessionManager.KEY_FIRSTNAME);
        String lastname = (String) usersDetails.get(SessionManager.KEY_LASTNAME);
        navigationView.setNavigationItemSelectedListener(this);
        TextView name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.name);
        CircleImageView imagePofile = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.image_profile);
        name.setText(firstname + " " + lastname);

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
                Intent intent=new Intent(HomePage.this, ScanOrder.class);
                startActivity(intent);
            }
        });


        String phone = (String) usersDetails.get(SessionManager.KEY_PHONENO);
        Query checkUser = FirebaseDatabase.getInstance().getReference("App/users").orderByChild("phoneno").equalTo(phone);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String _url = dataSnapshot.child(phone).child("image").getValue(String.class);
                    Glide.with(imagePofile).load(_url).into(imagePofile);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
     public static boolean hasPermissions(Context context, String... permissions) {
         if (context != null && permissions != null) {
             for (String permission : permissions) {
                 if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                     return false;
                 }
             }
         }
         return true;
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
