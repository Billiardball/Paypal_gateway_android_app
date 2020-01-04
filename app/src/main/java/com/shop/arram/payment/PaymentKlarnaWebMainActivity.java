package com.shop.arram.payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.klarna.checkout.KlarnaCheckout;
import com.klarna.checkout.SignalListener;
import com.shop.arram.R;
import com.shop.arram.activity.ThankYouActivity;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.RequestParamUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentKlarnaWebMainActivity extends BaseActivity {
        private WebView mWebView;
        private KlarnaCheckout mKlarnaCheckout;

    String url, thank_you_url, home_url, track_url, thank_you_again;

    private boolean isfirstLoad = false;
    private int buyNow;
    private String TAG = PaymentKlarnaWebMainActivity.class.getSimpleName();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_klarana_web_payment);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//            Button btnPay = (Button) findViewById(R.id.btnPay);
            setSupportActionBar(toolbar);

            url = getIntent().getExtras().getString(RequestParamUtils.CHECKOUT_URL);
            thank_you_again = getIntent().getExtras().getString(RequestParamUtils.THANKYOUExtra);
            thank_you_url = getIntent().getExtras().getString(RequestParamUtils.THANKYOU);
            home_url = getIntent().getExtras().getString(RequestParamUtils.HOME_URL);
            buyNow = getIntent().getExtras().getInt(RequestParamUtils.buynow);
            mWebView = (WebView) findViewById(R.id.web_view);
//            btnPay.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mWebView = (WebView) findViewById(R.id.web_view);
//                    mWebView.setWebViewClient(new WebViewClient() {
//                        @Override
//                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                            return false;
//                        }
//                    });
//                    mWebView.getSettings().setJavaScriptEnabled(true);
//
//                    initKlarnaCheckout();
//
//                    //Load the page containing the Klarna Checkout.
//                    //mWebView.loadUrl("https://www.klarnacheckout.com/");
//                   // mWebView.loadUrl("https://arram.de/en/checkout/");
//
//
//                    Log.e(TAG, "==url==checkout="+url);
//                    mWebView.loadUrl(url);
//                }
//            });


        }

        protected void initKlarnaCheckout() {
            //Attach Activity and WebView to checkout
            mKlarnaCheckout = new KlarnaCheckout(this, "kco-android-example://checkout");
            mKlarnaCheckout.setWebView(mWebView);

            //Attach the listener to handle event messages from checkout.
            mKlarnaCheckout.setSignalListener(new SignalListener() {
                @Override
                public void onSignal(String eventName, JSONObject jsonObject) {
                    if (eventName.equals("complete")) {
                        try {
                            String url = jsonObject.getString("uri");
                            mWebView.loadUrl(url);
                        } catch (JSONException e) {
                            Log.e(e.getMessage(), e.toString());
                        }
                    }
                }
            });
        }

        @Override
        protected void onDestroy() {
            if (mKlarnaCheckout != null) {
                mKlarnaCheckout.destroy();
            }


            Intent intent = new Intent(PaymentKlarnaWebMainActivity.this, ThankYouActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            super.onDestroy();
        }
}