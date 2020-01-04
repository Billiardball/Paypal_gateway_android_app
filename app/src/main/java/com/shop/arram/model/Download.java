package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Download {

    @SerializedName("download_id")
    @Expose
    public String downloadId;
    @SerializedName("download_url")
    @Expose
    public String downloadUrl;
    @SerializedName("product_id")
    @Expose
    public int productId;
    @SerializedName("product_name")
    @Expose
    public String productName;
    @SerializedName("download_name")
    @Expose
    public String downloadName;
    @SerializedName("order_id")
    @Expose
    public int orderId;
    @SerializedName("order_key")
    @Expose
    public String orderKey;
    @SerializedName("downloads_remaining")
    @Expose
    public String downloadsRemaining;
    @SerializedName("access_expires")
    @Expose
    public String accessExpires;
    @SerializedName("access_expires_gmt")
    @Expose
    public String accessExpiresGmt;
    @SerializedName("file")
    @Expose
    public File file;
    @SerializedName("_links")
    @Expose
    public Links links;

    public Download withDownloadId(String downloadId) {
        this.downloadId = downloadId;
        return this;
    }

    public Download withDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
        return this;
    }

    public Download withProductId(int productId) {
        this.productId = productId;
        return this;
    }

    public Download withProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Download withDownloadName(String downloadName) {
        this.downloadName = downloadName;
        return this;
    }

    public Download withOrderId(int orderId) {
        this.orderId = orderId;
        return this;
    }

    public Download withOrderKey(String orderKey) {
        this.orderKey = orderKey;
        return this;
    }

    public Download withDownloadsRemaining(String downloadsRemaining) {
        this.downloadsRemaining = downloadsRemaining;
        return this;
    }

    public Download withAccessExpires(String accessExpires) {
        this.accessExpires = accessExpires;
        return this;
    }

    public Download withAccessExpiresGmt(String accessExpiresGmt) {
        this.accessExpiresGmt = accessExpiresGmt;
        return this;
    }

    public Download withFile(File file) {
        this.file = file;
        return this;
    }

    public Download withLinks(Links links) {
        this.links = links;
        return this;
    }

    public class File {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("file")
        @Expose
        public String file;

        public File withName(String name) {
            this.name = name;
            return this;
        }

        public File withFile(String file) {
            this.file = file;
            return this;
        }

    }

    public class Links {

        @SerializedName("collection")
        @Expose
        public List<Collection> collection = null;
        @SerializedName("product")
        @Expose
        public List<Product> product = null;
        @SerializedName("order")
        @Expose
        public List<Order> order = null;

        public Links withCollection(List<Collection> collection) {
            this.collection = collection;
            return this;
        }

        public Links withProduct(List<Product> product) {
            this.product = product;
            return this;
        }

        public Links withOrder(List<Order> order) {
            this.order = order;
            return this;
        }

        public class Order {

            @SerializedName("href")
            @Expose
            public String href;

            public Order withHref(String href) {
                this.href = href;
                return this;
            }

        }

        public class Product {

            @SerializedName("href")
            @Expose
            public String href;

            public Product withHref(String href) {
                this.href = href;
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

    }
}