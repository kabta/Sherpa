package com.example.sherpaatourguide.maps;
import android.os.AsyncTask;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class GetNearbyPlacesData extends AsyncTask<Object, String, String> {

    private String googlePlacesData;
    private GoogleMap googleMap;
    private String url;

    @Override
    protected String doInBackground(Object... objects) {
        googleMap= (GoogleMap) objects[0];
        url=(String) objects[1];

        DownloadUrl downloadUrl= new DownloadUrl();
        try {
            googlePlacesData=downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s) {
        try {

            if(s==null) return;
            JSONObject parentObject = new JSONObject(s);
            JSONArray resultArray= parentObject.getJSONArray("results");

            for (int i = 0; i <resultArray.length() ; i++) {
                JSONObject jsonObject= resultArray.getJSONObject(i);
                JSONObject locationObject= jsonObject.getJSONObject("geometry").getJSONObject("locations");

                String latitude= locationObject.getString("lat");
                String longitude= locationObject.getString("lng");

                JSONObject nameObject= resultArray.getJSONObject(i);
                String name= nameObject.getString("name");

                LatLng latLng= new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));

                MarkerOptions markerOptions= new MarkerOptions();
                markerOptions.title(name);
                markerOptions.position(latLng);

                googleMap.addMarker(markerOptions);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
