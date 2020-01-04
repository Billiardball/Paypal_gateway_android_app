package com.shop.arram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class Home implements Serializable {

    @SerializedName("products_carousel")
    @Expose
    public ProductsCarousel productsCarousel;
    @SerializedName("app_logo_light")
    @Expose
    public String appLogoLight;
    @SerializedName("app_logo")
    @Expose
    public String appLogo;
    @SerializedName("main_category")
    @Expose
    public List<MainCategory> mainCategory = null;
    @SerializedName("main_slider")
    @Expose
    public List<MainSlider> mainSlider = null;
    @SerializedName("category_banners")
    @Expose
    public List<CategoryBanner> categoryBanners = null;
    @SerializedName("banner_ad")
    @Expose
    public List<BannerAd> bannerAd = null;
    @SerializedName("static_page")
    @Expose
    public StaticPage staticPage;
    @SerializedName("info_pages")
    @Expose
    public List<InfoPage> infoPages = null;
    @SerializedName("pgs_app_social_links")
    @Expose
    public PgsAppSocialLinks pgsAppSocialLinks;
    @SerializedName("feature_box_status")
    @Expose
    public String featureBoxStatus;
    @SerializedName("feature_box_heading")
    @Expose
    public String featureBoxHeading;
    @SerializedName("feature_box")
    @Expose
    public List<FeatureBox> featureBox = null;
    @SerializedName("pgs_app_contact_info")
    @Expose
    public PgsAppContactInfo pgsAppContactInfo;
    @SerializedName("all_categories")
    @Expose
    public List<AllCategory> allCategories = null;
    @SerializedName("is_wishlist_active")
    @Expose
    public Boolean isWishlistActive;
     @SerializedName("is_yith_featured_video_active")
     @Expose
     public Boolean isYithFeaturedVideoActive;
    @SerializedName("is_currency_switcher_active")
    @Expose
    public Boolean isCurrencySwitcherActive;
    @SerializedName("is_order_tracking_active")
    @Expose
    public Boolean isOrderTrackingActive;
    @SerializedName("is_reward_points_active")
    @Expose
    public Boolean isRewardPointsActive;
    @SerializedName("is_abandoned_cart_active")
    @Expose
    public Boolean isAbandonedCartActive;
    @SerializedName("is_guest_checkout_active")
    @Expose
    public Boolean isGuestCheckoutActive;
    @SerializedName("is_wpml_active")
    @Expose
    public Boolean isWpmlActive;
    @SerializedName("price_formate_options")
    @Expose
    public PriceFormateOptions priceFormateOptions;
    @SerializedName("ios_app_url")
    @Expose
    public String iosAppUrl;
    @SerializedName("site_language")
    @Expose
    public String siteLanguage;
    @SerializedName("app_color")
    @Expose
    public AppColor appColor;
    @SerializedName("is_rtl")
    @Expose
    public Boolean isRtl;
    @SerializedName("clockIcon")
    @Expose
    public String clockIcon;
    @SerializedName("notificationIcon")
    @Expose
    public String notificationIcon;
    @SerializedName("popular_products")
    @Expose
    public List<Product> popularProducts = null;

    @SerializedName("products_view_orders")
    @Expose
    public List<ProductsViewOrder> productsViewOrders = null;

    public MainCategory mainCategoryObject;

    @SerializedName("scheduled_sale_products")
    @Expose
    public ScheduledSaleProducts scheduledSaleProducts;

    @SerializedName("wpml_languages")
    @Expose
    public List<WpmlLanguage> wpmlLanguages = null;

    @SerializedName("checkout_redirect_url")
    @Expose
    public List<String> checkoutRedirectUrl;

    @SerializedName("checkout_cancel_url")
    @Expose
    public List<String> checkoutCancelUrl;

    @SerializedName("is_app_validation")
    @Expose
    public Boolean isAppValidation;


    public Home withScheduledSaleProducts(ScheduledSaleProducts scheduledSaleProducts) {
        this.scheduledSaleProducts = scheduledSaleProducts;
        return this;
    }

    public MainCategory getInstranceMainCategory() {
        mainCategoryObject = new MainCategory();
        return mainCategoryObject;
    }

    public Home withProductsCarousel(ProductsCarousel productsCarousel) {
        this.productsCarousel = productsCarousel;
        return this;
    }

    public Home withAppLogoLight(String appLogoLight) {
        this.appLogoLight = appLogoLight;
        return this;
    }

    public Home withAppLogo(String appLogo) {
        this.appLogo = appLogo;
        return this;
    }

    public Home withMainCategory(List<MainCategory> mainCategory) {
        this.mainCategory = mainCategory;
        return this;
    }

    public Home withMainSlider(List<MainSlider> mainSlider) {
        this.mainSlider = mainSlider;
        return this;
    }

    public Home withCategoryBanners(List<CategoryBanner> categoryBanners) {
        this.categoryBanners = categoryBanners;
        return this;
    }

    public Home withBannerAd(List<BannerAd> bannerAd) {
        this.bannerAd = bannerAd;
        return this;
    }

    public Home withStaticPage(StaticPage staticPage) {
        this.staticPage = staticPage;
        return this;
    }

    public Home withInfoPages(List<InfoPage> infoPages) {
        this.infoPages = infoPages;
        return this;
    }

    public Home withPgsAppSocialLinks(PgsAppSocialLinks pgsAppSocialLinks) {
        this.pgsAppSocialLinks = pgsAppSocialLinks;
        return this;
    }

    public Home withFeatureBoxStatus(String featureBoxStatus) {
        this.featureBoxStatus = featureBoxStatus;
        return this;
    }

    public Home withFeatureBoxHeading(String featureBoxHeading) {
        this.featureBoxHeading = featureBoxHeading;
        return this;
    }

    public Home withFeatureBox(List<FeatureBox> featureBox) {
        this.featureBox = featureBox;
        return this;
    }

    public Home withPgsAppContactInfo(PgsAppContactInfo pgsAppContactInfo) {
        this.pgsAppContactInfo = pgsAppContactInfo;
        return this;
    }

    public Home withAllCategories(List<AllCategory> allCategories) {
        this.allCategories = allCategories;
        return this;
    }

    public Home withIsWishlistActive(Boolean isWishlistActive) {
        this.isWishlistActive = isWishlistActive;
        return this;
    }

    public Home withIsCurrencySwitcherActive(Boolean isCurrencySwitcherActive) {
        this.isCurrencySwitcherActive = isCurrencySwitcherActive;
        return this;
    }

    public Home withIsOrderTrackingActive(Boolean isOrderTrackingActive) {
        this.isOrderTrackingActive = isOrderTrackingActive;
        return this;
    }

    public Home withIsRewardPointsActive(Boolean isRewardPointsActive) {
        this.isRewardPointsActive = isRewardPointsActive;
        return this;
    }

    public Home withIsAbandonedCartActive(Boolean isAbandonedCartActive) {
        this.isAbandonedCartActive = isAbandonedCartActive;
        return this;
    }

    public Home withIsGuestCheckoutActive(Boolean isGuestCheckoutActive) {
        this.isGuestCheckoutActive = isGuestCheckoutActive;
        return this;
    }

    public Home withIsWpmlActive(Boolean isWpmlActive) {
        this.isWpmlActive = isWpmlActive;
        return this;
    }

    public Home withPriceFormateOptions(PriceFormateOptions priceFormateOptions) {
        this.priceFormateOptions = priceFormateOptions;
        return this;
    }

    public Home withIosAppUrl(String iosAppUrl) {
        this.iosAppUrl = iosAppUrl;
        return this;
    }

    public Home withSiteLanguage(String siteLanguage) {
        this.siteLanguage = siteLanguage;
        return this;
    }

    public Home withAppColor(AppColor appColor) {
        this.appColor = appColor;
        return this;
    }

    public Home withIsRtl(Boolean isRtl) {
        this.isRtl = isRtl;
        return this;
    }


    public class ScheduledSaleProducts {

        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("products")
        @Expose
        public List<Product> products = null;

        public ScheduledSaleProducts withStatus(String status) {
            this.status = status;
            return this;
        }

        public ScheduledSaleProducts withProducts(List<Product> products) {
            this.products = products;
            return this;
        }

    }

    public class DealLife {

        @SerializedName("hours")
        @Expose
        public String hours;
        @SerializedName("minutes")
        @Expose
        public String minutes;
        @SerializedName("seconds")
        @Expose
        public String seconds;

        public DealLife withHours(String hours) {
            this.hours = hours;
            return this;
        }

        public DealLife withMinutes(String minutes) {
            this.minutes = minutes;
            return this;
        }

        public DealLife withSeconds(String seconds) {
            this.seconds = seconds;
            return this;
        }

    }

    public class PopularProduct {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("price_html")
        @Expose
        public String priceHtml;
        @SerializedName("rating")
        @Expose
        public String rating;

        public PopularProduct withId(String id) {
            this.id = id;
            return this;
        }

        public PopularProduct withTitle(String title) {
            this.title = title;
            return this;
        }

        public PopularProduct withImage(String image) {
            this.image = image;
            return this;
        }

        public PopularProduct withPriceHtml(String priceHtml) {
            this.priceHtml = priceHtml;
            return this;
        }


        public PopularProduct withRating(String rating) {
            this.rating = rating;
            return this;
        }

    }

    public class AllCategory {

        @SerializedName("description")
        @Expose
        public String description;
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("image")
        @Expose
        public Image image;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("parent")
        @Expose
        public Integer parent;
        @SerializedName("slug")
        @Expose
        public String slug;

        public AllCategory withDescription(String description) {
            this.description = description;
            return this;
        }

        public AllCategory withId(Integer id) {
            this.id = id;
            return this;
        }

        public AllCategory withImage(Image image) {
            this.image = image;
            return this;
        }

        public AllCategory withName(String name) {
            this.name = name;
            return this;
        }

        public AllCategory withParent(Integer parent) {
            this.parent = parent;
            return this;
        }

        public AllCategory withSlug(String slug) {
            this.slug = slug;
            return this;
        }

    }


    public class AppColor {

        @SerializedName("header_color")
        @Expose
        public String headerColor;
        @SerializedName("primary_color")
        @Expose
        public String primaryColor;
        @SerializedName("secondary_color")
        @Expose
        public String secondaryColor;

        public AppColor withHeaderColor(String headerColor) {
            this.headerColor = headerColor;
            return this;
        }

        public AppColor withPrimaryColor(String primaryColor) {
            this.primaryColor = primaryColor;
            return this;
        }

        public AppColor withSecondaryColor(String secondaryColor) {
            this.secondaryColor = secondaryColor;
            return this;
        }

    }


    public class BannerAd {

        @SerializedName("banner_ad_image_url")
        @Expose
        public String bannerAdImageUrl;
        @SerializedName("banner_ad_image_id")
        @Expose
        public String bannerAdImageId;
        @SerializedName("banner_ad_cat_id")
        @Expose
        public String bannerAdCatId;

        public BannerAd withBannerAdImageUrl(String bannerAdImageUrl) {
            this.bannerAdImageUrl = bannerAdImageUrl;
            return this;
        }

        public BannerAd withBannerAdImageId(String bannerAdImageId) {
            this.bannerAdImageId = bannerAdImageId;
            return this;
        }

        public BannerAd withBannerAdCatId(String bannerAdCatId) {
            this.bannerAdCatId = bannerAdCatId;
            return this;
        }

    }


    public class CategoryBanner {

        @SerializedName("cat_banners_image_id")
        @Expose
        public String catBannersImageId;
        @SerializedName("cat_banners_image_url")
        @Expose
        public String catBannersImageUrl;
        @SerializedName("cat_banners_cat_id")
        @Expose
        public String catBannersCatId;
        @SerializedName("cat_banners_title")
        @Expose
        public String catBannersTitle;

        public CategoryBanner withCatBannersImageId(String catBannersImageId) {
            this.catBannersImageId = catBannersImageId;
            return this;
        }

        public CategoryBanner withCatBannersImageUrl(String catBannersImageUrl) {
            this.catBannersImageUrl = catBannersImageUrl;
            return this;
        }

        public CategoryBanner withCatBannersCatId(String catBannersCatId) {
            this.catBannersCatId = catBannersCatId;
            return this;
        }

        public CategoryBanner withCatBannersTitle(String catBannersTitle) {
            this.catBannersTitle = catBannersTitle;
            return this;
        }

    }

    public class FeatureBox {

        @SerializedName("feature_image_id")
        @Expose
        public String featureImageId;
        @SerializedName("feature_title")
        @Expose
        public String featureTitle;
        @SerializedName("feature_content")
        @Expose
        public String featureContent;
        @SerializedName("feature_image")
        @Expose
        public String featureImage;

        public FeatureBox withFeatureImageId(String featureImageId) {
            this.featureImageId = featureImageId;
            return this;
        }

        public FeatureBox withFeatureTitle(String featureTitle) {
            this.featureTitle = featureTitle;
            return this;
        }

        public FeatureBox withFeatureContent(String featureContent) {
            this.featureContent = featureContent;
            return this;
        }

        public FeatureBox withFeatureImage(String featureImage) {
            this.featureImage = featureImage;
            return this;
        }

    }

    public class FeatureProducts {

        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("orderby")
        @Expose
        public String orderby;
        @SerializedName("order")
        @Expose
        public String order;
        @SerializedName("screen_order")
        @Expose
        public Integer screenOrder;
        @SerializedName("products")
        @Expose
        public List<Product> products = null;

        public FeatureProducts withStatus(String status) {
            this.status = status;
            return this;
        }

        public FeatureProducts withTitle(String title) {
            this.title = title;
            return this;
        }

        public FeatureProducts withOrderby(String orderby) {
            this.orderby = orderby;
            return this;
        }

        public FeatureProducts withOrder(String order) {
            this.order = order;
            return this;
        }

        public FeatureProducts withScreenOrder(Integer screenOrder) {
            this.screenOrder = screenOrder;
            return this;
        }

        public FeatureProducts withProducts(List<Product> products) {
            this.products = products;
            return this;
        }

    }

    public class Image {

        @SerializedName("src")
        @Expose
        public String src;

        public Image withSrc(String src) {
            this.src = src;
            return this;
        }

    }


    public class InfoPage {

        @SerializedName("info_pages_page_id")
        @Expose
        public String infoPagesPageId;

        public InfoPage withInfoPagesPageId(String infoPagesPageId) {
            this.infoPagesPageId = infoPagesPageId;
            return this;
        }

    }


    public class MainCategory {

        @SerializedName("main_cat_id")
        @Expose
        public String mainCatId;
        @SerializedName("main_cat_name")
        @Expose
        public String mainCatName;
        @SerializedName("main_cat_image")
        @Expose
        public String mainCatImage;

        public MainCategory withMainCatId(String mainCatId) {
            this.mainCatId = mainCatId;
            return this;
        }

        public MainCategory withMainCatName(String mainCatName) {
            this.mainCatName = mainCatName;
            return this;
        }

        public MainCategory withMainCatImage(String mainCatImage) {
            this.mainCatImage = mainCatImage;
            return this;
        }

    }


    public class MainSlider {

        @SerializedName("upload_image_id")
        @Expose
        public String uploadImageId;
        @SerializedName("slider_cat_id")
        @Expose
        public String sliderCatId;
        @SerializedName("upload_image_url")
        @Expose
        public String uploadImageUrl;

        public MainSlider withUploadImageId(String uploadImageId) {
            this.uploadImageId = uploadImageId;
            return this;
        }

        public MainSlider withSliderCatId(String sliderCatId) {
            this.sliderCatId = sliderCatId;
            return this;
        }

        public MainSlider withUploadImageUrl(String uploadImageUrl) {
            this.uploadImageUrl = uploadImageUrl;
            return this;
        }

    }


    public class PgsAppContactInfo {

        @SerializedName("address_line_1")
        @Expose
        public String addressLine1;
        @SerializedName("address_line_2")
        @Expose
        public String addressLine2;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("phone")
        @Expose
        public String phone;
        @SerializedName("whatsapp_no")
        @Expose
        public String whatsappNo;
        @SerializedName("whatsapp_floating_button")
        @Expose
        public String whatsappFloatingButton;

        public PgsAppContactInfo withAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        public PgsAppContactInfo withAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public PgsAppContactInfo withEmail(String email) {
            this.email = email;
            return this;
        }

        public PgsAppContactInfo withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public PgsAppContactInfo withWhatsappNo(String whatsappNo) {
            this.whatsappNo = whatsappNo;
            return this;
        }

        public PgsAppContactInfo withwhatsappFloatingButton(String whatsappFloatingButton) {
            this.whatsappFloatingButton = whatsappFloatingButton;
            return this;
        }

    }


    public class PgsAppSocialLinks {

        @SerializedName("facebook")
        @Expose
        public String facebook;
        @SerializedName("twitter")
        @Expose
        public String twitter;
        @SerializedName("linkedin")
        @Expose
        public String linkedin;
        @SerializedName("google_plus")
        @Expose
        public String googlePlus;
        @SerializedName("pinterest")
        @Expose
        public String pinterest;
        @SerializedName("instagram")
        @Expose
        public String instagram;

        public PgsAppSocialLinks withFacebook(String facebook) {
            this.facebook = facebook;
            return this;
        }

        public PgsAppSocialLinks withTwitter(String twitter) {
            this.twitter = twitter;
            return this;
        }

        public PgsAppSocialLinks withLinkedin(String linkedin) {
            this.linkedin = linkedin;
            return this;
        }

        public PgsAppSocialLinks withGooglePlus(String googlePlus) {
            this.googlePlus = googlePlus;
            return this;
        }

        public PgsAppSocialLinks withPinterest(String pinterest) {
            this.pinterest = pinterest;
            return this;
        }

        public PgsAppSocialLinks withInstagram(String instagram) {
            this.instagram = instagram;
            return this;
        }

    }


    public class PopularProducts {

        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("orderby")
        @Expose
        public String orderby;
        @SerializedName("order")
        @Expose
        public String order;
        @SerializedName("screen_order")
        @Expose
        public Integer screenOrder;
        @SerializedName("products")
        @Expose
        public List<Product> products = null;

        public PopularProducts withStatus(String status) {
            this.status = status;
            return this;
        }

        public PopularProducts withTitle(String title) {
            this.title = title;
            return this;
        }

        public PopularProducts withOrderby(String orderby) {
            this.orderby = orderby;
            return this;
        }

        public PopularProducts withOrder(String order) {
            this.order = order;
            return this;
        }

        public PopularProducts withScreenOrder(Integer screenOrder) {
            this.screenOrder = screenOrder;
            return this;
        }

        public PopularProducts withProducts(List<Product> products) {
            this.products = products;
            return this;
        }

    }





    public class PriceFormateOptions {

        @SerializedName("decimal_separator")
        @Expose
        public String decimalSeparator;
        @SerializedName("thousand_separator")
        @Expose
        public String thousandSeparator;
        @SerializedName("decimals")
        @Expose
        public Integer decimals;
        @SerializedName("currency_pos")
        @Expose
        public String currencyPos;
        @SerializedName("currency_symbol")
        @Expose
        public String currencySymbol;
        @SerializedName("currency_code")
        @Expose
        public String currencyCode;

        public PriceFormateOptions withDecimalSeparator(String decimalSeparator) {
            this.decimalSeparator = decimalSeparator;
            return this;
        }

        public PriceFormateOptions withThousandSeparator(String thousandSeparator) {
            this.thousandSeparator = thousandSeparator;
            return this;
        }

        public PriceFormateOptions withDecimals(Integer decimals) {
            this.decimals = decimals;
            return this;
        }

        public PriceFormateOptions withCurrencyPos(String currencyPos) {
            this.currencyPos = currencyPos;
            return this;
        }

        public PriceFormateOptions withCurrencySymbol(String currencySymbol) {
            this.currencySymbol = currencySymbol;
            return this;
        }

        public PriceFormateOptions withCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
            return this;
        }

    }


    public class Product {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("deal_life")
        @Expose
        public DealLife dealLife;
        @SerializedName("price_html")
        @Expose
        public String priceHtml;

        @SerializedName("percentage")
        @Expose
        public Integer percentage;
        @SerializedName("rating")
        @Expose
        public String rating;
        @SerializedName("tax_status")
        @Expose
        public String taxStatus;
        @SerializedName("tax_class")
        @Expose
        public String taxClass;

        public Product withId(String id) {
            this.id = id;
            return this;
        }

        public Product withTitle(String title) {
            this.title = title;
            return this;
        }

        public Product withImage(String image) {
            this.image = image;
            return this;
        }

        public Product withDealLife(DealLife dealLife) {
            this.dealLife = dealLife;
            return this;
        }

        public Product withPriceHtml(String priceHtml) {
            this.priceHtml = priceHtml;
            return this;
        }


        public Product withPercentage(Integer percentage) {
            this.percentage = percentage;
            return this;
        }

        public Product withRating(String rating) {
            this.rating = rating;
            return this;
        }

    }


    public class ProductsCarousel {

        @SerializedName("feature_products")
        @Expose
        public FeatureProducts featureProducts;
        @SerializedName("recent_products")
        @Expose
        public RecentProducts recentProducts;
        @SerializedName("special_deal_products")
        @Expose
        public SpecialDealProducts specialDealProducts;
        @SerializedName("popular_products")
        @Expose
        public PopularProducts popularProducts;
        @SerializedName("top_rated_products")
        @Expose
        public TopRatedProducts topRatedProducts;

        public ProductsCarousel withFeatureProducts(FeatureProducts featureProducts) {
            this.featureProducts = featureProducts;
            return this;
        }

        public ProductsCarousel withRecentProducts(RecentProducts recentProducts) {
            this.recentProducts = recentProducts;
            return this;
        }

        public ProductsCarousel withTopRatedProducts(TopRatedProducts topRatedProducts) {
            this.topRatedProducts = topRatedProducts;
            return this;
        }

        public ProductsCarousel withSpecialDealProducts(SpecialDealProducts specialDealProducts) {
            this.specialDealProducts = specialDealProducts;
            return this;
        }

        public ProductsCarousel withPopularProducts(PopularProducts popularProducts) {
            this.popularProducts = popularProducts;
            return this;
        }

    }

    public class TopRatedProducts {

        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("screen_order")
        @Expose
        public int screenOrder;
        @SerializedName("products")
        @Expose
        public List<Product> products = null;

        public TopRatedProducts withStatus(String status) {
            this.status = status;
            return this;
        }

        public TopRatedProducts withTitle(String title) {
            this.title = title;
            return this;
        }

        public TopRatedProducts withScreenOrder(int screenOrder) {
            this.screenOrder = screenOrder;
            return this;
        }

        public TopRatedProducts withProducts(List<Product> products) {
            this.products = products;
            return this;
        }
    }

    public class RecentProducts {

        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("orderby")
        @Expose
        public String orderby;
        @SerializedName("order")
        @Expose
        public String order;
        @SerializedName("screen_order")
        @Expose
        public Integer screenOrder;
        @SerializedName("products")
        @Expose
        public List<Product> products = null;

        public RecentProducts withStatus(String status) {
            this.status = status;
            return this;
        }

        public RecentProducts withTitle(String title) {
            this.title = title;
            return this;
        }

        public RecentProducts withOrderby(String orderby) {
            this.orderby = orderby;
            return this;
        }

        public RecentProducts withOrder(String order) {
            this.order = order;
            return this;
        }

        public RecentProducts withScreenOrder(Integer screenOrder) {
            this.screenOrder = screenOrder;
            return this;
        }

        public RecentProducts withProducts(List<Product> products) {
            this.products = products;
            return this;
        }

    }


    public class SpecialDealProducts {

        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("orderby")
        @Expose
        public String orderby;
        @SerializedName("order")
        @Expose
        public String order;
        @SerializedName("screen_order")
        @Expose
        public Integer screenOrder;
        @SerializedName("products")
        @Expose
        public List<Product> products = null;

        public SpecialDealProducts withStatus(String status) {
            this.status = status;
            return this;
        }

        public SpecialDealProducts withTitle(String title) {
            this.title = title;
            return this;
        }

        public SpecialDealProducts withOrderby(String orderby) {
            this.orderby = orderby;
            return this;
        }

        public SpecialDealProducts withOrder(String order) {
            this.order = order;
            return this;
        }

        public SpecialDealProducts withScreenOrder(Integer screenOrder) {
            this.screenOrder = screenOrder;
            return this;
        }

        public SpecialDealProducts withProducts(List<Product> products) {
            this.products = products;
            return this;
        }

    }

    public class StaticPage {

        @SerializedName("about_us")
        @Expose
        public String aboutUs;
        @SerializedName("terms_of_use")
        @Expose
        public String termsOfUse;
        @SerializedName("privacy_policy")
        @Expose
        public String privacyPolicy;

        public StaticPage withAboutUs(String aboutUs) {
            this.aboutUs = aboutUs;
            return this;
        }

        public StaticPage withTermsOfUse(String termsOfUse) {
            this.termsOfUse = termsOfUse;
            return this;
        }

        public StaticPage withPrivacyPolicy(String privacyPolicy) {
            this.privacyPolicy = privacyPolicy;
            return this;
        }

    }

    public class ProductsViewOrder {

        @SerializedName("name")
        @Expose
        public String name;

    }


    public class WpmlLanguage {

        @SerializedName("code")
        @Expose
        public String code;
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("native_name")
        @Expose
        public String nativeName;
        @SerializedName("active")
        @Expose
        public int active;
        @SerializedName("default_locale")
        @Expose
        public String defaultLocale;
        @SerializedName("translated_name")
        @Expose
        public String translatedName;
        @SerializedName("language_code")
        @Expose
        public String languageCode;
        @SerializedName("disp_language")
        @Expose
        public String dispLanguage;
        @SerializedName("site_language")
        @Expose
        public String siteLanguage;
        @SerializedName("is_rtl")
        @Expose
        public boolean isRtl;

        public WpmlLanguage withCode(String code) {
            this.code = code;
            return this;
        }

        public WpmlLanguage withId(String id) {
            this.id = id;
            return this;
        }

        public WpmlLanguage withNativeName(String nativeName) {
            this.nativeName = nativeName;
            return this;
        }

        public WpmlLanguage withActive(int active) {
            this.active = active;
            return this;
        }

        public WpmlLanguage withDefaultLocale(String defaultLocale) {
            this.defaultLocale = defaultLocale;
            return this;
        }

        public WpmlLanguage withTranslatedName(String translatedName) {
            this.translatedName = translatedName;
            return this;
        }

        public WpmlLanguage withLanguageCode(String languageCode) {
            this.languageCode = languageCode;
            return this;
        }

        public WpmlLanguage withDispLanguage(String dispLanguage) {
            this.dispLanguage = dispLanguage;
            return this;
        }

        public WpmlLanguage withSiteLanguage(String siteLanguage) {
            this.siteLanguage = siteLanguage;
            return this;
        }

        public WpmlLanguage withIsRtl(boolean isRtl) {
            this.isRtl = isRtl;
            return this;
        }

    }
}