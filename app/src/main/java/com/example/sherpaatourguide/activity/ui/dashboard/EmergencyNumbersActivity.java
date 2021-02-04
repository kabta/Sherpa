package com.example.sherpaatourguide.activity.ui.dashboard;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sherpaatourguide.R;

import java.util.ArrayList;
import java.util.List;

public class EmergencyNumbersActivity<PermissionHelperer> extends AppCompatActivity {
    private String pendingPhone;

    private LinearLayout rootLayout;
    private PermissionHelperer PermissionsHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_numbers);

        rootLayout = findViewById(R.id.rootLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        List<EmergencyNumber> emergencyNumbers = new ArrayList<>();
        emergencyNumbers.add(new EmergencyNumber("102", "Main Ambulance"));
        emergencyNumbers.add(new EmergencyNumber("061-462761", "Tourist Police"));
        emergencyNumbers.add(new EmergencyNumber("103", "Traffic Police"));
        emergencyNumbers.add(new EmergencyNumber("100", "Emergency Police"));
        emergencyNumbers.add(new EmergencyNumber("101", "Fire Department"));
        emergencyNumbers.add(new EmergencyNumber("061461500", "Electricity Emergency"));
        emergencyNumbers.add(new EmergencyNumber("129", "Natural Gas Emergency"));
        emergencyNumbers.add(new EmergencyNumber("061521091", "Blood bank "));
        emergencyNumbers.add(new EmergencyNumber("061465292", "Nepal Tourism Board Pokhara"));
        emergencyNumbers.add(new EmergencyNumber("4228094", "Red Cross Ambulance Service"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new EmergencyNumbersAdapter(this, emergencyNumbers));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

