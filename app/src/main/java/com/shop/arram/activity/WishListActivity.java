package com.shop.arram.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ciyashop.library.apicall.PostApi;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.shop.arram.R;
import com.shop.arram.adapter.WishListAdapter;
import com.shop.arram.customview.textview.TextViewBold;
import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.helper.DatabaseHelper;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.model.CategoryList;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;
import com.shop.arram.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WishListActivity extends BaseActivity implements OnItemClickListner, OnResponseListner {

    @BindView(R.id.rvWishList)
    RecyclerView rvWishList;

    @BindView(R.id.llEmpty)
    LinearLayout llEmpty;

    @BindView(R.id.tvEmptyTitle)
    TextViewBold tvEmptyTitle;

    @BindView(R.id.tvEmptyDesc)
    TextViewLight tvEmptyDesc;

    @BindView(R.id.tvContinueShopping)
    TextViewRegular tvContinueShopping;

    @BindView(R.id.progress_wheel)
    ProgressWheel progress_wheel;

    @BindView(R.id.llProgress)
    LinearLayout llProgress;

    @BindView(R.id.svWishList)
    NestedScrollView svWishList;

    private WishListAdapter wishListAdapter;
    private int page = 1;
    private DatabaseHelper databaseHelper;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    boolean setNoItemFound;

    List<CategoryList> list = new ArrayList<>();
    private boolean loading = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        ButterKnife.bind(this);
        setScreenLayoutDirection();
        databaseHelper = new DatabaseHelper(this);
        settvTitle(getResources().getString(R.string.my_wish_list));
        showSearch();
        showNotification();
        setToolbarTheme();
        setEmptyColor();
        showBackButton();
        llProgress.setVisibility(View.GONE);
        if (Constant.IS_WISH_LIST_ACTIVE) {
            getWishList(true);
            setWishListAdapter();
        } else {
            llEmpty.setVisibility(View.VISIBLE);
        }

        setBottomBar("wishList",svWishList);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (Constant.IS_WISH_LIST_ACTIVE) {
            list = new ArrayList<>();
            getWishList(true);
            setWishListAdapter();
        } else {
            llEmpty.setVisibility(View.VISIBLE);
        }
    }

    public void getWishList(boolean dialog) {
        String id = null;
        List<String> wishList = databaseHelper.getWishList();
        if (wishList.size() > 0) {
            for (int i = 0; i < wishList.size(); i++) {
                if (id == null) {
                    id = wishList.get(i);
                } else {
                    id = id + "," + wishList.get(i);
                }
            }
            getWishListData(id, dialog);
        } else {
            llEmpty.setVisibility(View.VISIBLE);
        }
    }

    public void setWishListAdapter() {
        wishListAdapter = new WishListAdapter(this, this);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvWishList.setLayoutManager(mLayoutManager);
        rvWishList.setAdapter(wishListAdapter);
        rvWishList.setNestedScrollingEnabled(false);
        rvWishList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            if (setNoItemFound != true) {
                                loading = false;
                                page = page + 1;
                                llProgress.setVisibility(View.VISIBLE);
                                progress_wheel.setBarColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
                                Log.e("End ", "Last Item Wow  and page no:- " + page);
                                getWishList(false);
                            }
                        }
                    }
                }
            }
        });

    }

    public void getWishListData(String data, boolean dialog) {
        if (Utils.isInternetConnected(this)) {
            if (dialog) {
                showProgress("");
            }
            PostApi postApi = new PostApi(WishListActivity.this, RequestParamUtils.getWishListData, this, getlanuage());

            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(RequestParamUtils.PAGE, page);
                jsonObject.put(RequestParamUtils.INCLUDE, data);
                postApi.callPostApi(new URLS().PRODUCT_URL + getPreferences().getString(RequestParamUtils.CurrencyText, ""), jsonObject.toString());
            } catch (Exception e) {
                Log.e("Json Exception", e.getMessage());
            }

        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onItemClick(int position, String value, int outerPos) {

        String userid = getPreferences().getString(RequestParamUtils.ID, "");

        if (!userid.equals("")) {
            removeWishList(true, userid, position + "");
        } else {
            if (outerPos - 1 == 0) {
                noDataFound();
            }
        }
    }

    public void removeWishList(boolean isDialogShow, String userid, String productid) {
        if (Utils.isInternetConnected(this)) {
            if (isDialogShow) {
                showProgress("");
            }
            PostApi postApi = new PostApi(WishListActivity.this, RequestParamUtils.removeWishList, this, getlanuage());
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(RequestParamUtils.USER_ID, userid);
                jsonObject.put(RequestParamUtils.PRODUCT_ID, productid);
                postApi.callPostApi(new URLS().REMOVE_FROM_WISHLIST, jsonObject.toString());
            } catch (Exception e) {
                Log.e("Json Exception", e.getMessage());
            }

        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onResponse(String response, String methodName) {
        if (methodName.equals(RequestParamUtils.getWishListData)) {

            llProgress.setVisibility(View.GONE);

            loading = true;

            if (response != null && response.length() > 0) {
                try {

                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        String jsonResponse = jsonArray.get(i).toString();
                        CategoryList categoryListRider = new Gson().fromJson(
                                jsonResponse, new TypeToken<CategoryList>() {
                                }.getType());
                        list.add(categoryListRider);
                    }

                    wishListAdapter.addAll(list);
                    llEmpty.setVisibility(View.GONE);
                    dismissProgress();

                } catch (Exception e) {
                    try {
                        JSONObject object = new JSONObject(response);
                        if (object.getString("message").equals("No product found")) {
                            setNoItemFound = true;
                            if (rvWishList.getAdapter().getItemCount() == 0) {
                                noDataFound();
                                tvContinueShopping.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        finish();
                                    }
                                });
                            }
                        }
                    } catch (JSONException e1) {
                        Log.e("noproductJSONException", e1.getMessage());
                    }
                    Log.e(methodName + "Gson Exception is ", e.getMessage());
                    dismissProgress();
                }
            }
        } else if (methodName.equals("removeWishList")) {

            if (rvWishList.getAdapter().getItemCount() == 0) {
                noDataFound();
            }
            dismissProgress();
        }
    }

    public void noDataFound() {
        llEmpty.setVisibility(View.VISIBLE);
        tvEmptyTitle.setText(getString(R.string.no_product_found));
        tvEmptyDesc.setVisibility(View.GONE);
    }

    @OnClick(R.id.tvContinueShopping)
    public void tvContinueShoppingClick() {
        Intent i = new Intent(WishListActivity.this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

}


