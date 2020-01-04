package com.shop.arram.payment.api;

public class Lines_items {

    int product_id;
    int quantity;

    public Lines_items(int product_id, int quantity) {
        this.product_id = product_id;
        this.quantity = quantity;
    }
}
