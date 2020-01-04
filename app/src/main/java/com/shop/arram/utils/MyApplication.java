package com.shop.arram.utils;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.multidex.MultiDex;

import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.applinks.AppLinkData;
import com.onesignal.OneSignal;
import com.pixplicity.easyprefs.library.Prefs;
import com.raygun.raygun4android.RaygunClient;
import com.shop.arram.fcm.MyNotificationOpenedHandler;
import com.shop.arram.fcm.MyNotificationReceivedHandler;

import io.fabric.sdk.android.Fabric;


/**
 * Created by Bhumi Shah on 12/23/2017.
 */

public class MyApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        RaygunClient.init(this);
        RaygunClient.enableCrashReporting();
        Fabric.with(this, new Crashlytics());
        new APIS();
        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(Constant.MyPREFERENCES)
                .setUseDefaultSharedPreference(true)
                .build();


        AppLinkData.fetchDeferredAppLinkData(this,
                new AppLinkData.CompletionHandler() {
                    @Override
                    public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                        // Process app link data
                    }
                });

        OneSignal.startInit(this)
                .setNotificationOpenedHandler(new MyNotificationOpenedHandler())
                .setNotificationReceivedHandler(new MyNotificationReceivedHandler())
                .init();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
