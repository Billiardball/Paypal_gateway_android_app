package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Trackingresponse {


    @SerializedName("shipments")
    @Expose
    private ArrayList<Shipments> Shipments;

    public Trackingresponse(ArrayList<com.shop.arram.model.Shipments> shipments) {
        Shipments = shipments;
    }

    public ArrayList<com.shop.arram.model.Shipments> getShipments() {
        return Shipments;
    }
}


