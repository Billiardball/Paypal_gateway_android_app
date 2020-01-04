package com.shop.arram.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ciyashop.library.apicall.PostApi;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shop.arram.R;
import com.shop.arram.adapter.NotificationAdapter;
import com.shop.arram.customview.textview.TextViewBold;
import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.customview.textview.TextViewMedium;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.model.Notification;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;
import com.shop.arram.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationActivity extends BaseActivity implements OnItemClickListner, OnResponseListner {

    @BindView(R.id.rvNotification)
    RecyclerView rvNotification;

    @BindView(R.id.llEmpty)
    LinearLayout llEmpty;

    @BindView(R.id.tvEmptyTitle)
    TextViewBold tvEmptyTitle;

    @BindView(R.id.tvEmptyDesc)
    TextViewLight tvEmptyDesc;

    @BindView(R.id.tvContinueShopping)
    TextViewRegular tvContinueShopping;

    @BindView(R.id.tvDeleteAll)
    TextViewMedium tvDeleteAll;

    private NotificationAdapter notificationAdapter;

    List<Notification.Datum> list = new ArrayList<>();

    boolean fromDeleteAll = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        setToolbarTheme();
        setEmptyColor();
        setScreenLayoutDirection();
        showBackButton();
        settvTitle(getResources().getString(R.string.notification));
        hideSearchNotification();
        getNotification();
        setNotificationAdapter();
    }

    public void setNotificationAdapter() {
        notificationAdapter = new NotificationAdapter(this, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvNotification.setLayoutManager(mLayoutManager);
        rvNotification.setAdapter(notificationAdapter);
        rvNotification.setNestedScrollingEnabled(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onItemClick(int position, String value, int outerPos) {
        String userid = getPreferences().getString(RequestParamUtils.ID, "");

        try {
            JSONObject jsonObject = new JSONObject();

            int[] arr = new int[1];
            int str = Integer.parseInt(value);

            arr[0] = str;
            jsonObject.put(RequestParamUtils.push_meta_id, new JSONArray(arr));
            deleteNotification(jsonObject);
            if (outerPos - 1 == 0) {
                showEmpty();
            }

            if (list.get(position).notCode.equals("1")) {
                //Coupon
                Intent intent = new Intent(this, RewardsActivity.class);
                startActivity(intent);
            } else if (list.get(position).notCode.equals("2")) {
                //order
                Intent intent = new Intent(this, MyOrderActivity.class);
                startActivity(intent);
            }

        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.tvDeleteAll)
    public void tvDeleteAllClick() {
        try {
            JSONObject jsonObject = new JSONObject();

            int[] arr = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                int str = Integer.parseInt(list.get(i).pushMetaId);
                arr[i] = str;
            }

            fromDeleteAll = true;
            jsonObject.put(RequestParamUtils.push_meta_id, new JSONArray(arr));
            deleteNotification(jsonObject);
        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
    }

    public void getNotification() {
        if (Utils.isInternetConnected(this)) {
            showProgress("");
            PostApi postApi = new PostApi(this, RequestParamUtils.getNotification, this, getlanuage());

            try {
                JSONObject jsonObject = new JSONObject();

                String token = getPreferences().getString(RequestParamUtils.NOTIFICATION_TOKEN, "");

                jsonObject.put(RequestParamUtils.deviceToken, FirebaseInstanceId.getInstance().getToken());
                jsonObject.put(RequestParamUtils.deviceType, Constant.DEVICE_TYPE);

                postApi.callPostApi(new URLS().PUSH_NOTIFICATION, jsonObject.toString());

            } catch (Exception e) {
                Log.e("error", e.getMessage());
            }
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }


    }

    public void deleteNotification(JSONObject jsonObject) {
        if (Utils.isInternetConnected(this)) {
            showProgress("");

            PostApi postApi = new PostApi(this, RequestParamUtils.deleteNotifications, this, getlanuage());
            postApi.callPostApi(new URLS().DELETE_NOTIFICATIONS, jsonObject.toString());
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onResponse(final String response, String methodName) {

        if (methodName.equals(RequestParamUtils.getNotification)) {

            dismissProgress();
            if (response != null && response.length() > 0) {
                try {
                    final Notification notificationRider = new Gson().fromJson(
                            response, new TypeToken<Notification>() {
                            }.getType());

                    if (notificationRider.status.equals("success")) {
                        llEmpty.setVisibility(View.GONE);

                        list.addAll(notificationRider.data);
                        notificationAdapter.addAll(list);

                    } else {
                        showEmpty();
                    }
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                }
            } else {
                showEmpty();
            }
        } else if (methodName.equals(RequestParamUtils.deleteNotifications)) {

            dismissProgress();
            if (response != null && response.length() > 0) {
                try {

                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");
                    if (status.equals("success")) {

                        if (fromDeleteAll) {
                            list.clear();
                            notificationAdapter.notifyDataSetChanged();
                        }
                        fromDeleteAll = false;
                        if (notificationAdapter.getItemCount() == 0) {
                            showEmpty();
                        }
                    }
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                }
            }
        }

    }

    @OnClick(R.id.tvContinueShopping)
    public void tvContinueShoppingClick() {
        Intent i = new Intent(NotificationActivity.this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    public void showEmpty() {
        llEmpty.setVisibility(View.VISIBLE);
        tvEmptyTitle.setText(R.string.no_notification_yet);
    }
}