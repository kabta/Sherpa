package com.example.sherpaatourguide.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sherpaatourguide.R;
import com.example.sherpaatourguide.activity.RetrieveDetails;

/**/
public class ExploreAdapter extends AppCompatActivity {
    private TextView stupa;
    //private ImageView stupai;
    private CardView cstupa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_explore);

        //cstupa = findViewById(R.id.stupa);
        //cstupa.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //  public void onClick(View v) {

        //    Intent intent = new Intent(ExploreFragment.this, RetrieveDetails.class);
        //  startActivity(intent);
        //finish();
        //}
        //});


    }
}

