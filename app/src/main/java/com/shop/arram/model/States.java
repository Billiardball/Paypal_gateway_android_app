package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class States {

    @SerializedName("status")
    @Expose
    public String status;


    @SerializedName("data")
    @Expose
    public List<StatesList> data;

    public States withStatus(String status) {
        this.status = status;
        return this;
    }

    public States withData(List<StatesList> data) {
        this.data = data;
        return this;
    }

    public StatesList getCountyListInstance() {

        return new StatesList();
    }

    public class StatesList {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("code")
        @Expose
        public String code;

        public StatesList withName(String name) {
            this.name = name;
            return this;
        }

        public StatesList withCode(String code) {
            this.code = code;
            return this;
        }
    }
}
