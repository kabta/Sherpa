package com.example.sherpaatourguide.activity.ui.dashboard;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Facts extends RecyclerView.Adapter {

    private String fatsname;


    public Facts(String s, String factsname) {
        this.fatsname = factsname;

    }

    public Facts(FactsActivity factsActivity, List<Facts> facts) {

    }

    public static void setOnClickListener(View.OnClickListener onClickListener) {


    }

    public String getFactsname() {
        return fatsname;
    }

    public void setFactsname(String factsname) {
        this.fatsname = factsname;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



    public Facts get(int position) {
        return null;
    }
}



