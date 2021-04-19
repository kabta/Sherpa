package com.example.sherpaatourguide.retrievedetails;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sherpaatourguide.BusStationData;
import com.example.sherpaatourguide.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 *
 */
public class ShowBusAdapter extends FirebaseRecyclerAdapter<BusStationData,ShowBusAdapter.myviewholder> {
    public ShowBusAdapter( FirebaseRecyclerOptions<BusStationData> showbus) {
        super(showbus);
    }


    @Override
    public myviewholder onCreateViewHolder( ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_show_bus,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        private ImageView busimg;
        private TextView name,details,phone, location;
        public myviewholder( View itemView)
        {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.showbusstationName);

            busimg=(ImageView)itemView.findViewById(R.id.showbusstationImage);
            details=(TextView)itemView.findViewById(R.id.showbusstationDetail);
            location=(TextView)itemView.findViewById(R.id.showbusstationLocation);
            phone=(TextView)itemView.findViewById(R.id.showbusstationPhone);
        }
    }
    @Override
    protected void onBindViewHolder( myviewholder holder, int position,  BusStationData model)
    {
        holder.name.setText(model.getBusStationname());
        Glide.with(holder.busimg.getContext()).load(model.getImageId()).into(holder.busimg);
        holder.details.setText(model.getBusDescription());
        holder.location.setText(model.getBusStationLocation());

        holder.phone.setText(model.getBusStationPhone());



    }


}
//use glide library to display image from url