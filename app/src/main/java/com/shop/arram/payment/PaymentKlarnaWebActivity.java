package com.shop.arram.payment;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.shop.arram.R;
import com.shop.arram.activity.ThankYouActivity;
import com.shop.arram.activity.WebviewActivity;
import com.shop.arram.model.Customer;
import com.shop.arram.model.LogIn;
import com.shop.arram.payment.api.Billing_address;
import com.shop.arram.payment.api.Billing_order;
import com.shop.arram.payment.api.Klarnaservice;
import com.shop.arram.payment.api.Lines_items;
import com.shop.arram.payment.api.Merchanturls;
import com.shop.arram.payment.api.Orderlines;
import com.shop.arram.payment.api.Sendclass;
import com.shop.arram.payment.api.Sendorder;
import com.shop.arram.payment.api.Shipping_address;
import com.shop.arram.payment.api.Shipping_lines;
import com.shop.arram.payment.api.Shipping_order;
import com.shop.arram.utils.BaseActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.klarna.checkout.KlarnaCheckout;
import com.klarna.checkout.SignalListener;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;
import com.shop.arram.utils.ServiceGenerator;
import com.shop.arram.utils.ServiceGenerator_other;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentKlarnaWebActivity extends BaseActivity {
    private WebView mWebView;
    private KlarnaCheckout mKlarnaCheckout;

    private String TAG = PaymentKlarnaWebActivity.class.getSimpleName();
    String payment_request, payment_response;
    JSONArray jsonArray;
    JSONObject obj;
    String checkout_url, confirmation_url, order_id;
    Billing_address billing_address_for_send;
    Shipping_address shipping_address_for_send;

    private Customer customer = new Customer();

    RelativeLayout relativelayout;
    float order_amount = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klarana_web_payment);
        setToolbarTheme();
        settvTitle(getResources().getString(R.string.payment));
        showBackButton();
        hideSearchNotification();


//        Log.d("Paymentwebactiivty", "iss>payment_request" + payment_request);
//        Log.d("Paymentwebactiivty", "iss>PAYMEsNT_response" + payment_response);
        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setVerticalScrollBarEnabled(true);
        mWebView.setHorizontalScrollBarEnabled(true);


        mWebView.setVisibility(View.VISIBLE);


        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(mWebView, url);

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

//                        Log.d("Paymentklarnawebactivity", "iss><" + confirmation_url);
                if (url.equals(confirmation_url + "/")) {

                    order_complete();


                }
//                Log.d("Paymentactiirtre", "issurl" + url);
//                Log.d("Paymentactiirtre", "issurl" + confirmation_url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });


        if (getIntent().getExtras() != null) {
            payment_request = getIntent().getExtras().getString(RequestParamUtils.payment_request);
            payment_response = getIntent().getExtras().getString(RequestParamUtils.payment_response);
            loadpayment();
        }

    }


    public void loadpayment() {

        try {

            obj = new JSONObject(payment_request);
            jsonArray = obj.getJSONArray("cart_items");
//            Log.d("My App", obj.toString());

        } catch (Throwable t) {
//            Log.e("My App", "Could not parse malformed JSON: \"" + payment_request + "\"");
        }


        try {

            JSONObject obj = new JSONObject(payment_response);
            checkout_url = obj.getString("checkout_url");
            confirmation_url = obj.getString("thankyou");
            Log.d("My App", obj.toString());

        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + payment_request + "\"");
        }


        showProgress("");
