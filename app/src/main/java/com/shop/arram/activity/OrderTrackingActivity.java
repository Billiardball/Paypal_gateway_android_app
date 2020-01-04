package com.shop.arram.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.shop.arram.R;
import com.shop.arram.adapter.ExampleTimeLineAdapter;
import com.shop.arram.model.OrderStatus;
import com.shop.arram.model.Shipments;
import com.shop.arram.model.TimeLineModel;
import com.shop.arram.model.Trackingresponse;
import com.shop.arram.utils.APIS;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Tracking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderTrackingActivity extends BaseActivity {

    private ArrayList<TimeLineModel> mDataList = new ArrayList<>();
    RecyclerView recyclerView;
    ExampleTimeLineAdapter ExampleTimeLineAdapter;
    public Retrofit retrofit;

    TextView text_from_origin, text_to_destination, text_status_description, text_status_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_order_tracking);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setScreenLayoutDirection();
        settvTitle(getResources().getString(R.string.track_order));
        showBackButton();
        setToolbarTheme();
        hideSearchNotification();
        setDataListItems();

        text_to_destination = findViewById(R.id.text_to_destination);
        text_from_origin = findViewById(R.id.text_from_origin);
        text_status_description = findViewById(R.id.text_status_description);
        text_status_time = findViewById(R.id.text_status_time);

    }

    private void setDataListItems() {

        if (getIntent().getExtras() != null) {
            showProgress("");
            String tracking_id = getIntent().getExtras().getString("trackingid");
            Retrofit retrofit = getRetrofitClient();
            Tracking trackingAPIs = retrofit.create(Tracking.class);

            Call<Trackingresponse> call = trackingAPIs.getTrackingdata(tracking_id);
            call.enqueue(new Callback<Trackingresponse>() {
                @Override
                public void onResponse(Call<Trackingresponse> call, Response<Trackingresponse> response) {
                    /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                     */
                    dismissProgress();
                    if (response.body() != null) {

                        if (response.body().getShipments() != null){
                            for (int i = 0; i < response.body().getShipments().get(0).getEvents().size(); i++) {

                                mDataList.add(new TimeLineModel(response.body().getShipments().get(0).getEvents().get(i).getEventsDescription(), response.body().getShipments().get(0).getEvents().get(i).getEventsTimestamp(), OrderStatus.ACTIVE));
                            }
                            text_from_origin.setText(response.body().getShipments().get(0).getOrigin().getAddress().getAddressLocality());
                            text_to_destination.setText(response.body().getShipments().get(0).getDestination().getAddress().getAddressLocality());
                            text_status_description.setText(response.body().getShipments().get(0).getStatus().getStatusDescription());
                            text_status_time.setText(formatDateTime("yyyy-MM-dd'T'HH:mm:ss", "hh:mm a, dd-MMM-yyyy",response.body().getShipments().get(0).getStatus().getStatusTimestamp()));
                            initRecyclerView();
                        }else{
                            text_from_origin.setText(getResources().getString(R.string.no_data_found));
                            text_to_destination.setText(getResources().getString(R.string.no_data_found));
                            text_status_description.setText(getResources().getString(R.string.no_data_found));

                        }


                    }
                }

                @Override
                public void onFailure(Call<Trackingresponse> call, Throwable t) {
                /*
                Error callback
                */
                    dismissProgress();
                    Toast.makeText(OrderTrackingActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }

    }

    private void initRecyclerView() {


        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(OrderTrackingActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.rvOrderTracking);
        recyclerView.setLayoutManager(linearLayoutManager);

        ExampleTimeLineAdapter = new ExampleTimeLineAdapter(OrderTrackingActivity.this, mDataList);
        recyclerView.setAdapter(ExampleTimeLineAdapter);
    }

    public Retrofit getRetrofitClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        //If condition to ensure we don't create multiple retrofit instances in a single application
        if (retrofit == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.level(HttpLoggingInterceptor.Level.BASIC);
            logging.level(HttpLoggingInterceptor.Level.HEADERS);
            logging.level(HttpLoggingInterceptor.Level.BODY);

            httpClient.addInterceptor(interceptor);
            httpClient.addInterceptor(logging);


            //Defining the Retrofit using Builder
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://arram.de/") //This is the only mandatory call on Builder object.
                    .addConverterFactory(GsonConverterFactory.create()) // Convertor library used to convert response into POJO
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

    public String formatDateTime(String originalFormat, String ouputFormat, String dates) {
        LocalDateTime date = LocalDateTime.parse(dates, DateTimeFormatter.ofPattern(originalFormat, Locale.ENGLISH));
        return date.format(DateTimeFormatter.ofPattern(ouputFormat, Locale.ENGLISH));
    }
}
