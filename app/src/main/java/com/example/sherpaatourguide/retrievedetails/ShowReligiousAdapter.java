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
import com.example.sherpaatourguide.activity.ui.DestinationData;
import com.example.sherpaatourguide.activity.ui.ReligiousData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class ShowReligiousAdapter extends FirebaseRecyclerAdapter<ReligiousData,ShowReligiousAdapter.relviewholder>  {


    public ShowReligiousAdapter(@NonNull FirebaseRecyclerOptions<ReligiousData> options) {
        super(options);
    }

    @NonNull
    @Override
    public relviewholder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_show_religious,parent,false);
        return new relviewholder(view);
    }

    class relviewholder extends RecyclerView.ViewHolder {
        private ImageView relimg;
        private TextView name, details, location;

        public relviewholder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.showrelName);

            relimg = (ImageView) itemView.findViewById(R.id.showrelImage);
            details = (TextView) itemView.findViewById(R.id.showrelDetail);
            location = (TextView) itemView.findViewById(R.id.showrelLocation);
        }
}

    @Override
    public void onBindViewHolder(@NonNull relviewholder holder, int position, ReligiousData model) {
        holder.name.setText(model.getReName());
        Glide.with(holder.relimg.getContext()).load(model.getReImageId()).into(holder.relimg);
        holder.details.setText(model.getReDescription());
        holder.location.setText(model.getReLocation());
   }
}


