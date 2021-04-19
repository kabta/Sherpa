package com.example.sherpaatourguide.retrievedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sherpaatourguide.R;
import com.example.sherpaatourguide.activity.ui.RestaurantData;
import com.example.sherpaatourguide.activity.ui.ShoppingMallsData;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ShowShoppingActivity extends AppCompatActivity {


    private RecyclerView srecview;
    ShowShoppingAdapter showShoppingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_shopping);
        srecview=(RecyclerView)findViewById(R.id.shoprecview);
        srecview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ShoppingMallsData> options =
                new FirebaseRecyclerOptions.Builder<ShoppingMallsData>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ShoppingMalls"), ShoppingMallsData.class)
                        .build();


        showShoppingAdapter=new ShowShoppingAdapter(options);
        srecview.setAdapter(showShoppingAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        showShoppingAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        showShoppingAdapter.stopListening();
    }

}