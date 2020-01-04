package com.shop.arram.model;

/**
 * Created by User on 02-12-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("id")
    @Expose
    public int id;
    @Expose
    public String dateCreated;
    @SerializedName("date_created_gmt")
    @Expose
    public String dateCreatedGmt;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("dob")
    @Expose
    public String dob;
    @SerializedName("date_modified")
    @Expose
    public String dateModified;
    @SerializedName("date_modified_gmt")
    @Expose
    public String dateModifiedGmt;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("billing")
    @Expose
    public Billing billing;
    @SerializedName("shipping")
    @Expose
    public Shipping shipping;
    @SerializedName("is_paying_customer")
    @Expose
    public boolean isPayingCustomer;
    @SerializedName("orders_count")
    @Expose
    public int ordersCount;
    @SerializedName("total_spent")
    @Expose
    public String totalSpent;
    @SerializedName("avatar_url")
    @Expose
    public String avatarUrl;
    @SerializedName("pgs_profile_image")
    @Expose
    public String pgsProfileImage;
    @SerializedName("ios_app_url")
    @Expose
    public String iosAppUrl;

    public Customer withId(int id) {
        this.id = id;
        return this;
    }

    public Customer withDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public Customer withDateCreatedGmt(String dateCreatedGmt) {
        this.dateCreatedGmt = dateCreatedGmt;
        return this;
    }

    public Customer withGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Customer withDob(String dob) {
        this.dob = dob;
        return this;
    }

    public Customer withDateModified(String dateModified) {
        this.dateModified = dateModified;
        return this;
    }

    public Customer withDateModifiedGmt(String dateModifiedGmt) {
        this.dateModifiedGmt = dateModifiedGmt;
        return this;
    }

    public Customer withEmail(String email) {
        this.email = email;
        return this;
    }

    public Customer withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Customer withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Customer withRole(String role) {
        this.role = role;
        return this;
    }

    public Customer withUsername(String username) {
        this.username = username;
        return this;
    }

    public Customer withBilling(Billing billing) {
        this.billing = billing;
        return this;
    }

    public Customer withShipping(Shipping shipping) {
        this.shipping = shipping;
        return this;
    }

    public Customer withIsPayingCustomer(boolean isPayingCustomer) {
        this.isPayingCustomer = isPayingCustomer;
        return this;
    }

    public Customer withOrdersCount(int ordersCount) {
        this.ordersCount = ordersCount;
        return this;
    }

    public Customer withTotalSpent(String totalSpent) {
        this.totalSpent = totalSpent;
        return this;
    }

    public Customer withAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public Customer withPgsProfileImage(String pgsProfileImage) {
        this.pgsProfileImage = pgsProfileImage;
        return this;
    }

    public Customer withIosAppUrl(String iosAppUrl) {
        this.iosAppUrl = iosAppUrl;
        return this;
    }

    public class Billing {

        @SerializedName("first_name")
        @Expose
        public String firstName;
        @SerializedName("last_name")
        @Expose
        public String lastName;
        @SerializedName("company")
        @Expose
        public String company;
        @SerializedName("address_1")
        @Expose
        public String address1;
        @SerializedName("address_2")
        @Expose
        public String address2;
        @SerializedName("city")
        @Expose
        public String city;
        @SerializedName("state")
        @Expose
        public String state;
        @SerializedName("postcode")
        @Expose
        public String postcode;
        @SerializedName("country")
        @Expose
        public String country;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("phone")
        @Expose
        public String phone;

        public Billing withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Billing withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Billing withCompany(String company) {
            this.company = company;
            return this;
        }

        public Billing withAddress1(String address1) {
            this.address1 = address1;
            return this;
        }

        public Billing withAddress2(String address2) {
            this.address2 = address2;
            return this;
        }

        public Billing withCity(String city) {
            this.city = city;
            return this;
        }

        public Billing withState(String state) {
            this.state = state;
            return this;
        }

        public Billing withPostcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public Billing withCountry(String country) {
            this.country = country;
            return this;
        }

        public Billing withEmail(String email) {
            this.email = email;
            return this;
        }

        public Billing withPhone(String phone) {
            this.phone = phone;
            return this;
        }

    }

    public class Shipping {

        @SerializedName("first_name")
        @Expose
        public String firstName;
        @SerializedName("last_name")
        @Expose
        public String lastName;
        @SerializedName("company")
        @Expose
        public String company;
        @SerializedName("address_1")
        @Expose
        public String address1;
        @SerializedName("address_2")
        @Expose
        public String address2;
        @SerializedName("city")
        @Expose
        public String city;
        @SerializedName("state")
        @Expose
        public String state;
        @SerializedName("postcode")
        @Expose
        public String postcode;
        @SerializedName("country")
        @Expose
        public String country;

        public Shipping withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Shipping withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Shipping withCompany(String company) {
            this.company = company;
            return this;
        }

        public Shipping withAddress1(String address1) {
            this.address1 = address1;
            return this;
        }

        public Shipping withAddress2(String address2) {
            this.address2 = address2;
            return this;
        }

        public Shipping withCity(String city) {
            this.city = city;
            return this;
        }

        public Shipping withState(String state) {
            this.state = state;
            return this;
        }

        public Shipping withPostcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public Shipping withCountry(String country) {
            this.country = country;
            return this;
        }

    }
}