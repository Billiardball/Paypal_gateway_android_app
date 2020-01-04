package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ankita on 4/3/2018.
 */

public class Success {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;

    public Success withStatus(String status) {
        this.status = status;
        return this;
    }

    public Success withMessage(String message) {
        this.message = message;
        return this;
    }

}
