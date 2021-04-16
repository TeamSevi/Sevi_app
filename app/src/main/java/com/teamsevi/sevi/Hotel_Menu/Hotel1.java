package com.teamsevi.sevi.Hotel_Menu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.teamsevi.sevi.Adapter.Adapter_category;
import com.teamsevi.sevi.Adapter.Adapter_menu;
import com.teamsevi.sevi.Model.Model_category;
import com.teamsevi.sevi.Model.Model_menu;
import com.teamsevi.sevi.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.teamsevi.sevi.Adapter.Adapter_category.pref;
import static com.teamsevi.sevi.Adapter.Adapter_category.pref1;

public class Hotel1 extends AppCompatActivity {
    RecyclerView recyclerView,recyclerView1;
    Adapter_menu adapter;
    Button button;
    ElegantNumberButton elegantNumberButton;
    TextView textView;
    String e;
    //Adapter_category adapter1;
   

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel1);
        setTitle("");
        SharedPreferences shared = getSharedPreferences("HotelSession", MODE_PRIVATE);
        String hotelid = shared.getString("hotelid", "");
       /* SharedPreferences shared1 = getSharedPreferences("CategorySession", MODE_PRIVATE);
        String itemcategory = shared1.getString("itemcategory", "");
        recyclerView1 = (RecyclerView)findViewById(R.id.rev1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        FirebaseRecyclerOptions<Model_category> options1 =
                new FirebaseRecyclerOptions.Builder<Model_category>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Hotel").child(hotelid).child("items"), Model_category.class)
                        .build();
        adapter1 = new Adapter_category(options1,this);
        recyclerView1.setAdapter(adapter1);*/
        //System.out.println(hotelid);
      /*  Query checkUser = FirebaseDatabase.getInstance().getReference("Hotel").child(hotelid).child("items").orderByChild("itemcategory").equalTo(category);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String categoryid = "";
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        categoryid = snap.getKey();
                    }
                    //creaate a session
                    SharedPreferences sharedpreferences = getSharedPreferences("CategorySession", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("categoryid", categoryid);
                    editor.commit();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
       /* int a= 85;
        button = findViewById(R.id.btnPlaceOrder);
        elegantNumberButton = (ElegantNumberButton)findViewById(R.id.d);
elegantNumberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
    @Override
    public void onClick(View view) {
        e = elegantNumberButton.getNumber();
    }
});
        textView = findViewById(R.id.total);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int t = (Integer.parseInt(String.valueOf(a))) * (Integer.parseInt(e));
                textView.setText(t);
            }
        });*/
        recyclerView = (RecyclerView)findViewById(R.id.rev);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model_menu> options =
                new FirebaseRecyclerOptions.Builder<Model_menu>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Hotel").child(hotelid).child("items"), Model_menu.class)
                        .build();
        adapter = new Adapter_menu(options);
        recyclerView.setAdapter(adapter);
//String c = category;
    }


    @Override
    protected void onStart() {
        super.onStart();
       // adapter1.startListening();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
      //  adapter1.stopListening();
        adapter.stopListening();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();
        Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<Model_menu> options =
                new FirebaseRecyclerOptions.Builder<Model_menu>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Hotel").child("items").startAt(s).endAt(s+"\uf8ff"), Model_menu.class)
                        .build();
        adapter = new Adapter_menu(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}