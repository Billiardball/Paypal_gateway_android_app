package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bhumi Shah on 11/23/2017.
 */


public class CategoryList {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("app_thumbnail")
    @Expose
    public String appthumbnail;
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

    @SerializedName("seller_info")
    @Expose
    public SellerInfo sellerInfo;

    @SerializedName("featured_video")
    @Expose
    public FeaturedVideo featuredVideo;

    @SerializedName("addition_info_html")
    @Expose
    public String additionInfoHtml;


    @SerializedName("tax_price")
    @Expose
    public String taxPrice;


    public boolean isProductInWishList = false;


    @SerializedName("rewards_message")
    @Expose
    public String rewardMessage;

    public Attribute getAttributeInstance() {
        return new Attribute();
    }

    public CategoryList withId(int id) {
        this.id = id;
        return this;
    }

    public CategoryList withName(String name) {
        this.name = name;
        return this;
    }

    public CategoryList withSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public CategoryList withPermalink(String permalink) {
        this.permalink = permalink;
        return this;
    }

    public CategoryList withDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public CategoryList withDateCreatedGmt(String dateCreatedGmt) {
        this.dateCreatedGmt = dateCreatedGmt;
        return this;
    }

    public CategoryList withDateModified(String dateModified) {
        this.dateModified = dateModified;
        return this;
    }

    public CategoryList withDateModifiedGmt(String dateModifiedGmt) {
        this.dateModifiedGmt = dateModifiedGmt;
        return this;
    }

    public CategoryList withType(String type) {
        this.type = type;
        return this;
    }

    public CategoryList withStatus(String status) {
        this.status = status;
        return this;
    }

    public CategoryList withFeatured(boolean featured) {
        this.featured = featured;
        return this;
    }

    public CategoryList withCatalogVisibility(String catalogVisibility) {
        this.catalogVisibility = catalogVisibility;
        return this;
    }

    public CategoryList withDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryList withShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public CategoryList withSku(String sku) {
        this.sku = sku;
        return this;
    }

    public CategoryList withPrice(String price) {
        this.price = price;
        return this;
    }

