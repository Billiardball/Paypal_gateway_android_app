package com.shop.arram.utils;

import android.app.Activity;
import android.util.Log;

import com.shop.arram.R;
import com.shop.arram.model.CategoryList;
import com.shop.arram.model.Home;
import com.shop.arram.model.Orders;
import com.shop.arram.model.Sort;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Bhumi Shah on 11/21/2017.
 */

public class Constant {

    public static final String MyPREFERENCES = "com.shop.arram"; // Add your package name

    public static final String PACKAGE_NAME = "com.shop.arram";  // Add your package name

    public static final String DeepLinkDomain = "example.page.link";// Add your Deeplink Domain URL

    public static final String DynamicLinkIosParameters = "com.shop.arram"; // Add your Deeplink IOS package name if you have

    public static final int PlaystoreMinimumVersion = 1; // add your playstore version


    public static final String AppleAppStoreId = "1234567890";  // Apple Application AppStoreId

    public static final String AppleAppVersion = "1.0"; // Apple App version

    public static final String DEVICE_TYPE = "2";

    public static final String MOBILE_COUNTRY_CODE = "+91"; // Add your mobile country Code

    public static final boolean IS_DOWNLOAD_SHOW = false;


    public static final int DISTANCERANGE = 500;
    public static final String PRIMARY_COLOR = "#04d39f";
    public static String INFO_PAGE_DATA = "";
    public static boolean IS_WISH_LIST_ACTIVE = false;
    public static boolean IS_CURRENCY_SWITCHER_ACTIVE = false;
    public static boolean IS_ORDER_TRACKING_ACTIVE = false;
    public static boolean IS_GUEST_CHECKOUT_ACTIVE = false;
    public static boolean IS_REWARD_POINT_ACTIVE = false;
    public static boolean IS_WPML_ACTIVE = false;
    public static boolean IS_RTL = false;
    public static boolean IS_YITH_FEATURED_VIDEO_ACTIVE = false;
    public static int Decimal = 2;
    public static String THOUSANDSSEPRETER = ",";
    public static String DECIMALSEPRETER = ".";
    public static boolean IS_CURRENCY_SET = false;
    public static String SECONDARY_COLOR = "#04d39f";
    public static List<Home.AllCategory> MAINCATEGORYLIST = new ArrayList<>();
    public static CategoryList CATEGORYDETAIL = new CategoryList();
    public static Orders ORDERDETAIL = new Orders();
    public static String CURRENCYSYMBOL;
    public static String CURRENCYSYMBOLPref = "";
    public static String CURRENCYSYMBOLPOSTION = "left";
    public static Home.PgsAppSocialLinks SOCIALLINK;
    public static String APPLOGO = "";
    public static String CURRENCYCODE = "";

    public static String APPLOGO_LIGHT = "";

    public static String DEVICE_TOKEN = "";

    public static String APP_VER = "app-ver";

    public static String APP_COLOR = "primary_color";

    public static String SECOND_COLOR = "secondary_color";

    public static String HEADER_COLOR = "header_color";

    public static String HEAD_COLOR = "#04d39f";

    public static String ADDRESS_LINE1 = "address_line1";

    public static String ADDRESS_LINE2 = "address_line2";

    public static String PHONE = "phone_number";

    public static String WHATSAPP = "whatsapp_number";

    public static String WHATSAPPENABLE = "whatsappFloatingButton";

    public static String EMAIL = "email_address";

    public static String RTL = "is_rtl";

    public static float LAT;

    public static float LNG;

    public static String APP_TRANSPARENT = "colorPrimaryTransperent";

    public static String APP_TRANSPARENT_VERY_LIGHT = "colorPrimaryVeryLight";

    public static final String CIYASHOP_ = "Ciyahsop";
    public static final String CIYASHOP_CODE = "Ciyahsop";


    public static List<String> CurrencyList = new ArrayList<>();

    public static List<Home.WpmlLanguage> LANGUAGELIST = new ArrayList<>();

    public static List<String> CheckoutURL = new ArrayList<>();

    public static List<String> CancelUrl = new ArrayList<>();

    public static List<Sort> getSortList(Activity activity) {
        List<Sort> sortList = new ArrayList<>();
        if (sortList.size() == 0) {
            sortList.add(new Sort(activity.getString(R.string.newest_first), 0, ""));
            sortList.add(new Sort(activity.getString(R.string.rating), 1, "rating"));
            sortList.add(new Sort(activity.getString(R.string.popularity), 2, "popularity"));
            sortList.add(new Sort(activity.getString(R.string.low_to_high), 3, "price"));
            sortList.add(new Sort(activity.getString(R.string.high_to_low), 4, "price-desc"));
        }
        return sortList;
    }

    public static String setDecimalTwo(Double digit) {
        return new DecimalFormat("##.##").format(digit);
    }

    public static String setDecimal(Double digit) {

        String decimal = "#,##0.";

        if (Constant.Decimal == 0) {
            decimal = "#,##0";
        }
        if ((digit == Math.floor(digit)) && !Double.isInfinite(digit)) {
            // integer type
            for (int i = 0; i < Constant.Decimal; i++) {
                decimal = decimal + "0";
            }
        } else {
            for (int i = 0; i < Constant.Decimal; i++) {
                decimal = decimal + "#";
            }
        }


        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols(Locale.US);
        if (Constant.Decimal != 0 && !Constant.THOUSANDSSEPRETER.equals("")) {
            unusualSymbols.setDecimalSeparator((char) Constant.DECIMALSEPRETER.charAt(0));
        }

        if (!Constant.THOUSANDSSEPRETER.equals("")) {
            unusualSymbols.setGroupingSeparator(Constant.THOUSANDSSEPRETER.charAt(0));
        }

//        String strange = "#,##0.000";
        DecimalFormat weirdFormatter = new DecimalFormat(decimal, unusualSymbols);

        weirdFormatter.setGroupingSize(3);

        String data = weirdFormatter.format(digit);
        Log.e("data is ", data + "");

        return data + "";
    }

    public static String setDate(String str) {
        try {
            DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
            DateFormat targetFormat = new SimpleDateFormat("MMM dd, yyyy");
            Date date = originalFormat.parse(str);
            String formattedDate = targetFormat.format(date);  // 20120821
            return formattedDate;
        } catch (ParseException e) {
            Log.e("error", e.getMessage());
        }
        return null;
    }


}
