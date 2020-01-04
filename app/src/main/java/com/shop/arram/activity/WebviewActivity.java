package com.shop.arram.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.ciyashop.library.apicall.Ciyashop;
import com.ciyashop.library.apicall.PostApi;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shop.arram.R;
import com.shop.arram.helper.DatabaseHelper;
import com.shop.arram.model.Cart;
import com.shop.arram.model.CategoryList;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;
import com.shop.arram.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WebviewActivity extends BaseActivity implements OnResponseListner {

    private static final String TAG = "WebviewActivity";
    @BindView(R.id.wvCheckOut)
    WebView wvCheckOut;

    @BindView(R.id.wvCheckOut1)
    WebView wvCheckOut1;


    @BindView(R.id.ivBack)
    ImageView ivBack;

    String url, thank_you_url, home_url, track_url, thank_you_again;
    private DatabaseHelper databaseHelper;
    private boolean isfirstLoad = false;
    private int buyNow;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        databaseHelper = new DatabaseHelper(this);
        ButterKnife.bind(this);
        settvImage();
        hideSearchNotification();
        setToolbarTheme();
        showBackButton();
        setScreenLayoutDirection();
        url = getIntent().getExtras().getString(RequestParamUtils.CHECKOUT_URL);
        thank_you_again = getIntent().getExtras().getString(RequestParamUtils.THANKYOUExtra);
        thank_you_url = getIntent().getExtras().getString(RequestParamUtils.THANKYOU);
        home_url = getIntent().getExtras().getString(RequestParamUtils.HOME_URL);
        buyNow = getIntent().getExtras().getInt(RequestParamUtils.buynow);
        wvCheckOut.getSettings().setLoadsImagesAutomatically(true);
        wvCheckOut.getSettings().setJavaScriptEnabled(true);
        wvCheckOut.getSettings().setDomStorageEnabled(true);
        wvCheckOut.addJavascriptInterface(new WebAppInterface(this), "Android");
        wvCheckOut.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(wvCheckOut, true);
        }

        wvCheckOut.setWebViewClient(new myWebClient());
        CookieManager.getInstance().setAcceptCookie(true);

        try {

            JSONObject jsonObject = new JSONObject();
            String customerId = getPreferences().getString(RequestParamUtils.ID, "");

            jsonObject.put(RequestParamUtils.user_id, customerId);
            jsonObject.put(RequestParamUtils.cartItems, getCartDataForAPI());
            jsonObject.put(RequestParamUtils.os, RequestParamUtils.android);
            if (Constant.IS_WPML_ACTIVE) {
                if (getPreferences().getString(RequestParamUtils.LANGUAGE, "").isEmpty()) {
                    if (!getPreferences().getString(RequestParamUtils.DEFAULTLANGUAGE, "").isEmpty()) {
                        jsonObject.put(RequestParamUtils.Languages, getPreferences().getString(RequestParamUtils.DEFAULTLANGUAGE, ""));
                    }
                } else {
                    jsonObject.put(RequestParamUtils.Languages, getPreferences().getString(RequestParamUtils.LANGUAGE, ""));
                }
            }

            Log.e("jsonObject ", jsonObject.toString());
            String postData = jsonObject.toString();
            wvCheckOut.postUrl(url, postData.getBytes());
            showProgress("");
            setToolbarTheme();
            wvCheckOut.setVisibility(View.GONE);

        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }

        //Initiated Checkout Facebook

        List<Cart> cartList = databaseHelper.getFromCart(buyNow);
        for (int i = 0; i < cartList.size(); i++) {

            String product = cartList.get(i).getProduct();

            // get product detail via Gson from string
            CategoryList categoryListRider = new Gson().fromJson(product, new TypeToken<CategoryList>() {
            }.getType());

            //Initiated Checkout Facebook
            logInitiatedCheckoutEvent(cartList.get(i).getProductid(), categoryListRider.name, cartList.get(i).getQuantity(), false, Constant.CURRENCYSYMBOL, Double.parseDouble(categoryListRider.price));

        }


        // Get system current date and time
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String formattedDate = df.format(calendar.getTime());

        // Save ABANDONED card details for facebook pixel
        SharedPreferences.Editor pre = getPreferences().edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartList);
        //save Array list value in list
        pre.putString(RequestParamUtils.ABANDONED, json);
        pre.putString(RequestParamUtils.ABANDONEDTIME, formattedDate);
        pre.commit();

        //get value from string to array list
        String abandoned = getPreferences().getString(RequestParamUtils.ABANDONED, "");
        Type type = new TypeToken<List<Cart>>() {
        }.getType();
        List<Cart> connections = new Gson().fromJson(abandoned, type);

        Log.e(TAG, "formattedDate: " + getPreferences().getString(RequestParamUtils.ABANDONEDTIME, ""));
        Log.e(TAG, "shouldOverrideUrlLoading: " + connections.size());
    }

    public JSONArray getCartDataForAPI() {
        List<Cart> cartList = databaseHelper.getFromCart(buyNow);
        if (cartList.size() > 0) {
            try {
                JSONArray jsonArray = new JSONArray();

                for (int i = 0; i < cartList.size(); i++) {
//                    String product = cartList.get(i).getProduct();

                    JSONObject object = new JSONObject();

                    object.put(RequestParamUtils.PRODUCT_ID, cartList.get(i).getProductid() + "");
                    if (new Ciyashop(WebviewActivity.this).getPreferences()) {
                        object.put(RequestParamUtils.quantity, new Ciyashop(WebviewActivity.this).getQuantity() + "");
                    } else {
                        object.put(RequestParamUtils.quantity, cartList.get(i).getQuantity() + "");
                    }

                    if (cartList.get(i).getVariation() != null) {
                        JSONObject ob1 = new JSONObject(cartList.get(i).getVariation());

                        object.put(RequestParamUtils.variation, ob1);
                    }

                    object.put(RequestParamUtils.variationId, cartList.get(i).getVariationid() + "");
                    jsonArray.put(object);
                }

                return jsonArray;

            } catch (Exception e) {
                Log.e("error", e.getMessage());
            }
        }
        return null;
    }

    @OnClick(R.id.ivBack)
    public void ivBackClick() {
        if (wvCheckOut.canGoBack()) {
            wvCheckOut.goBack();
        } else {
            CookieManager.getInstance().removeAllCookie();
            wvCheckOut.clearCache(true);
            wvCheckOut.clearHistory();
            clearCookies(WebviewActivity.this);
            logout();
        }
    }

    //Custom WebViewClient
    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);

            Log.e("Responce", "Url called");

            String text = "";

            String[] separated = url.split("\\?");
            String checkurl = separated[0]; // this will contain "Fruit"

            for (int i = 0; i < Constant.CheckoutURL.size(); i++) {
                String value = Constant.CheckoutURL.get(i);
                if ((value.charAt(0) + "").contains("/")) {

                    Log.e(TAG, "fullURL: " + value);
                    StringBuilder sb = new StringBuilder(value);
                    sb.deleteCharAt(0);
                    value = sb.toString();

                    Log.e(TAG, "Deleted url      : " + value);
                }
                if ((value.charAt(value.length() - 1) + "").contains("/")) {

                    StringBuilder sb = new StringBuilder(value);
                    sb.deleteCharAt(value.length() - 1);
                    value = sb.toString();
                }

                if (!value.equals("") && value != null) {


                    if (checkurl.contains(value)) {
                        //text = Constant.CheckoutURL.get(i);
                        text = value;
                        break;
                    }
                }
            }
            Log.e(TAG, "shouldOverrideUrlLoading: " + text);


            if (!text.isEmpty() && checkurl.contains(text)) {
                if (isfirstLoad) {

                    List<Cart> cartList = databaseHelper.getFromCart(buyNow);
                    for (int i = 0; i < cartList.size(); i++) {
                        String product = cartList.get(i).getProduct();

                        CategoryList categoryListRider = new Gson().fromJson(product, new TypeToken<CategoryList>() {
                        }.getType());


                        logPurchasedEvent(cartList.get(i).getQuantity(), categoryListRider.name, cartList.get(i).getProductid(), Constant.CURRENCYCODE, Double.parseDouble(categoryListRider.price));
                    }

                    SharedPreferences.Editor pre = getPreferences().edit();
                    pre.putString(RequestParamUtils.ABANDONED, "");
                    pre.putString(RequestParamUtils.ABANDONEDTIME, "");
                    pre.commit();

                    Intent intent = new Intent(WebviewActivity.this, ThankYouActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    wvCheckOut.clearCache(true);
                    wvCheckOut.clearHistory();
                    clearCookies(WebviewActivity.this);
                    isfirstLoad = false;
                } else {
                    Log.e("Else Condition ", "Called");
                }
            } else if (home_url != null && url.contains(home_url)) {
//                Toast.makeText(WebviewActivity.this,"Something went wrong ...try after Sometime or Contact Admin",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageCommitVisible(WebView view, String url) {
            super.onPageCommitVisible(view, url);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            // progress.setVisibility(View.GONE);
            super.onPageFinished(view, url);

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wvCheckOut.canGoBack()) {
            wvCheckOut.goBack();
            return true;
        } else {
            CookieManager.getInstance().removeAllCookie();
            wvCheckOut.clearCache(true);
            wvCheckOut.clearHistory();
            clearCookies(WebviewActivity.this);
            logout();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void logout() {
        if (Utils.isInternetConnected(this)) {
            showProgress("");

            PostApi postApi = new PostApi(this, "logout", this, getlanuage());
            postApi.callPostApi(new URLS().LOGOUT, "");
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onResponse(final String response, String methodName) {
        dismissProgress();
        if (methodName.equals(RequestParamUtils.logout)) {


            if (response != null && response.length() > 0) {
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    String status = jsonObj.getString("status");
                    if (status.equals("success")) {
                        finish();
                    } else {

                    }
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                }
            }
        }
    }


    @SuppressWarnings("deprecation")
    public void clearCookies(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            Log.e("log", "Using clearCookies code for API >=" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else {
            Log.e("log", "Using clearCookies code for API <" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
            CookieSyncManager cookieSyncMngr = CookieSyncManager.createInstance(context);
            cookieSyncMngr.startSync();
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }


    }

    public class WebAppInterface {
        Context mContext;

        /**
         * Instantiate the interface and set the context
         */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /**
         * Show a toast from the web page
         */
        @JavascriptInterface
        public void showToast(final String toast) {
            Log.e("Title is ", toast);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dismissProgress();
                    if (toast != null) {
                        CookieManager.getInstance().setAcceptCookie(true);
                        wvCheckOut.loadUrl(toast);
                        wvCheckOut.setVisibility(View.VISIBLE);
                        isfirstLoad = true;
                        dismissProgress();
                    }
                }
            });

        }
    }


}
