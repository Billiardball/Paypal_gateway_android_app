package com.shop.arram.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.shop.arram.R;
import com.shop.arram.customview.textview.TextViewBold;
import com.shop.arram.customview.textview.TextViewMedium;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;
import com.squareup.picasso.Picasso;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductQuickDetailActivity extends BaseActivity {

    @BindView(R.id.tvSubTitle)
    TextViewMedium tvSubTitle;

    @BindView(R.id.ivProduct)
    ImageView ivProduct;

    @BindView(R.id.tvProductName)
    TextViewBold tvProductName;

    @BindView(R.id.tvDescription)
    HtmlTextView tvDescription;

    @BindView(R.id.wvDetail)
    WebView wvDetail;

    public static String changedHeaderHtml(String htmlText) {

        String head = "<head><meta name=\"viewport\" content=\"width=device-width, user-scalable=yes\" /></head>";

        String closedTag = "</body></html>";
        String changeFontHtml = head + htmlText + closedTag;
        return changeFontHtml;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_quick_detail);
        ButterKnife.bind(this);
        setToolbarTheme();
        hideSearchNotification();
        setScreenLayoutDirection();

        String description = getIntent().getExtras().getString(RequestParamUtils.description);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            tvDescription.setText(Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT));
//        } else {
//            tvDescription.setText(Html.fromHtml(description));
//        }
        tvDescription.setHtml(description,
                new HtmlHttpImageGetter(tvDescription));

        if (description != "") {
            wvDetail.setInitialScale(1);

            wvDetail.getSettings().setLoadsImagesAutomatically(true);
            wvDetail.getSettings().setUseWideViewPort(true);
            wvDetail.loadData(changedHeaderHtml(description), "text/html", "UTF-8");
            wvDetail.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
            wvDetail.getSettings().setBuiltInZoomControls(true);
        }

        String subTitle = getIntent().getExtras().getString(RequestParamUtils.title);
        String productImage = getIntent().getExtras().getString(RequestParamUtils.image);
        String productName = getIntent().getExtras().getString(RequestParamUtils.name);

        settvTitle(subTitle);
        tvSubTitle.setText(subTitle);
        tvProductName.setText(productName);
        tvProductName.setTextColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));

        if (productImage.length() > 0) {
            ivProduct.setVisibility(View.VISIBLE);
            Picasso.with(this).load(productImage).into(ivProduct);
        } else {
            ivProduct.setVisibility(View.INVISIBLE);
        }

        showBackButton();
    }


}
