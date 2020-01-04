package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bhumi Shah on 11/24/2017.
 */


public class Variation {


    @SerializedName("id")
    @Expose
    public int id;
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
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("permalink")
    @Expose
    public String permalink;
    @SerializedName("sku")
    @Expose
    public String sku;
    @SerializedName("price")
    @Expose
    public String price;
    @SerializedName("regular_price")
    @Expose
    public String regularPrice;
    @SerializedName("sale_price")
    @Expose
    public String salePrice;
    @Expose
    public Object dateOnSaleToGmt;
    @SerializedName("on_sale")
    @Expose
    public boolean onSale;
    @SerializedName("visible")
    @Expose
    public boolean visible;
    @SerializedName("purchasable")
    @Expose
    public boolean purchasable;
    @SerializedName("virtual")
    @Expose
    public boolean virtual;
    @SerializedName("downloadable")
    @Expose
    public boolean downloadable;
    @SerializedName("downloads")
    @Expose
    public List<Object> downloads = null;
    @SerializedName("download_limit")
    @Expose
    public int downloadLimit;
    @SerializedName("download_expiry")
    @Expose
    public int downloadExpiry;
    @SerializedName("tax_status")
    @Expose
    public String taxStatus;
    @SerializedName("tax_class")
    @Expose
    public String taxClass;
    @SerializedName("manage_stock")
    @Expose
    public boolean manageStock;
    @SerializedName("stock_quantity")
    @Expose
    public int stockQuantity;
    @SerializedName("in_stock")
    @Expose
    public boolean inStock;
    @SerializedName("backorders")
    @Expose
    public String backorders;
    @SerializedName("backorders_allowed")
    @Expose
    public boolean backordersAllowed;
    @SerializedName("backordered")
    @Expose
    public boolean backordered;
    @SerializedName("weight")
    @Expose
    public String weight;
    @SerializedName("dimensions")
    @Expose
    public Dimensions dimensions;
    @SerializedName("shipping_class")
    @Expose
    public String shippingClass;
    @SerializedName("shipping_class_id")
    @Expose
    public int shippingClassId;
    @SerializedName("image")
    @Expose
    public Image image;
    @SerializedName("attributes")
    @Expose
    public List<Attribute> attributes = null;
    @SerializedName("menu_order")
    @Expose
    public int menuOrder;

    @SerializedName("price_html")
    @Expose
    public String priceHtml;

    @SerializedName("tax_price")
    @Expose
    public String taxPrice;

    public Variation withId(int id) {
        this.id = id;
        return this;
    }

    public Variation withDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public Variation withDateCreatedGmt(String dateCreatedGmt) {
        this.dateCreatedGmt = dateCreatedGmt;
        return this;
    }

    public Variation withDateModified(String dateModified) {
        this.dateModified = dateModified;
        return this;
    }

    public Variation withDateModifiedGmt(String dateModifiedGmt) {
        this.dateModifiedGmt = dateModifiedGmt;
        return this;
    }

    public Variation withDescription(String description) {
        this.description = description;
        return this;
    }

    public Variation withPermalink(String permalink) {
        this.permalink = permalink;
        return this;
    }

    public Variation withSku(String sku) {
        this.sku = sku;
        return this;
    }

    public Variation withPrice(String price) {
        this.price = price;
        return this;
    }

