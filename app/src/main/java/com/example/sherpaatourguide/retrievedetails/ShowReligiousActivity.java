package com.example.sherpaatourguide.retrievedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sherpaatourguide.BusStationData;
import com.example.sherpaatourguide.R;
import com.example.sherpaatourguide.activity.ui.ReligiousData;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ShowReligiousActivity extends AppCompatActivity {

    private RecyclerView rrecview;
    ShowReligiousAdapter showReligiousAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_religious);
        rrecview=(RecyclerView)findViewById(R.id.relrecview);
        rrecview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ReligiousData> options =
                new FirebaseRecyclerOptions.Builder<ReligiousData>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Religious places"), ReligiousData.class)
                        .build();


        showReligiousAdapter=new ShowReligiousAdapter(options);
        rrecview.setAdapter(showReligiousAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        showReligiousAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        showReligiousAdapter.stopListening();
    }
}