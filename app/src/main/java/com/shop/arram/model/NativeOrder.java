package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ankita on 12/3/2018.
 */

public class NativeOrder {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("parent_id")
    @Expose
    public int parentId;
    @SerializedName("number")
    @Expose
    public String number;
    @SerializedName("order_key")
    @Expose
    public String orderKey;
    @SerializedName("created_via")
    @Expose
    public String createdVia;
    @SerializedName("version")
    @Expose
    public String version;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("currency")
    @Expose
    public String currency;
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
    @SerializedName("discount_total")
    @Expose
    public String discountTotal;
    @SerializedName("discount_tax")
    @Expose
    public String discountTax;
    @SerializedName("shipping_total")
    @Expose
    public String shippingTotal;
    @SerializedName("shipping_tax")
    @Expose
    public String shippingTax;
    @SerializedName("cart_tax")
    @Expose
    public String cartTax;
    @SerializedName("total")
    @Expose
    public String total;
    @SerializedName("total_tax")
    @Expose
    public String totalTax;
    @SerializedName("prices_include_tax")
    @Expose
    public boolean pricesIncludeTax;
    @SerializedName("customer_id")
    @Expose
    public int customerId;
    @SerializedName("customer_ip_address")
    @Expose
    public String customerIpAddress;
    @SerializedName("customer_user_agent")
    @Expose
    public String customerUserAgent;
    @SerializedName("customer_note")
    @Expose
    public String customerNote;
    @SerializedName("billing")
    @Expose
    public Billing billing;
    @SerializedName("shipping")
    @Expose
    public Shipping shipping;
    @SerializedName("payment_method")
    @Expose
    public String paymentMethod;
    @SerializedName("payment_method_title")
    @Expose
    public String paymentMethodTitle;
    @SerializedName("transaction_id")
    @Expose
    public String transactionId;
    @SerializedName("date_paid")
    @Expose
    public String datePaid;
    @SerializedName("date_paid_gmt")
    @Expose
    public String datePaidGmt;
    @SerializedName("date_completed")
    @Expose
    public Object dateCompleted;
    @SerializedName("date_completed_gmt")
    @Expose
    public Object dateCompletedGmt;
    @SerializedName("cart_hash")
    @Expose
    public String cartHash;
    @SerializedName("meta_data")
    @Expose
    public List<Object> metaData = null;
    @SerializedName("line_items")
    @Expose
    public List<LineItem> lineItems = null;
    @SerializedName("tax_lines")
    @Expose
    public List<TaxLine> taxLines = null;
    @SerializedName("shipping_lines")
    @Expose
    public List<Object> shippingLines = null;
    @SerializedName("fee_lines")
    @Expose
    public List<Object> feeLines = null;
    @SerializedName("coupon_lines")
    @Expose
    public List<Object> couponLines = null;
    @SerializedName("refunds")
    @Expose
    public List<Object> refunds = null;
    @SerializedName("_links")
    @Expose
    public Links links;

    public NativeOrder withId(int id) {
        this.id = id;
        return this;
    }

    public NativeOrder withParentId(int parentId) {
        this.parentId = parentId;
        return this;
    }

    public NativeOrder withNumber(String number) {
        this.number = number;
        return this;
    }

    public NativeOrder withOrderKey(String orderKey) {
        this.orderKey = orderKey;
        return this;
    }

    public NativeOrder withCreatedVia(String createdVia) {
        this.createdVia = createdVia;
        return this;
    }

    public NativeOrder withVersion(String version) {
        this.version = version;
        return this;
    }

    public NativeOrder withStatus(String status) {
        this.status = status;
        return this;
    }

    public NativeOrder withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public NativeOrder withDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public NativeOrder withDateCreatedGmt(String dateCreatedGmt) {
        this.dateCreatedGmt = dateCreatedGmt;
        return this;
    }

    public NativeOrder withDateModified(String dateModified) {
        this.dateModified = dateModified;
        return this;
    }

    public NativeOrder withDateModifiedGmt(String dateModifiedGmt) {
        this.dateModifiedGmt = dateModifiedGmt;
        return this;
    }

    public NativeOrder withDiscountTotal(String discountTotal) {
        this.discountTotal = discountTotal;
        return this;
    }

    public NativeOrder withDiscountTax(String discountTax) {
        this.discountTax = discountTax;
        return this;
    }

    public NativeOrder withShippingTotal(String shippingTotal) {
        this.shippingTotal = shippingTotal;
        return this;
    }

    public NativeOrder withShippingTax(String shippingTax) {
        this.shippingTax = shippingTax;
        return this;
    }

    public NativeOrder withCartTax(String cartTax) {
        this.cartTax = cartTax;
        return this;
    }

    public NativeOrder withTotal(String total) {
        this.total = total;
        return this;
    }

    public NativeOrder withTotalTax(String totalTax) {
        this.totalTax = totalTax;
        return this;
    }

