package com.shop.arram.model;


import android.app.Activity;

import com.shop.arram.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by UV on 29-Nov-16.
 */
public class NavigationList {
    public static NavigationList _naviNavigationList;
    public static List<Integer> imageList = new ArrayList<>();
    public static List<String> titleList = new ArrayList<>();

    public static NavigationList getInstance(Activity activity) {
        if (_naviNavigationList == null) {
            _naviNavigationList = new NavigationList();
        }
        imageList = Arrays.asList(R.drawable.ic_notification_white,R.drawable.ic_my_rewards,
                R.drawable.ic_cart_white, R.drawable.ic_my_wishlist_white, R.drawable.ic_my_account, R.drawable.ic_my_order);
        titleList = Arrays.asList(activity.getString(R.string.notification),activity.getString(R.string.my_reward), activity.getString(R.string.my_cart),
                activity.getString(R.string.my_wish_list), activity.getString(R.string.my_account), activity.getString(R.string.my_orders));
        return _naviNavigationList;
    }
    public static List<Integer> getImageList() {
        return imageList;
    }

    public static List<String> getTitleList() {
        return titleList;
    }

}
