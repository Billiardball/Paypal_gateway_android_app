package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Shipments {


    @SerializedName("origin")
    @Expose
    private Origin origin;

    @SerializedName("destination")
    @Expose
    private Destination destination;

    @SerializedName("status")
    @Expose
    private Status status;

    @SerializedName("events")
    @Expose
    private ArrayList<Events> events;

    public Shipments(Origin origin, Destination destination, Status status, ArrayList<Events> events) {
        this.origin = origin;
        this.destination = destination;
        this.status = status;
        this.events = events;
    }

    public Origin getOrigin() {
        return origin;
    }

    public Destination getDestination() {
        return destination;
    }

    public Status getStatus() {
        return status;
    }

    public ArrayList<Events> getEvents() {
        return events;
    }

    public class Events {
        @SerializedName("timestamp")
        @Expose
        private String timestamp;

        @SerializedName("description")
        @Expose
        private String description;


        public Events(String timestamp, String description) {
            this.timestamp = timestamp;
            this.description = description;
        }

        public String getEventsTimestamp() {
            return timestamp;
        }

        public String getEventsDescription() {
            return description;
        }
    }

    public class Status {
        @SerializedName("timestamp")
        @Expose
        private String timestamp;

        @SerializedName("description")
        @Expose
        private String description;


        public Status(String timestamp, String description) {
            this.timestamp = timestamp;
            this.description = description;
        }

        public String getStatusTimestamp() {
            return timestamp;
        }

        public String getStatusDescription() {
            return description;
        }
    }
}


