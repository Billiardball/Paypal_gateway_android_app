package com.shop.arram.payment.api;

public class Shipping_address {

    String given_name;
    String family_name;
    String email;
    String title;
    String street_address;
    String street_address2;
    String street_name;
    String street_number;
    String house_extension;
    String postal_code;
    String city;
    String region;
    String phone;
    String country;

    public Shipping_address(String given_name, String family_name, String email, String title, String street_address, String street_address2, String street_name, String street_number, String house_extension, String postal_code, String city, String region, String phone, String country) {
        this.given_name = given_name;
        this.family_name = family_name;
        this.email = email;
        this.title = title;
        this.street_address = street_address;
        this.street_address2 = street_address2;
        this.street_name = street_name;
        this.street_number = street_number;
        this.house_extension = house_extension;
        this.postal_code = postal_code;
        this.city = city;
        this.region = region;
        this.phone = phone;
        this.country = country;
    }

    public String getGiven_name() {
        return given_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }

    public String getStreet_address() {
        return street_address;
    }

    public String getStreet_address2() {
        return street_address2;
    }

    public String getStreet_name() {
        return street_name;
    }

    public String getStreet_number() {
        return street_number;
    }

    public String getHouse_extension() {
        return house_extension;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }
}
