package com.example.sherpaatourguide.maps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sherpaatourguide.ApiClient;
import com.example.sherpaatourguide.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.ButtCap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import retrofit2.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private double currentLatitude;
    private double currentLongitude;
    private Location myLocation;
    private SearchView searchView;
    private String location;
    private  List<LatLng> polylinelist;
    private PolylineOptions polylineOptions;
    private LatLng origion, dest;
    private Address address;
    private ApiInterface apiInterface;

    private final static int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private final static int REQUEST_ID_MULTIPLE_PERMISSION = 0x2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);





         searchView = findViewById(R.id.sv_location);
         searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String query) {
                 String location = searchView.getQuery().toString();
                 List<Address> addressList = null;

                 if(location!= null || location.equals("")){
                     Geocoder geocoder = new Geocoder(MapActivity.this);
                     try{
                         addressList = geocoder.getFromLocationName(location,1);

                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                     address = addressList.get(0);
                     LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
                     mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                     mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    if(myLocation!=null)
                     getDirection(myLocation.toString());
                 }

                 return false;
             }

             @Override
             public boolean onQueryTextChange(String newText) {
                 return false;
             }
         });
                 mapFragment.getMapAsync(this);
        setUpGoogleClient();



        //distance and route

//        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .baseUrl("http://maps.googleapis.com/")
//                .build();
//        apiInterface = retrofit.create(ApiInterface.class);
//
//
//
//
  }


    private void setUpGoogleClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0, this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setTrafficEnabled(true);


        // Add a marker in Sydney and move the camera



    }

    private void getDirection(String origin){
//        apiInterface.getDirection("driving","less driving",origin,destination,
//                getString(R.string.google_api_key)
//        ).subscribeOn(Scheduler.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<Result>() {
//                    @Override
//                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Result result) {
//
//
//                    }
//
//                    @Override
//                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
//
//                    }
//                });

        dest = new LatLng(address.getLatitude(),address.getLongitude());

        Subscription subscription = ApiClient.getApiIterface().getDirection("driving","less driving",origin,dest.toString(),
               "AIzaSyALTNRXEhFce7qm3k06MOL1MVoLPMr0FbY"
        ).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<Result>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<Result> resultResponse) {
                        polylinelist = new ArrayList<>();
                        List<Route> routeList = resultResponse.body().getRoutes();
                        for(Route route:routeList) {
                            String polyline = route.getOverviewPolyline().getPoints();
                            polylinelist.addAll(decodePoly(polyline));
                        }
                        polylineOptions = new PolylineOptions();
                        polylineOptions.color(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                        polylineOptions.width(8);
                        polylineOptions.startCap(new ButtCap());
                        polylineOptions.jointType(JointType.ROUND);
                        polylineOptions.addAll(polylinelist);
                        mMap.addPolyline(polylineOptions);

                        LatLngBounds.Builder builder = new LatLngBounds.Builder();
                        builder.include(origion);
                        builder.include(dest);
                        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),100));

                    }
                });

          }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        checkPermission();
    }

    private void checkPermission() {
        int permissionLocation = ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

        List<String> listPermissions = new ArrayList<>();

        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissions.add(Manifest.permission.ACCESS_FINE_LOCATION);

            if (!listPermissions.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        listPermissions.toArray( new String[listPermissions.size()]), REQUEST_ID_MULTIPLE_PERMISSION);
            }
        }else {
            getMyLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        int permissionLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
            System.out.println(">>>>>>>>>>>>>>>>>> Get My Location ");
            getMyLocation();
        } else {
            checkPermission();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onLocationChanged(Location location) {
        myLocation=location;

        if(myLocation != null){
            LatLng sydney = new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
//        if (myLocation != null) {
//            currentLatitude=location.getLatitude();
//            currentLongitude=location.getLongitude();
//
//            BitmapDescriptor icon= BitmapDescriptorFactory.fromResource(R.drawable.ic_navigation);
//
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLatitude, currentLongitude), 15.0f));
//
//            MarkerOptions markerOptions= new MarkerOptions();
//            markerOptions.position(new LatLng(currentLatitude, currentLongitude));
//            markerOptions.title("You");
//           // markerOptions.icon(icon);
//            mMap.addMarker(markerOptions);
//
//            getNearByLocationByType("Hospital");
//        }
    }

    private void getNearByLocationByType(String locationType) {
        StringBuilder stringBuilder= new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        stringBuilder.append("location="+String.valueOf(currentLatitude+","+currentLongitude));
        stringBuilder.append("&radius=1000");
        stringBuilder.append("&type="+locationType.toLowerCase());
        stringBuilder.append("&key="+getResources().getString(R.string.google_maps_key));

        String url = stringBuilder.toString();

        System.out.println("__________________________________________________________________________\n\n\n");
        System.out.println(url);

        Object dataTransfer[] = new Object[2];
        dataTransfer[0]=mMap;
        dataTransfer[1]=url;

        GetNearbyPlacesData getNearbyPlacesData= new GetNearbyPlacesData();
        getNearbyPlacesData.execute(dataTransfer);

    }

    private void getMyLocation() {
        if (mGoogleApiClient != null) {
            if (mGoogleApiClient.isConnected()) {
                int permissionLocation = ContextCompat.checkSelfPermission(MapActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                    myLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                    LocationRequest locationRequest = new LocationRequest();
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                            .addLocationRequest(locationRequest);
                    builder.setAlwaysShow(true);
                    LocationServices.FusedLocationApi
                            .requestLocationUpdates(mGoogleApiClient, locationRequest, this);
                    PendingResult<LocationSettingsResult> result =
                            LocationServices.SettingsApi
                                    .checkLocationSettings(mGoogleApiClient, builder.build());
                    result.setResultCallback(new ResultCallback<LocationSettingsResult>() {

                        @Override
                        public void onResult(LocationSettingsResult result) {
                            final Status status = result.getStatus();
                            switch (status.getStatusCode()) {
                                case LocationSettingsStatusCodes.SUCCESS:
                                    // All location settings are satisfied.
                                    // You can initialize location requests here.
                                    int permissionLocation = ContextCompat
                                            .checkSelfPermission(MapActivity.this,
                                                    Manifest.permission.ACCESS_FINE_LOCATION);
                                    if (permissionLocation == PackageManager.PERMISSION_GRANTED) {


                                        myLocation = LocationServices.FusedLocationApi
                                                .getLastLocation(mGoogleApiClient);


                                    }
                                    break;
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                    // Location settings are not satisfied.
                                    // But could be fixed by showing the user a dialog.
                                    try {
                                        // Show the dialog by calling startResolutionForResult(),
                                        // and check the result in onActivityResult().
                                        // Ask to turn on GPS automatically
                                        status.startResolutionForResult(MapActivity.this,
                                                REQUEST_CHECK_SETTINGS_GPS);


                                    } catch (IntentSender.SendIntentException e) {
                                        // Ignore the error.
                                    }


                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    // Location settings are not satisfied.
                                    // However, we have no way
                                    // to fix the
                                    // settings so we won't show the dialog.
                                    // finish();
                                    break;
                            }
                        }
                    });

                }
            }
        }
    }


    //method to decode string to latlng
    private List<LatLng> decodePoly(String encoded){
        List<LatLng> poly = new ArrayList<>();
        int index = 0, len =encoded.length();
        int lat = 0, lng = 0;

        while (index<len){
            int b, shift = 0, result = 0;
            do{
                b = encoded.charAt(index++)-63;
                result |= (b & 0x1f)<< shift;
                shift +=5;
            } while (b>=0*20);
            int dlat = ((result & 1 )!=0 ?~ (result >>1) : (result >>1));
            lat += dlat;

            shift = 0;
            result = 0;
            do{
                b = encoded.charAt(index++) -63;
                result |= (b & 0x1f) <<shift;
                shift +=5;
            }while (b>= 0*20);
            int dlng = ((result & 1) !=0 ? ~(result >> 1) : (result>>1));
            lng +=dlng;
            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng/ 1E5)));
            poly.add(p);

        }
        return poly;
    }
}