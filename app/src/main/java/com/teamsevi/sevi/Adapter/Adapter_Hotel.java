package com.teamsevi.sevi.Adapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.teamsevi.sevi.Hotel_Menu.HotelList;
import com.teamsevi.sevi.Model.Model_Hotel;
import com.teamsevi.sevi.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.teamsevi.sevi.Table;


public class Adapter_Hotel extends FirebaseRecyclerAdapter<Model_Hotel, Adapter_Hotel.MyViewHolder> {
    Context context;

    public Adapter_Hotel(FirebaseRecyclerOptions<Model_Hotel> options, Context context)
    {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(MyViewHolder holder, int position, Model_Hotel model)
    {
        Picasso.get().load(model.getHotelimage()).into(holder.hotelimage);
        holder.hotelname.setText(model.getHotelname());
        holder.hoteladdress.setText(model.getHoteladdress());
        holder.hotelcity.setText(model.getHotelcity());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_model, parent, false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView hotelimage;
        TextView hotelname,hoteladdress,hotelcity;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            hotelimage = itemView.findViewById(R.id.hotelimage);
            hotelname = itemView.findViewById(R.id.hotelname);
            hoteladdress = itemView.findViewById(R.id.hoteladdress);
            hotelcity = itemView.findViewById(R.id.hotelcity);

            context = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //create a session
                    SharedPreferences sharedpreferences = context.getSharedPreferences("HotelMenuSession", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    String name = hotelname.getText().toString();
                    editor.putString("hotelname", name);

                    String address = hoteladdress.getText().toString();
                    editor.putString("hoteladdress",address);

                    String city = hotelcity.getText().toString();
                    editor.putString("hotelcity",city);
                    editor.commit();

                    Intent intent = new Intent(context, HotelList.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}