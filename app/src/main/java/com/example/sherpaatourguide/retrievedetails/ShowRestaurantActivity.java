package com.example.sherpaatourguide.retrievedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sherpaatourguide.BusStationData;
import com.example.sherpaatourguide.R;
import com.example.sherpaatourguide.activity.ui.RestaurantData;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ShowRestaurantActivity extends AppCompatActivity {

    private RecyclerView resrecview;
    ShowRestaurantAdapter showRestaurantAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_restaurant);
        resrecview=(RecyclerView)findViewById(R.id.resrecview);
        resrecview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<RestaurantData> options =
                new FirebaseRecyclerOptions.Builder<RestaurantData>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Restaurant"), RestaurantData.class)
                        .build();


        showRestaurantAdapter=new ShowRestaurantAdapter(options);
        resrecview.setAdapter(showRestaurantAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        showRestaurantAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        showRestaurantAdapter.stopListening();
    }
}