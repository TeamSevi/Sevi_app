package com.teamsevi.sevi.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.teamsevi.sevi.Model.Model_category;
import com.teamsevi.sevi.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_category extends FirebaseRecyclerAdapter<Model_category, Adapter_category.holder_category> {

    public Adapter_category(@NonNull FirebaseRecyclerOptions<Model_category> options1) {
        super(options1);
    }

    protected void onBindViewHolder(@NonNull holder_category holder1, int position, @NonNull Model_category model1) {
        holder1.t.setText(model1.getItemcategory());
    }

    @NonNull
    @Override
    public holder_category onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.category,parent,false);
        return new holder_category(view1);
    }

    class holder_category extends RecyclerView.ViewHolder{
        TextView t;
        public holder_category(@NonNull View itemView) {
            super(itemView);
            t =(TextView)itemView.findViewById(R.id.textView5);
        }
    }
}