    public NativeOrder withPricesIncludeTax(boolean pricesIncludeTax) {
        this.pricesIncludeTax = pricesIncludeTax;
        return this;
    }

    public NativeOrder withCustomerId(int customerId) {
        this.customerId = customerId;
        return this;
    }

    public NativeOrder withCustomerIpAddress(String customerIpAddress) {
        this.customerIpAddress = customerIpAddress;
        return this;
    }

    public NativeOrder withCustomerUserAgent(String customerUserAgent) {
        this.customerUserAgent = customerUserAgent;
        return this;
    }

    public NativeOrder withCustomerNote(String customerNote) {
        this.customerNote = customerNote;
        return this;
    }

    public NativeOrder withBilling(Billing billing) {
        this.billing = billing;
        return this;
    }

    public NativeOrder withShipping(Shipping shipping) {
        this.shipping = shipping;
        return this;
    }

    public NativeOrder withPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public NativeOrder withPaymentMethodTitle(String paymentMethodTitle) {
        this.paymentMethodTitle = paymentMethodTitle;
        return this;
    }

    public NativeOrder withTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public NativeOrder withDatePaid(String datePaid) {
        this.datePaid = datePaid;
        return this;
    }

    public NativeOrder withDatePaidGmt(String datePaidGmt) {
        this.datePaidGmt = datePaidGmt;
        return this;
    }

    public NativeOrder withDateCompleted(Object dateCompleted) {
        this.dateCompleted = dateCompleted;
        return this;
    }

    public NativeOrder withDateCompletedGmt(Object dateCompletedGmt) {
        this.dateCompletedGmt = dateCompletedGmt;
        return this;
    }

    public NativeOrder withCartHash(String cartHash) {
        this.cartHash = cartHash;
        return this;
    }

    public NativeOrder withMetaData(List<Object> metaData) {
        this.metaData = metaData;
        return this;
    }

