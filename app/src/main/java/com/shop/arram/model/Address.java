package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("addressLocality")
    @Expose
    private String addressLocality;

    @SerializedName("countryCode")
    @Expose
    private String countryCode;

    public Address(String addressLocality, String countryCode) {
        this.addressLocality = addressLocality;
        this.countryCode = countryCode;
    }

    public String getAddressLocality() {
        return addressLocality;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
