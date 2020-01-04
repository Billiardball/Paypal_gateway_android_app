package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bhumi Shah on 11/30/2017.
 */

public class PriceFilter {
    @SerializedName("min_price")
    @Expose
    public String minPrice;
    @SerializedName("max_price")
    @Expose
    public String maxPrice;
    @SerializedName("currency_symbol")
    @Expose
    public String currencySymbol;

    public PriceFilter withMinPrice(String minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public PriceFilter withMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public PriceFilter withCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
        return this;
    }
}
