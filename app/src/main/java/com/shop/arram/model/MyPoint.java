package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ankita on 5/10/2018.
 */

public class MyPoint {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("data")
    @Expose
    public Data data;

    public MyPoint withStatus(String status) {
        this.status = status;
        return this;
    }

    public MyPoint withData(Data data) {
        this.data = data;
        return this;
    }

    public class Data {

        @SerializedName("points_balance")
        @Expose
        public int pointsBalance;
        @SerializedName("points_label")
        @Expose
        public String pointsLabel;
        @SerializedName("events")
        @Expose
        public List<Event> events = null;
        @SerializedName("total_rows")
        @Expose
        public String totalRows;
        @SerializedName("current_page")
        @Expose
        public int currentPage;
        @SerializedName("count")
        @Expose
        public int count;

        public Data withPointsBalance(int pointsBalance) {
            this.pointsBalance = pointsBalance;
            return this;
        }

        public Data withPointsLabel(String pointsLabel) {
            this.pointsLabel = pointsLabel;
            return this;
        }

        public Data withEvents(List<Event> events) {
            this.events = events;
            return this;
        }

        public Data withTotalRows(String totalRows) {
            this.totalRows = totalRows;
            return this;
        }

        public Data withCurrentPage(int currentPage) {
            this.currentPage = currentPage;
            return this;
        }

        public Data withCount(int count) {
            this.count = count;
            return this;
        }

    }

    public class Event {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("user_id")
        @Expose
        public String userId;
        @SerializedName("points")
        @Expose
        public String points;
        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("user_points_id")
        @Expose
        public Object userPointsId;
        @SerializedName("order_id")
        @Expose
        public Object orderId;
        @SerializedName("admin_user_id")
        @Expose
        public String adminUserId;
        @SerializedName("data")
        @Expose
        public Object data;
        @SerializedName("date")
        @Expose
        public String date;
        @SerializedName("date_display_human")
        @Expose
        public String dateDisplayHuman;
        @SerializedName("date_display")
        @Expose
        public String dateDisplay;
        @SerializedName("description")
        @Expose
        public String description;

        public Event withId(String id) {
            this.id = id;
            return this;
        }

        public Event withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Event withPoints(String points) {
            this.points = points;
            return this;
        }

        public Event withType(String type) {
            this.type = type;
            return this;
        }

        public Event withUserPointsId(Object userPointsId) {
            this.userPointsId = userPointsId;
            return this;
        }

        public Event withOrderId(Object orderId) {
            this.orderId = orderId;
            return this;
        }

        public Event withAdminUserId(String adminUserId) {
            this.adminUserId = adminUserId;
            return this;
        }

        public Event withData(Object data) {
            this.data = data;
            return this;
        }

        public Event withDate(String date) {
            this.date = date;
            return this;
        }

        public Event withDateDisplayHuman(String dateDisplayHuman) {
            this.dateDisplayHuman = dateDisplayHuman;
            return this;
        }

        public Event withDateDisplay(String dateDisplay) {
            this.dateDisplay = dateDisplay;
            return this;
        }

        public Event withDescription(String description) {
            this.description = description;
            return this;
        }

    }


}
