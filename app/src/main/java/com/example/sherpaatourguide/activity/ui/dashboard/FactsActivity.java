package com.example.sherpaatourguide.activity.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.sherpaatourguide.R;

import java.util.ArrayList;
import java.util.List;

public class FactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        Toolbar toolbar = findViewById(R.id.toolbar);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        List<Facts> facts = new ArrayList<>();
        String text = new String();
        facts.add(new Facts(text, "Nepali is the chief or official language."));
        facts.add(new Facts(text, "Pokhara is also known as the City of Lakes in Nepal due to the picturesque lakes situated among the snow-clad mountains, surrounded by lush greenery."));
        facts.add(new Facts(text,"Mystical caves such as Mahendra, Gupteshwor, Sita, Crazy, Chamero, Crazy and Devil’s Fall add to the beauty and mysticism of this place."));
        facts.add(new Facts(text, "A city surrounded by snow clad hill top is bound to provide a spectacular sunrise to the visitors."));
        facts.add(new Facts(text, "When in Pokhara, do not forget trekking!"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FactsAdapter(this, facts));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
