package com.shop.arram.payment.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Sendorder {
    @SerializedName("payment_method")
    private String payment_method;
    @SerializedName("payment_method_title")
    private String payment_method_title;
    @SerializedName("transaction_id")
    private String transaction_id;
    @SerializedName("set_paid")
    private Boolean set_paid;
    @SerializedName("customer_id")
    private int customer_id;
    @SerializedName("line_items")
    private ArrayList<Lines_items> line_items;
    @SerializedName("billing")
    private Billing_order billing;
    @SerializedName("shipping")
    private Shipping_order shipping;
    @SerializedName("shipping_lines")
    private ArrayList<Shipping_lines> shipping_lines;

    public Sendorder(String payment_method, String payment_method_title, String transaction_id, Boolean set_paid, int customer_id,
                     ArrayList<Lines_items> line_items, Billing_order billing, Shipping_order shipping,
                     ArrayList<Shipping_lines> shipping_lines) {
        this.payment_method = payment_method;
        this.payment_method_title = payment_method_title;
        this.transaction_id = transaction_id;
        this.set_paid = set_paid;
        this.customer_id = customer_id;
        this.line_items = line_items;
        this.billing = billing;
        this.shipping = shipping;
        this.shipping_lines = shipping_lines;
    }
}