    public NativeOrder withLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
        return this;
    }

    public NativeOrder withTaxLines(List<TaxLine> taxLines) {
        this.taxLines = taxLines;
        return this;
    }

    public NativeOrder withShippingLines(List<Object> shippingLines) {
        this.shippingLines = shippingLines;
        return this;
    }

    public NativeOrder withFeeLines(List<Object> feeLines) {
        this.feeLines = feeLines;
        return this;
    }

    public NativeOrder withCouponLines(List<Object> couponLines) {
        this.couponLines = couponLines;
        return this;
    }

    public NativeOrder withRefunds(List<Object> refunds) {
        this.refunds = refunds;
        return this;
    }

    public NativeOrder withLinks(Links links) {
        this.links = links;
        return this;
    }

    public class Self {

        @SerializedName("href")
        @Expose
        public String href;

        public Self withHref(String href) {
            this.href = href;
            return this;
        }

    }

    public class Shipping {

        @SerializedName("first_name")
        @Expose
        public String firstName;
        @SerializedName("last_name")
        @Expose
        public String lastName;
        @SerializedName("company")
        @Expose
        public String company;
        @SerializedName("address_1")
        @Expose
        public String address1;
        @SerializedName("address_2")
        @Expose
        public String address2;
        @SerializedName("city")
        @Expose
        public String city;
        @SerializedName("state")
        @Expose
        public String state;
        @SerializedName("postcode")
        @Expose
        public String postcode;
        @SerializedName("country")
        @Expose
        public String country;

        public Shipping withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Shipping withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Shipping withCompany(String company) {
            this.company = company;
            return this;
        }

        public Shipping withAddress1(String address1) {
            this.address1 = address1;
            return this;
        }

        public Shipping withAddress2(String address2) {
            this.address2 = address2;
            return this;
        }

        public Shipping withCity(String city) {
            this.city = city;
            return this;
        }

        public Shipping withState(String state) {
            this.state = state;
            return this;
        }

        public Shipping withPostcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public Shipping withCountry(String country) {
            this.country = country;
            return this;
        }

    }
    public class Tax {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("total")
        @Expose
        public String total;
        @SerializedName("subtotal")
        @Expose
        public String subtotal;

        public Tax withId(int id) {
            this.id = id;
            return this;
        }

        public Tax withTotal(String total) {
            this.total = total;
            return this;
        }

        public Tax withSubtotal(String subtotal) {
            this.subtotal = subtotal;
            return this;
        }

    }

    public class TaxLine {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("rate_code")
        @Expose
        public String rateCode;
        @SerializedName("rate_id")
        @Expose
        public int rateId;
        @SerializedName("label")
        @Expose
        public String label;
        @SerializedName("compound")
        @Expose
        public boolean compound;
        @SerializedName("tax_total")
        @Expose
        public String taxTotal;
        @SerializedName("shipping_tax_total")
        @Expose
        public String shippingTaxTotal;
        @SerializedName("meta_data")
        @Expose
        public List<Object> metaData = null;

        public TaxLine withId(int id) {
            this.id = id;
            return this;
        }

        public TaxLine withRateCode(String rateCode) {
            this.rateCode = rateCode;
            return this;
        }

        public TaxLine withRateId(int rateId) {
            this.rateId = rateId;
            return this;
        }

        public TaxLine withLabel(String label) {
            this.label = label;
            return this;
        }

        public TaxLine withCompound(boolean compound) {
            this.compound = compound;
            return this;
        }

        public TaxLine withTaxTotal(String taxTotal) {
            this.taxTotal = taxTotal;
            return this;
        }

        public TaxLine withShippingTaxTotal(String shippingTaxTotal) {
            this.shippingTaxTotal = shippingTaxTotal;
            return this;
        }

        public TaxLine withMetaData(List<Object> metaData) {
            this.metaData = metaData;
            return this;
        }


    }

    public class Links {

        @SerializedName("self")
        @Expose
        public List<Self> self = null;
        @SerializedName("collection")
        @Expose
        public List<Collection> collection = null;

        public Links withSelf(List<Self> self) {
            this.self = self;
            return this;
        }

        public Links withCollection(List<Collection> collection) {
            this.collection = collection;
            return this;
        }

    }

    public class LineItem {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("product_id")
        @Expose
        public int productId;
        @SerializedName("variation_id")
        @Expose
        public int variationId;
        @SerializedName("quantity")
        @Expose
        public int quantity;
        @SerializedName("tax_class")
        @Expose
        public String taxClass;
        @SerializedName("subtotal")
        @Expose
        public String subtotal;
        @SerializedName("subtotal_tax")
        @Expose
        public String subtotalTax;
        @SerializedName("total")
        @Expose
        public String total;
        @SerializedName("total_tax")
        @Expose
        public String totalTax;
        @SerializedName("taxes")
        @Expose
        public List<Tax> taxes = null;
        @SerializedName("meta_data")
        @Expose
        public List<Object> metaData = null;
        @SerializedName("sku")
        @Expose
        public String sku;
        @SerializedName("price")
        @Expose
        public int price;

        public LineItem withId(int id) {
            this.id = id;
            return this;
        }

        public LineItem withName(String name) {
            this.name = name;
            return this;
        }

        public LineItem withProductId(int productId) {
            this.productId = productId;
            return this;
        }

        public LineItem withVariationId(int variationId) {
            this.variationId = variationId;
            return this;
        }

        public LineItem withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public LineItem withTaxClass(String taxClass) {
            this.taxClass = taxClass;
            return this;
        }

        public LineItem withSubtotal(String subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public LineItem withSubtotalTax(String subtotalTax) {
            this.subtotalTax = subtotalTax;
            return this;
        }

        public LineItem withTotal(String total) {
            this.total = total;
            return this;
        }

        public LineItem withTotalTax(String totalTax) {
            this.totalTax = totalTax;
            return this;
        }

        public LineItem withTaxes(List<Tax> taxes) {
            this.taxes = taxes;
            return this;
        }

        public LineItem withMetaData(List<Object> metaData) {
            this.metaData = metaData;
            return this;
        }

        public LineItem withSku(String sku) {
            this.sku = sku;
            return this;
        }

        public LineItem withPrice(int price) {
            this.price = price;
            return this;
        }

    }

    public class Collection {

        @SerializedName("href")
        @Expose
        public String href;

        public Collection withHref(String href) {
            this.href = href;
            return this;
        }

    }

    public class Billing {

        @SerializedName("first_name")
        @Expose
        public String firstName;
        @SerializedName("last_name")
        @Expose
        public String lastName;
        @SerializedName("company")
        @Expose
        public String company;
        @SerializedName("address_1")
        @Expose
        public String address1;
        @SerializedName("address_2")
        @Expose
        public String address2;
        @SerializedName("city")
        @Expose
        public String city;
        @SerializedName("state")
        @Expose
        public String state;
        @SerializedName("postcode")
        @Expose
        public String postcode;
        @SerializedName("country")
        @Expose
        public String country;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("phone")
        @Expose
        public String phone;

        public Billing withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Billing withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Billing withCompany(String company) {
            this.company = company;
            return this;
        }

        public Billing withAddress1(String address1) {
            this.address1 = address1;
            return this;
        }

        public Billing withAddress2(String address2) {
            this.address2 = address2;
            return this;
        }

        public Billing withCity(String city) {
            this.city = city;
            return this;
        }

        public Billing withState(String state) {
            this.state = state;
            return this;
        }

        public Billing withPostcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public Billing withCountry(String country) {
            this.country = country;
            return this;
        }

        public Billing withEmail(String email) {
            this.email = email;
            return this;
        }

        public Billing withPhone(String phone) {
            this.phone = phone;
            return this;
        }

    }
}
