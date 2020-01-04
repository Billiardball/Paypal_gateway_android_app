package com.shop.arram.utils;

import android.util.Log;


public class Debug {

    public static final boolean DEBUG = false;

    public static void e(String tag, String msg) {
        if (DEBUG) {
            if (tag.isEmpty())
                tag = "unknown";

            Log.e(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            if (tag.isEmpty())
                tag = "unknown";
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            if (tag.isEmpty())
                tag = "unknown";
            Log.w(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            if (tag.isEmpty())
                tag = "unknown";
            Log.d(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (DEBUG) {
            if (tag.isEmpty())
                tag = "unknown";
            Log.v(tag, msg);
        }
    }

}
