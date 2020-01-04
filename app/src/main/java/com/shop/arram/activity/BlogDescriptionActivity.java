package com.shop.arram.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.shop.arram.R;
import com.shop.arram.customview.pulltozoom.PullToZoomScrollViewEx;
import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BlogDescriptionActivity extends BaseActivity {

    @BindView(R.id.tvBlogDate)
    TextViewRegular tvBlogDate;

    @BindView(R.id.iv_zoom)
    ImageView ivBlog;

    @BindView(R.id.tvCategory)
    TextViewRegular tvCategory;

    @BindView(R.id.tvBlogContent)
    TextViewLight tvBlogContent;

    @BindView(R.id.scroll_view)
    PullToZoomScrollViewEx scrollView;

    @BindView(R.id.tvBlogTitle)
    TextViewLight tvBlogTitle;


    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_discription);
        loadViewForCode();
        ButterKnife.bind(this);
//        showBackButton();
        setScreenLayoutDirection();
        getIntentData();
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 13.0F)));
        scrollView.setHeaderLayoutParams(localObject);
    }

    private void loadViewForCode() {
        PullToZoomScrollViewEx scrollView = (PullToZoomScrollViewEx) findViewById(R.id.scroll_view);
        View zoomView = LayoutInflater.from(this).inflate(R.layout.profile_zoom_view, null, false);
        View contentView = LayoutInflater.from(this).inflate(R.layout.blog_content, null, false);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);
    }

    @OnClick(R.id.ivShare)
    public void ivShareClick() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String shareMessage = "\n" + tvBlogTitle.getText().toString()+ "\n\n";
            shareMessage = shareMessage + bundle.getString("link") + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            Log.e("Exception is ", e.getMessage());
        }
    }

    private void getIntentData() {
        bundle = getIntent().getExtras();
        if (bundle != null) ;
        tvBlogDate.setText(bundle.getString("date"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvBlogContent.setText(Html.fromHtml(bundle.getString("description"), Html.FROM_HTML_MODE_COMPACT));
        } else {
            tvBlogTitle.setText(Html.fromHtml(bundle.getString("name")));
            tvBlogContent.setText(Html.fromHtml(bundle.getString("description")));
        }
        if (bundle.getString("image") != null)
            Glide.with(this)
                    .load(bundle.getString("image"))
                    .asBitmap().format(DecodeFormat.PREFER_ARGB_8888)
                    .error(R.drawable.no_image_available)
                    .into(ivBlog);
    }
}
