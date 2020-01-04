package com.shop.arram.payment.api;

public class Shipping_order {

    String first_name;
    String last_name;
    String address_1;
    String address_2;
    String city;
    String state;
    String postcode;
    String country;

    public Shipping_order(String first_name, String last_name, String address_1, String address_2,
                          String city, String state, String postcode, String country) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.country = country;
    }
}
