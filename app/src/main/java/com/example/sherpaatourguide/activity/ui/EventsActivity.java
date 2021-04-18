package com.example.sherpaatourguide.activity.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.sherpaatourguide.R;
import com.example.sherpaatourguide.activity.ui.dashboard.Events;
import com.example.sherpaatourguide.activity.ui.dashboard.EventsAdapter;

import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity {

    private String eventsname;
    private string eventsdate;
    private Image eventimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
     //   Toolbar toolbar = findViewById(R.id.toolbar);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

       // setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);

        List<Events> events = new ArrayList<>();
        events.add(new Events("16st Oct", "Dashain", R.drawable.dashain));
        events.add(new Events("31st Aug", "Teej",  R.drawable.teej));
        events.add(new Events("29th June", "Asare Ropai",  R.drawable.ropain));
        events.add(new Events("13th April", "Nepali New Year",  R.drawable.newyr));
        events.add(new Events("26th March", "Holi",  R.drawable.holi));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new EventsAdapter(events));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private class string {
    }
}