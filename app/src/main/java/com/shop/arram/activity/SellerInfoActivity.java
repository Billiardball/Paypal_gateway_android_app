package com.shop.arram.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ciyashop.library.apicall.PostApi;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shop.arram.R;
import com.shop.arram.adapter.SellerProductAdapter;
import com.shop.arram.adapter.SellerReviewAdapter;
import com.shop.arram.customview.GridSpacingItemDecoration;
import com.shop.arram.customview.textview.TextViewBold;
import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.customview.textview.TextViewMedium;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.helper.DatabaseHelper;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.model.CategoryList;
import com.shop.arram.model.SellerData;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class SellerInfoActivity extends BaseActivity implements OnItemClickListner, OnResponseListner {

    @BindView(R.id.rvCategoryGrid)
    RecyclerView rvCategoryGrid;

    @BindView(R.id.rvReview)
    RecyclerView rvReview;

    @BindView(R.id.ivBannerImage)
    ImageView ivBannerImage;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.civProfileImage)
    CircleImageView civProfileImage;

    @BindView(R.id.tvName)
    TextViewBold tvName;

    @BindView(R.id.tvRating)
    TextViewMedium tvRating;

    @BindView(R.id.tvStoreDescription)
    TextViewLight tvStoreDescription;

    @BindView(R.id.tvSellerAddress)
    TextViewLight tvSellerAddress;

    @BindView(R.id.llReview)
    LinearLayout llReview;

    @BindView(R.id.tvContactSeller)
    TextViewLight tvContactSeller;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;

    @BindView(R.id.nsvSellerData)
    NestedScrollView nsvSellerData;

    @BindView(R.id.tvViewAllReview)
    TextViewRegular tvViewAllReview;

    private SellerProductAdapter sellerProductAdapter;
    private SellerReviewAdapter sellerReviewAdapter;
    private String sellerInfo;
    private String sellerid;
    private int page = 1;

    private boolean loading = true;
    private DatabaseHelper databaseHelper;

    List<CategoryList> list = new ArrayList<>();
    JSONArray jsonArray = new JSONArray();

    int pastVisiblesItems, visibleItemCount, totalItemCount;

    boolean setNoItemFound;

    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private State mCurrentState = State.IDLE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_info);
        ButterKnife.bind(this);
        setScreenLayoutDirection();
        databaseHelper = new DatabaseHelper(this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                setGridRecycleView();
            }
        });

        collapsing_toolbar.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.HEADER_COLOR, Constant.PRIMARY_COLOR)));
        collapsing_toolbar.setTitle(getResources().getString(R.string.Seller_Information));
        collapsing_toolbar.setContentScrimColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        collapsing_toolbar.setExpandedTitleColor(getResources().getColor(R.color.transparent_white));
        collapsing_toolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        collapsing_toolbar.setStatusBarScrimColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        initCollapsingToolbar();
        setReviewData();
        sellerid = getIntent().getExtras().getString(RequestParamUtils.ID);
        getSellerInfo(true);
        setThemeColor();
    }


    public void setThemeColor() {
        tvName.setTextColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        tvContactSeller.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));
        tvViewAllReview.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));
    }


    public void getSellerInfo(boolean dialog) {
        if (Utils.isInternetConnected(this)) {
            if (dialog) {
                showProgress("");
            }
            PostApi postApi = new PostApi(SellerInfoActivity.this, RequestParamUtils.seller, this, getlanuage());
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(RequestParamUtils.PAGE, page);
                jsonObject.put(RequestParamUtils.sellerId, sellerid);

                postApi.callPostApi(new URLS().SELLER + getPreferences().getString(RequestParamUtils.CurrencyText, ""), jsonObject.toString());
            } catch (Exception e) {
                Log.e("Json Exception", e.getMessage());
            }

        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }


    }

    private void initCollapsingToolbar() {
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

                if (i == 0) {
                    if (mCurrentState != State.EXPANDED) {
                        toolbar.setBackgroundColor(Color.TRANSPARENT);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.TRANSPARENT);
                        }
                    }
                    mCurrentState = State.EXPANDED;
                } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
                    if (mCurrentState != State.COLLAPSED) {
                        toolbar.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
                        }
                    }
                    mCurrentState = State.COLLAPSED;
                } else {
                    if (mCurrentState != State.IDLE) {
                        toolbar.setBackgroundColor(Color.TRANSPARENT);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.TRANSPARENT);
                        }
                    }
                    mCurrentState = State.IDLE;
                }
            }
        });
    }

    public void setGridRecycleView() {
        sellerProductAdapter = new SellerProductAdapter(this, this);
        final GridLayoutManager mLayoutManager = new GridLayoutManager(SellerInfoActivity.this, 2, LinearLayoutManager.VERTICAL, false);
        rvCategoryGrid.setLayoutManager(mLayoutManager);
        rvCategoryGrid.setAdapter(sellerProductAdapter);
        rvCategoryGrid.setNestedScrollingEnabled(false);
        rvCategoryGrid.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(3), true));
        nsvSellerData.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            if (setNoItemFound != true) {
                                loading = false;
                                page = page + 1;
                                getSellerInfo(false);
                            }
                        }
                    }
                }
            }
        });

    }

    public void setReviewData() {
        sellerReviewAdapter = new SellerReviewAdapter(this, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvReview.setLayoutManager(mLayoutManager);
        rvReview.setAdapter(sellerReviewAdapter);
        rvReview.setNestedScrollingEnabled(false);
    }

    @OnClick(R.id.tvContactSeller)
    public void tvContactSellerClick() {
        Intent intent = new Intent(this, ContactSellerActivity.class);
        intent.putExtra(RequestParamUtils.ID, sellerid);
        startActivity(intent);
    }

    @OnClick(R.id.tvViewAllReview)
    public void tvNewUserClick() {
        Intent intent = new Intent(SellerInfoActivity.this, SellerReviewActivity.class);
        intent.putExtra(RequestParamUtils.sellerInfo, sellerInfo);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position, String value, int outerPos) {

        try {
            String str = jsonArray.get(position).toString();

            JSONObject jsonObject = new JSONObject(str);

            JSONObject jsonObjectSeller = new JSONObject(sellerInfo);

            JSONObject jsonObjectSeller1 = jsonObjectSeller.getJSONObject(RequestParamUtils.sellerInfo);
            jsonObjectSeller1.put(RequestParamUtils.isSeller, true);

            jsonObject.put(RequestParamUtils.sellerInfo, jsonObjectSeller1);

            CategoryList categoryListRider = new Gson().fromJson(
                    jsonObject.toString(), new TypeToken<CategoryList>() {
                    }.getType());

            Constant.CATEGORYDETAIL = categoryListRider;
            Intent intent = new Intent(this, ProductDetailActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
    }

    @Override
    public void onResponse(String response, String methodName) {
        if (methodName.equals(RequestParamUtils.seller)) {

            dismissProgress();
            if (response != null && response.length() > 0) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (loading) {
                        Log.e("if ", "Called");
                        sellerInfo = response;

                        SellerData sellerDataRider = new Gson().fromJson(
                                response, new TypeToken<SellerData>() {
                                }.getType());


                        if (sellerDataRider.sellerInfo.bannerUrl != null && sellerDataRider.sellerInfo.bannerUrl.length() > 0) {
                            ivBannerImage.setVisibility(View.VISIBLE);
                            String bannerUrl = sellerDataRider.sellerInfo.bannerUrl.replace("\\", "");
                            Picasso.with(this).load(bannerUrl).error(R.drawable.male).into(ivBannerImage);
                        } else {
                            ivBannerImage.setVisibility(View.INVISIBLE);
                        }

                        if (sellerDataRider.sellerInfo.avatar != null && sellerDataRider.sellerInfo.avatar.length() > 0) {
                            civProfileImage.setVisibility(View.VISIBLE);
                            Picasso.with(this).load(sellerDataRider.sellerInfo.avatar.replace("\\", "")).into(civProfileImage);
                        } else {
                            ivBannerImage.setVisibility(View.INVISIBLE);
                        }


                        tvName.setText(sellerDataRider.sellerInfo.storeName);
                        tvRating.setText("" + sellerDataRider.sellerInfo.sellerRating.rating);

                        if (sellerDataRider.sellerInfo.storeDescription != null && sellerDataRider.sellerInfo.storeDescription.length() != 0) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                tvStoreDescription.setText(Html.fromHtml(sellerDataRider.sellerInfo.storeDescription, Html.FROM_HTML_MODE_COMPACT));

                            } else {
                                tvStoreDescription.setText(Html.fromHtml(sellerDataRider.sellerInfo.storeDescription));
                            }
                            tvStoreDescription.setVisibility(View.VISIBLE);
                        } else {
                            tvStoreDescription.setVisibility(View.GONE);
                        }

                        if (sellerDataRider.sellerInfo.sellerAddress.length() != 0) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                tvSellerAddress.setText(Html.fromHtml(sellerDataRider.sellerInfo.sellerAddress, Html.FROM_HTML_MODE_COMPACT));

                            } else {
                                tvSellerAddress.setText(Html.fromHtml(sellerDataRider.sellerInfo.sellerAddress));
                            }
                            tvSellerAddress.setVisibility(View.VISIBLE);
                        } else {
                            tvSellerAddress.setVisibility(View.GONE);
                        }

                        if (sellerDataRider.sellerInfo.contactSeller) {
                            tvContactSeller.setClickable(true);
                        } else {
                            tvContactSeller.setClickable(false);
                        }

                        if (sellerDataRider.sellerInfo.reviewList != null && sellerDataRider.sellerInfo.reviewList.size() > 0) {
                            sellerReviewAdapter.addAll(sellerDataRider.sellerInfo.reviewList);

                        } else {
                            llReview.setVisibility(View.GONE);
                        }

                        JSONArray jsonArray1 = jsonObject.getJSONArray(RequestParamUtils.products);

                        if (jsonArray1.length() > 0) {
                            jsonArray = concatArray(jsonArray, jsonArray1);
                            new setDataInRecycleview().execute(jsonArray1.toString());

                        } else {
                            setNoItemFound = true;
                        }

                    } else {
                        Log.e("else ", "Called");
                        JSONArray jsonArray1 = jsonObject.getJSONArray(RequestParamUtils.products);
                        if (jsonArray1.length() > 0) {
                            jsonArray = concatArray(jsonArray, jsonArray1);
                            new setDataInRecycleview().execute(jsonArray1.toString());
                        } else {
                            setNoItemFound = true;
                        }

                    }
                } catch (Exception e) {
                    Log.e(methodName + "Gson Exception is ", e.getMessage());
                }

                loading = true;
                nsvSellerData.setVisibility(View.VISIBLE);
            }
        }
    }


    public class setDataInRecycleview extends AsyncTask<String, String, List<CategoryList>> {


        @Override
        protected List<CategoryList> doInBackground(String... params) {
            Log.e("DoInBackground", "Called");
            list = new ArrayList<>();

            try {
                JSONArray array = new JSONArray(params[0]);

                for (int i = 0; i < array.length(); i++) {
                    String jsonResponse = array.get(i).toString();
                    CategoryList categoryListRider = new Gson().fromJson(
                            jsonResponse, new TypeToken<CategoryList>() {
                            }.getType());
                    list.add(categoryListRider);
                }
            } catch (JSONException e) {
                Log.e("Json Exception is ", e.getMessage());
            }

            return list;
        }


        @Override
        protected void onPostExecute(List<CategoryList> categoryLists) {
            super.onPostExecute(categoryLists);
            Log.e("On Post", "Called");
            sellerProductAdapter.addAll(categoryLists);
        }
    }

    private JSONArray concatArray(JSONArray arr1, JSONArray arr2)
            throws JSONException {
        JSONArray result = new JSONArray();
        for (int i = 0; i < arr1.length(); i++) {
            result.put(arr1.get(i));
        }
        for (int i = 0; i < arr2.length(); i++) {
            result.put(arr2.get(i));
        }
        return result;
    }

}
