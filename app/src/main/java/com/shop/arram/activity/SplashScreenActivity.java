package com.shop.arram.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ParseException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ciyashop.library.apicall.PostApi;
import com.ciyashop.library.apicall.URLS;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.raygun.raygun4android.RaygunClient;
import com.shop.arram.R;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.model.CategoryList;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;
import com.shop.arram.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreenActivity extends BaseActivity {

    private static final String TAG = "SplashScreenActivity";
    @BindView(R.id.tvSplashText)
    TextViewRegular tvSplashText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);


        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        tvSplashText.setTextColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        Constant.DEVICE_TOKEN = refreshedToken;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            int systemtime = Settings.Global.getInt(getContentResolver(), Settings.Global.AUTO_TIME, 0);
            Log.e("System time is ", systemtime + " ");
        } else {
            int systemtime = Settings.System.getInt(getContentResolver(), Settings.System.AUTO_TIME, 0);
            Log.e("System time is ", systemtime + " ");
        }
        if (Constant.DEVICE_TOKEN != null && !Constant.DEVICE_TOKEN.equals("")) {
            if (getPreferences().getString(RequestParamUtils.DEVICE_TOKEN, "").equals("") ||
                    !getPreferences().getString(RequestParamUtils.DEVICE_TOKEN, "").equals(Constant.DEVICE_TOKEN)) {
                addDeviceToken();

            }
        }
        Log.e("token", "Refreshed token: " + refreshedToken);
        if (mayRequestPermission()) {
            setData();
        }

        Utils.printHashKey(this);
    }

    public void getNotification() {
        if (getIntent() != null) {
            Intent intent = new Intent(this, HomeActivity.class);
            if (getIntent().getExtras() != null) {
                String noteCode = getIntent().getExtras().getString("not_code");
                if (noteCode != null) {
                    if (Integer.parseInt(noteCode) == 1) {
                        intent = new Intent(this, RewardsActivity.class);
                        intent.putExtra(RequestParamUtils.Splashscreen, true);
                    } else if (Integer.parseInt(noteCode) == 2) {
                        intent = new Intent(this, MyOrderActivity.class);
                        intent.putExtra(RequestParamUtils.Splashscreen, true);
                    } else if (Integer.parseInt(noteCode) == 3) {
                        intent = new Intent(this, HomeActivity.class);
                        intent.putExtra(RequestParamUtils.Splashscreen, true);
                    }
                }


                startactivityIntent(intent);
            } else {
                startactivityIntent(new Intent(SplashScreenActivity.this, HomeActivity.class));
            }
        } else {
            startactivityIntent(new Intent(SplashScreenActivity.this, HomeActivity.class));
        }
    }

    public void startactivityIntent(final Intent intent) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

    @Override
    public Uri getReferrer() {

        // There is a built in function available from SDK>=22
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            return super.getReferrer();
        }

        Intent intent = getIntent();
        Uri referrer = (Uri) intent.getExtras().get("android.intent.extra.REFERRER");
        if (referrer != null) {
            return referrer;
        }

        String referrerName = intent.getStringExtra("android.intent.extra.REFERRER_NAME");

        if (referrerName != null) {
            try {
                Log.e("Exception is ", referrerName);
                return Uri.parse(referrerName);
            } catch (ParseException e) {
                Log.e("Exception is ", e.getMessage());
                // ...
            }
        }

        return null;
    }


    private boolean mayRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    1212);

            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 1212) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setData();
            } else {
                Snackbar.make(findViewById(R.id.crMain), "Permission must be need", Snackbar.LENGTH_INDEFINITE)
                        .setAction(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            @TargetApi(Build.VERSION_CODES.M)
                            public void onClick(View v) {
                                final Intent i = new Intent();
                                i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                i.addCategory(Intent.CATEGORY_DEFAULT);
                                i.setData(Uri.parse("package:" + getPackageName()));
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(i);
                            }
                        }).show();
            }
        }
    }


    public void setData() {
        buildGoogleApiClient();


        /*Added For Deep Linking By Nirav Shah 28-08-2018*/

        final Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        Log.e("Data From deeplink", data + " null");


        if (data != null) {

            Log.e(TAG, "setData: " + data.toString());
            FirebaseDynamicLinks.getInstance()
                    .getDynamicLink(getIntent())
                    .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                        @Override
                        public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                            //Get dynamic link from result (may be null if no link is found)

                            Uri deepLink = null;
                            if (pendingDynamicLinkData != null) {
                                deepLink = pendingDynamicLinkData.getLink();
                            }
                            if (deepLink != null) {
                                Log.e("onSuccessDeepLink: ", "" + deepLink);

                                String[] separated = deepLink.toString().split("#");
                                String path1 = separated[0];
                                String path2 = separated[1];


                                Log.e("lastPathSegment: ", "" + path2);

                                if (path2 != null && path2.length() > 0) {
                                    getProductDetails(path2);
                                } else {

                                }

                            } else {
                                Log.e("", "DeepLink No Link ");
                                String data = intent.getDataString();
                                String action = intent.getAction();
                                if (Intent.ACTION_VIEW.equals(action) && data != null) {

                                    String[] separated = data.toString().split("#");
                                    String path1 = separated[0];
                                    String path2 = separated[1];

                                    if (path2 != null && path2.length() > 0) {
                                        getProductDetails(path2);
                                    } else {

                                    }


                                } else {
                                    startactivityIntent(new Intent(SplashScreenActivity.this, HomeActivity.class));
                                }
                            }
                        }
                    })
                    .addOnFailureListener(this, new OnFailureListener() {

                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("", "getDynamicLink : onFailure" + e.getMessage());
                            Log.e("", "getDynamicLink : onFailure" + e.toString());
                        }
                    });

            /*Deeplink Over */
        } else {


            getNotification();
        }


    }


    private void getProductDetails(String lastPathSegment) {

        if (Utils.isInternetConnected(SplashScreenActivity.this)) {
            showProgress("");
            PostApi postApi = new PostApi(SplashScreenActivity.this, RequestParamUtils.getProductDetail, this, getlanuage());
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(RequestParamUtils.INCLUDE, lastPathSegment);
                postApi.callPostApi(new URLS().PRODUCT_URL + (SplashScreenActivity.this).getPreferences().getString(RequestParamUtils.CurrencyText, ""), jsonObject.toString());
            } catch (Exception e) {
                Log.e("Json Exception", e.getMessage());
            }
        } else {
            Toast.makeText(SplashScreenActivity.this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }

    }

    public void addDeviceToken() {
        if (Utils.isInternetConnected(this)) {
            PostApi postApi = new PostApi(SplashScreenActivity.this, RequestParamUtils.addDeviceToken, this, getlanuage());
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(RequestParamUtils.DEVICE_TOKEN, Constant.DEVICE_TOKEN);
                jsonObject.put(RequestParamUtils.DEVICE_TYPE, RequestParamUtils.android);
                postApi.callPostApi(new URLS().ADDNOTIFICATION, jsonObject.toString());
            } catch (Exception e) {
                Log.e("Json Exception", e.getMessage());
            }
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResponse(String response, String methodName) {
        if (methodName.equals(RequestParamUtils.addDeviceToken)) {
            if (response != null && response.length() > 0) {
                try {
                    Log.e("Response is ", response);
                    getPreferences().edit().putString(RequestParamUtils.DEVICE_TOKEN, Constant.DEVICE_TOKEN).commit();
                } catch (Exception e) {
                    Log.e(methodName + "Gson Exception is ", e.getMessage());
                }
            }
        } else if (methodName.equals(RequestParamUtils.getProductDetail)) {
            if (response != null && response.length() > 0) {
                try {
                    finish();
                    JSONArray jsonArray = new JSONArray(response);
                    CategoryList categoryListRider = new Gson().fromJson(
                            jsonArray.get(0).toString(), new TypeToken<CategoryList>() {
                            }.getType());
                    Constant.CATEGORYDETAIL = categoryListRider;

                    if (categoryListRider.type.equals(RequestParamUtils.external)) {

                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(categoryListRider.externalUrl));
                        startActivity(browserIntent);
                    } else {
                        Intent intent = new Intent(SplashScreenActivity.this, ProductDetailActivity.class);
                        intent.putExtra(RequestParamUtils.fromdeeplink, true);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    Log.e(methodName + "Gson Exception is ", e.getMessage());
                }
                dismissProgress();
            }
        }
    }

}