    public Variation withRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
        return this;
    }

    public Variation withSalePrice(String salePrice) {
        this.salePrice = salePrice;
        return this;
    }



    public Variation withDateOnSaleToGmt(Object dateOnSaleToGmt) {
        this.dateOnSaleToGmt = dateOnSaleToGmt;
        return this;
    }

    public Variation withOnSale(boolean onSale) {
        this.onSale = onSale;
        return this;
    }

    public Variation withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public Variation withPurchasable(boolean purchasable) {
        this.purchasable = purchasable;
        return this;
    }

    public Variation withVirtual(boolean virtual) {
        this.virtual = virtual;
        return this;
    }

    public Variation withDownloadable(boolean downloadable) {
        this.downloadable = downloadable;
        return this;
    }

    public Variation withDownloads(List<Object> downloads) {
        this.downloads = downloads;
        return this;
    }

    public Variation withDownloadLimit(int downloadLimit) {
        this.downloadLimit = downloadLimit;
        return this;
    }

    public Variation withDownloadExpiry(int downloadExpiry) {
        this.downloadExpiry = downloadExpiry;
        return this;
    }

    public Variation withTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
        return this;
    }

    public Variation withTaxClass(String taxClass) {
        this.taxClass = taxClass;
        return this;
    }

    public Variation withManageStock(boolean manageStock) {
        this.manageStock = manageStock;
        return this;
    }

    public Variation withStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
        return this;
    }

    public Variation withInStock(boolean inStock) {
        this.inStock = inStock;
        return this;
    }

    public Variation withBackorders(String backorders) {
        this.backorders = backorders;
        return this;
    }

    public Variation withBackordersAllowed(boolean backordersAllowed) {
        this.backordersAllowed = backordersAllowed;
        return this;
    }

    public Variation withBackordered(boolean backordered) {
        this.backordered = backordered;
        return this;
    }

    public Variation withWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public Variation withDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
        return this;
    }

    public Variation withShippingClass(String shippingClass) {
        this.shippingClass = shippingClass;
        return this;
    }

    public Variation withShippingClassId(int shippingClassId) {
        this.shippingClassId = shippingClassId;
        return this;
    }

    public Variation withImage(Image image) {
        this.image = image;
        return this;
    }

    public Variation withAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    public Variation withMenuOrder(int menuOrder) {
        this.menuOrder = menuOrder;
        return this;
    }



    public Variation withPriceHtml(String priceHtml) {
        this.priceHtml = priceHtml;
        return this;
    }




    public class Image {

        @SerializedName("id")
        @Expose
        public int id;
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
        @SerializedName("src")
        @Expose
        public String src;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("alt")
        @Expose
        public String alt;
        @SerializedName("position")
        @Expose
        public int position;

        public Image withId(int id) {
            this.id = id;
            return this;
        }

        public Image withDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public Image withDateCreatedGmt(String dateCreatedGmt) {
            this.dateCreatedGmt = dateCreatedGmt;
            return this;
        }

        public Image withDateModified(String dateModified) {
            this.dateModified = dateModified;
            return this;
        }

        public Image withDateModifiedGmt(String dateModifiedGmt) {
            this.dateModifiedGmt = dateModifiedGmt;
            return this;
        }

        public Image withSrc(String src) {
            this.src = src;
            return this;
        }

        public Image withName(String name) {
            this.name = name;
            return this;
        }

        public Image withAlt(String alt) {
            this.alt = alt;
            return this;
        }

        public Image withPosition(int position) {
            this.position = position;
            return this;
        }

    }



    public class MetaDatum {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("key")
        @Expose
        public String key;
        @SerializedName("value")
        @Expose
        public Value value;

        public MetaDatum withId(int id) {
            this.id = id;
            return this;
        }

        public MetaDatum withKey(String key) {
            this.key = key;
            return this;
        }

        public MetaDatum withValue(Value value) {
            this.value = value;
            return this;
        }

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

    public class Up {

        @SerializedName("href")
        @Expose
        public String href;

        public Up withHref(String href) {
            this.href = href;
            return this;
        }

    }

    public class Value {

        @SerializedName("vc_grid_id")
        @Expose
        public List<Object> vcGridId = null;

        public Value withVcGridId(List<Object> vcGridId) {
            this.vcGridId = vcGridId;
            return this;
        }

    }


    public class Dimensions {

        @SerializedName("length")
        @Expose
        public String length;
        @SerializedName("width")
        @Expose
        public String width;
        @SerializedName("height")
        @Expose
        public String height;

        public Dimensions withLength(String length) {
            this.length = length;
            return this;
        }

        public Dimensions withWidth(String width) {
            this.width = width;
            return this;
        }

        public Dimensions withHeight(String height) {
            this.height = height;
            return this;
        }

    }

    public class Attribute {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("option")
        @Expose
        public String option;

        public Attribute withId(int id) {
            this.id = id;
            return this;
        }

        public Attribute withName(String name) {
            this.name = name;
            return this;
        }

        public Attribute withOption(String option) {
            this.option = option;
            return this;
        }

    }
}
