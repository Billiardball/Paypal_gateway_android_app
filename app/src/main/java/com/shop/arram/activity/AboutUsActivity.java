package com.shop.arram.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ciyashop.library.apicall.PostApi;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import com.shop.arram.BuildConfig;
import com.shop.arram.R;
import com.shop.arram.customview.textview.TextViewBold;
import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutUsActivity extends BaseActivity implements OnResponseListner {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @BindView(R.id.tvAboutusContent)
    HtmlTextView tvAboutusContent;

    @BindView(R.id.tvVersion)
    TextViewLight tvVersion;

    @BindView(R.id.tvTermsAndCondition)
    TextViewLight tvTermsAndCondition;

    @BindView(R.id.tvPrivacyPolicy)
    TextViewLight tvPrivacyPolicy;

    @BindView(R.id.ivPinterest)
    ImageView ivPinterest;

    @BindView(R.id.ivTwitter)
    ImageView ivTwitter;

    @BindView(R.id.ivFacebook)
    ImageView ivFacebook;

    @BindView(R.id.ivInstagram)
    ImageView ivInstagram;

    @BindView(R.id.ivLinkedin)
    ImageView ivLinkedin;

    @BindView(R.id.ivGooglePlus)
    ImageView ivGooglePlus;

    @BindView(R.id.Logo)
    ImageView Logo;

    @BindView(R.id.tvMoreAboutUs)
    TextViewBold tvMoreAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        setToolbarTheme();
        setScreenLayoutDirection();
        settvTitle(getResources().getString(R.string.about_us));
        showBackButton();
        aboutUs();
        hideSearchNotification();
        int versionCode = BuildConfig.VERSION_CODE;
        tvVersion.setText(getString(R.string.version) + versionCode);
        if (Constant.SOCIALLINK != null) {
            if (Constant.SOCIALLINK.pinterest == null || Constant.SOCIALLINK.pinterest.length() == 0) {
                ivPinterest.setVisibility(View.GONE);
            }
            if (Constant.SOCIALLINK.twitter == null || Constant.SOCIALLINK.twitter.length() == 0) {
                ivTwitter.setVisibility(View.GONE);
            }
            if (Constant.SOCIALLINK.facebook == null || Constant.SOCIALLINK.facebook.length() == 0) {
                ivFacebook.setVisibility(View.GONE);
            }
            if (Constant.SOCIALLINK.linkedin == null || Constant.SOCIALLINK.linkedin.length() == 0) {
                ivLinkedin.setVisibility(View.GONE);
            }
            if (Constant.SOCIALLINK.googlePlus == null || Constant.SOCIALLINK.googlePlus.length() == 0) {
                ivGooglePlus.setVisibility(View.GONE);
            }
            if (Constant.SOCIALLINK.instagram == null || Constant.SOCIALLINK.instagram.length() == 0) {
                ivInstagram.setVisibility(View.GONE);
            }
        } else {
            ivPinterest.setVisibility(View.GONE);
            ivTwitter.setVisibility(View.GONE);
            ivFacebook.setVisibility(View.GONE);
            ivLinkedin.setVisibility(View.GONE);
            ivGooglePlus.setVisibility(View.GONE);
            ivInstagram.setVisibility(View.GONE);
        }
        if (Constant.APPLOGO != null && !Constant.APPLOGO.equals("")) {
            Picasso.with(this).load(Constant.APPLOGO).error(R.drawable.logo).into(Logo);
        }
        setColorTheme();
    }

    public void setColorTheme() {
        tvTermsAndCondition.setTextColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        tvPrivacyPolicy.setTextColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        ivFacebook.setColorFilter(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        ivGooglePlus.setColorFilter(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        ivLinkedin.setColorFilter(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        ivTwitter.setColorFilter(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        ivPinterest.setColorFilter(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        ivInstagram.setColorFilter(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
    }

    @OnClick(R.id.ivPinterest)
    public void ivPinterestClick() {
        String url = Constant.SOCIALLINK.pinterest;
        if (!url.startsWith(RequestParamUtils.UrlStartWith) && !url.startsWith(RequestParamUtils.UrlStartWithsecure)) {
            url = RequestParamUtils.UrlStartWith + url;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @OnClick(R.id.ivInstagram)
    public void ivInstagramClick() {
        String url = Constant.SOCIALLINK.instagram;
        if (!url.startsWith(RequestParamUtils.UrlStartWith) && !url.startsWith(RequestParamUtils.UrlStartWithsecure)) {
            url = RequestParamUtils.UrlStartWith + url;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @OnClick(R.id.ivTwitter)
    public void ivTwitterClick() {
        String url = Constant.SOCIALLINK.twitter;
        if (!url.startsWith(RequestParamUtils.UrlStartWith) && !url.startsWith(RequestParamUtils.UrlStartWithsecure)) {
            url = RequestParamUtils.UrlStartWith + url;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @OnClick(R.id.ivFacebook)
    public void ivFacebookClick() {
        String url = Constant.SOCIALLINK.facebook;
        if (!url.startsWith(RequestParamUtils.UrlStartWith) && !url.startsWith(RequestParamUtils.UrlStartWithsecure)) {
            url = RequestParamUtils.UrlStartWith + url;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @OnClick(R.id.ivLinkedin)
    public void ivLinkedinClick() {
        String url = Constant.SOCIALLINK.linkedin;
        if (!url.startsWith(RequestParamUtils.UrlStartWith) && !url.startsWith(RequestParamUtils.UrlStartWithsecure)) {
            url = RequestParamUtils.UrlStartWith + url;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @OnClick(R.id.ivGooglePlus)
    public void ivGooglePlusClick() {
        String url = Constant.SOCIALLINK.googlePlus;
        if (!url.startsWith(RequestParamUtils.UrlStartWith) && !url.startsWith(RequestParamUtils.UrlStartWithsecure)) {
            url = RequestParamUtils.UrlStartWith + url;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @OnClick(R.id.tvTermsAndCondition)
    public void tvTermsAndConditionClick() {
        Intent intent = new Intent(AboutUsActivity.this, TermsAndPrivacyActivity.class);
        intent.putExtra(RequestParamUtils.PassingData, RequestParamUtils.termsOfUse);
        startActivity(intent);
    }

    @OnClick(R.id.tvPrivacyPolicy)
    public void tvPrivacyPolicyClick() {
        Intent intent = new Intent(AboutUsActivity.this, TermsAndPrivacyActivity.class);
        intent.putExtra(RequestParamUtils.PassingData, RequestParamUtils.privacyPolicy);
        startActivity(intent);
    }

    public void aboutUs() {
        PostApi postApi = new PostApi(this, RequestParamUtils.staticPage, this, getlanuage());
        JSONObject object = new JSONObject();
        try {
            object.put(RequestParamUtils.page, RequestParamUtils.about_us);
            showProgress("");
            postApi.callPostApi(new URLS().STATIC_PAGES, object.toString());

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
                        if (content.equals("")) {

                            tvMoreAboutUs.setVisibility(View.GONE);
                            tvAboutusContent.setVisibility(View.GONE);

                        } else {
                            tvMoreAboutUs.setVisibility(View.VISIBLE);
                            tvAboutusContent.setVisibility(View.VISIBLE);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                tvAboutusContent.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_COMPACT));
                            } else {
                                tvAboutusContent.setText(Html.fromHtml(content));
                            }


                            tvAboutusContent.setHtml(content,
                                    new HtmlHttpImageGetter(tvAboutusContent));
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
