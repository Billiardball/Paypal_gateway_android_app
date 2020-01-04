package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Countrys {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("data")
    @Expose
    public List<CountyList> data;
    @SerializedName("base_country")
    @Expose
    public String baseCountry;

    public Countrys withStatus(String status) {
        this.status = status;
        return this;
    }

    public Countrys withData(List<CountyList> data) {
        this.data = data;
        return this;
    }

    public Countrys withBaseCountry(String baseCountry) {
        this.baseCountry = baseCountry;
        return this;


    }

    public CountyList getCountyListInstance() {

        return new CountyList();
    }

    public class CountyList {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("code")
        @Expose
        public String code;

        public CountyList withName(String name) {
            this.name = name;
            return this;
        }

        public CountyList withCode(String code) {
            this.code = code;
            return this;
        }
    }
}
