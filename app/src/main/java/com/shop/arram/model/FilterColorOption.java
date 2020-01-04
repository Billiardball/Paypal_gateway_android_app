package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bhumi Shah on 11/30/2017.
 */

public class FilterColorOption {
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
    public List<Option> options = null;

    public FilterColorOption withId(int id) {
        this.id = id;
        return this;
    }

    public FilterColorOption withName(String name) {
        this.name = name;
        return this;
    }

    public FilterColorOption withPosition(int position) {
        this.position = position;
        return this;
    }

    public FilterColorOption withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public FilterColorOption withVariation(boolean variation) {
        this.variation = variation;
        return this;
    }

    public FilterColorOption withOptions(List<Option> options) {
        this.options = options;
        return this;
    }

    public Option getOption() {

        return new Option();
    }


    public class Option {

        @SerializedName("color_code")
        @Expose
        public String colorCode;
        @SerializedName("color_name")
        @Expose
        public String colorName;

        public Option withColorCode(String colorCode) {
            this.colorCode = colorCode;
            return this;
        }

        public Option withColorName(String colorName) {
            this.colorName = colorName;
            return this;
        }

    }
}
