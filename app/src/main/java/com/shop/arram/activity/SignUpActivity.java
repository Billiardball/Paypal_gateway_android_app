package com.shop.arram.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.ciyashop.library.apicall.PostApi;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shop.arram.R;
import com.shop.arram.customview.edittext.EditTextRegular;
import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.javaclasses.SyncWishList;
import com.shop.arram.model.LogIn;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;
import com.shop.arram.utils.Utils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity implements OnResponseListner {


    @BindView(R.id.etUsername)
    EditTextRegular etUsername;

    @BindView(R.id.etEmail)
    EditTextRegular etEmail;

    @BindView(R.id.etContact)
    EditTextRegular etContact;

    @BindView(R.id.etPass)
    EditTextRegular etPass;

    @BindView(R.id.etConfirmPass)
    EditTextRegular etConfirmPass;

    @BindView(R.id.ivLogo)
    ImageView ivLogo;

    @BindView(R.id.tvAlreadyAccount)
    TextViewLight tvAlreadyAccount;

    @BindView(R.id.tvSignInNow)
    TextViewLight tvSignInNow;

    @BindView(R.id.tvSignUp)
    TextViewLight tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        setScreenLayoutDirection();
        setThemeColor();
    }

    @OnClick(R.id.tvSignInNow)
    public void tvSignInNowClick() {
        Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.ivBlackBackButton)
    public void ivBackClick() {
        onBackPressed();
    }


    public void setThemeColor() {

        if (Constant.APPLOGO != null && !Constant.APPLOGO.equals("")) {
            Picasso.with(this).load(Constant.APPLOGO).error(R.drawable.logo).into(ivLogo);
        }
        Drawable mDrawable = getResources().getDrawable(R.drawable.login);
        mDrawable.setColorFilter(new
                PorterDuffColorFilter(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)), PorterDuff.Mode.OVERLAY));


        tvAlreadyAccount.setTextColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        tvSignInNow.setTextColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        tvSignUp.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));
    }

    @OnClick(R.id.tvSignUp)
    public void tvSignUpClick() {
        if (etUsername.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.enter_username, Toast.LENGTH_SHORT).show();
        } else {
            if (etEmail.getText().toString().isEmpty()) {
                Toast.makeText(this, R.string.enter_email_address, Toast.LENGTH_SHORT).show();
            } else {
                if (Utils.isValidEmail(etEmail.getText().toString())) {
                    if (etContact.getText().toString().isEmpty()) {
                        Toast.makeText(this, R.string.enter_contact_number, Toast.LENGTH_SHORT).show();
                    } else {
                        if (etPass.getText().toString().isEmpty()) {
                            Toast.makeText(this, R.string.enter_password, Toast.LENGTH_SHORT).show();
                        } else {
                            if (etConfirmPass.getText().toString().isEmpty()) {
                                Toast.makeText(this, R.string.enter_confirm_password, Toast.LENGTH_SHORT).show();
                            } else {
                                if (etPass.getText().toString().equals(etConfirmPass.getText().toString())) {
                                    registerUser();
                                } else {
                                    Toast.makeText(this, R.string.password_and_confirm_password_not_matched, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                } else {
                    Toast.makeText(this, R.string.enter_valid_email_address, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void registerUser() {
        if (Utils.isInternetConnected(this)) {
            showProgress("");
            PostApi postApi = new PostApi(this, "create_customer", this, getlanuage());
            JSONObject object = new JSONObject();
            try {

                object.put(RequestParamUtils.email, etEmail.getText().toString());
                object.put(RequestParamUtils.username, etUsername.getText().toString());
                object.put(RequestParamUtils.mobile, etContact.getText().toString());
                object.put(RequestParamUtils.PASSWORD, etPass.getText().toString());
                object.put(RequestParamUtils.deviceType, Constant.DEVICE_TYPE);

                String token = getPreferences().getString(RequestParamUtils.NOTIFICATION_TOKEN, "");
                object.put(RequestParamUtils.deviceToken, token);

                postApi.callPostApi(new URLS().CREATE_CUSTOMER, object.toString());

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Toast.makeText(this, R.string.something_went_wrong_try_after_somtime, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onResponse(final String response, String methodName) {

        if (methodName.equals(RequestParamUtils.createCustomer)) {
            if (response != null && response.length() > 0) {
                try {
                    final LogIn loginRider = new Gson().fromJson(
                            response, new TypeToken<LogIn>() {
                            }.getType());

                    JSONObject jsonObj = new JSONObject(response);
                    String status = jsonObj.getString("status");

                    if (status.equals("error")) {
                        Toast.makeText(getApplicationContext(), jsonObj.getString("message"), Toast.LENGTH_SHORT).show(); //display in long period of time
                        dismissProgress();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //set call here
                                if (loginRider.status.equals("success")) {

                                    SharedPreferences.Editor pre = getPreferences().edit();
                                    pre.putString(RequestParamUtils.CUSTOMER, "");
                                    pre.putString(RequestParamUtils.ID, loginRider.user.id + "");
                                    pre.putString(RequestParamUtils.PASSWORD, etPass.getText().toString());
                                    pre.commit();

                                    new SyncWishList(SignUpActivity.this).syncWishList(getPreferences().getString(RequestParamUtils.ID, ""), false);

//                                    Intent intent = new Intent(SignUpActivity.this, AccountActivity.class);
//                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), R.string.enter_proper_detail, Toast.LENGTH_SHORT).show(); //display in long period of time
                                }
                            }
                        });
                        dismissProgress();
                    }
                } catch (Exception e) {
                    Log.e(methodName + "Gson Exception is ", e.getMessage());
                    Toast.makeText(getApplicationContext(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show(); //display in long period of time
                }
            }
        }
    }


}
