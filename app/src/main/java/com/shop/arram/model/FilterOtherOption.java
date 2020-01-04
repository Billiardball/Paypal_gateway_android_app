package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhumi Shah on 11/30/2017.
 */

public class FilterOtherOption {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("position")
    @Expose
    public int position;
    @SerializedName("visible")
    @Expose
    public boolean visible;
    @SerializedName("variation")
    @Expose
    public boolean variation;
    @SerializedName("options")
    @Expose
    public List<String> options = new ArrayList<>();
    @SerializedName("rating_filters_status")
    @Expose
    public String ratingFiltersStatus;
    @SerializedName("rating_filters")
    @Expose
    public List<RatingFilter> ratingFilters = null;

    public FilterOtherOption withId(int id) {
        this.id = id;
        return this;
    }

    public FilterOtherOption withName(String name) {
        this.name = name;
        return this;
    }

    public FilterOtherOption withPosition(int position) {
        this.position = position;
        return this;
    }

    public FilterOtherOption withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public FilterOtherOption withVariation(boolean variation) {
        this.variation = variation;
        return this;
    }

    public FilterOtherOption withOptions(List<String> options) {
        this.options = options;
        return this;
    }

    public FilterOtherOption withRatingFiltersStatus(String ratingFiltersStatus) {
        this.ratingFiltersStatus = ratingFiltersStatus;
        return this;
    }

    public FilterOtherOption withRatingFilters(List<RatingFilter> ratingFilters) {
        this.ratingFilters = ratingFilters;
        return this;
    }

    public class RatingFilter {

        @SerializedName("rating_html")
        @Expose
        public int ratingHtml;

        public RatingFilter withRatingHtml(int ratingHtml) {
            this.ratingHtml = ratingHtml;
            return this;
        }

    }

}
