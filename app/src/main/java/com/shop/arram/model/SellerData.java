package com.shop.arram.model;

/**
 * Created by User on 30-11-2017.
 */


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellerData {

    @SerializedName("seller_info")
    @Expose
    public SellerInfo sellerInfo;
    @SerializedName("products")
    @Expose
    public List<Product> products = null;

    public SellerData withSellerInfo(SellerInfo sellerInfo) {
        this.sellerInfo = sellerInfo;
        return this;
    }

    public SellerData withProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public class Product {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("slug")
        @Expose
        public String slug;
        @SerializedName("permalink")
        @Expose
        public String permalink;
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
        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("featured")
        @Expose
        public boolean featured;
        @SerializedName("catalog_visibility")
        @Expose
        public String catalogVisibility;
        @SerializedName("description")
        @Expose
        public String description;
        @SerializedName("short_description")
        @Expose
        public String shortDescription;
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
        @SerializedName("date_on_sale_from")
        @Expose
        public Object dateOnSaleFrom;
        @SerializedName("date_on_sale_from_gmt")
        @Expose
        public Object dateOnSaleFromGmt;
        @SerializedName("date_on_sale_to")
        @Expose
        public Object dateOnSaleTo;
        @SerializedName("date_on_sale_to_gmt")
        @Expose
        public Object dateOnSaleToGmt;
        @SerializedName("price_html")
        @Expose
        public String priceHtml;
        @SerializedName("on_sale")
        @Expose
        public boolean onSale;
        @SerializedName("purchasable")
        @Expose
        public boolean purchasable;
        @SerializedName("total_sales")
        @Expose
        public int totalSales;
        @SerializedName("virtual")
        @Expose
        public boolean virtual;
        @SerializedName("downloadable")
        @Expose
        public boolean downloadable;
        @SerializedName("download_limit")
        @Expose
        public int downloadLimit;
        @SerializedName("download_expiry")
        @Expose
        public int downloadExpiry;
        @SerializedName("external_url")
        @Expose
        public String externalUrl;
        @SerializedName("button_text")
        @Expose
        public String buttonText;
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
        public Object stockQuantity;
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
        @SerializedName("sold_individually")
        @Expose
        public boolean soldIndividually;
        @SerializedName("weight")
        @Expose
        public String weight;
        @SerializedName("dimensions")
        @Expose
        public Dimensions dimensions;
        @SerializedName("shipping_required")
        @Expose
        public boolean shippingRequired;
        @SerializedName("shipping_taxable")
        @Expose
        public boolean shippingTaxable;
        @SerializedName("shipping_class")
        @Expose
        public String shippingClass;
        @SerializedName("shipping_class_id")
        @Expose
        public int shippingClassId;
        @SerializedName("reviews_allowed")
        @Expose
        public boolean reviewsAllowed;
        @SerializedName("average_rating")
        @Expose
        public String averageRating;
        @SerializedName("rating_count")
        @Expose
        public int ratingCount;
        @SerializedName("related_ids")
        @Expose
        public List<Integer> relatedIds = null;
        @SerializedName("upsell_ids")
        @Expose
        public List<Object> upsellIds = null;
        @SerializedName("cross_sell_ids")
        @Expose
        public List<Object> crossSellIds = null;
        @SerializedName("parent_id")
        @Expose
        public int parentId;
        @SerializedName("purchase_note")
        @Expose
        public String purchaseNote;
        @SerializedName("categories")
        @Expose
        public List<Category> categories = null;
        @SerializedName("tags")
        @Expose
        public List<Object> tags = null;
        @SerializedName("images")
        @Expose
        public List<Image> images = null;
        @SerializedName("attributes")
        @Expose
        public List<Attribute> attributes = null;
        @SerializedName("default_attributes")
        @Expose
        public List<Object> defaultAttributes = null;
        @SerializedName("variations")
        @Expose
        public List<Integer> variations = null;
        @SerializedName("grouped_products")
        @Expose
        public List<Integer> groupedProducts = null;
        @SerializedName("menu_order")
        @Expose
        public int menuOrder;

        public Product withId(int id) {
            this.id = id;
            return this;
        }

        public Product withName(String name) {
            this.name = name;
            return this;
        }

        public Product withSlug(String slug) {
            this.slug = slug;
            return this;
        }

        public Product withPermalink(String permalink) {
            this.permalink = permalink;
            return this;
        }

        public Product withDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public Product withDateCreatedGmt(String dateCreatedGmt) {
            this.dateCreatedGmt = dateCreatedGmt;
            return this;
        }

        public Product withDateModified(String dateModified) {
            this.dateModified = dateModified;
            return this;
        }

        public Product withDateModifiedGmt(String dateModifiedGmt) {
            this.dateModifiedGmt = dateModifiedGmt;
            return this;
        }

        public Product withType(String type) {
            this.type = type;
            return this;
        }

        public Product withStatus(String status) {
            this.status = status;
            return this;
        }

        public Product withFeatured(boolean featured) {
            this.featured = featured;
            return this;
        }

        public Product withCatalogVisibility(String catalogVisibility) {
            this.catalogVisibility = catalogVisibility;
            return this;
        }

        public Product withDescription(String description) {
            this.description = description;
            return this;
        }

        public Product withShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public Product withSku(String sku) {
            this.sku = sku;
            return this;
        }

        public Product withPrice(String price) {
            this.price = price;
            return this;
        }

        public Product withRegularPrice(String regularPrice) {
            this.regularPrice = regularPrice;
            return this;
        }

        public Product withSalePrice(String salePrice) {
            this.salePrice = salePrice;
            return this;
        }

        public Product withDateOnSaleFrom(Object dateOnSaleFrom) {
            this.dateOnSaleFrom = dateOnSaleFrom;
            return this;
        }

        public Product withDateOnSaleFromGmt(Object dateOnSaleFromGmt) {
            this.dateOnSaleFromGmt = dateOnSaleFromGmt;
            return this;
        }

        public Product withDateOnSaleTo(Object dateOnSaleTo) {
            this.dateOnSaleTo = dateOnSaleTo;
            return this;
        }

        public Product withDateOnSaleToGmt(Object dateOnSaleToGmt) {
            this.dateOnSaleToGmt = dateOnSaleToGmt;
            return this;
        }

        public Product withPriceHtml(String priceHtml) {
            this.priceHtml = priceHtml;
            return this;
        }

        public Product withOnSale(boolean onSale) {
            this.onSale = onSale;
            return this;
        }

        public Product withPurchasable(boolean purchasable) {
            this.purchasable = purchasable;
            return this;
        }

        public Product withTotalSales(int totalSales) {
            this.totalSales = totalSales;
            return this;
        }

        public Product withVirtual(boolean virtual) {
            this.virtual = virtual;
            return this;
        }

        public Product withDownloadable(boolean downloadable) {
            this.downloadable = downloadable;
            return this;
        }


        public Product withDownloadLimit(int downloadLimit) {
            this.downloadLimit = downloadLimit;
            return this;
        }

        public Product withDownloadExpiry(int downloadExpiry) {
            this.downloadExpiry = downloadExpiry;
            return this;
        }

        public Product withExternalUrl(String externalUrl) {
            this.externalUrl = externalUrl;
            return this;
        }

        public Product withButtonText(String buttonText) {
            this.buttonText = buttonText;
            return this;
        }

        public Product withTaxStatus(String taxStatus) {
            this.taxStatus = taxStatus;
            return this;
        }

        public Product withTaxClass(String taxClass) {
            this.taxClass = taxClass;
            return this;
        }

        public Product withManageStock(boolean manageStock) {
            this.manageStock = manageStock;
            return this;
        }

        public Product withStockQuantity(Object stockQuantity) {
            this.stockQuantity = stockQuantity;
            return this;
        }

        public Product withInStock(boolean inStock) {
            this.inStock = inStock;
            return this;
        }

        public Product withBackorders(String backorders) {
            this.backorders = backorders;
            return this;
        }

        public Product withBackordersAllowed(boolean backordersAllowed) {
            this.backordersAllowed = backordersAllowed;
            return this;
        }

        public Product withBackordered(boolean backordered) {
            this.backordered = backordered;
            return this;
        }

        public Product withSoldIndividually(boolean soldIndividually) {
            this.soldIndividually = soldIndividually;
            return this;
        }

        public Product withWeight(String weight) {
            this.weight = weight;
            return this;
        }

        public Product withDimensions(Dimensions dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Product withShippingRequired(boolean shippingRequired) {
            this.shippingRequired = shippingRequired;
            return this;
        }

        public Product withShippingTaxable(boolean shippingTaxable) {
            this.shippingTaxable = shippingTaxable;
            return this;
        }

        public Product withShippingClass(String shippingClass) {
            this.shippingClass = shippingClass;
            return this;
        }

        public Product withShippingClassId(int shippingClassId) {
            this.shippingClassId = shippingClassId;
            return this;
        }

        public Product withReviewsAllowed(boolean reviewsAllowed) {
            this.reviewsAllowed = reviewsAllowed;
            return this;
        }

        public Product withAverageRating(String averageRating) {
            this.averageRating = averageRating;
            return this;
        }

        public Product withRatingCount(int ratingCount) {
            this.ratingCount = ratingCount;
            return this;
        }

        public Product withRelatedIds(List<Integer> relatedIds) {
            this.relatedIds = relatedIds;
            return this;
        }

        public Product withUpsellIds(List<Object> upsellIds) {
            this.upsellIds = upsellIds;
            return this;
        }

        public Product withCrossSellIds(List<Object> crossSellIds) {
            this.crossSellIds = crossSellIds;
            return this;
        }

        public Product withParentId(int parentId) {
            this.parentId = parentId;
            return this;
        }

        public Product withPurchaseNote(String purchaseNote) {
            this.purchaseNote = purchaseNote;
            return this;
        }

        public Product withCategories(List<Category> categories) {
            this.categories = categories;
            return this;
        }

        public Product withTags(List<Object> tags) {
            this.tags = tags;
            return this;
        }

        public Product withImages(List<Image> images) {
            this.images = images;
            return this;
        }

        public Product withAttributes(List<Attribute> attributes) {
            this.attributes = attributes;
            return this;
        }

        public Product withDefaultAttributes(List<Object> defaultAttributes) {
            this.defaultAttributes = defaultAttributes;
            return this;
        }

        public Product withVariations(List<Integer> variations) {
            this.variations = variations;
            return this;
        }

        public Product withGroupedProducts(List<Integer> groupedProducts) {
            this.groupedProducts = groupedProducts;
            return this;
        }

        public Product withMenuOrder(int menuOrder) {
            this.menuOrder = menuOrder;
            return this;
        }


        public class Attribute {

            @SerializedName("id")
            @Expose
            public int id;
            @SerializedName("name")
            @Expose
            public String name;
            @SerializedName("position")
            @Expose
            public int position;
            @SerializedName("visible")
            @Expose
            public boolean visible;
            @SerializedName("variation")
            @Expose
            public boolean variation;
            @SerializedName("options")
            @Expose
            public List<String> options = null;

            public Attribute withId(int id) {
                this.id = id;
                return this;
            }

            public Attribute withName(String name) {
                this.name = name;
                return this;
            }

            public Attribute withPosition(int position) {
                this.position = position;
                return this;
            }

            public Attribute withVisible(boolean visible) {
                this.visible = visible;
                return this;
            }

            public Attribute withVariation(boolean variation) {
                this.variation = variation;
                return this;
            }

            public Attribute withOptions(List<String> options) {
                this.options = options;
                return this;
            }

        }

        public class Category {

            @SerializedName("id")
            @Expose
            public int id;
            @SerializedName("name")
            @Expose
            public String name;
            @SerializedName("slug")
            @Expose
            public String slug;

            public Category withId(int id) {
                this.id = id;
                return this;
            }

            public Category withName(String name) {
                this.name = name;
                return this;
            }

            public Category withSlug(String slug) {
                this.slug = slug;
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

    }


    public class SellerInfo {

        @SerializedName("is_seller")
        @Expose
        public boolean isSeller;
        @SerializedName("contact_seller")
        @Expose
        public boolean contactSeller;
        @SerializedName("seller_id")
        @Expose
        public String sellerId;
        @SerializedName("store_name")
        @Expose
        public String storeName;
        @SerializedName("store_tnc")
        @Expose
        public String storeTnc;
        @SerializedName("seller_rating")
        @Expose
        public SellerRating sellerRating;
        @SerializedName("review_list")
        @Expose
        public List<ReviewList> reviewList = null;
        @SerializedName("store_description")
        @Expose
        public String storeDescription;
        @SerializedName("avatar")
        @Expose
        public String avatar;
        @SerializedName("banner_url")
        @Expose
        public String bannerUrl;
        @SerializedName("seller_address")
        @Expose
        public String sellerAddress;

        public SellerInfo withIsSeller(boolean isSeller) {
            this.isSeller = isSeller;
            return this;
        }

        public SellerInfo withContactSeller(boolean contactSeller) {
            this.contactSeller = contactSeller;
            return this;
        }

        public SellerInfo withSellerId(String sellerId) {
            this.sellerId = sellerId;
            return this;
        }

        public SellerInfo withStoreName(String storeName) {
            this.storeName = storeName;
            return this;
        }


        public SellerInfo withStoreTnc(String storeTnc) {
            this.storeTnc = storeTnc;
            return this;
        }

        public SellerInfo withSellerRating(SellerRating sellerRating) {
            this.sellerRating = sellerRating;
            return this;
        }

        public SellerInfo withReviewList(List<ReviewList> reviewList) {
            this.reviewList = reviewList;
            return this;
        }

        public SellerInfo withStoreDescription(String storeDescription) {
            this.storeDescription = storeDescription;
            return this;
        }

        public SellerInfo withAvatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public SellerInfo withBannerUrl(String bannerUrl) {
            this.bannerUrl = bannerUrl;
            return this;
        }

        public SellerInfo withSellerAddress(String sellerAddress) {
            this.sellerAddress = sellerAddress;
            return this;
        }

        public class SellerRating {

            @SerializedName("rating")
            @Expose
            public float rating;
            @SerializedName("count")
            @Expose
            public int count;

            public SellerRating withRating(int rating) {
                this.rating = rating;
                return this;
            }

            public SellerRating withCount(int count) {
                this.count = count;
                return this;
            }

        }

        public class ReviewList {

            @SerializedName("rating")
            @Expose
            public String rating;
            @SerializedName("comment_author")
            @Expose
            public String commentAuthor;
            @SerializedName("verified")
            @Expose
            public boolean verified;
            @SerializedName("comment_date")
            @Expose
            public String commentDate;
            @SerializedName("comment_content")
            @Expose
            public String commentContent;
            @SerializedName("avatar")
            @Expose
            public String avatar;


            public ReviewList withCommentAuthor(String commentAuthor) {
                this.commentAuthor = commentAuthor;
                return this;
            }

            public ReviewList withVerified(boolean verified) {
                this.verified = verified;
                return this;
            }

            public ReviewList withCommentDate(String commentDate) {
                this.commentDate = commentDate;
                return this;
            }

            public ReviewList withCommentContent(String commentContent) {
                this.commentContent = commentContent;
                return this;
            }

            public ReviewList withAvatar(String avatar) {
                this.avatar = avatar;
                return this;
            }

        }

    }

}