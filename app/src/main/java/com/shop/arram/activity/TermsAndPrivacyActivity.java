package com.shop.arram.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import com.ciyashop.library.apicall.PostApi;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import com.shop.arram.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.RequestParamUtils;
import com.shop.arram.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class TermsAndPrivacyActivity extends BaseActivity implements OnResponseListner {

    String data;

    @BindView(R.id.tvContentDesc)
    TextViewLight tvContentDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_privacy);
        ButterKnife.bind(this);
        showBackButton();
        setToolbarTheme();
        hideSearchNotification();
        data = getIntent().getStringExtra(RequestParamUtils.PassingData);

        if (data.equals(RequestParamUtils.privacyPolicy)) {
            settvTitle(getResources().getString(R.string.privacy_policy));
        } else if (data.equals(RequestParamUtils.termsOfUse)) {
            settvTitle(getResources().getString(R.string.terms_condition));
        }

        getPages();
    }

    public void getPages() {
        if (Utils.isInternetConnected(this)) {
            showProgress("");
            PostApi postApi = new PostApi(this, RequestParamUtils.staticPage, this, getlanuage());
            JSONObject object = new JSONObject();
            try {

                object.put(RequestParamUtils.page, data);
                postApi.callPostApi(new URLS().STATIC_PAGES, object.toString());

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

        if (methodName.equals(RequestParamUtils.staticPage)) {
            dismissProgress();
            if (response != null && response.length() > 0) {
                try {
                    //set call here
                    JSONObject jsonObj = new JSONObject(response);

                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {
                        String content = jsonObj.getString("data");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            tvContentDesc.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_COMPACT));
                        } else {
                            tvContentDesc.setText(Html.fromHtml(content));
                        }
                    } else {
                        Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
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
