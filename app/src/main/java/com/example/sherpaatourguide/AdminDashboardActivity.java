package com.example.sherpaatourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sherpaatourguide.activity.DestinationActivity;
import com.example.sherpaatourguide.activity.LoginActivity;
import com.example.sherpaatourguide.activity.ui.BusStationActivity;
import com.example.sherpaatourguide.activity.ui.ReligiousActivity;
import com.example.sherpaatourguide.activity.ui.RestaurantActivity;
import com.example.sherpaatourguide.activity.ui.ShoppingActivity;
import com.google.firebase.auth.FirebaseAuth;

public class AdminDashboardActivity extends AppCompatActivity {

    private Button destinationbtn, religiousbtn, busstationbtn, restbtn, mallsbtn, logout;
    FirebaseAuth mfirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        destinationbtn = findViewById(R.id.destinationbtn);
        religiousbtn = findViewById(R.id.religiousbtn);
        busstationbtn = findViewById(R.id.busbtn);
        restbtn = findViewById(R.id.restbtn);
        mallsbtn = findViewById(R.id.shopbtn);
        logout = findViewById(R.id.admin_logout);
        mfirebaseAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfirebaseAuth.signOut();
                signOutUser();
            }
        });

        destinationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent destinationintent = new Intent(AdminDashboardActivity.this, DestinationActivity.class);
                startActivity(destinationintent);
            }
        });

        religiousbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent religiousintent = new Intent(AdminDashboardActivity.this, ReligiousActivity.class);
                startActivity(religiousintent);
            }
        });

        busstationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent busintent = new Intent(AdminDashboardActivity.this, BusStationActivity.class);
                startActivity(busintent);
            }
        });

        restbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restintent = new Intent(AdminDashboardActivity.this, RestaurantActivity.class);
                startActivity(restintent);
            }
        });

        mallsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shoppingintent = new Intent(AdminDashboardActivity.this, ShoppingActivity.class);
                startActivity(shoppingintent);
            }
        });

    }

    private void signOutUser() {
        Intent i = new Intent(AdminDashboardActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
        Toast.makeText(this,"Logout sucessful",Toast.LENGTH_SHORT).show();
    }

}