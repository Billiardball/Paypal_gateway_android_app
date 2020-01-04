package com.shop.arram.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
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
import com.shop.arram.customview.edittext.EditTextRegular;
import com.shop.arram.customview.textview.TextViewMedium;
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

public class AddAddressActivity extends BaseActivity implements OnResponseListner {

    @BindView(R.id.tvActivityTitle)
    TextViewMedium tvActivityTitle;

    private Bundle bundle;
    private int type;

    @BindView(R.id.tilPhone)
    TextInputLayout tilPhone;

    @BindView(R.id.etFirstName)
    EditTextRegular etFirstName;

    @BindView(R.id.etLastName)
    EditTextRegular etLastName;

    @BindView(R.id.etAddress1)
    EditTextRegular etAddress1;

    @BindView(R.id.etAddress2)
    EditTextRegular etAddress2;

    @BindView(R.id.etPincode)
    EditTextRegular etPincode;

    @BindView(R.id.etCity)
    EditTextRegular etCity;

    @BindView(R.id.etCompany)
    EditTextRegular etCompany;

    @BindView(R.id.etPhoneNumber)
    EditTextRegular etPhoneNumber;

    @BindView(R.id.tvCancel)
    TextViewRegular tvCancel;

    @BindView(R.id.tvSave)
    TextViewRegular tvSave;

    @BindView(R.id.llButton)
    LinearLayout llButton;

    private Customer customer = new Customer();
    private String cust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
        setScreenLayoutDirection();
        setToolbarTheme();
        setThemeColor();
        bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getInt(RequestParamUtils.type);
        }

        if (type == 0) {
            tvActivityTitle.setText(getResources().getText(R.string.add_billing_address));
        } else {
            tvActivityTitle.setText(getResources().getText(R.string.add_shipping_address));
            tilPhone.setVisibility(View.GONE);
        }
        settvTitle(getResources().getString(R.string.add_new_addresses));
        showBackButton();
    }

    public void setThemeColor() {
        llButton.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        cust = getPreferences().getString(RequestParamUtils.CUSTOMER, "");
        customer = new Gson().fromJson(
                cust, new TypeToken<Customer>() {
                }.getType());
        setAddress();
    }

    public void setAddress() {

        if (type == 0) {
            //billing Address

            etFirstName.setText(customer.billing.firstName + "");
            etLastName.setText(customer.billing.lastName + "");
            etPincode.setText(customer.billing.postcode + "");
            etAddress1.setText(customer.billing.address1 + "");
            etAddress2.setText(customer.billing.address2 + "");
            etCity.setText(customer.billing.city + "");
            etPhoneNumber.setText(customer.billing.phone + "");
            etCompany.setText(customer.billing.company + "");

        } else {
            //Shipping Address

            etFirstName.setText(customer.shipping.firstName + "");
            etLastName.setText(customer.shipping.lastName + "");
            etPincode.setText(customer.shipping.postcode + "");
            etAddress1.setText(customer.shipping.address1 + "");
            etAddress2.setText(customer.shipping.address2 + "");
            etCity.setText(customer.shipping.city + "");
            etCompany.setText(customer.shipping.company + "");
        }
    }

    @OnClick(R.id.tvCancel)
    public void tvCancelClick() {
        finish();
    }

    @OnClick(R.id.tvSave)
    public void tvSaveClick() {
        updateAddress();
    }

    public void updateAddress() {
        if (Utils.isInternetConnected(this)) {
            showProgress("");

            PostApi postApi = new PostApi(this, RequestParamUtils.updateCustomer, this, getlanuage());
            try {
                if (type == 0) {
                    //Billing Address
                    customer.billing.firstName = etFirstName.getText().toString();
                    customer.billing.lastName = etLastName.getText().toString();
                    customer.billing.postcode = etPincode.getText().toString();
                    customer.billing.address1 = etAddress1.getText().toString();
                    customer.billing.address2 = etAddress2.getText().toString();
                    customer.billing.city = etCity.getText().toString();
                    customer.billing.company = etCompany.getText().toString();
                    customer.billing.phone = etPhoneNumber.getText().toString();
                } else {
                    //Shipping Address
                    customer.shipping.firstName = etFirstName.getText().toString();
                    customer.shipping.lastName = etLastName.getText().toString();
                    customer.shipping.postcode = etPincode.getText().toString();
                    customer.shipping.address1 = etAddress1.getText().toString();
                    customer.shipping.address2 = etAddress2.getText().toString();
                    customer.shipping.city = etCity.getText().toString();
                    customer.shipping.company = etCompany.getText().toString();
                }
                String data = new Gson().toJson(customer);
                JSONObject jsonObject = new JSONObject(data);
                String id = getPreferences().getString(RequestParamUtils.ID, "");

                jsonObject.put(RequestParamUtils.user_id, id);
                postApi.callPostApi(new URLS().UPDATE_CUSTOMER, jsonObject.toString());
            } catch (JSONException e) {
                Log.e("error", e.getMessage());
            }
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onResponse(final String response, String methodName) {

        if (methodName.equals(RequestParamUtils.updateCustomer)) {

            dismissProgress();
            if (response != null && response.length() > 0) {
                try {
                    JSONObject jsonObj = new JSONObject(response);

                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {
                        customer = new Gson().fromJson(
                                response, new TypeToken<Customer>() {
                                }.getType());

                        SharedPreferences.Editor pre = getPreferences().edit();
                        pre.putString(RequestParamUtils.CUSTOMER, response);
                        pre.commit();
                        Toast.makeText(this, R.string.address_updated_successfully, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, R.string.something_went_wrong_try_after_somtime, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                }
            }
        }
    }

}