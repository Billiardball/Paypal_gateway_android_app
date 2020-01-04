package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StoreFinder {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("data")
    @Expose
    public List<Datum> data = null;

    public StoreFinder withStatus(String status) {
        this.status = status;
        return this;
    }

    public StoreFinder withData(List<Datum> data) {
        this.data = data;
        return this;
    }



    public class Datum {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("address")
        @Expose
        public String address;
        @SerializedName("lat")
        @Expose
        public String lat;
        @SerializedName("lng")
        @Expose
        public String lng;

        public Datum withId(int id) {
            this.id = id;
            return this;
        }

        public Datum withAddress(String address) {
            this.address = address;
            return this;
        }

        public Datum withLat(String lat) {
            this.lat = lat;
            return this;
        }

        public Datum withLng(String lng) {
            this.lng = lng;
            return this;
        }

    }
}
