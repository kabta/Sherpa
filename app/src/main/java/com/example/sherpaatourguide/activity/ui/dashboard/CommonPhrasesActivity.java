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

public class CommonPhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_phrases);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);

        List<CommonPhrase> commonPhrases = new ArrayList<>();
        commonPhrases.add(new CommonPhrase("Hello", "नमस्कार", "Sabah al ful", R.drawable.phrase_hello));
        commonPhrases.add(new CommonPhrase("Bye", "बिदाई ", "Ma'a el salama", R.drawable.phrase_bye));
        commonPhrases.add(new CommonPhrase("Thank you", "धन्यवाद", "Shukran", R.drawable.phrase_thanks));
        commonPhrases.add(new CommonPhrase("Okay", "ल", "Tamaam", R.drawable.phrase_okay));
        commonPhrases.add(new CommonPhrase("No", "होईन", "Laa", R.drawable.phrase_no));
        commonPhrases.add(new CommonPhrase("Right here please", "कृपया यहाँ", "Ala gamb lw samaht", R.drawable.phrase_right_here));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CommonPhrasesAdapter(this, commonPhrases));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
