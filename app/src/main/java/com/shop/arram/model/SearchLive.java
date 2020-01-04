package com.shop.arram.model;

public class SearchLive {
    public int id;
    public String name;
    public String image;

    public SearchLive(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
