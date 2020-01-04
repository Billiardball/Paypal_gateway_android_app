package com.shop.arram.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.shop.arram.R;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.RequestParamUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoViewActivity extends BaseActivity {
    @BindView(R.id.wvVideoView)
    WebView wvVideoView;

    @BindView(R.id.ivBack)
    ImageView ivBack;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        ButterKnife.bind(this);
        settvImage();
        hideSearchNotification();
        setToolbarTheme();
        showBackButton();
        url = getIntent().getExtras().getString(RequestParamUtils.VIDEO_URL);


        wvVideoView.getSettings().setJavaScriptEnabled(true);
        wvVideoView.getSettings().setDomStorageEnabled(true);
        wvVideoView.getSettings().setJavaScriptEnabled(true);
        wvVideoView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        wvVideoView.getSettings().setSupportMultipleWindows(false);
        wvVideoView.getSettings().setSupportZoom(false);
        wvVideoView.setVerticalScrollBarEnabled(false);
        wvVideoView.setHorizontalScrollBarEnabled(false);
        wvVideoView.loadUrl(url);

        wvVideoView.setWebViewClient(new loadWebView());
    }

    private class loadWebView extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }

}