    public CategoryList withRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
        return this;
    }

    public CategoryList withSalePrice(String salePrice) {
        this.salePrice = salePrice;
        return this;
    }

    public CategoryList withDateOnSaleFrom(Object dateOnSaleFrom) {
        this.dateOnSaleFrom = dateOnSaleFrom;
        return this;
    }

    public CategoryList withDateOnSaleFromGmt(Object dateOnSaleFromGmt) {
        this.dateOnSaleFromGmt = dateOnSaleFromGmt;
        return this;
    }

    public CategoryList withDateOnSaleTo(Object dateOnSaleTo) {
        this.dateOnSaleTo = dateOnSaleTo;
        return this;
    }

    public CategoryList withDateOnSaleToGmt(Object dateOnSaleToGmt) {
        this.dateOnSaleToGmt = dateOnSaleToGmt;
        return this;
    }

    public CategoryList withPriceHtml(String priceHtml) {
        this.priceHtml = priceHtml;
        return this;
    }

    public CategoryList withOnSale(boolean onSale) {
        this.onSale = onSale;
        return this;
    }

    public CategoryList withPurchasable(boolean purchasable) {
        this.purchasable = purchasable;
        return this;
    }

    public CategoryList withTotalSales(int totalSales) {
        this.totalSales = totalSales;
        return this;
    }

    public CategoryList withVirtual(boolean virtual) {
        this.virtual = virtual;
        return this;
    }

    public CategoryList withDownloadable(boolean downloadable) {
        this.downloadable = downloadable;
        return this;
    }


    public CategoryList withDownloadLimit(int downloadLimit) {
        this.downloadLimit = downloadLimit;
        return this;
    }

    public CategoryList withDownloadExpiry(int downloadExpiry) {
        this.downloadExpiry = downloadExpiry;
        return this;
    }

    public CategoryList withExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
        return this;
    }

    public CategoryList withButtonText(String buttonText) {
        this.buttonText = buttonText;
        return this;
    }

    public CategoryList withTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
        return this;
    }

    public CategoryList withTaxClass(String taxClass) {
        this.taxClass = taxClass;
        return this;
    }

    public CategoryList withManageStock(boolean manageStock) {
        this.manageStock = manageStock;
        return this;
    }

    public CategoryList withStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
        return this;
    }

    public CategoryList withInStock(boolean inStock) {
        this.inStock = inStock;
        return this;
    }

    public CategoryList withBackorders(String backorders) {
        this.backorders = backorders;
        return this;
    }

    public CategoryList withBackordersAllowed(boolean backordersAllowed) {
        this.backordersAllowed = backordersAllowed;
        return this;
    }

    public CategoryList withBackordered(boolean backordered) {
        this.backordered = backordered;
        return this;
    }

    public CategoryList withSoldIndividually(boolean soldIndividually) {
        this.soldIndividually = soldIndividually;
        return this;
    }

    public CategoryList withWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public CategoryList withDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
        return this;
    }

    public CategoryList withShippingRequired(boolean shippingRequired) {
        this.shippingRequired = shippingRequired;
        return this;
    }

    public CategoryList withShippingTaxable(boolean shippingTaxable) {
        this.shippingTaxable = shippingTaxable;
        return this;
    }

    public CategoryList withShippingClass(String shippingClass) {
        this.shippingClass = shippingClass;
        return this;
    }

    public CategoryList withShippingClassId(int shippingClassId) {
        this.shippingClassId = shippingClassId;
        return this;
    }

    public CategoryList withReviewsAllowed(boolean reviewsAllowed) {
        this.reviewsAllowed = reviewsAllowed;
        return this;
    }

    public CategoryList withAverageRating(String averageRating) {
        this.averageRating = averageRating;
        return this;
    }

    public CategoryList withRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
        return this;
    }

    public CategoryList withRelatedIds(List<Integer> relatedIds) {
        this.relatedIds = relatedIds;
        return this;
    }

    public CategoryList withUpsellIds(List<Object> upsellIds) {
        this.upsellIds = upsellIds;
        return this;
    }

    public CategoryList withCrossSellIds(List<Object> crossSellIds) {
        this.crossSellIds = crossSellIds;
        return this;
    }

    public CategoryList withParentId(int parentId) {
        this.parentId = parentId;
        return this;
    }

    public CategoryList withPurchaseNote(String purchaseNote) {
        this.purchaseNote = purchaseNote;
        return this;
    }

    public CategoryList withCategories(List<Category> categories) {
        this.categories = categories;
        return this;
    }

    public CategoryList withTags(List<Object> tags) {
        this.tags = tags;
        return this;
    }

    public CategoryList withImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public CategoryList withAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    public CategoryList withDefaultAttributes(List<Object> defaultAttributes) {
        this.defaultAttributes = defaultAttributes;
        return this;
    }

    public CategoryList withVariations(List<Integer> variations) {
        this.variations = variations;
        return this;
    }

    public CategoryList withGroupedProducts(List<Integer> groupedProducts) {
        this.groupedProducts = groupedProducts;
        return this;
    }

    public CategoryList withMenuOrder(int menuOrder) {
        this.menuOrder = menuOrder;
        return this;
    }


    public CategoryList withSellerInfo(SellerInfo sellerInfo) {
        this.sellerInfo = sellerInfo;
        return this;
    }

    public Image getImageInstance() {
        return new Image();
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


    public class Attribute {

        public int selectedid = 0;
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
        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("type")
        @Expose
        public String type="";

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

        public Image withUrl(String url) {
            this.url = url;
            return this;
        }

        public Image withType(String type) {
            this.type = type;
            return this;
        }

    }


    public class SellerInfo {

        @SerializedName("is_seller")
        @Expose
        public boolean isSeller;
        @SerializedName("seller_id")
        @Expose
        public String sellerId;
        @SerializedName("store_name")
        @Expose
        public String storeName;
        @SerializedName("seller_address")
        @Expose
        public String sellerAddress;
        @SerializedName("seller_rating")
        @Expose
        public SellerRating sellerRating;
        @SerializedName("store_tnc")
        @Expose
        public String storeTnc;
        @SerializedName("contact_seller")
        @Expose
        public boolean contactSeller;

        public SellerInfo withIsSeller(boolean isSeller) {
            this.isSeller = isSeller;
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


        public SellerInfo withSellerAddress(String sellerAddress) {
            this.sellerAddress = sellerAddress;
            return this;
        }

        public SellerInfo withSellerRating(SellerRating sellerRating) {
            this.sellerRating = sellerRating;
            return this;
        }

        public SellerInfo withStoreTnc(String storeTnc) {
            this.storeTnc = storeTnc;
            return this;
        }

        public SellerInfo withContactSeller(boolean contactSeller) {
            this.contactSeller = contactSeller;
            return this;
        }

    }

    public class SellerRating {

        @SerializedName("rating")
        @Expose
        public float rating;
        @SerializedName("count")
        @Expose
        public int count;

        public SellerRating withCount(int count) {
            this.count = count;
            return this;
        }

    }

    public class FeaturedVideo {

        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("product_id")
        @Expose
        public int productId;
        @SerializedName("video_type")
        @Expose
        public String videoType;
        @SerializedName("video_id")
        @Expose
        public String videoId;
        @SerializedName("image_url")
        @Expose
        public String imageUrl;

        public FeaturedVideo withUrl(String url) {
            this.url = url;
            return this;
        }

        public FeaturedVideo withProductId(int productId) {
            this.productId = productId;
            return this;
        }

        public FeaturedVideo withVideoType(String videoType) {
            this.videoType = videoType;
            return this;
        }

        public FeaturedVideo withVideoId(String videoId) {
            this.videoId = videoId;
            return this;
        }

        public FeaturedVideo withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }
    }
}