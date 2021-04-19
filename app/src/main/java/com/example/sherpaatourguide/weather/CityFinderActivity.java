package com.example.sherpaatourguide.weather;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sherpaatourguide.R;

class CityFinderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_finder);
        final EditText editText=findViewById(R.id.searchCity);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                String newCity=editText.getText().toString();
                Intent intent=new Intent(CityFinderActivity.this,WeatherActivity.class);
                intent.putExtra("City",newCity);
                startActivity(intent);
                return false;
            }
        });
    }

}