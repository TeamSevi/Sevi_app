package com.teamsevi.sevi.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.teamsevi.sevi.Model.Model_menu;
import com.teamsevi.sevi.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_menu extends FirebaseRecyclerAdapter<Model_menu, Adapter_menu.holder> {

    public String total;
    public Adapter_menu(@NonNull FirebaseRecyclerOptions<Model_menu> options) {
        super(options);
    }

    protected void onBindViewHolder(@NonNull holder holder, int position, @NonNull Model_menu model) {
        holder.textView.setText(model.getItemname());
        holder.textView1.setText(model.getItemdescription());
        holder.textView2.setText(model.getItemprice());
        total = holder.t1.getText().toString();
    }


    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu,parent,false);
        return new holder(view);
    }

    class holder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView textView,textView1,textView2,t,t1;
    Button i,d;
    public holder(@NonNull View itemView) {
        super(itemView);
        t1 =(TextView)itemView.findViewById(R.id.total);
        t =(TextView)itemView.findViewById(R.id.integer_number);
        imageView =(ImageView)itemView.findViewById(R.id.i);
        textView =(TextView)itemView.findViewById(R.id.t1);
        textView1 =(TextView)itemView.findViewById(R.id.t2);
        textView2 =(TextView)itemView.findViewById(R.id.t3);

    }
}
}
