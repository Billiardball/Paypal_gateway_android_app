package com.shop.arram.model;

/**
 * Created by User on 04-12-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InfoPages {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("data")
    @Expose
    public List<Datum> data = null;

    public InfoPages withStatus(String status) {
        this.status = status;
        return this;
    }

    public InfoPages withData(List<Datum> data) {
        this.data = data;
        return this;
    }

    public class Datum {

        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("page_id")
        @Expose
        public String pageId;

        public Datum withTitle(String title) {
            this.title = title;
            return this;
        }

        public Datum withPageId(String pageId) {
            this.pageId = pageId;
            return this;
        }

    }

}