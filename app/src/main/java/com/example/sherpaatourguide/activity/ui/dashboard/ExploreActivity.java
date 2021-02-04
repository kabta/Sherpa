package com.example.sherpaatourguide.activity.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.sherpaatourguide.R;
import com.example.sherpaatourguide.home.ExploreAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExploreActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        Toolbar toolbar = findViewById(R.id.toolbar);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        List<Explore> explore = new ArrayList<>();
        explore.add(new Explore("Browse By Attractive Places", "Chinese Stupa ", "Fewa Lake", R.drawable.stupa, R.drawable.phewa));
        explore.add(new Explore("Browse By Top Restaurants", "Rupakot Resort ", "Shangrila Village", R.drawable.rupakot, R.drawable.shangilla));
        explore.add(new Explore("Browse By Top Hotels", "Hotel Grandee ", "Sherpa Kitchen", R.drawable.grande, R.drawable.shangilla));
        explore.add(new Explore("Browse By Religious places", "Barahi Temple ", "Sherpa Bindabasini Temple", R.drawable.barahi, R.drawable.bindabasini));
        explore.add(new Explore("Nearby Shopping Malls", "Pokhara Trade Mall ", "Bhatbhateni", R.drawable.trade, R.drawable.bhatbhateni));
        explore.add(new Explore("Neareby Bus Station", "Prithiwi Bus Station ", "Lamachaur Bus Station", R.drawable.prithivibus, R.drawable.prithivibus));
        explore.add(new Explore("Nearby Airport", "Pokhara Airport ", "", R.drawable.pkrairport, R.drawable.pkrairport));
        explore.add(new Explore("Entertainment Zone", "Bunjee ", "Paragliding", R.drawable.bunje, R.drawable.paragliding));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdapterLesson(explore, this) );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
