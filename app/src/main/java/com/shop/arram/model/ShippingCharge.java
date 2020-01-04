package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ankita on 12/3/2018.
 */

public class ShippingCharge {

    @SerializedName("show_shipping")
    @Expose
    public int showShipping;
    @SerializedName("shipping")
    @Expose
    public Shipping shipping;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("taxes_total")
    @Expose
    public TaxesTotal taxesTotal;
    @SerializedName("order_total_html")
    @Expose
    public String orderTotalHtml;
    @SerializedName("order_total")
    @Expose
    public String order_total;

    public ShippingCharge withShowShipping(int showShipping) {
        this.showShipping = showShipping;
        return this;
    }

    public ShippingCharge withShipping(Shipping shipping) {
        this.shipping = shipping;
        return this;
    }

    public ShippingCharge withStatus(String status) {
        this.status = status;
        return this;
    }

    public ShippingCharge withTaxesTotal(TaxesTotal taxesTotal) {
        this.taxesTotal = taxesTotal;
        return this;
    }

    public ShippingCharge withOrderTotalHtml(String orderTotalHtml) {
        this.orderTotalHtml = orderTotalHtml;
        return this;
    }

    public ShippingCharge withorderTotal(String ordertotal) {
        this.order_total = ordertotal;
        return this;
    }


    public class Method {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("label")
        @Expose
        public String label;
        @SerializedName("cost")
        @Expose
        public String cost;
        @SerializedName("cost_display")
        @Expose
        public String costDisplay;
        @SerializedName("method_id")
        @Expose
        public String methodId;

        public Method withId(String id) {
            this.id = id;
            return this;
        }

        public Method withLabel(String label) {
            this.label = label;
            return this;
        }

        public Method withCost(String cost) {
            this.cost = cost;
            return this;
        }

        public Method withCostDisplay(String costDisplay) {
            this.costDisplay = costDisplay;
            return this;
        }

        public Method withMethodId(String methodId) {
            this.methodId = methodId;
            return this;
        }

    }

    public class Shipping {

        @SerializedName("methods")
        @Expose
        public List<Method> methods = null;
        @SerializedName("chosen")
        @Expose
        public String chosen;
        @SerializedName("index")
        @Expose
        public int index;

        public Shipping withMethods(List<Method> methods) {
            this.methods = methods;
            return this;
        }

        public Shipping withChosen(String chosen) {
            this.chosen = chosen;
            return this;
        }

        public Shipping withIndex(int index) {
            this.index = index;
            return this;
        }

    }

    public class TaxesTotal {

        @SerializedName("code")
        @Expose
        public String code;
        @SerializedName("label")
        @Expose
        public String label;
        @SerializedName("formatted_amount")
        @Expose
        public String formattedAmount;
        @SerializedName("amount")
        @Expose
        public String amount;

        public TaxesTotal withCode(String code) {
            this.code = code;
            return this;
        }

        public TaxesTotal withLabel(String label) {
            this.label = label;
            return this;
        }

        public TaxesTotal withFormattedAmount(String formattedAmount) {
            this.formattedAmount = formattedAmount;
            return this;
        }

        public TaxesTotal withAmount(String amount) {
            this.amount = amount;
            return this;
        }

    }
}
