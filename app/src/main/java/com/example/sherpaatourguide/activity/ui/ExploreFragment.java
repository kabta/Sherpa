package com.example.sherpaatourguide.activity.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.sherpaatourguide.R;

import com.example.sherpaatourguide.activity.ui.dashboard.CommonPhrasesActivity;
import com.example.sherpaatourguide.activity.ui.dashboard.EmergencyNumbersActivity;
import com.example.sherpaatourguide.activity.ui.dashboard.Explore;
import com.example.sherpaatourguide.activity.ui.dashboard.FactsActivity;
import com.example.sherpaatourguide.retrievedetails.ShowBusActivity;
import com.example.sherpaatourguide.retrievedetails.ShowDestinationActivity;
import com.example.sherpaatourguide.retrievedetails.ShowReligiousActivity;
import com.example.sherpaatourguide.retrievedetails.ShowRestaurantActivity;
import com.example.sherpaatourguide.retrievedetails.ShowShoppingActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ExploreFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExploreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment ExploreFragment.
     */
    // TODO: Rename and change types and number of parameters


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
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        CardView Destination= view.findViewById(R.id.destinationCv);
        Destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ShowDestinationActivity.class));
            }
        });
        CardView Religiousplace = view.findViewById(R.id.religiousCv);
        Religiousplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ShowReligiousActivity.class));
            }
        });
        CardView BusStations = view.findViewById(R.id.busStationCv);
        BusStations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ShowBusActivity.class));
            }
        });
        CardView Restaurant = view.findViewById(R.id.restaurantCv);
        Restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ShowRestaurantActivity.class));
            }
        });


        CardView ShoppingMalls = view.findViewById(R.id.shoppingCv);
        ShoppingMalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ShowShoppingActivity.class));
            }
        });

        return view;
    }



}