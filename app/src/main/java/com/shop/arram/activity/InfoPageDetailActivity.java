package com.shop.arram.activity;

import com.ciyashop.library.apicall.PostApi;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import com.shop.arram.R;
import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.RequestParamUtils;
import com.shop.arram.utils.Utils;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoPageDetailActivity extends BaseActivity implements OnResponseListner {

    @BindView(R.id.tvDescription)
    TextViewLight tvDescription;

    private String pageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page_detail);
        ButterKnife.bind(this);
        pageID = getIntent().getExtras().getString(RequestParamUtils.ID);
        settvTitle(getIntent().getExtras().getString(RequestParamUtils.title));
        showBackButton();
        getPageData();
        setToolbarTheme();
    }

    public void getPageData() {
        if (Utils.isInternetConnected(this)) {
            showProgress("");
            PostApi postApi = new PostApi(this, RequestParamUtils.infoPages, this, getlanuage());
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(RequestParamUtils.pageId, pageID);
                postApi.callPostApi(new URLS().INFO_PAGES, jsonObject.toString());
            } catch (Exception e) {
                Log.e("Json Exception", e.getMessage());
            }
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onResponse(String response, String methodName) {

        if (methodName.equals(RequestParamUtils.infoPages)) {
            dismissProgress();
            if (response != null && response.length() > 0) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("success")) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            tvDescription.setText(Html.fromHtml(jsonObject.getString("data"), Html.FROM_HTML_MODE_COMPACT));
                        } else {
                            tvDescription.setText(Html.fromHtml(jsonObject.getString("data")));
                        }

                    }
                } catch (Exception e) {
                    Log.e(methodName + "Gson Exception is ", e.getMessage());
                }
            }
        }
    }

}
