
package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Orders {

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
    @SerializedName("order_tracking_data")
    @Expose
    public List<OrderTrackingData> orderTrackingData = null;
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
    public Object datePaid;
    @SerializedName("date_paid_gmt")
    @Expose
    public Object datePaidGmt;
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
    public List<Object> meta_data = null;


    @SerializedName("line_items")
    @Expose
    public List<LineItem> lineItems = null;
    @SerializedName("tax_lines")
    @Expose
    public List<TaxLine> taxLines = null;
    @SerializedName("shipping_lines")
    @Expose
    public List<ShippingLine> shippingLines = null;
    @SerializedName("fee_lines")
    @Expose
    public List<Object> feeLines = null;
    @SerializedName("coupon_lines")
    @Expose
    public List<Object> couponLines = null;
    @SerializedName("refunds")
    @Expose
    public List<Object> refunds = null;

    public Orders withId(int id) {
        this.id = id;
        return this;
    }

    public Orders withParentId(int parentId) {
        this.parentId = parentId;
        return this;
    }

    public Orders withNumber(String number) {
        this.number = number;
        return this;
    }

    public Orders withOrderKey(String orderKey) {
        this.orderKey = orderKey;
        return this;
    }

    public Orders withCreatedVia(String createdVia) {
        this.createdVia = createdVia;
        return this;
    }

    public Orders withVersion(String version) {
        this.version = version;
        return this;
    }

    public Orders withStatus(String status) {
        this.status = status;
        return this;
    }

    public Orders withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public Orders withDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public Orders withDateCreatedGmt(String dateCreatedGmt) {
        this.dateCreatedGmt = dateCreatedGmt;
        return this;
    }

    public Orders withDateModified(String dateModified) {
        this.dateModified = dateModified;
        return this;
    }

    public Orders withDateModifiedGmt(String dateModifiedGmt) {
        this.dateModifiedGmt = dateModifiedGmt;
        return this;
    }

    public Orders withDiscountTotal(String discountTotal) {
        this.discountTotal = discountTotal;
        return this;
    }

    public Orders withDiscountTax(String discountTax) {
        this.discountTax = discountTax;
        return this;
    }

    public Orders withShippingTotal(String shippingTotal) {
        this.shippingTotal = shippingTotal;
        return this;
    }

    public Orders withShippingTax(String shippingTax) {
        this.shippingTax = shippingTax;
        return this;
    }

    public Orders withCartTax(String cartTax) {
        this.cartTax = cartTax;
        return this;
    }

    public Orders withTotal(String total) {
        this.total = total;
        return this;
    }

    public Orders withTotalTax(String totalTax) {
        this.totalTax = totalTax;
        return this;
    }

    public Orders withPricesIncludeTax(boolean pricesIncludeTax) {
        this.pricesIncludeTax = pricesIncludeTax;
        return this;
    }

    public Orders withCustomerId(int customerId) {
        this.customerId = customerId;
        return this;
    }

    public Orders withCustomerIpAddress(String customerIpAddress) {
        this.customerIpAddress = customerIpAddress;
        return this;
    }

    public Orders withCustomerUserAgent(String customerUserAgent) {
        this.customerUserAgent = customerUserAgent;
        return this;
    }

    public Orders withCustomerNote(String customerNote) {
        this.customerNote = customerNote;
        return this;
    }

    public Orders withBilling(Billing billing) {
        this.billing = billing;
        return this;
    }

    public Orders withShipping(Shipping shipping) {
        this.shipping = shipping;
        return this;
    }

    public Orders withPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }



    public Orders withPaymentMethodTitle(String paymentMethodTitle) {
        this.paymentMethodTitle = paymentMethodTitle;
        return this;
    }

    public Orders withTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public Orders withDatePaid(Object datePaid) {
        this.datePaid = datePaid;
        return this;
    }

    public Orders withDatePaidGmt(Object datePaidGmt) {
        this.datePaidGmt = datePaidGmt;
        return this;
    }

    public Orders withDateCompleted(Object dateCompleted) {
        this.dateCompleted = dateCompleted;
        return this;
    }

    public Orders withDateCompletedGmt(Object dateCompletedGmt) {
        this.dateCompletedGmt = dateCompletedGmt;
        return this;
    }

    public Orders withCartHash(String cartHash) {
        this.cartHash = cartHash;
        return this;
    }

    public Orders withLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
        return this;
    }

    public Orders withTaxLines(List<TaxLine> taxLines) {
        this.taxLines = taxLines;
        return this;
    }

    public Orders withShippingLines(List<ShippingLine> shippingLines) {
        this.shippingLines = shippingLines;
        return this;
    }

    public Orders withOrderTrackingData(List<OrderTrackingData> orderTrackingData) {
        this.orderTrackingData = orderTrackingData;
        return this;
    }

    public Orders withFeeLines(List<Object> feeLines) {
        this.feeLines = feeLines;
        return this;
    }

    public Orders withCouponLines(List<Object> couponLines) {
        this.couponLines = couponLines;
        return this;
    }

    public Orders withRefunds(List<Object> refunds) {
        this.refunds = refunds;
        return this;
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
        public float price;
        @SerializedName("product_image")
        @Expose
        public String productImage;

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

        public LineItem withProductImage(String productImage) {
            this.productImage = productImage;
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

    public class ShippingLine {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("method_title")
        @Expose
        public String methodTitle;
        @SerializedName("method_id")
        @Expose
        public String methodId;
        @SerializedName("total")
        @Expose
        public String total;
        @SerializedName("total_tax")
        @Expose
        public String totalTax;
        @SerializedName("taxes")
        @Expose
        public List<Tax_> taxes = null;

        public ShippingLine withId(int id) {
            this.id = id;
            return this;
        }

        public ShippingLine withMethodTitle(String methodTitle) {
            this.methodTitle = methodTitle;
            return this;
        }

        public ShippingLine withMethodId(String methodId) {
            this.methodId = methodId;
            return this;
        }

        public ShippingLine withTotal(String total) {
            this.total = total;
            return this;
        }

        public ShippingLine withTotalTax(String totalTax) {
            this.totalTax = totalTax;
            return this;
        }

        public ShippingLine withTaxes(List<Tax_> taxes) {
            this.taxes = taxes;
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

    public class OrderTrackingData{

        @SerializedName("use_track_button")
        @Expose
        public boolean usetrackbutton;
        @SerializedName("track_message_1")
        @Expose
        public String trackmessage1;
        @SerializedName("track_message_2")
        @Expose
        public String trackmessage2;
        @SerializedName("order_tracking_link")
        @Expose
        public String ordertrackinglink;

        public OrderTrackingData withUseTrackButton(boolean usetrackbutton) {
            this.usetrackbutton = usetrackbutton;
            return this;
        }

        public OrderTrackingData withTrackMessage1(String trackmessage1) {
            this.trackmessage1 = trackmessage1;
            return this;
        }

        public OrderTrackingData withTrackMessage2(String trackmessage2) {
            this.trackmessage2 = trackmessage2;
            return this;
        }

        public OrderTrackingData withOrderTrackingLink(String ordertrackinglink) {
            this.ordertrackinglink = ordertrackinglink;
            return this;
        }

    }

    public class Tax_ {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("total")
        @Expose
        public String total;
        @SerializedName("subtotal")
        @Expose
        public String subtotal;

        public Tax_ withId(int id) {
            this.id = id;
            return this;
        }

        public Tax_ withTotal(String total) {
            this.total = total;
            return this;
        }

        public Tax_ withSubtotal(String subtotal) {
            this.subtotal = subtotal;
            return this;
        }

    }
}