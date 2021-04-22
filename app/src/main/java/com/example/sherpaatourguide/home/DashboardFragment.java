package com.example.sherpaatourguide.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sherpaatourguide.AdminDashboardActivity;
import com.example.sherpaatourguide.R;
import com.example.sherpaatourguide.Users;
import com.example.sherpaatourguide.activity.LoginActivity;
import com.example.sherpaatourguide.activity.ui.EventsActivity;
import com.example.sherpaatourguide.activity.ui.dashboard.CommonPhrasesActivity;
import com.example.sherpaatourguide.activity.ui.dashboard.EmergencyNumbersActivity;
import com.example.sherpaatourguide.activity.ui.dashboard.FactsActivity;
import com.example.sherpaatourguide.maps.MapActivity;
import com.example.sherpaatourguide.weather.WeatherActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class DashboardFragment extends Fragment {

    private FirebaseAuth mfirebaseAuth;
    private TextView mTvEmail, mTvLogout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public DashboardFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        mTvEmail = view.findViewById(R.id.tv_email);
        mTvLogout = view.findViewById(R.id.logout);
        mTvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfirebaseAuth.signOut();
                signOutUser();
            }
        });


        mfirebaseAuth = FirebaseAuth.getInstance();
        CardView CommonPhrase= view.findViewById(R.id.cv_CommonPhrases);
        CommonPhrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CommonPhrasesActivity.class));
            }
        });
        CardView EmergencyNumber = view.findViewById(R.id.cv_Emergency);
        EmergencyNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EmergencyNumbersActivity.class));
            }
        });

        CardView Facts = view.findViewById(R.id.cv_Facts);
        Facts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FactsActivity.class));
            }
        });


        CardView Events = view.findViewById(R.id.cv_Events);
        Events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EventsActivity.class));
            }
        });

        CardView Maps = view.findViewById(R.id.cv_Map);
        Maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MapActivity.class));
            }
        });
        CardView Weather = view.findViewById(R.id.cv_Weather);
        Weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WeatherActivity.class));
            }
        });

        return view;


    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser mFirebaseUser = mfirebaseAuth.getCurrentUser();
        Users user = new Users();
        if(mFirebaseUser!= null){
           //mTvEmail.setText(mfirebaseAuth.getEmail());
        }
        else
        {
            mTvEmail.setText("THERE!!!");
        }
    }
    private void signOutUser() {
        Intent i = new Intent(getActivity(), LoginActivity.class);
        startActivity(i);
        getActivity().finish();
        Toast.makeText(getActivity(),"Logout sucessful",Toast.LENGTH_SHORT).show();
    }

}