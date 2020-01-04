package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bhumi Shah on 12/7/2017.
 */

public class SyncWishListModel {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("sync_list")
    @Expose
    public List<SyncList> syncList = null;

    public SyncWishListModel withStatus(String status) {
        this.status = status;
        return this;
    }

    public SyncWishListModel withSyncList(List<SyncList> syncList) {
        this.syncList = syncList;
        return this;
    }

    public class SyncList {

        @SerializedName("ID")
        @Expose
        public String iD;
        @SerializedName("prod_id")
        @Expose
        public String prodId;
        @SerializedName("quantity")
        @Expose
        public String quantity;
        @SerializedName("user_id")
        @Expose
        public String userId;
        @SerializedName("wishlist_id")
        @Expose
        public String wishlistId;
        @SerializedName("dateadded")
        @Expose
        public String dateadded;

        public SyncList withID(String iD) {
            this.iD = iD;
            return this;
        }

        public SyncList withProdId(String prodId) {
            this.prodId = prodId;
            return this;
        }

        public SyncList withQuantity(String quantity) {
            this.quantity = quantity;
            return this;
        }

        public SyncList withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public SyncList withWishlistId(String wishlistId) {
            this.wishlistId = wishlistId;
            return this;
        }

        public SyncList withDateadded(String dateadded) {
            this.dateadded = dateadded;
            return this;
        }

    }
}
