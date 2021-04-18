package com.example.sherpaatourguide.retrievedetails;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sherpaatourguide.BusStationData;
import com.example.sherpaatourguide.R;
import com.example.sherpaatourguide.activity.ui.DestinationData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ShowDestinationAdapter extends FirebaseRecyclerAdapter<DestinationData,ShowDestinationAdapter.desviewholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ShowDestinationAdapter(@NonNull FirebaseRecyclerOptions<DestinationData> options) {
        super(options);
    }

    @Override
    public ShowDestinationAdapter.desviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_show_destination,parent,false);
        return new desviewholder(view);    }

    @Override
    protected void onBindViewHolder(@NonNull desviewholder holder, int position, @NonNull DestinationData model) {
        holder.dname.setText(model.getdName());
        Glide.with(holder.desimg.getContext()).load(model.getdImageId()).into(holder.desimg);
        holder.ddetails.setText(model.getdDescription());
        holder.dlocation.setText(model.getdLocation());
    }


    class desviewholder extends RecyclerView.ViewHolder
    {
        private ImageView desimg;
        private TextView dname,ddetails, dlocation;
        public desviewholder(View itemView)
        {
            super(itemView);
            dname=(TextView)itemView.findViewById(R.id.showdesName);

            desimg=(ImageView)itemView.findViewById(R.id.showdesImage);
            ddetails=(TextView)itemView.findViewById(R.id.showdesDetail);
            dlocation=(TextView)itemView.findViewById(R.id.showdesLocation);
        }
    }



    }

