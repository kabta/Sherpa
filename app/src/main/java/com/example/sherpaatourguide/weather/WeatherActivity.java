package com.example.sherpaatourguide.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sherpaatourguide.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.time.Instant;

import cz.msebera.android.httpclient.Header;

public class WeatherActivity extends AppCompatActivity {
    final String APP_ID = "cb702edfe5466f96594a57cc9230e164";
    final String WHEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";
    final long MIN_TIME = 5000;
    final float MIN_DISTANCE = 100;
    final int REQUEST_CODE = 101;
    String Location_provider = LocationManager.GPS_PROVIDER;

    TextView NameOfCity, weatherState, Temperature;
    ImageView mweatherIcon;
    RelativeLayout mCityFinder;
    LocationManager mLocationManager;
    LocationListener mLocationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherState = findViewById(R.id.wheatherCondition);
        Temperature = findViewById(R.id.temprature);
        mweatherIcon = findViewById(R.id.wheatherIcon);
        mCityFinder = findViewById(R.id.cityFinder);
        NameOfCity = findViewById(R.id.cityName);

        mCityFinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this, CityFinderActivity.class);
                startActivity(intent);
            }
        });


    }

    /*@Override
    protected void onResume() {
        super.onResume();
        getWeatherForCurrentLocation();
    }*/
    @Override
    protected void onResume()
    {
        super.onResume();
        Intent mIntent=getIntent();
        String city=mIntent.getStringExtra("City");
        if(city!=null)
        {
            getWeatherForNewCity(city);
        }
        else
        {
            getWeatherForCurrentLocation();
        }
    }
    private void getWeatherForNewCity(String city)
    {
        RequestParams params=new RequestParams();
        params.put("q",city);
        params.put("appid",APP_ID);
        letsdoSomeNetworking(params);
    }

    private void getWeatherForCurrentLocation() {


        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                String Latitude = String.valueOf(location.getLatitude());
                String Longitude = String.valueOf(location.getLongitude());

                RequestParams params=new RequestParams();
                params.put("lat",Latitude);
                params.put("lon",Longitude);
                params.put("appid",APP_ID);
                letsdoSomeNetworking(params);

            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE );
            return;
        }
        mLocationManager.requestLocationUpdates(Location_provider, MIN_TIME, MIN_DISTANCE, mLocationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CODE)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Location Get SuccessFully", Toast.LENGTH_SHORT).show();
                getWeatherForCurrentLocation();
            }
            else
            {
                //user denied the permission
            }
        }
    }
    private void letsdoSomeNetworking(RequestParams params)
    {
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(WHEATHER_URL,params,new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        //super.onSuccess(statusCode, headers, response);
                        Toast.makeText(WeatherActivity.this, "Sucessful", Toast.LENGTH_SHORT).show();
                        weatherData weatherD=weatherData.fromJson(response);
                        updateUI(weatherD);


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                }
        );

    }
    private void updateUI(weatherData weather)
    {
        Temperature.setText(weather.getmTemperature());
        NameOfCity.setText(weather.getmCity());
        weatherState.setText(weather.getmWeatherType());
        int resourceID=getResources().getIdentifier(weather.getmIcon(),"drawable",getPackageName());
        mweatherIcon.setImageResource(resourceID);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mLocationManager!=null)
        {
            mLocationManager.removeUpdates(mLocationListener);
        }
    }
}