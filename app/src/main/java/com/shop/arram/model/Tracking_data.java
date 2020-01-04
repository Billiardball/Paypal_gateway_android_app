package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tracking_data {

    @SerializedName("shipments")
    @Expose
    private List<Shipments> shipments;
}
