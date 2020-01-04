package com.shop.arram.payment.api;

import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;

public class Sendclass {
    @SerializedName("purchase_country")
    private String purchase_country;
    @SerializedName("purchase_currency")
    private String purchase_currency;
    @SerializedName("locale")
    private String locale;
    @SerializedName("order_amount")
    private int order_amount;
    @SerializedName("order_tax_amount")
    private int order_tax_amount;
    @SerializedName("order_lines")
    private ArrayList<Orderlines> order_lines;
    @SerializedName("merchant_urls")
    private Merchanturls merchant_urls;
    @SerializedName("html_snippet")
    private String html_snippet;
    @SerializedName("billing_address")
    private Billing_address billing_address;

    @SerializedName("shipping_address")
    private Shipping_address shipping_address;

    @SerializedName("order_id")
    private String order_id;

    public Sendclass(String purchase_country, String purchase_currency, String locale, int order_amount, int order_tax_amount,
                     ArrayList<Orderlines> order_lines, Merchanturls merchant_urls, Billing_address billing_address,
                     Shipping_address shipping_address) {
        this.purchase_country = purchase_country;
        this.purchase_currency = purchase_currency;
        this.locale = locale;
        this.order_amount = order_amount;
        this.order_tax_amount = order_tax_amount;
        this.order_lines = order_lines;
        this.merchant_urls = merchant_urls;
        this.billing_address = billing_address;
        this.shipping_address = shipping_address;
    }

    public String getHtml_snippet() {
        return html_snippet;
    }

    public String getOrder_id() {
        return order_id;
    }

    public Billing_address getBilling_address() {
        return billing_address;
    }

    public Shipping_address getShipping_address() {
        return shipping_address;
    }
}
