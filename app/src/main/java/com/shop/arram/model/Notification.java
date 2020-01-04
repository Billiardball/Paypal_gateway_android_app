package com.shop.arram.model;

/**
 * Created by User on 09-12-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Notification {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("data")
    @Expose
    public List<Datum> data = null;


    public Notification withStatus(String status) {
        this.status = status;
        return this;
    }

    public Notification withData(List<Datum> data) {
        this.data = data;
        return this;
    }

    public class Datum {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("user_id")
        @Expose
        public String userId;
        @SerializedName("device_token")
        @Expose
        public String deviceToken;
        @SerializedName("device_type")
        @Expose
        public String deviceType;
        @SerializedName("push_meta_id")
        @Expose
        public String pushMetaId;
        @SerializedName("msg")
        @Expose
        public String msg;
        @SerializedName("custom_msg")
        @Expose
        public String customMsg;
        @SerializedName("not_code")
        @Expose
        public String notCode;

        public Datum withId(String id) {
            this.id = id;
            return this;
        }

        public Datum withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Datum withDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
            return this;
        }

        public Datum withDeviceType(String deviceType) {
            this.deviceType = deviceType;
            return this;
        }

        public Datum withPushMetaId(String pushMetaId) {
            this.pushMetaId = pushMetaId;
            return this;
        }

        public Datum withMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Datum withCustomMsg(String customMsg) {
            this.customMsg = customMsg;
            return this;
        }

        public Datum withNotCode(String notCode) {
            this.notCode = notCode;
            return this;
        }

    }

}