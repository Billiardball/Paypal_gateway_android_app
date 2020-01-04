package com.shop.arram.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ciyashop.library.apicall.Ciyashop;
import com.ciyashop.library.apicall.PostApi;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.shop.arram.R;
import com.shop.arram.adapter.BannerViewPagerAdapter;
import com.shop.arram.adapter.CategoryAdapter;
import com.shop.arram.adapter.DynamicItemAdapter;
import com.shop.arram.adapter.HomeTopCategoryAdapter;
import com.shop.arram.adapter.NavigationDrawerAdapter;
import com.shop.arram.adapter.RecentViewAdapter;
import com.shop.arram.adapter.RecentlyAddedAdapter;
import com.shop.arram.adapter.SelectProductAdapter;
import com.shop.arram.adapter.SixReasonAdapter;
import com.shop.arram.adapter.SpecialOfferAdapter;
import com.shop.arram.adapter.TopRatedProductAdapter;
import com.shop.arram.adapter.VerticalBannerAdapter;
import com.shop.arram.customview.CustomLinearLayoutManager;
import com.shop.arram.customview.edittext.EditTextRegular;
import com.shop.arram.customview.textview.TextViewBold;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.helper.DatabaseHelper;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.model.CategoryList;
import com.shop.arram.model.Home;
import com.shop.arram.model.NavigationList;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;
import com.shop.arram.utils.Utils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements OnItemClickListner, OnResponseListner {

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @BindView(R.id.drawerListView)
    ListView drawerListView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    @BindView(R.id.rvTopCategory)
    RecyclerView rvTopCategory;
    @BindView(R.id.rvCategory)
    RecyclerView rvCategory;
    @BindView(R.id.rvVerticalBanner)
    RecyclerView rvVerticalBanner;
    @BindView(R.id.rvSixReason)
    RecyclerView rvSixReason;
    @BindView(R.id.rvRecentOffer)
    RecyclerView rvRecentOffer;
    @BindView(R.id.vpBanner)
    ViewPager vpBanner;
    @BindView(R.id.layoutDots)
    LinearLayout layoutDots;
    @BindView(R.id.ivBack)
    ImageView ivDrawer;
    @BindView(R.id.llMain)
    LinearLayout llMain;
    @BindView(R.id.llTopCategory)
    LinearLayout llTopCategory;
    @BindView(R.id.llBanner)
    LinearLayout llBanner;
    @BindView(R.id.llCategory)
    LinearLayout llCategory;
    @BindView(R.id.llVerticalBanner)
    LinearLayout llVerticalBanner;
    @BindView(R.id.llMainContent)
    LinearLayout llMainContent;
    @BindView(R.id.llSixReason)
    LinearLayout llSixReason;
    @BindView(R.id.llRecentView)
    LinearLayout llRecentView;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;
    @BindView(R.id.ablHome)
    AppBarLayout ablHome;

    @BindView(R.id.svHome)
    NestedScrollView svHome;

    @BindView(R.id.llBottomBar)
    LinearLayout llBottomBar;


    @BindView(R.id.ivNotification)
    ImageView ivNotification;
    @BindView(R.id.tvSixResonTitle)
    TextViewBold tvSixResonTitle;
    @BindView(R.id.llMenus)
    LinearLayout llMenus;
    @BindView(R.id.etSearch)
    EditTextRegular etSearch;
    Home homeRider;
    private TextView[] dots;
    private int[] layouts;
    private int currentPosition;
    private BannerViewPagerAdapter bannerViewPagerAdapter;
    private HomeTopCategoryAdapter homeTopCategoryAdapter;
    private CategoryAdapter categoryAdapter;
    private VerticalBannerAdapter verticalBannerAdapter;
    private SixReasonAdapter sixReasonAdapter;
    private RecentViewAdapter recentViewAdapter;
    private View listHeaderView;
    private TextViewRegular tvName;
    private boolean ishead = false;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationDrawerAdapter navigationDrawerAdapter;
    private DatabaseHelper databaseHelper;
    private boolean isAutoScroll = false, isSpecialDeal = false;
    private long mBackPressed;
    private Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
//set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                .setTitle("Please check this information")
//set message
                .setMessage(R.string.alert_dialog)
//set positive button
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        Intent intent = new Intent(HomeActivity.this , LogInActivity.class);
                        startActivity(intent);
                    }
                })
