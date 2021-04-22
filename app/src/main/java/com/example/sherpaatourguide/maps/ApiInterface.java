package com.example.sherpaatourguide.maps;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiInterface {

    @GET("maps/api/directions/json")
    Observable<Response<Result>> getDirection(@Query("mode") String mode,
                                              @Query("transit_routing_preference") String preference,
                                              @Query("origin") String origin,
                                              @Query("destination") String destinations,
                                              @Query("key") String key);




}
