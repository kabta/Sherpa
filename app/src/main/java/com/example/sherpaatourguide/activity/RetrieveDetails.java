package com.example.sherpaatourguide.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sherpaatourguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RetrieveDetails extends AppCompatActivity {
    TextView name, pdetails;
    ImageView pimage;
    Button locatebtn;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_details);


        name = (TextView) findViewById(R.id.placename);
        pdetails = (TextView) findViewById(R.id.details);

        locatebtn = (Button) findViewById(R.id.locate_button);
        reff = FirebaseDatabase.getInstance().getReference().child("Destination");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Name = dataSnapshot.child("Name").getValue().toString();
                String Details = dataSnapshot.child("Details").getValue().toString();
                name.setText(Name);
                pdetails.setText(Details);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}