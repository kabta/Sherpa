package com.example.sherpaatourguide.activity.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sherpaatourguide.R;

import java.util.List;

public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.ViewHolder> {


    private FactsActivity context;
    private List<Facts> fact;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView factsname;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            factsname = view.findViewById(R.id.text);
        }
    }

    public FactsAdapter(FactsActivity context, List<Facts> facts) {
        this.context = context;
        this.fact = facts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_facts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Facts facts = fact.get(position);

        holder.factsname.setText(facts.getFactsname());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return fact.size();
    }
}
