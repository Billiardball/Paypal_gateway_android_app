package com.shop.arram.payment.api;

public class Shipping_lines {

    private String method_id;
    private String method_title;
    private String total;

    public Shipping_lines(String method_id, String method_title, String total) {
        this.method_id = method_id;
        this.method_title = method_title;
        this.total = total;
    }
}
