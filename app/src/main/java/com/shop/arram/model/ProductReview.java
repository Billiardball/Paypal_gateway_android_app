package com.shop.arram.model;

/**
 * Created by Bhumi Shah on 11/29/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductReview {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("date_created")
    @Expose
    public String dateCreated;
    @SerializedName("date_created_gmt")
    @Expose
    public String dateCreatedGmt;
    @SerializedName("review")
    @Expose
    public String review;
    @SerializedName("rating")
    @Expose
    public int rating;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("verified")
    @Expose
    public boolean verified;
    @SerializedName("_links")
    @Expose
    public Links links;

    public ProductReview withId(int id) {
        this.id = id;
        return this;
    }

    public ProductReview withDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public ProductReview withDateCreatedGmt(String dateCreatedGmt) {
        this.dateCreatedGmt = dateCreatedGmt;
        return this;
    }

    public ProductReview withReview(String review) {
        this.review = review;
        return this;
    }

    public ProductReview withRating(int rating) {
        this.rating = rating;
        return this;
    }

    public ProductReview withName(String name) {
        this.name = name;
        return this;
    }

    public ProductReview withEmail(String email) {
        this.email = email;
        return this;
    }

    public ProductReview withVerified(boolean verified) {
        this.verified = verified;
        return this;
    }

    public ProductReview withLinks(Links links) {
        this.links = links;
        return this;
    }


    public class Links {

        @SerializedName("self")
        @Expose
        public List<Self> self = null;
        @SerializedName("collection")
        @Expose
        public List<Collection> collection = null;
        @SerializedName("up")
        @Expose
        public List<Up> up = null;

        public Links withSelf(List<Self> self) {
            this.self = self;
            return this;
        }

        public Links withCollection(List<Collection> collection) {
            this.collection = collection;
            return this;
        }

        public Links withUp(List<Up> up) {
            this.up = up;
            return this;
        }

    }

    public class Collection {

        @SerializedName("href")
        @Expose
        public String href;

        public Collection withHref(String href) {
            this.href = href;
            return this;
        }

    }

    public class Self {

        @SerializedName("href")
        @Expose
        public String href;

        public Self withHref(String href) {
            this.href = href;
            return this;
        }

    }
    public class Up {

        @SerializedName("href")
        @Expose
        public String href;

        public Up withHref(String href) {
            this.href = href;
            return this;
        }

    }

}
