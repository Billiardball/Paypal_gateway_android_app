package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Origin {

    @SerializedName("address")
    @Expose
    private Address address;


    public Origin(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
