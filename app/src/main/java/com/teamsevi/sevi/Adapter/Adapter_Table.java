package com.teamsevi.sevi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.teamsevi.sevi.Hotel_Menu.Hotel1;
import com.teamsevi.sevi.Model.Model_Table;
import com.teamsevi.sevi.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_Table extends FirebaseRecyclerAdapter<Model_Table,Adapter_Table.holder> {
    private Context context;

    public Adapter_Table(@NonNull FirebaseRecyclerOptions<Model_Table> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull holder holder, int position, @NonNull Model_Table model) {
    holder.textView.setText(model.getName());

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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context,Hotel1.class);
                    context.startActivity(i);
                }
            });
        }
    }

}