//set negative button
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        Toast.makeText(getApplicationContext(),"You don't have to read about guide",Toast.LENGTH_LONG).show();
                    }
                })
                .show();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        ButterKnife.bind(this);

        Constant.IS_RTL = getPreferences().getBoolean(Constant.RTL, false);
        setHomecolorTheme(getPreferences().getString(Constant.HEADER_COLOR, Constant.HEAD_COLOR));
        setScreenLayoutDirection();
        ivDrawer.setImageDrawable(getResources().getDrawable(R.drawable.ic_drawer));
        // Get token and Save Notification Token
        String token = FirebaseInstanceId.getInstance().getToken();
        SharedPreferences.Editor pre = getPreferences().edit();
        pre.putString(RequestParamUtils.NOTIFICATION_TOKEN, token);

        pre.commit();
        etSearch.setHint(getResources().getString(R.string.search));
        getHomeData();
        initDrawer();
        swipeView();
        settvImage();
        showNotification();
        showCart();
        setHomeCategoryData();
        setView();
        categoryData();
        verticalBannerData();
        setSixReasonAdapter();
        setRecentViewAdapter();
        getRecentData();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    // TODO: Remove this code after the UrlTable2 has been checked in.
    public void getRecentData() {
        databaseHelper = new DatabaseHelper(HomeActivity.this);
        List<CategoryList> recentList = databaseHelper.getRecentViewList();
        recentViewAdapter.addAll(recentList);
        if (recentList.size() > 0) {
            llRecentView.setVisibility(View.VISIBLE);
        } else {
            llRecentView.setVisibility(View.GONE);
        }
    }

    public void getHomeData() {
        if (Utils.isInternetConnected(this)) {
            showProgress("");
            try {
                PostApi postApi = new PostApi(this, RequestParamUtils.getHomeData, this, getlanuage());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(RequestParamUtils.appVersion, URLS.version);
//                Log.e("?lang=fr: ", getPreferences().getString(RequestParamUtils.DefaultLanguage, "") );


                postApi.callPostApi(new URLS().HOME +
                        getPreferences().getString(RequestParamUtils.CurrencyText, ""), jsonObject.toString());
            } catch (Exception e) {
                Log.e("Home", e.getMessage());
            }

        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }

    }

    public void swipeView() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getHomeData();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(R.color.colorPrimary,
                R.color.orange,
                R.color.red,
                R.color.blue
        );
    }

    public void initDrawer() {

        if (Constant.IS_RTL) {
            DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.FILL_PARENT);
            params.gravity = Gravity.RIGHT;
            drawerListView.setLayoutParams(params);
        } else {
            DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.FILL_PARENT);
            params.gravity = Gravity.LEFT;
            drawerListView.setLayoutParams(params);
        }


        LayoutInflater inflater = getLayoutInflater();
        listHeaderView = inflater.inflate(R.layout.nav_header, null, false);
        tvName = (TextViewRegular) listHeaderView.findViewById(R.id.tvName);

        if (!ishead) {
            drawerListView.addHeaderView(listHeaderView);
            ishead = true;
        }

        navigationDrawerAdapter = new NavigationDrawerAdapter(this);
        drawerListView.setAdapter(navigationDrawerAdapter);
        ivDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constant.IS_RTL) {
                    drawer_layout.openDrawer(Gravity.RIGHT);
                } else {
                    drawer_layout.openDrawer(Gravity.LEFT);
                }
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this, drawer_layout, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                selectItemFragment(position - 1);
            }
        });

        setBottomBar("home", svHome);
    }

    private void selectItemFragment(int position) {

        if (position == -1) {

        } else {
            if (position < navigationDrawerAdapter.getSeprater()) {
                Intent intent = new Intent(this, CategoryListActivity.class);
                intent.putExtra(RequestParamUtils.CATEGORY, navigationDrawerAdapter.getList().get(position).mainCatId);
                intent.putExtra(RequestParamUtils.IS_WISHLIST_ACTIVE, Constant.IS_WISH_LIST_ACTIVE);
                startActivity(intent);
            } else if (position == navigationDrawerAdapter.getSeprater()) {
                Intent intent = new Intent(this, SearchCategoryListActivity.class);
                startActivity(intent);
            } else {
                selectlocalFragment(navigationDrawerAdapter.getList().get(position).mainCatName);

            }
        }
        drawerListView.setItemChecked(position, true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                drawer_layout.closeDrawer(drawerListView);

            }
        }, 200);
    }


    public void selectlocalFragment(String name) {

        if (name.equals(getResources().getString(R.string.notification))) {
            Intent notificationIntent = new Intent(HomeActivity.this, NotificationActivity.class);
            startActivity(notificationIntent);
        } else if (name.equals(getResources().getString(R.string.my_reward))) {
            Intent rewardIntent = new Intent(HomeActivity.this, RewardsActivity.class);
            startActivity(rewardIntent);
        } else if (name.equals(getResources().getString(R.string.my_cart))) {
            Intent cartIntent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(cartIntent);
        } else if (name.equals(getResources().getString(R.string.my_wish_list))) {
            Intent wishListIntent = new Intent(HomeActivity.this, WishListActivity.class);
            startActivity(wishListIntent);
        } else if (name.equals(getResources().getString(R.string.my_account))) {
            Intent accountIntent = new Intent(HomeActivity.this, AccountActivity.class);
            startActivity(accountIntent);
        } else if (name.equals(getResources().getString(R.string.my_orders))) {
            Intent myOrderIntent = new Intent(HomeActivity.this, MyOrderActivity.class);
            startActivity(myOrderIntent);
        }
    }

    public void setHomeCategoryData() {
        homeTopCategoryAdapter = new HomeTopCategoryAdapter(this, this);
        CustomLinearLayoutManager mLayoutManager = new CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvTopCategory.setLayoutManager(mLayoutManager);
        rvTopCategory.setAdapter(homeTopCategoryAdapter);
        rvTopCategory.setNestedScrollingEnabled(false);
        ViewCompat.setNestedScrollingEnabled(rvTopCategory, false);
        rvTopCategory.setHasFixedSize(true);
        rvTopCategory.setItemViewCacheSize(20);
        rvTopCategory.setDrawingCacheEnabled(true);
        rvTopCategory.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    public void categoryData() {
        categoryAdapter = new CategoryAdapter(this, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvCategory.setLayoutManager(mLayoutManager);
        rvCategory.setAdapter(categoryAdapter);
        rvCategory.setNestedScrollingEnabled(false);
        ViewCompat.setNestedScrollingEnabled(rvCategory, false);
        rvCategory.setHasFixedSize(true);
        rvCategory.setItemViewCacheSize(20);
        rvCategory.setDrawingCacheEnabled(true);
        rvCategory.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    public void verticalBannerData() {
        verticalBannerAdapter = new VerticalBannerAdapter(this, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvVerticalBanner.setLayoutManager(mLayoutManager);
        rvVerticalBanner.setAdapter(verticalBannerAdapter);
        rvVerticalBanner.setNestedScrollingEnabled(false);
        ViewCompat.setNestedScrollingEnabled(rvVerticalBanner, false);
        rvVerticalBanner.setHasFixedSize(true);
        rvVerticalBanner.setItemViewCacheSize(20);
        rvVerticalBanner.setDrawingCacheEnabled(true);
        rvVerticalBanner.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        rvVerticalBanner.getRecycledViewPool().setMaxRecycledViews(0, 0);
    }


    public void setSixReasonAdapter() {
        sixReasonAdapter = new SixReasonAdapter(this, this);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rvSixReason.setLayoutManager(mLayoutManager);
        rvSixReason.setAdapter(sixReasonAdapter);
        rvSixReason.setNestedScrollingEnabled(false);
        ViewCompat.setNestedScrollingEnabled(rvSixReason, false);
        rvSixReason.setHasFixedSize(true);
        rvSixReason.setItemViewCacheSize(20);
        rvSixReason.setDrawingCacheEnabled(true);
        rvSixReason.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    public void setRecentViewAdapter() {
        recentViewAdapter = new RecentViewAdapter(this, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvRecentOffer.setLayoutManager(mLayoutManager);
        rvRecentOffer.setAdapter(recentViewAdapter);
        rvRecentOffer.setNestedScrollingEnabled(false);
        ViewCompat.setNestedScrollingEnabled(rvRecentOffer, false);
        rvRecentOffer.setHasFixedSize(true);
        rvRecentOffer.setItemViewCacheSize(20);
        rvRecentOffer.setDrawingCacheEnabled(true);
        rvRecentOffer.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        rvRecentOffer.getRecycledViewPool().setMaxRecycledViews(0, 0);
    }


    private void setView() {
        bannerViewPagerAdapter = new BannerViewPagerAdapter(this);
        vpBanner.setAdapter(bannerViewPagerAdapter);
        vpBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position, vpBanner.getAdapter().getCount());
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void autoScroll() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (currentPosition == bannerViewPagerAdapter.getCount() - 1) {
                            currentPosition = 0;
                        } else {
                            currentPosition = currentPosition + 1;
                        }
                        vpBanner.setCurrentItem(currentPosition);
                        addBottomDots(currentPosition, bannerViewPagerAdapter.getCount());
                        autoScroll();
                    }
                }, 6000);

            }
        }, 1000);
    }

    private void addBottomDots(int currentPage, int lenght) {
        layoutDots.removeAllViews();
        dots = new TextView[lenght];

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.gray));
            layoutDots.addView(dots[i]);
        }

        if (dots.length > 0 && dots.length >= currentPage) {

            dots[currentPage].setTextColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        }

    }


    @Override
    public void onItemClick(int position, String value, int outerPos) {

    }

    @OnClick(R.id.etSearch)
    public void etSearchClick() {
        Intent intent = new Intent(HomeActivity.this, SearchFromHomeActivity.class);
        startActivity(intent);
    }


    @Override
    public void onResponse(final String response, String methodName) {


        if (methodName.equals(RequestParamUtils.getHomeData)) {
            if (response != null && response.length() > 0) {
                swipeContainer.setRefreshing(false);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    //Convert json response into gson and made model class
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    homeRider = gson.fromJson(
                            jsonObject.toString(), new TypeToken<Home>() {
                            }.getType());


                    runOnUiThread(new Runnable() {
                        @SuppressLint("NewApi")
                        @Override
                        public void run() {

                            llMain.setVisibility(View.VISIBLE);
                            llBottomBar.setVisibility(View.VISIBLE);

                            if (homeRider != null) {
                                if (homeRider.isAppValidation != null) {
                                    new Ciyashop(HomeActivity.this).setFlag(homeRider.isAppValidation, false);
                                }

                                //set all constatnt value from the respones
                                setConstantValue();

                                //set theme color from the reponse
                                setThemeIconColor();

                                //set  all color from the response into preferences
                                setColorPreferences(homeRider.appColor.primaryColor, homeRider.appColor.secondaryColor, homeRider.appColor.headerColor);

                                //set color into toolbar of home activity
                                setHomecolorTheme(getPreferences().getString(Constant.HEADER_COLOR, Constant.HEAD_COLOR));

                                //set lungage into local
                                setLocale(homeRider.siteLanguage);

                                //CheckOut URLs get
                                if (homeRider.checkoutRedirectUrl != null && homeRider.checkoutRedirectUrl.size() > 0) {
                                    setCheckoutURL(homeRider.checkoutRedirectUrl);
                                }

                             /*   if (homeRider.checkoutCancelUrl != null && homeRider.checkoutCancelUrl.size() > 0) {
                                    setCancelUrl(homeRider.checkoutCancelUrl);
                                }*/

                                //set crousol product
                                if (homeRider.productsCarousel != null) {
                                    AddNewCorosol();
                                } else {
                                    llMenus.removeAllViews();
                                    if (homeRider.popularProducts != null) {
                                        AddPopularProducts();
                                    }

                                    if (homeRider.scheduledSaleProducts != null) {
                                        AddSpecialDealProducts();
                                    }
                                }

                                //set lungage list  from the response
                                if (homeRider.isWpmlActive != null && homeRider.isWpmlActive) {
                                    if (homeRider.wpmlLanguages != null) {
                                        Constant.LANGUAGELIST = (homeRider.wpmlLanguages);
                                    }
                                } else {
                                    SharedPreferences.Editor pre = getPreferences().edit();
                                    pre.putString(RequestParamUtils.LANGUAGE, "");
                                    pre.commit();
                                }

                                //set currency list from resposne
                                setCurrency(response);

                            }


                            for (int i = 0; i < homeRider.allCategories.size(); i++) {
                                if (homeRider.allCategories.get(i).name.equals("Uncategorized")) {
                                    homeRider.allCategories.remove(i);
                                }
                            }

                            Constant.MAINCATEGORYLIST.clear();
                            Constant.MAINCATEGORYLIST.addAll(homeRider.allCategories);

                            //set main category list from response
                            setMainCategoryList(homeRider.mainCategory);

                            //set banner slider from response
                            setSliderList(homeRider.mainSlider);

                            //set top banner
                            setCategoryList(homeRider.categoryBanners);

                            //set vertical banner
                            setVerticalBannerList(homeRider.bannerAd);

                            //set feature box or six reason from response
                            if (homeRider.featureBoxStatus != null && homeRider.featureBoxStatus.equals("enable")) {
                                setSixReasonrList(homeRider.featureBox, homeRider.featureBoxHeading);
                            } else {
                                llSixReason.setVisibility(View.GONE);
                            }

                            SharedPreferences.Editor editor = getPreferences().edit();
                            editor.putString(Constant.APPLOGO, homeRider.appLogo);
                            editor.putString(Constant.APPLOGO_LIGHT, homeRider.appLogoLight);
                            editor.commit();
                            settvImage();
                            Picasso.with(HomeActivity.this).load(homeRider.notificationIcon).into(ivNotification);


                        }


                    });


                    dismissProgress();

                } catch (Exception e) {
                    dismissProgress();
                    Log.e(methodName + "Gson Exception is ", e.getMessage());
                }
            }

            getRecentData();
        }

    }


