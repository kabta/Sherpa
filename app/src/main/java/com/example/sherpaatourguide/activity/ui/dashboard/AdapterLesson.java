package com.example.sherpaatourguide.activity.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sherpaatourguide.R;

import java.util.List;

public class AdapterLesson extends RecyclerView.Adapter<AdapterLesson.ViewHolder> {


    private List<Explore> mData;
    private LayoutInflater layoutInflater;
    private Context context;


    public AdapterLesson(List<Explore> mData, Context context) {
        this.mData = mData;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_explore, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Explore explore = mData.get(position);
        holder.browse.setText(explore.getBrowse());
        holder.text1.setText(explore.getText1());
        holder.text2.setText(explore.getText2());
        holder.image1.setImageResource(mData.get(position).getImage1());
        holder.image2.setImageResource(explore.getImage2());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image1, image2;
        public TextView text1, text2, browse;

        public ViewHolder(@NonNull View view) {
            super(view);
            browse = view.findViewById(R.id.Browse);
            text1 = view.findViewById(R.id.Text1);
            text2 = view.findViewById(R.id.Text2);
            image1 = view.findViewById(R.id.Image1);
            image2 = view.findViewById(R.id.Image2);
        }
    }

}
