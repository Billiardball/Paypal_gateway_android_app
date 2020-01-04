package com.shop.arram.payment.api;

public class Merchanturls {

    String terms;
    String checkout;
    String confirmation;
    String push;

    public Merchanturls(String terms, String checkout, String confirmation, String push) {
        this.terms = terms;
        this.checkout = checkout;
        this.confirmation = confirmation;
        this.push = push;
    }
}
