package com.teamsevi.sevi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamsevi.sevi.Hotel_Menu.Hotel1;
import com.teamsevi.sevi.Model.Model_Table;
import com.teamsevi.sevi.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;

public class Adapter_Table extends FirebaseRecyclerAdapter<Model_Table,Adapter_Table.holder> {
    private Context context;
    public String table;
    public Adapter_Table(@NonNull FirebaseRecyclerOptions<Model_Table> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull holder holder, int position, @NonNull Model_Table model) {
    holder.textView.setText(model.getName());
        table = holder.textView.getText().toString();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table,parent,false);
        return new holder(view);
    }

    class holder extends RecyclerView.ViewHolder{
        TextView textView;
        LinearLayout linearLayout;

        public holder(@NonNull View itemView) {
            super(itemView);
            textView =(TextView)itemView.findViewById(R.id.t4);
            linearLayout = itemView.findViewById(R.id.table1);

            SharedPreferences sharedpreferences = context.getSharedPreferences("tableSession", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("name", table);
            editor.apply();
            String name = sharedpreferences.getString("name", "");
            SharedPreferences shared = context.getSharedPreferences("HotelSession", MODE_PRIVATE);
            String hotelid = shared.getString("hotelid", "");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference mDatabase  =  FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("Hotel").child(hotelid).child("tables").child(name).child("status").setValue("running");
                    Intent i = new Intent(context,Hotel1.class);
                    context.startActivity(i);
                }
            });
        }
    }

}
