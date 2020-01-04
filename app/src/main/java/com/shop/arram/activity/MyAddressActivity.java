package com.shop.arram.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
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
import com.shop.arram.customview.textview.TextViewBold;
import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.model.Customer;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;
import com.shop.arram.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAddressActivity extends BaseActivity implements OnResponseListner {

    @BindView(R.id.llBillingAddress)
    LinearLayout llBillingAddress;

    @BindView(R.id.llShippingAddress)
    LinearLayout llShippingAddress;

    @BindView(R.id.llNoBillingAddress)
    LinearLayout llNoBillingAddress;

    @BindView(R.id.llNoShippingAddress)
    LinearLayout llNoShippingAddress;

    @BindView(R.id.tvNoBillingAddressAdd)
    TextViewRegular tvNoBillingAddressAdd;

    @BindView(R.id.tvNoShippingAddressAdd)
    TextViewRegular tvNoShippingAddressAdd;

    @BindView(R.id.tvBillingName)
    TextViewBold tvBillingName;

    @BindView(R.id.tvShippingName)
    TextViewBold tvShippingName;

    @BindView(R.id.tvBillingAddress)
    TextViewLight tvBillingAddress;

    @BindView(R.id.tvShippingAddress)
    TextViewLight tvShippingAddress;

    @BindView(R.id.tvBillingPhoneNumber)
    TextViewLight tvBillingPhoneNumber;

    @BindView(R.id.tvShippingRemove)
    TextViewBold tvShippingRemove;

    @BindView(R.id.tvBillingRemove)
    TextViewBold tvBillingRemove;

    @BindView(R.id.tvShippingEdit)
    TextViewBold tvShippingEdit;

    @BindView(R.id.tvBillingEdit)
    TextViewBold tvBillingEdit;

    private Customer customer = new Customer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        ButterKnife.bind(this);
        setThemeColor();
        setToolbarTheme();
        setScreenLayoutDirection();
        settvTitle(getResources().getString(R.string.my_address));
        showBackButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("here", "in on resume");
        setAddress();
    }

    public void setThemeColor() {
        tvBillingRemove.setTextColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        tvShippingRemove.setTextColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        tvNoShippingAddressAdd.setTextColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        tvNoBillingAddressAdd.setTextColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
    }

    public void setAddress() {
        //set Address data
        String cust = getPreferences().getString(RequestParamUtils.CUSTOMER, "");
        customer = new Gson().fromJson(
                cust, new TypeToken<Customer>() {
                }.getType());

        if (customer.billing.phone.equals("") && customer.billing.firstName.equals("")
                && customer.billing.lastName.equals("") && customer.billing.address1.equals("") &&
                customer.billing.address2.equals("") && customer.billing.company.equals("") &&
                customer.billing.city.equals("") && customer.billing.state.equals("") &&
                customer.billing.postcode.equals("")) {
            llBillingAddress.setVisibility(View.GONE);
            llNoBillingAddress.setVisibility(View.VISIBLE);
        } else {
            llBillingAddress.setVisibility(View.VISIBLE);
            llNoBillingAddress.setVisibility(View.GONE);

            tvBillingPhoneNumber.setText(customer.billing.phone + "");

            String address1empty = customer.billing.address1.equals("") ? "" : ", ";
            String address2empty = customer.billing.address2.equals("") ? "" : ", ";
            String cityempty = customer.billing.city.equals("") ? "" : ", ";
            String stateempty = customer.billing.city.equals("") ? "" : ", ";
            String countryempty = customer.billing.country.equals("") ? "" : ", ";

            String address = customer.billing.address1 +
                    address1empty + customer.billing.address2 +
                    address2empty + customer.billing.city + cityempty +
                    customer.billing.state + stateempty + customer.billing.country +
                    countryempty + customer.billing.postcode;
            tvBillingAddress.setText(address);

            tvBillingName.setText(customer.billing.firstName + " " + customer.billing.lastName);
        }

        if (customer.shipping.firstName.equals("") &&
                customer.shipping.lastName.equals("") && customer.shipping.address1.equals("")
                && customer.shipping.address2.equals("") && customer.shipping.company.equals("")
                && customer.shipping.city.equals("") && customer.shipping.state.equals("")
                && customer.shipping.postcode.equals("")) {
            llShippingAddress.setVisibility(View.GONE);
            llNoShippingAddress.setVisibility(View.VISIBLE);
        } else {
            llShippingAddress.setVisibility(View.VISIBLE);
            llNoShippingAddress.setVisibility(View.GONE);

            String address1empty = customer.shipping.address1.equals("") ? "" : ", ";
            String address2empty = customer.shipping.address2.equals("") ? "" : ", ";
            String cityempty = customer.shipping.city.equals("") ? "" : ", ";
            String stateempty = customer.shipping.city.equals("") ? "" : ", ";
            String countryempty = customer.shipping.country.equals("") ? "" : ", ";

            String address = customer.shipping.address1 + address1empty + customer.shipping.address2 + address2empty + customer.shipping.city + cityempty + customer.shipping.state + stateempty + customer.shipping.country + countryempty + customer.shipping.postcode;
            tvShippingAddress.setText(address);

            tvShippingName.setText(customer.shipping.firstName + " " + customer.shipping.lastName);
        }

    }

    public void addBilling() {
        Intent intent = new Intent(MyAddressActivity.this, AddAddressActivity.class);
        intent.putExtra(RequestParamUtils.type, 0);
        startActivity(intent);
    }

    public void addShipping() {
        Intent intent = new Intent(MyAddressActivity.this, AddAddressActivity.class);
        intent.putExtra(RequestParamUtils.type, 1);
        startActivity(intent);
    }

    @OnClick(R.id.tvBillingEdit)
    public void tvBillingEditClick() {
        addBilling();
    }


    @OnClick(R.id.tvShippingEdit)
    public void tvShippingEditClick() {
        addShipping();
    }


    @OnClick(R.id.tvNoBillingAddressAdd)
    public void tvNoBillingAddressAddClick() {
        addBilling();
    }

    @OnClick(R.id.tvNoShippingAddressAdd)
    public void tvNoShippingAddressAddClick() {
        addShipping();
    }

    @OnClick(R.id.tvBillingRemove)
    public void tvBillingRemoveClick() {
        try {
            JSONObject main = new JSONObject();
            JSONObject jsonObject = new JSONObject();

            jsonObject.put(RequestParamUtils.address1, "");
            jsonObject.put(RequestParamUtils.address2, "");
            jsonObject.put(RequestParamUtils.city, "");
            jsonObject.put(RequestParamUtils.company, "");
            jsonObject.put(RequestParamUtils.country, "");
            jsonObject.put(RequestParamUtils.firstName, "");
            jsonObject.put(RequestParamUtils.lastName, "");
            jsonObject.put(RequestParamUtils.phone, "");
            jsonObject.put(RequestParamUtils.postcode, "");
            jsonObject.put(RequestParamUtils.state, "");
            main.put(RequestParamUtils.billing, jsonObject);

            removeAddress(main);

        } catch (JSONException e) {
            Log.e("error", e.getMessage());
        }
    }

    @OnClick(R.id.tvShippingRemove)
    public void tvShippingRemoveClick() {
        try {
            JSONObject main = new JSONObject();
            JSONObject jsonObject = new JSONObject();

            jsonObject.put(RequestParamUtils.address1, "");
            jsonObject.put(RequestParamUtils.address2, "");
            jsonObject.put(RequestParamUtils.city, "");
            jsonObject.put(RequestParamUtils.company, "");
            jsonObject.put(RequestParamUtils.country, "");
            jsonObject.put(RequestParamUtils.firstName, "");
            jsonObject.put(RequestParamUtils.lastName, "");
            jsonObject.put(RequestParamUtils.postcode, "");
            jsonObject.put(RequestParamUtils.state, "");
            main.put(RequestParamUtils.shipping, jsonObject);

            removeAddress(main);

        } catch (JSONException e) {
            Log.e("error", e.getMessage());
        }
    }

    public void removeAddress(JSONObject object) {
        if (Utils.isInternetConnected(this)) {
            showProgress("");
            PostApi postApi = new PostApi(this, RequestParamUtils.removeAddress, this, getlanuage());
            String customerId = getPreferences().getString(RequestParamUtils.ID, "");
            postApi.callPostApi(new URLS().WOO_MAIN_URL + new URLS().WOO_CUSTOMERS + "/" + customerId, object.toString());
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onResponse(final String response, String methodName) {

        if (methodName.equals(RequestParamUtils.removeAddress)) {

            dismissProgress();
            if (response != null && response.length() > 0) {
                try {
                    JSONObject jsonObj = new JSONObject(response);

                    String status = jsonObj.getString("status");
                    if (status.equals("error")) {
                        String msg = jsonObj.getString("message");
                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    try {
                        customer = new Gson().fromJson(
                                response, new TypeToken<Customer>() {
                                }.getType());

                        SharedPreferences.Editor pre = getPreferences().edit();
                        pre.putString(RequestParamUtils.CUSTOMER, response);
                        pre.commit();
                        setAddress();
                    } catch (Exception e1) {
                        Log.e("Exception is ", e1.getMessage());
                    }


                }
            }
        }
    }
}