//        Klarnaservice loginService =
//                ServiceGenerator.createService(Klarnaservice.class, "PK14355_d7b5b6a2e618",
//                        "uTTA9Xaubpo5cCY2");

        Klarnaservice loginService =
                ServiceGenerator.createService(Klarnaservice.class, "K678486_4d0171a4aceb",
                        "of6hiFxE9F36pE3N");
        // prepare call in Retrofit 2.0
        try {

            ArrayList<Orderlines> am = new ArrayList<>();
            order_amount = 0f;
            for (int i = 0; i < jsonArray.length(); i++) {

                int unit_price = (int) (Float.parseFloat(jsonArray.getJSONObject(i).getString("price")) * 100);
                am.add(new Orderlines("physical", jsonArray.getJSONObject(i).getString("name"),
                        Integer.parseInt(jsonArray.getJSONObject(i).getString("quantity")),
                        unit_price,
                        0, (Integer.parseInt(jsonArray.getJSONObject(i).getString("quantity")) *
                        unit_price),
                        0));

                order_amount += (Integer.parseInt(jsonArray.getJSONObject(i).getString("quantity")) * unit_price);

//                Log.d("Paymentwebactiivty", "isdsdsd>><" + Float.parseFloat(jsonArray.getJSONObject(i).getString("price")));
//                Log.d("Paymentwebactiivty", "isdsdsd>><" + order_amount);
            }
            if (order_amount < 2999) {
                am.add(new Orderlines("shipping_fee", "Shipping Charge", 1,
                        600,
                        0, 600,
                        0));

                order_amount += 600;
            }

            Merchanturls ma = new Merchanturls("https://www.example.com/terms.html",
                    checkout_url,
                    confirmation_url,
                    "https://www.example.com/api/push");

            String cust = getPreferences().getString(RequestParamUtils.CUSTOMER, "");

            customer = new Gson().fromJson(
                    cust, new TypeToken<Customer>() {
                    }.getType());
            Billing_address ba;
            Shipping_address sa;
            if (customer != null) {
                if (customer.billing.phone.equals("") && customer.billing.firstName.equals("")
                        && customer.billing.lastName.equals("") && customer.billing.address1.equals("") &&
                        customer.billing.address2.equals("") && customer.billing.company.equals("") &&
                        customer.billing.city.equals("") && customer.billing.state.equals("") &&
                        customer.billing.postcode.equals("")) {
                    ba = new Billing_address("",
                            "", "", "", "",
                            "", "", "", "",
                            "", "",
                            "", "", "");
                } else {


                    ba = new Billing_address(customer.billing.firstName,
                            customer.billing.lastName, customer.email, "", customer.billing.address1,
                            customer.billing.address2, customer.billing.address1, "", "",
                            customer.billing.postcode, customer.billing.city,
                            customer.billing.state, customer.billing.phone, customer.billing.country);
                }

                if (customer.shipping.firstName.equals("") &&
                        customer.shipping.lastName.equals("") && customer.shipping.address1.equals("")
                        && customer.shipping.address2.equals("") && customer.shipping.company.equals("")
                        && customer.shipping.city.equals("") && customer.shipping.state.equals("")
                        && customer.shipping.postcode.equals("")) {

                    sa = new Shipping_address("",
                            "", "", "", "",
                            "", "", "", "",
                            "", "",
                            "", "", "");

                } else {

                    if (customer.billing.phone.equals("")) {
                        sa = new Shipping_address(customer.shipping.firstName,
                                customer.shipping.lastName, customer.email, "", customer.shipping.address1,
                                customer.shipping.address2, customer.shipping.address1, "", "",
                                customer.shipping.postcode, customer.shipping.city,
                                customer.shipping.state, "", customer.shipping.country);
                    } else {
                        sa = new Shipping_address(customer.shipping.firstName,
                                customer.shipping.lastName, customer.email, "", customer.shipping.address1,
                                customer.shipping.address2, customer.shipping.address1, "", "",
                                customer.shipping.postcode, customer.shipping.city,
                                customer.shipping.state, customer.billing.phone, customer.shipping.country);
                    }

                }
            } else {
                ba = new Billing_address("",
                        "", "", "", "",
                        "", "", "", "",
                        "", "",
                        "", "", "");

                sa = new Shipping_address("",
                        "", "", "", "",
                        "", "", "", "",
                        "", "",
                        "", "", "");
            }


            Sendclass sc = new Sendclass("DE",
                    "EUR", getlanuage(), (int) order_amount,
                    0, am, ma, ba, sa);


            Call<Sendclass> call = loginService.basicLogin(sc);
            call.enqueue(new Callback<Sendclass>() {
                @Override
                public void onResponse(Call<Sendclass> call, Response<Sendclass> response) {
                    dismissProgress();
                    if (response.isSuccessful()) {
                        // user object available
//                        Log.d("Paymentwebactiivty", "iss>body" + response.body().toString());

                        String snippet = response.body().getHtml_snippet();
                        order_id = response.body().getOrder_id();
                        billing_address_for_send = response.body().getBilling_address();
                        shipping_address_for_send = response.body().getShipping_address();
                        mWebView.loadData(snippet, "text/html", "UTF-8");
                        initKlarnaCheckout();
                    } else {
//                        Log.d("Paymentwebactiivty", "iss>bodyda" + response);
//                                Log.w("Paymentwebactiivty.2.0 getFeed > Full json res wrapped in gson => ",new Gson().toJson(response));
                        // error response, no access to resource?
                    }
                }

                @Override
                public void onFailure(Call<Sendclass> call, Throwable t) {
                    // something went completely south (like no internet connection)
//                    Log.d("Paymentwebactiivty", "Erro" + t.getMessage());


                    dismissProgress();

                    Toast.makeText(PaymentKlarnaWebActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void initKlarnaCheckout() {
        //Attach Activity and WebView to checkout
        mKlarnaCheckout = new KlarnaCheckout(this, confirmation_url);

        mKlarnaCheckout.setWebView(mWebView);

        //Attach the listener to handle event messages from checkout.
        mKlarnaCheckout.setSignalListener(new SignalListener() {
            @Override
            public void onSignal(String eventName, JSONObject jsonObject) {
                if (eventName.equals("complete")) {
                    try {
                        String url = jsonObject.getString("uri");
//                        Log.d("Paymentwebactiivty", "iss><><>" + url);
                        mWebView.loadUrl(url);
                    } catch (JSONException e) {
//                        Log.e(e.getMessage(), e.toString());
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
        super.onDestroy();
    }

    public void order_complete() {
        showProgress("");
        Klarnaservice order_complete =
                ServiceGenerator_other.createService(Klarnaservice.class, "ck_dc6e46c6f25a5c737ee313930e2c3e6ab4dd8ef1",
                        "cs_1ae4ddbe59aa4db5427bce36d20ef842f35df7f5");
        // prepare call in Retrofit 2.0
        try {

            if (jsonArray.length() > 0) {
                ArrayList<Lines_items> am = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    am.add(new Lines_items(Integer.parseInt(jsonArray.getJSONObject(i).getString("product_id")),
                            Integer.parseInt(jsonArray.getJSONObject(i).getString("quantity"))));


                }

                Billing_order ba;
                Shipping_order so;
                ba = new Billing_order(billing_address_for_send.getGiven_name(),
                        billing_address_for_send.getFamily_name(), billing_address_for_send.getStreet_address(),
                        billing_address_for_send.getStreet_address2(), billing_address_for_send.getCity(),
                        billing_address_for_send.getRegion(), billing_address_for_send.getPostal_code(),
                        billing_address_for_send.getCountry(),
                        billing_address_for_send.getEmail(), billing_address_for_send.getPhone());
                so = new Shipping_order(shipping_address_for_send.getGiven_name(),
                        shipping_address_for_send.getFamily_name(), shipping_address_for_send.getStreet_address(),
                        shipping_address_for_send.getStreet_address2(), shipping_address_for_send.getCity(),
                        shipping_address_for_send.getRegion(), shipping_address_for_send.getPostal_code(),
                        shipping_address_for_send.getCountry());



                ArrayList<Shipping_lines> sl = new ArrayList<>();
                if (order_amount < 2999){
                    sl.add(new Shipping_lines("pr_dhl_paket", "DHL Paket", "6"));
                }else{
                    sl.add(new Shipping_lines("pr_dhl_paket", "DHL Paket", "0"));
                }

                Sendorder sc = new Sendorder("kco", "Klarna",
                        order_id, true, Integer.parseInt(obj.getString("user_id")), am,
                        ba, so, sl);


                Call<Sendorder> call = order_complete.order_com(sc);
                call.enqueue(new Callback<Sendorder>() {
                    @Override
                    public void onResponse(Call<Sendorder> call, Response<Sendorder> response) {
                        dismissProgress();
                        if (response.isSuccessful()) {
                            // user object available
//                            Log.d("Paymentwebactiivty", "iss>body" + response.body().toString());
                            Intent intent = new Intent(PaymentKlarnaWebActivity.this, ThankYouActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
//                            Log.d("Paymentwebactiivty", "iss>bodyda" + response);
                            Toast.makeText(PaymentKlarnaWebActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//                                Log.w("Paymentwebactiivty.2.0 getFeed > Full json res wrapped in gson => ",new Gson().toJson(response));
                            // error response, no access to resource?
                        }
                    }

                    @Override
                    public void onFailure(Call<Sendorder> call, Throwable t) {
                        // something went completely south (like no internet connection)
//                        Log.d("Paymentwebactiivty", "Erro" + t.getMessage());

                        dismissProgress();
                        Toast.makeText(PaymentKlarnaWebActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                dismissProgress();
                Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}