/*    private void setCancelUrl(List<String> checkoutCancelUrl) {
        Constant.CancelUrl.clear();
        Constant.CancelUrl = new ArrayList<>();
        Constant.CancelUrl.addAll(checkoutCancelUrl);
    }*/

    private void setCheckoutURL(List<String> checkoutRedirectUrl) {
        Constant.CheckoutURL.clear();
        Constant.CheckoutURL = new ArrayList<>();
        Constant.CheckoutURL.addAll(checkoutRedirectUrl);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setThemeIconColor() {

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor(getPreferences().getString(Constant.PRIMARY_COLOR, Constant.PRIMARY_COLOR)));
        gradientDrawable.setCornerRadius(5);
    }


    public void setConstantValue() {
        if (homeRider.pgsAppContactInfo != null) {
            if (homeRider.pgsAppContactInfo.addressLine1 != null) {
                Constant.ADDRESS_LINE1 = homeRider.pgsAppContactInfo.addressLine1;
            }
            if (homeRider.pgsAppContactInfo.addressLine2 != null) {
                Constant.ADDRESS_LINE2 = homeRider.pgsAppContactInfo.addressLine2;
            }
            if (homeRider.pgsAppContactInfo.email != null) {
                Constant.EMAIL = homeRider.pgsAppContactInfo.email;
            }
            if (homeRider.pgsAppContactInfo.phone != null) {
                Constant.PHONE = homeRider.pgsAppContactInfo.phone;
            }
            if (homeRider.pgsAppContactInfo.whatsappNo != null) {
                Constant.WHATSAPP = homeRider.pgsAppContactInfo.whatsappNo;
            }
            if (homeRider.pgsAppContactInfo.whatsappFloatingButton != null) {
                Constant.WHATSAPPENABLE = homeRider.pgsAppContactInfo.whatsappFloatingButton;
                //Constant.WHATSAPPENABLE = "disable";
            }
            if (homeRider.priceFormateOptions.currencyCode != null) {
                Constant.CURRENCYCODE = Html.fromHtml(homeRider.priceFormateOptions.currencyCode).toString();
            }

        }
        if (homeRider.isCurrencySwitcherActive != null) {
            Constant.IS_CURRENCY_SWITCHER_ACTIVE = homeRider.isCurrencySwitcherActive;
        }
        if (homeRider.isGuestCheckoutActive != null) {
            Constant.IS_GUEST_CHECKOUT_ACTIVE = homeRider.isGuestCheckoutActive;
        }
        if (homeRider.isWpmlActive != null) {
            Constant.IS_WPML_ACTIVE = homeRider.isWpmlActive;
        }
        if (homeRider.isOrderTrackingActive != null) {
            Constant.IS_ORDER_TRACKING_ACTIVE = homeRider.isOrderTrackingActive;
        }

        if (homeRider.isRewardPointsActive != null) {
            Constant.IS_REWARD_POINT_ACTIVE = homeRider.isRewardPointsActive;
        }

        if (homeRider.isWishlistActive != null) {
            Constant.IS_WISH_LIST_ACTIVE = homeRider.isWishlistActive;
        }
        if (homeRider.isYithFeaturedVideoActive != null) {
            Constant.IS_YITH_FEATURED_VIDEO_ACTIVE = homeRider.isYithFeaturedVideoActive;
        }

        if (homeRider.pgsAppSocialLinks != null) {
            Constant.SOCIALLINK = homeRider.pgsAppSocialLinks;
        }
        if (homeRider.priceFormateOptions != null) {
            if (homeRider.priceFormateOptions.currencyPos != null) {
                Constant.CURRENCYSYMBOLPOSTION = homeRider.priceFormateOptions.currencyPos;
            }
            if (homeRider.priceFormateOptions.currencySymbol != null) {
                Constant.CURRENCYSYMBOL = Html.fromHtml(homeRider.priceFormateOptions.currencySymbol).toString();
                SharedPreferences.Editor pre = getPreferences().edit();
                pre.putString(Constant.CURRENCYSYMBOLPref, Html.fromHtml(homeRider.priceFormateOptions.currencySymbol).toString());
                pre.commit();
            }
            if (homeRider.priceFormateOptions.decimals != null) {
                Constant.Decimal = homeRider.priceFormateOptions.decimals;
            }
            if (homeRider.priceFormateOptions.decimalSeparator != null) {
                Constant.DECIMALSEPRETER = homeRider.priceFormateOptions.decimalSeparator;
            }
            if (homeRider.priceFormateOptions.thousandSeparator != null) {
                Constant.THOUSANDSSEPRETER = homeRider.priceFormateOptions.thousandSeparator;
            }
        }

        if (homeRider.appLogo != null) {
            Constant.APPLOGO = homeRider.appLogo;
        }

        if (homeRider.appLogoLight != null) {
            Constant.APPLOGO_LIGHT = homeRider.appLogoLight;
        }

        if (getPreferences().getString(RequestParamUtils.LANGUAGE, "").equals("")) {
            if (homeRider.isRtl != null) {
                Constant.IS_RTL = homeRider.isRtl;
                getPreferences().edit().putBoolean(Constant.RTL, Constant.IS_RTL).commit();
            }

        } else {
            Constant.IS_RTL = getPreferences().getBoolean(Constant.RTL, false);
        }

    }

    public void setColorPreferences(String primaryColor, String secondaryColor, String HeaderColor) {
        String colorSubString = (primaryColor.substring(primaryColor.lastIndexOf("#") + 1));
        SharedPreferences.Editor editor = getPreferences().edit();

        if (!primaryColor.equals("")) {
            editor.putString(Constant.APP_COLOR, primaryColor);
            editor.putString(Constant.APP_TRANSPARENT, "#aa" + colorSubString);
            editor.putString(Constant.APP_TRANSPARENT_VERY_LIGHT, "#44" + colorSubString);
        }
        if (!secondaryColor.equals("")) {
            editor.putString(Constant.SECOND_COLOR, secondaryColor);
        }
        if (!HeaderColor.equals("")) {
            editor.putString(Constant.HEADER_COLOR, HeaderColor);
        }
        editor.commit();
    }

    public void setMainCategoryList(List<Home.MainCategory> list) {
        if (list != null) {
            List<Home.MainCategory> mainCategoryList = new ArrayList<>();
            if (list.size() > 0) {
                if (list.size() > 5) {
                    for (int i = 0; i <= 5; i++) {
                        mainCategoryList.add(list.get(i));
                    }
                } else {

                    mainCategoryList.addAll(list);
                }
                Home.MainCategory mainCategory = new Home().getInstranceMainCategory();
                mainCategory.mainCatName = getString(R.string.more);
                mainCategoryList.add(mainCategory);
                homeTopCategoryAdapter.addAll(mainCategoryList);
                llTopCategory.setVisibility(View.VISIBLE);


            } else {
                llTopCategory.setVisibility(View.GONE);
            }
            navigationDrawerAdapter.setSepreter(mainCategoryList.size() - 1);
            List<Home.MainCategory> drawerList = new ArrayList<Home.MainCategory>();
            drawerList.addAll(mainCategoryList);
            for (int i = 0; i < NavigationList.getInstance(this).getImageList().size(); i++) {
                Home.MainCategory mainCategory = new Home().getInstranceMainCategory();
                mainCategory.mainCatName = NavigationList.getInstance(this).getTitleList().get(i);
                mainCategory.mainCatImage = NavigationList.getInstance(this).getImageList().get(i) + "";
                mainCategory.mainCatId = i + "";
                drawerList.add(mainCategory);
            }
            navigationDrawerAdapter.addAll(drawerList);
        } else {
            llTopCategory.setVisibility(View.GONE);
            navigationDrawerAdapter.setSepreter(0);
            List<Home.MainCategory> drawerList = new ArrayList<Home.MainCategory>();
            for (int i = 0; i < NavigationList.getInstance(this).getImageList().size(); i++) {
                Home.MainCategory mainCategory = new Home().getInstranceMainCategory();
                mainCategory.mainCatName = NavigationList.getInstance(this).getTitleList().get(i);
                mainCategory.mainCatImage = NavigationList.getInstance(this).getImageList().get(i) + "";
                mainCategory.mainCatId = i + "";
                drawerList.add(mainCategory);
            }
            navigationDrawerAdapter.addAll(drawerList);
        }

    }

    public void setSliderList(List<Home.MainSlider> list) {
        if (list != null) {
            if (list.size() > 0) {
                bannerViewPagerAdapter.addAll(list);
                if (!isAutoScroll) {
                    addBottomDots(0, vpBanner.getAdapter().getCount());
                    autoScroll();
                    isAutoScroll = true;
                }
                llBanner.setVisibility(View.VISIBLE);
            } else {
                llBanner.setVisibility(View.GONE);
            }
        } else {
            llBanner.setVisibility(View.GONE);
        }

    }

    public void setCategoryList(List<Home.CategoryBanner> list) {
        if (list != null) {
            if (list.size() > 0) {
                categoryAdapter.addAll(list);
                llCategory.setVisibility(View.VISIBLE);
            } else {
                llCategory.setVisibility(View.GONE);
            }
        } else {
            llCategory.setVisibility(View.GONE);
        }
    }

    public void setVerticalBannerList(List<Home.BannerAd> list) {
        if (list != null) {
            if (list.size() > 0) {
                verticalBannerAdapter.addAll(list);
                llVerticalBanner.setVisibility(View.VISIBLE);
            } else {
                llVerticalBanner.setVisibility(View.GONE);
            }
        } else {
            llVerticalBanner.setVisibility(View.GONE);
        }

    }

    public void setSixReasonrList(List<Home.FeatureBox> list, String title) {
        if (list != null) {
            if (list.size() > 0) {
                sixReasonAdapter.addAll(list);

                if (list.size() == 1) {
                    if (list.get(0).featureContent.equals("")) {
                        llSixReason.setVisibility(View.GONE);
                    } else {

                        llSixReason.setVisibility(View.VISIBLE);

                    }
                } else {
                    llSixReason.setVisibility(View.VISIBLE);
                }

            } else {
                llSixReason.setVisibility(View.GONE);
            }

            tvSixResonTitle.setText(title);
        } else {
            llSixReason.setVisibility(View.GONE);
        }

    }

    private void setTimer(final TextView tvTimer, final LinearLayout llSpecialOffer) {
        if (handler != null) {
            handler.removeCallbacks(null);

        } else {
            handler = new Handler();
        }

        final int delay = 1000; //milliseconds
        handler.postDelayed(new Runnable() {
            public void run() {
                //do something
                handler.postDelayed(this, delay);
                long time = convertInMilisecond(tvTimer.getText().toString()) - 1000;
                if (time == 0) {
                    llSpecialOffer.setVisibility(View.GONE);
                } else {
                    tvTimer.setText(convertInTimeFormet(time));
                }
            }
        }, delay);
    }

    private long convertInMilisecond(String time) {

        String[] tokens = time.split(":");
        int secondsToMs = Integer.parseInt(tokens[2]) * 1000;
        int minutesToMs = Integer.parseInt(tokens[1]) * 60000;
        int hoursToMs = Integer.parseInt(tokens[0]) * 3600000;
        long total = secondsToMs + minutesToMs + hoursToMs;
        return total;
    }

    private String convertInTimeFormet(long millis) {
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        return hms;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getRecentData();
        showCart();
        if (Constant.IS_CURRENCY_SET) {
            getHomeData();
            databaseHelper.clearRecentItem();
            databaseHelper.clearCart();
            Constant.IS_CURRENCY_SET = false;
        }
    }


    public void AddNewCorosol() {

        llMenus.removeAllViews();

        if (homeRider.productsViewOrders != null && homeRider.productsViewOrders.size() > 0) {

            for (int i = 0; i < homeRider.productsViewOrders.size(); i++) {

                if (homeRider.productsViewOrders.get(i).name.equals(RequestParamUtils.recentProducts)) {
                    AddRecentProducts();
                }
                if (homeRider.productsViewOrders.get(i).name.equals(RequestParamUtils.specialDealProducts)) {
                    AddSpecialDealProducts();
                }
                if (homeRider.productsViewOrders.get(i).name.equals(RequestParamUtils.featureProducts)) {
                    AddFeatureProducts();
                }
                if (homeRider.productsViewOrders.get(i).name.equals(RequestParamUtils.popularProducts)) {
                    AddPopularProducts();
                }
                if (homeRider.productsViewOrders.get(i).name.equals(RequestParamUtils.TOPRATEDPRODUCT)) {
                    AddTopRatedProducts();
                }
            }
        }
    }

    public void AddFeatureProducts() {

        if (homeRider.productsCarousel.featureProducts != null && homeRider.productsCarousel.featureProducts.status.equals("enable") && homeRider.productsCarousel.featureProducts.products.size() > 0) {

            SelectProductAdapter selectProductAdapter;
            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.dynamic_mostpopuler, null);
            TextView tvProductName = (TextView) view.findViewById(R.id.tvProductName);
            TextView tvViewAll = (TextView) view.findViewById(R.id.tvViewAll);
            RecyclerView rvProducts = (RecyclerView) view.findViewById(R.id.rvProducts);
            selectProductAdapter = new SelectProductAdapter(HomeActivity.this, HomeActivity.this);
            selectProductAdapter.addAll(homeRider.productsCarousel.featureProducts.products);
            rvProducts.setHasFixedSize(true);
            rvProducts.setNestedScrollingEnabled(false);
            GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
            rvProducts.setLayoutManager(mLayoutManager);
      /*  RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvProducts.setLayoutManager(mLayoutManager);*/
            rvProducts.setAdapter(selectProductAdapter);
            if (!homeRider.productsCarousel.featureProducts.title.isEmpty()) {
                tvProductName.setText(homeRider.productsCarousel.featureProducts.title);
            } else {
                tvProductName.setText(getResources().getString(R.string.featureProducts));
            }
            tvViewAll.setText(getResources().getString(R.string.view_all));
            tvViewAll.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));

            tvViewAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(HomeActivity.this, CategoryListActivity.class);
                    intent.putExtra(RequestParamUtils.FEATURE, true);
                    startActivity(intent);
                }
            });
            llMenus.addView(view);

        }

    }

    public void AddRecentProducts() {

        if (homeRider.productsCarousel.recentProducts != null && homeRider.productsCarousel.recentProducts.status.equals("enable") && homeRider.productsCarousel.recentProducts.products.size() > 0) {
            RecentlyAddedAdapter selectProductAdapter;
            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.dynamic_mostpopuler, null);
            TextView tvProductName = (TextView) view.findViewById(R.id.tvProductName);
            TextView tvViewAll = (TextView) view.findViewById(R.id.tvViewAll);
            RecyclerView rvProducts = (RecyclerView) view.findViewById(R.id.rvProducts);
            selectProductAdapter = new RecentlyAddedAdapter(HomeActivity.this, HomeActivity.this);

            rvProducts.setHasFixedSize(true);
            rvProducts.setNestedScrollingEnabled(false);
            GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
            rvProducts.setLayoutManager(mLayoutManager);
      /*  RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvProducts.setLayoutManager(mLayoutManager);*/
            rvProducts.setAdapter(selectProductAdapter);
            selectProductAdapter.addAll(homeRider.productsCarousel.recentProducts.products);
            if (!homeRider.productsCarousel.recentProducts.title.isEmpty()) {
                tvProductName.setText(homeRider.productsCarousel.recentProducts.title);
            } else {
                tvProductName.setText(getResources().getString(R.string.recently_view_product));
            }
            tvViewAll.setText(getResources().getString(R.string.view_all));
            tvViewAll.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));
            tvViewAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, CategoryListActivity.class);
                    intent.putExtra(RequestParamUtils.POSITION, 0);
                    startActivity(intent);
                }
            });
            llMenus.addView(view);
        }
    }

    public void AddTopRatedProducts() {

        if (homeRider.productsCarousel.topRatedProducts != null && homeRider.productsCarousel.topRatedProducts.status.equals("enable") && homeRider.productsCarousel.topRatedProducts.products.size() > 0) {
            TopRatedProductAdapter topRatedProductAdapter;
            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.dynamic_mostpopuler, null);
            TextView tvProductName = view.findViewById(R.id.tvProductName);
            TextView tvViewAll = view.findViewById(R.id.tvViewAll);
            RecyclerView rvProducts = view.findViewById(R.id.rvProducts);
            topRatedProductAdapter = new TopRatedProductAdapter(HomeActivity.this, HomeActivity.this);
            topRatedProductAdapter.addAll(homeRider.productsCarousel.topRatedProducts.products);
            rvProducts.setHasFixedSize(true);
            rvProducts.setNestedScrollingEnabled(false);
            GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
            rvProducts.setLayoutManager(mLayoutManager);
            rvProducts.setAdapter(topRatedProductAdapter);
            if (!homeRider.productsCarousel.recentProducts.title.isEmpty()) {
                tvProductName.setText(homeRider.productsCarousel.topRatedProducts.title);
            } else {
                tvProductName.setText(getResources().getString(R.string.top_rated_product));
            }
            tvViewAll.setText(getResources().getString(R.string.view_all));
            tvViewAll.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));
            tvViewAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, CategoryListActivity.class);
                    intent.putExtra(RequestParamUtils.POSITION, 1);
                    intent.putExtra(RequestParamUtils.ORDER_BY, "rating");
                    startActivity(intent);
                }
            });
            llMenus.addView(view);
        }
    }

    private void AddPopularProducts() {
        DynamicItemAdapter mAdapter;
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dynamic_mostpopuler, null);
        TextView tvProductName = (TextView) view.findViewById(R.id.tvProductName);
        TextView tvViewAll = (TextView) view.findViewById(R.id.tvViewAll);
        RecyclerView rvProducts = (RecyclerView) view.findViewById(R.id.rvProducts);
        rvProducts.setHasFixedSize(true);
        rvProducts.setNestedScrollingEnabled(false);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rvProducts.setLayoutManager(mLayoutManager);
        tvViewAll.setText(getResources().getString(R.string.view_all));
        tvViewAll.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));

        if (homeRider.productsCarousel != null) {
            if (homeRider.productsCarousel.popularProducts != null &&
                    homeRider.productsCarousel.popularProducts.status != null &&
                    homeRider.productsCarousel.popularProducts.status.equals("enable") &&
                    homeRider.productsCarousel.popularProducts.products.size() > 0) {

                mAdapter = new DynamicItemAdapter(HomeActivity.this, homeRider.productsCarousel.popularProducts.products);

                rvProducts.setAdapter(mAdapter);

                if (homeRider.productsCarousel.popularProducts.title.length() > 0) {
                    tvProductName.setText(homeRider.productsCarousel.popularProducts.title);
                } else {
                    tvProductName.setText(getResources().getString(R.string.most_popular_poroducts));
                }
                tvViewAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(HomeActivity.this, CategoryListActivity.class);
                        intent.putExtra(RequestParamUtils.ORDER_BY, RequestParamUtils.popularity);
                        intent.putExtra(RequestParamUtils.POSITION, 2);
                        intent.putExtra(RequestParamUtils.IS_WISHLIST_ACTIVE, Constant.IS_WISH_LIST_ACTIVE);
                        startActivity(intent);
                    }
                });

                llMenus.addView(view);

            }

        } else {

            if (homeRider.popularProducts != null && homeRider.popularProducts.size() > 0) {
                mAdapter = new DynamicItemAdapter(HomeActivity.this, homeRider.popularProducts);

                rvProducts.setAdapter(mAdapter);
                tvProductName.setText(getResources().getString(R.string.most_popular_poroducts));
                tvViewAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(HomeActivity.this, CategoryListActivity.class);
                        intent.putExtra(RequestParamUtils.ORDER_BY, RequestParamUtils.popularity);
                        intent.putExtra(RequestParamUtils.POSITION, 2);
                        intent.putExtra(RequestParamUtils.IS_WISHLIST_ACTIVE, Constant.IS_WISH_LIST_ACTIVE);
                        startActivity(intent);
                    }
                });
                llMenus.addView(view);
            }


        }


    }


    private void AddSpecialDealProducts() {

        final SpecialOfferAdapter mAdapter;
        String timer = "";
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dynamic_spacialoffer, null);
        LinearLayout llSpecialOffer = (LinearLayout) view.findViewById(R.id.llSpecialOffer);
        ImageView ivTimer = (ImageView) view.findViewById(R.id.ivTimer);
        TextView tvDealOfdayTitle = (TextView) view.findViewById(R.id.tvDealOfdayTitle);
        TextView tvTimer = (TextView) view.findViewById(R.id.tvTimer);


        TextView tvViewAllSpecialDeal = (TextView) view.findViewById(R.id.tvViewAllSpecialDeal);
        tvViewAllSpecialDeal.setText(getResources().getString(R.string.view_all));
        RecyclerView rvSpecialOffer = (RecyclerView) view.findViewById(R.id.rvSpecialOffer);
        mAdapter = new SpecialOfferAdapter(HomeActivity.this, HomeActivity.this);
        rvSpecialOffer.setHasFixedSize(true);
        rvSpecialOffer.setNestedScrollingEnabled(false);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rvSpecialOffer.setLayoutManager(mLayoutManager);
        rvSpecialOffer.setAdapter(mAdapter);
        ivTimer.setImageResource(R.drawable.ic_watch);
        ivTimer.setColorFilter(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        gradientDrawable.setCornerRadius(5);


        tvViewAllSpecialDeal.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));


        if (homeRider.productsCarousel != null) {


            if (homeRider.productsCarousel.specialDealProducts.products != null && homeRider.productsCarousel.specialDealProducts.status.equals("enable") && homeRider.productsCarousel.specialDealProducts.products.size() > 0) {
                mAdapter.addAll(homeRider.productsCarousel.specialDealProducts.products);
                if (homeRider.productsCarousel.specialDealProducts.title.length() > 0) {
                    tvDealOfdayTitle.setText(homeRider.productsCarousel.specialDealProducts.title);
                } else {
                    tvDealOfdayTitle.setText(getResources().getString(R.string.special_offer));
                }
                if (homeRider.productsCarousel.specialDealProducts.products.size() > 0) {
                    timer = homeRider.productsCarousel.specialDealProducts.products.get(0).dealLife.hours + ":" + homeRider.productsCarousel.specialDealProducts.products.get(0).dealLife.minutes + ":" + homeRider.productsCarousel.specialDealProducts.products.get(0).dealLife.seconds;
                }

                tvTimer.setText(timer);


                setTimer(tvTimer, llSpecialOffer);


                tvViewAllSpecialDeal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String productid = "";
                        for (int i = 0; i < mAdapter.getList().size(); i++) {
                            if (productid.equals("")) {
                                productid = mAdapter.getList().get(i).id;
                            } else {
                                productid = productid + "," + mAdapter.getList().get(i).id;
                            }
                        }
                        Intent intent = new Intent(HomeActivity.this, CategoryListActivity.class);
                        intent.putExtra(RequestParamUtils.DEAL_OF_DAY, productid);
                        startActivity(intent);

                    }
                });

                llMenus.addView(view);
            }


        } else {

            if (homeRider.scheduledSaleProducts.products != null && homeRider.scheduledSaleProducts.products.size() > 0) {
                mAdapter.addAll(homeRider.scheduledSaleProducts.products);
                tvDealOfdayTitle.setText(getResources().getString(R.string.special_offer));

                if (homeRider.scheduledSaleProducts.products.size() > 0) {
                    timer = homeRider.scheduledSaleProducts.products.get(0).dealLife.hours + ":" + homeRider.scheduledSaleProducts.products.get(0).dealLife.minutes + ":" + homeRider.scheduledSaleProducts.products.get(0).dealLife.seconds;
                }

                tvTimer.setText(timer);


                setTimer(tvTimer, llSpecialOffer);


                tvViewAllSpecialDeal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String productid = "";
                        for (int i = 0; i < mAdapter.getList().size(); i++) {
                            if (productid.equals("")) {
                                productid = mAdapter.getList().get(i).id;
                            } else {
                                productid = productid + "," + mAdapter.getList().get(i).id;
                            }
                        }
                        Intent intent = new Intent(HomeActivity.this, CategoryListActivity.class);
                        intent.putExtra(RequestParamUtils.DEAL_OF_DAY, productid);
                        startActivity(intent);

                    }
                });
                llMenus.addView(view);
            }
        }


    }

    public void setCurrency(String response) {
        if (Constant.IS_CURRENCY_SWITCHER_ACTIVE) {
            try {
                JSONObject jsonObj = new JSONObject(response);
                JSONObject currency_switcher = jsonObj.getJSONObject(RequestParamUtils.currencySwitcher);
                Constant.CurrencyList = new ArrayList<>();
                JSONArray namearray = currency_switcher.names();  //<<< get all keys in JSONArray
                for (int i = 0; i < namearray.length(); i++) {
                    JSONObject c = currency_switcher.getJSONObject(namearray.get(i).toString());
                    String name = c.getString(RequestParamUtils.name);
                    String symbol = c.getString(RequestParamUtils.symbol);

                    JSONObject obj = new JSONObject();
                    obj.put(RequestParamUtils.NAME, name);
                    obj.put(RequestParamUtils.SYMBOL, symbol);

                    // adding contact to contact list
                    Constant.CurrencyList.add(String.valueOf(obj));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public void setLocale(String lang) {
        if (!getPreferences().getString(RequestParamUtils.LANGUAGE, "").equals("")) {
            lang = getPreferences().getString(RequestParamUtils.LANGUAGE, "");
        }
        String languageToLoad = lang; // your language
        if (lang.contains("-")) {
            String[] array = lang.split("-");
            if (array.length > 0) {
                languageToLoad = array[0];
            } else {
                languageToLoad = lang;
            }
        } else {
            languageToLoad = lang;
        }
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        if (!getPreferences().getString(RequestParamUtils.DEFAULTLANGUAGE, "").equals("") && !getPreferences().getString(RequestParamUtils.DEFAULTLANGUAGE, "").equals(languageToLoad)) {
            recreate();
            getPreferences().edit().putBoolean(RequestParamUtils.iSSITELANGUAGECALLED, false).commit();
        }
        if (getPreferences().getString(RequestParamUtils.LANGUAGE, "").isEmpty()) {
            if (!getPreferences().getBoolean(RequestParamUtils.iSSITELANGUAGECALLED, false)) {
                getPreferences().edit().putBoolean(RequestParamUtils.iSSITELANGUAGECALLED, true).commit();
                (HomeActivity.this).getPreferences().edit().putString(RequestParamUtils.DEFAULTLANGUAGE, languageToLoad).commit();
                recreate();
            }
        }
        setScreenLayoutDirection();
    }


    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Snackbar.make(llMain, getResources().getString(R.string.exitformapp), Snackbar.LENGTH_LONG).show();
        }
        mBackPressed = System.currentTimeMillis();
    }

}



