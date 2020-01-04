package com.shop.arram.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ciyashop.library.apicall.PostApi;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import com.shop.arram.R;
import com.shop.arram.customview.edittext.EditTextRegular;
import com.shop.arram.customview.textview.TextViewBold;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;
import com.shop.arram.utils.Utils;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactSellerActivity extends BaseActivity implements OnResponseListner {

    @BindView(R.id.etName)
    EditTextRegular etName;

    @BindView(R.id.etEmail)
    EditTextRegular etEmail;

    @BindView(R.id.etMessage)
    EditTextRegular etMessage;

    @BindView(R.id.tvSend)
    TextViewBold tvSend;

    private String sellerid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_seller);
        ButterKnife.bind(this);
        setToolbarTheme();
        tvSend.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));
        String dealername = getIntent().getExtras().getString(RequestParamUtils.Dealer);
        if (dealername != null) {
            settvTitle(dealername);
        } else {
            settvTitle(RequestParamUtils.Dealer);
        }

        showBackButton();
        sellerid = getIntent().getExtras().getString(RequestParamUtils.ID);
    }

    @OnClick(R.id.tvSend)
    public void tvSendClick() {
        if (etName.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.enter_name, Toast.LENGTH_SHORT).show();
        } else {
            if (etEmail.getText().toString().isEmpty()) {
                Toast.makeText(this, R.string.enter_email_address, Toast.LENGTH_SHORT).show();
            } else {
                if (Utils.isValidEmail(etEmail.getText().toString())) {
                    if (etMessage.getText().toString().isEmpty()) {
                        Toast.makeText(this, R.string.enter_message, Toast.LENGTH_SHORT).show();
                    } else {
                        contactSeller();
                    }
                } else {
                    Toast.makeText(this, R.string.enter_valid_email_address, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void contactSeller() {
        if (Utils.isInternetConnected(this)) {
            showProgress("");
            PostApi postApi = new PostApi(ContactSellerActivity.this, RequestParamUtils.contactSeller, this, getlanuage());
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(RequestParamUtils.name, etName.getText().toString());
                jsonObject.put(RequestParamUtils.email, etEmail.getText().toString());
                jsonObject.put(RequestParamUtils.message, etMessage.getText().toString());
                jsonObject.put(RequestParamUtils.sellerId, sellerid);

                postApi.callPostApi(new URLS().CONTACT_SELLER, jsonObject.toString());
            } catch (Exception e) {
                Log.e("Json Exception", e.getMessage());
            }
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onResponse(String response, String methodName) {
        if (methodName.equals(RequestParamUtils.contactSeller)) {
            dismissProgress();
            if (response != null && response.length() > 0) {
                try {
                    //set call here
                    JSONObject jsonObj = new JSONObject(response);

                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {
                        Toast.makeText(this, R.string.message_sent_successfully, Toast.LENGTH_SHORT).show();
                        etName.getText().clear();
                        etEmail.getText().clear();
                        etMessage.getText().clear();
                    } else {
                        Toast.makeText(this, R.string.something_went_wrong_try_after_somtime, Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Log.e(methodName + "Gson Exception is ", e.getMessage());
                    Toast.makeText(this, R.string.something_went_wrong_try_after_somtime, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show(); //display in long period of time
            }
        }
    }

}
