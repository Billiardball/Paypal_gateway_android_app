package com.shop.arram.model;

/**
 * Created by Bhumi Shah on 11/23/2017.
 */

public class Sort {

    private String name;
    private int id;
    private String syntext;

    public Sort(String name, int id, String syntext) {
        this.name = name;
        this.id = id;
        this.syntext = syntext;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSyntext() {
        return syntext;
    }

    public void setSyntext(String syntext) {
        this.syntext = syntext;
    }
}
