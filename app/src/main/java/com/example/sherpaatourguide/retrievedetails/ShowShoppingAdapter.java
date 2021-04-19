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
import com.example.sherpaatourguide.R;
import com.example.sherpaatourguide.activity.ui.RestaurantData;
import com.example.sherpaatourguide.activity.ui.ShoppingMallsData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class ShowShoppingAdapter extends FirebaseRecyclerAdapter<ShoppingMallsData, ShowShoppingAdapter.shopviewholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ShowShoppingAdapter(@NonNull FirebaseRecyclerOptions<ShoppingMallsData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ShowShoppingAdapter.shopviewholder holder, int position, @NonNull ShoppingMallsData model) {
        holder.name.setText(model.getShopname());
        Glide.with(holder.shopimg.getContext()).load(model.getImageId()).into(holder.shopimg);
        holder.details.setText(model.getShopDescription());
        holder.location.setText(model.getShopLocation());

        holder.phone.setText(model.getShopPhone());
    }

    @NonNull
    @Override
    public ShowShoppingAdapter.shopviewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_show_shopping,parent,false);
        return new shopviewholder(view);
    }

    public class shopviewholder extends RecyclerView.ViewHolder {
        private ImageView shopimg;
        private TextView name, details, phone, location;

        public shopviewholder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.showshopName);

            shopimg = (ImageView) itemView.findViewById(R.id.showshopImage);
            details = (TextView) itemView.findViewById(R.id.showshopDetail);
            location = (TextView) itemView.findViewById(R.id.showshopLocation);
            phone = (TextView) itemView.findViewById(R.id.showshopPhone);
        }
    }
}