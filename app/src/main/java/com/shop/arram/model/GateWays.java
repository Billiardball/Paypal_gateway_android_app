package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GateWays {


    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("data")
    @Expose
    public List<GateWaysList> data;

    public GateWays withStatus(String status) {
        this.status = status;
        return this;
    }

    public GateWays withData(List<GateWaysList> data) {
        this.data = data;
        return this;
    }

    public class GateWaysList {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("description")
        @Expose
        public String description;

        public GateWaysList withId(String id) {
            this.id = id;
            return this;
        }

        public GateWaysList withTitle(String title) {
            this.title = title;
            return this;
        }

        public GateWaysList withDescription(String description) {
            this.description = description;
            return this;
        }
    }

}
