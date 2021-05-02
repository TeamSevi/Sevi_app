package com.teamsevi.sevi.Hotel_Menu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.teamsevi.sevi.Database.SessionManager;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.common.internal.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teamsevi.sevi.Adapter.Adapter_category;
import com.teamsevi.sevi.Adapter.Adapter_menu;
import com.teamsevi.sevi.Database.SessionManager;
import com.teamsevi.sevi.Home.HomePage;
import com.teamsevi.sevi.Model.Model_category;
import com.teamsevi.sevi.Model.Model_menu;
import com.teamsevi.sevi.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import java.util.HashMap;
import java.util.Random;

import static com.teamsevi.sevi.Adapter.Adapter_category.pref;
import static com.teamsevi.sevi.Adapter.Adapter_category.pref1;

public class Hotel1 extends AppCompatActivity implements PaymentResultListener {
    RecyclerView recyclerView,recyclerView1;
    Adapter_menu adapter;
    Button button,btnPlaceOrder;
    ElegantNumberButton elegantNumberButton;
    TextView textView;
    String e;

    private SessionManager sessionManager;
    //Adapter_category adapter1;
   

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel1);
        setTitle("");
        SharedPreferences shared = getSharedPreferences("HotelSession", MODE_PRIVATE);
        String hotelid = shared.getString("hotelid", "");

        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        String samount = "799";

        int amount = Math.round(Float.parseFloat(samount) * 100);

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_test_s85NL1xiku1HwG");
                checkout.setImage(R.drawable.sevi);

                JSONObject object =  new JSONObject();
                try {
                    object.put("name","Sevi");
                    object.put("description","payment to the Sevi");
                    object.put("theme.color","#0093DD");
                    object.put("currency","INR");
                    object.put("amount",amount);
                    object.put("prefill.contact","9876543210");
                    object.put("prefill.email","xyz@gmail.com");

                    checkout.open(Hotel1.this,object);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

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
        adapter = new Adapter_menu(options,this);
        recyclerView.setAdapter(adapter);
//String c = category;
    }


    @Override
    public void onPaymentSuccess(String s) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Payment ID ");

        builder.setMessage(s);

        builder.show();
        SharedPreferences shared = getSharedPreferences("HotelSession", MODE_PRIVATE);
        String hotelid = shared.getString("hotelid", "");

        SharedPreferences shared1 = getSharedPreferences("QuantitySession", MODE_PRIVATE);
        String Quantity = shared1.getString("Quantity", "");

String total = null;
        HashMap<String,String> tot = sessionManager.getgrandtotal(total);
         float t = Float.parseFloat(tot.get(SessionManager.KEY_TOTAL));
        /*byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String uid = new String(array, Charset.forName("UTF-8"));*/

        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8)
        { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();

        SessionManager sessionManager = new SessionManager(this);
        HashMap usersDetails = sessionManager.getUserDetailFromSession();
        String firstname = (String) usersDetails.get(SessionManager.KEY_FIRSTNAME);

        FirebaseDatabase rootnode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootnode.getReference("Hotel");
        String name=firstname;
        float price = t;
        int qty = Integer.parseInt(Quantity);

        //UserHelperClass addNewUser = new UserHelperClass(firstname);
        Order add = new Order(name,price,qty);

        reference.child(hotelid).child("orders").child(saltStr).setValue(add);

        Intent intent = new Intent(getApplicationContext(), HomePage.class);
        startActivity(intent);
    }

    @Override
    public void onPaymentError(int i, String s) {

        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

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
        adapter = new Adapter_menu(options,this);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}