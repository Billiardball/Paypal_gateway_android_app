package com.shop.arram.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 29-11-2017.
 */

public class Rewards {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public List<Datum> data = null;

    public Rewards withStatus(String status) {
        this.status = status;
        return this;
    }

    public Rewards withMessage(String message) {
        this.message = message;
        return this;
    }

    public Rewards withData(List<Datum> data) {
        this.data = data;
        return this;
    }

    public class Datum {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("code")
        @Expose
        public String code;
        @SerializedName("amount")
        @Expose
        public String amount;
        @SerializedName("date_created")
        @Expose
        public String dateCreated;
        @SerializedName("date_created_gmt")
        @Expose
        public String dateCreatedGmt;
        @SerializedName("date_modified")
        @Expose
        public String dateModified;
        @SerializedName("date_modified_gmt")
        @Expose
        public String dateModifiedGmt;
        @SerializedName("discount_type")
        @Expose
        public String discountType;
        @SerializedName("description")
        @Expose
        public String description;
        @SerializedName("date_expires")
        @Expose
        public String dateExpires;
        @SerializedName("date_expires_gmt")
        @Expose
        public String dateExpiresGmt;
        @SerializedName("usage_count")
        @Expose
        public int usageCount;
        @SerializedName("individual_use")
        @Expose
        public boolean individualUse;
        @SerializedName("product_ids")
        @Expose
        public List<Object> productIds = null;
        @SerializedName("excluded_product_ids")
        @Expose
        public List<Object> excludedProductIds = null;
        @SerializedName("usage_limit")
        @Expose
        public Object usageLimit;
        @SerializedName("usage_limit_per_user")
        @Expose
        public Object usageLimitPerUser;
        @SerializedName("limit_usage_to_x_items")
        @Expose
        public Object limitUsageToXItems;
        @SerializedName("free_shipping")
        @Expose
        public boolean freeShipping;
        @SerializedName("product_categories")
        @Expose
        public List<Object> productCategories = null;
        @SerializedName("excluded_product_categories")
        @Expose
        public List<Object> excludedProductCategories = null;
        @SerializedName("exclude_sale_items")
        @Expose
        public boolean excludeSaleItems;
        @SerializedName("minimum_amount")
        @Expose
        public String minimumAmount;
        @SerializedName("maximum_amount")
        @Expose
        public String maximumAmount;
        @SerializedName("email_restrictions")
        @Expose
        public List<Object> emailRestrictions = null;
        @SerializedName("is_coupon_scratched")
        @Expose
        public String isCouponScratched;

        public Datum withId(int id) {
            this.id = id;
            return this;
        }

        public Datum withCode(String code) {
            this.code = code;
            return this;
        }

        public Datum withAmount(String amount) {
            this.amount = amount;
            return this;
        }

        public Datum withDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public Datum withDateCreatedGmt(String dateCreatedGmt) {
            this.dateCreatedGmt = dateCreatedGmt;
            return this;
        }

        public Datum withDateModified(String dateModified) {
            this.dateModified = dateModified;
            return this;
        }

        public Datum withDateModifiedGmt(String dateModifiedGmt) {
            this.dateModifiedGmt = dateModifiedGmt;
            return this;
        }

        public Datum withDiscountType(String discountType) {
            this.discountType = discountType;
            return this;
        }

        public Datum withDescription(String description) {
            this.description = description;
            return this;
        }

        public Datum withDateExpires(String dateExpires) {
            this.dateExpires = dateExpires;
            return this;
        }

        public Datum withDateExpiresGmt(String dateExpiresGmt) {
            this.dateExpiresGmt = dateExpiresGmt;
            return this;
        }

        public Datum withUsageCount(int usageCount) {
            this.usageCount = usageCount;
            return this;
        }

        public Datum withIndividualUse(boolean individualUse) {
            this.individualUse = individualUse;
            return this;
        }

        public Datum withProductIds(List<Object> productIds) {
            this.productIds = productIds;
            return this;
        }

        public Datum withExcludedProductIds(List<Object> excludedProductIds) {
            this.excludedProductIds = excludedProductIds;
            return this;
        }

        public Datum withUsageLimit(Object usageLimit) {
            this.usageLimit = usageLimit;
            return this;
        }

        public Datum withUsageLimitPerUser(Object usageLimitPerUser) {
            this.usageLimitPerUser = usageLimitPerUser;
            return this;
        }

        public Datum withLimitUsageToXItems(Object limitUsageToXItems) {
            this.limitUsageToXItems = limitUsageToXItems;
            return this;
        }

        public Datum withFreeShipping(boolean freeShipping) {
            this.freeShipping = freeShipping;
            return this;
        }

        public Datum withProductCategories(List<Object> productCategories) {
            this.productCategories = productCategories;
            return this;
        }

        public Datum withExcludedProductCategories(List<Object> excludedProductCategories) {
            this.excludedProductCategories = excludedProductCategories;
            return this;
        }

        public Datum withExcludeSaleItems(boolean excludeSaleItems) {
            this.excludeSaleItems = excludeSaleItems;
            return this;
        }

        public Datum withMinimumAmount(String minimumAmount) {
            this.minimumAmount = minimumAmount;
            return this;
        }

        public Datum withMaximumAmount(String maximumAmount) {
            this.maximumAmount = maximumAmount;
            return this;
        }

        public Datum withEmailRestrictions(List<Object> emailRestrictions) {
            this.emailRestrictions = emailRestrictions;
            return this;
        }



        public Datum withIsCouponScratched(String isCouponScratched) {
            this.isCouponScratched = isCouponScratched;
            return this;
        }

    }




}
