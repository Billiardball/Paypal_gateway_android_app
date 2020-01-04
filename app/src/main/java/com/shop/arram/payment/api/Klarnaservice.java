package com.shop.arram.payment.api;

import com.shop.arram.model.LogIn;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Klarnaservice {

    @POST("/checkout/v3/orders")
    Call<Sendclass> basicLogin(@Body Sendclass task);

    @POST("/wp-json/wc/v2/orders")
    Call<Sendorder> order_com(@Body Sendorder task);
}
