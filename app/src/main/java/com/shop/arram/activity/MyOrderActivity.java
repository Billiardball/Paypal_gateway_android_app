package com.shop.arram.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.shop.arram.R;
import com.shop.arram.adapter.MyOrderAdapter;
import com.shop.arram.customview.textview.TextViewBold;
import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.model.Orders;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.RequestParamUtils;
import com.shop.arram.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderActivity extends BaseActivity implements OnItemClickListner, OnResponseListner {

    @BindView(R.id.rvMyOrders)
    RecyclerView rvMyOrders;

    @BindView(R.id.llEmpty)
    LinearLayout llEmpty;

    @BindView(R.id.tvEmptyTitle)
    TextViewBold tvEmptyTitle;

    @BindView(R.id.tvEmptyDesc)
    TextViewLight tvEmptyDesc;

    @BindView(R.id.tvContinueShopping)
    TextViewRegular tvContinueShopping;
    List<Orders> list = new ArrayList<>();
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    Boolean setNoItemFound = false;
    private MyOrderAdapter myOrderAdapter;
    private int page = 1;
    private boolean loading = true;
    private boolean Splashscreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        setToolbarTheme();
        setScreenLayoutDirection();
        settvTitle(getResources().getString(R.string.my_orders));
        if (getPreferences().getString(RequestParamUtils.ID, "").equals("")) {
            Intent myOrderIntent = new Intent(this, LogInActivity.class);
            startActivity(myOrderIntent);
        }
        showBackButton();
        setEmptyColor();
        hideSearchNotification();
        setMyOrderAdapter();
        myOrder(true);

        Intent intent = getIntent();
        if (intent.hasExtra(RequestParamUtils.Splashscreen)) {
            Splashscreen = intent.getBooleanExtra(RequestParamUtils.Splashscreen, true);
        } else {
            Splashscreen = false;
        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Backpressed();
            }
        });
    }

    public void setMyOrderAdapter() {
        myOrderAdapter = new MyOrderAdapter(this, this);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMyOrders.setLayoutManager(mLayoutManager);
        rvMyOrders.setAdapter(myOrderAdapter);
        rvMyOrders.setNestedScrollingEnabled(false);
        rvMyOrders.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                                Log.e("End ", "Last Item Wow  and page no:- " + page);
                                myOrder(false);
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onItemClick(int position, String value, int outerPos) {

    }

    public void myOrder(boolean loaderShow) {

        if (Utils.isInternetConnected(this)) {
            if (loaderShow) {
                showProgress("");
            }
            PostApi postApi = new PostApi(this, RequestParamUtils.orders, this, getlanuage());
            JSONObject object = new JSONObject();
            try {
                object.put(RequestParamUtils.PAGE, page);
                String customerId = getPreferences().getString(RequestParamUtils.ID, "");

                object.put(RequestParamUtils.customer, customerId);
                postApi.callPostApi(new URLS().ORDERS + getPreferences().getString(RequestParamUtils.CurrencyText, ""), object.toString());

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onResponse(String response, String methodName) {
        if (methodName.equals(RequestParamUtils.orders)) {
            dismissProgress();
            if (response != null && response.length() > 0) {
                try {
                    //set call here
                    loading = true;

                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        String jsonResponse = jsonArray.get(i).toString();
                        Orders categoryListRider = new Gson().fromJson(
                                jsonResponse, new TypeToken<Orders>() {
                                }.getType());
                        list.add(categoryListRider);
                    }

                    myOrderAdapter.addAll(list);

                } catch (Exception e) {
                    try {
                        JSONObject object = new JSONObject(response);
                        if (object.getString("status").equals("error")) {
                            setNoItemFound = true;
                            if (rvMyOrders.getAdapter().getItemCount() == 0) {
                                llEmpty.setVisibility(View.VISIBLE);
                                tvEmptyTitle.setText(R.string.no_order_found);
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
                }
            } else {
                llEmpty.setVisibility(View.VISIBLE);
                tvEmptyTitle.setText(R.string.no_order_found);
                tvContinueShopping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
            }
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if (getPreferences().getString(RequestParamUtils.ID, "").equals("")) {
            finish();
        }
        page = 0;
        list = new ArrayList<>();
        myOrder(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Backpressed();
    }


    public void Backpressed() {
        if (Splashscreen) {
            Intent intent = new Intent(MyOrderActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            finish();
        }
    }
}
