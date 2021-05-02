package com.teamsevi.sevi.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.teamsevi.sevi.Database.SessionManager;
import com.teamsevi.sevi.Model.Model_menu;
import com.teamsevi.sevi.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_menu extends FirebaseRecyclerAdapter<Model_menu, Adapter_menu.holder> {
    private Context context;

    public int Quantity;
    double totalamount;
    private SessionManager sessionManager;
    public Adapter_menu(@NonNull FirebaseRecyclerOptions<Model_menu> options, Context context) {
        super(options);
        this.context = context;
        sessionManager = new SessionManager(context);
    }

    protected void onBindViewHolder(@NonNull holder holder, int position, @NonNull Model_menu model) {
        holder.textView.setText(model.getItemname());
        holder.textView1.setText(model.getItemdescription());
        holder.textView2.setText(model.getItemprice());
        Glide.with(context).load(model.getItemimage()).into(holder.imageView);
        Quantity = Integer.parseInt(holder.t.getText().toString());
        getTotal(Quantity, Double.parseDouble(model.getItemprice()));

    }
    public double getTotal(int value, double amount){


        double amountall = amount;
        int q = value;
        totalamount = amountall * q;
        sessionManager.grandtotal("Ksh: " + totalamount);
        return totalamount;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu,parent,false);
        return new holder(view);
    }

    class holder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView textView,textView1,textView2,t1;
    EditText t;
    Button i,d;
    public holder(@NonNull View itemView) {
        super(itemView);

        t =(EditText) itemView.findViewById(R.id.integer_number);
        imageView =(ImageView)itemView.findViewById(R.id.i);
        textView =(TextView)itemView.findViewById(R.id.t1);
        textView1 =(TextView)itemView.findViewById(R.id.t2);
        textView2 =(TextView)itemView.findViewById(R.id.t3);
        SharedPreferences sharedpreferences = context.getSharedPreferences("QuantitySession", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("Quantity", String.valueOf(Quantity));
        editor.apply();

    }
}
}
