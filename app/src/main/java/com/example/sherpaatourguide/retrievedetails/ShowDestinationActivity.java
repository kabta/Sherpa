package com.example.sherpaatourguide.retrievedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sherpaatourguide.R;
import com.example.sherpaatourguide.activity.ui.DestinationData;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ShowDestinationActivity extends AppCompatActivity {

    private RecyclerView drecview;
    private ShowDestinationAdapter showDestinationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_destination);
        drecview=(RecyclerView)findViewById(R.id.desrecview);
        drecview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<DestinationData> option =
                new FirebaseRecyclerOptions.Builder<DestinationData>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Destinations"), DestinationData.class)
                        .build();


        showDestinationAdapter=new ShowDestinationAdapter(option);
        drecview.setAdapter(showDestinationAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        showDestinationAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        showDestinationAdapter.stopListening();
    }

}