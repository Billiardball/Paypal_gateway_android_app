package com.shop.arram.payment.api;

public class Orderlines {
    String name;
    String type;
    int quantity;
    int unit_price;
    int tax_rate;
    int total_amount;
    int total_tax_amount;

    public Orderlines(String type,String name, int quantity, int unit_price,
                      int tax_rate, int total_amount, int total_tax_amount) {
        this.type = type;
        this.name = name;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.tax_rate = tax_rate;
        this.total_amount = total_amount;
        this.total_tax_amount = total_tax_amount;
    }
}
