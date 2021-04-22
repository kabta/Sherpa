package com.example.sherpaatourguide.retrievedetails;

import android.content.Intent;
import android.net.Uri;
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
import com.example.sherpaatourguide.activity.ui.RestaurantData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class ShowRestaurantAdapter extends FirebaseRecyclerAdapter<RestaurantData, ShowRestaurantAdapter.resviewholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ShowRestaurantAdapter(@NonNull FirebaseRecyclerOptions<RestaurantData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull resviewholder holder, int position, @NonNull RestaurantData model) {
        holder.name.setText(model.getName());
        Glide.with(holder.resimg.getContext()).load(model.getImageId()).into(holder.resimg);
        holder.details.setText(model.getDescription());
        holder.location.setText(model.getLocation());

        holder.phone.setText(model.getPhone());
    }


    @Override
    public resviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_show_restaurant, parent, false);
        return new resviewholder(view);
    }

    public class resviewholder extends RecyclerView.ViewHolder {
        private ImageView resimg;
        private TextView name, details, phone, location,tvcall;

        public resviewholder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.showresName);

            resimg = (ImageView) itemView.findViewById(R.id.showresImage);
            details = (TextView) itemView.findViewById(R.id.showresDetail);
            location = (TextView) itemView.findViewById(R.id.showresLocation);
            phone = (TextView) itemView.findViewById(R.id.showresPhone);
            tvcall = (TextView) itemView.findViewById(R.id.callres);
            tvcall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int callphone = phone.getInputType();
                    if(phone!= null){
                        String s= "tel:" + callphone;
                        Intent intent = new Intent((Intent.ACTION_CALL));
                        intent.setData(Uri.parse(s));
                        startListening();
                    }
                }
            });

        }
    }



}