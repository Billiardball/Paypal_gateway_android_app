package com.shop.arram.utils;

import com.shop.arram.model.Shipments;
import com.shop.arram.model.Trackingresponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Tracking {

    @GET("api/tracking")
    Call<Trackingresponse> getTrackingdata(@Query("trackingNumber") String trackingNumber);

//    @GET("test.php")
//    Call<Trackingresponse> getTrackingdata(@Query("trackingNumber") String trackingNumber);

}

