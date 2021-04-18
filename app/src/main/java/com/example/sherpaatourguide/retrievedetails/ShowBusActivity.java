package com.example.sherpaatourguide.retrievedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.sherpaatourguide.BusStationData;
import com.example.sherpaatourguide.R;
import com.example.sherpaatourguide.activity.ui.dashboard.EventsAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class ShowBusActivity extends AppCompatActivity {

    private RecyclerView recview;
    ShowBusAdapter showBusAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bus);
        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<BusStationData> options =
                new FirebaseRecyclerOptions.Builder<BusStationData>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("BusStationData"), BusStationData.class)
                        .build();


        showBusAdapter=new ShowBusAdapter(options);
        recview.setAdapter(showBusAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        showBusAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        showBusAdapter.stopListening();
    }
}