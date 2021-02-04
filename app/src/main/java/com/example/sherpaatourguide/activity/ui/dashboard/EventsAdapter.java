package com.example.sherpaatourguide.activity.ui.dashboard;

import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sherpaatourguide.R;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {


    private List<Events> event;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public ImageView eventimg;
        public TextView eventsname,eventsdate;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            eventimg = view.findViewById(R.id.event_img);
            eventsname = view.findViewById(R.id.events_name);
            eventsdate = view.findViewById(R.id.events_date);

        }
    }

    public EventsAdapter(List<Events> events) {
        this.event = events;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final EventsAdapter.ViewHolder holder, int position) {
        final Events events = event.get(position);
        holder.eventsname.setText(events.getEventsname());
       holder.eventimg.setImageResource(events.getEventimg());
        holder.eventsdate.setText(events.getEventsdate());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return event.size();
    }
}

