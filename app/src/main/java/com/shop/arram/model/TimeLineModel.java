package com.shop.arram.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TimeLineModel implements Parcelable {


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public TimeLineModel createFromParcel(Parcel in) {
            return new TimeLineModel(in);
        }

        public TimeLineModel[] newArray(int size) {
            return new TimeLineModel[size];
        }
    };


    String message;
    String date;
    OrderStatus status;

    public TimeLineModel(String message, String date, OrderStatus status) {
        this.message = message;
        this.date = date;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    // Parcelling part
    public TimeLineModel(Parcel in) {
        this.message = in.readString();
        this.date = in.readString();
        this.status = OrderStatus.valueOf(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.message);
        dest.writeString(this.date);
        dest.writeString(this.status.name());
    }
}
