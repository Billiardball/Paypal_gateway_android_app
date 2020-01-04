package com.shop.arram.javaclasses;

import com.shop.arram.model.FilterOtherOption;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhumi Shah on 11/30/2017.
 */

public class FilterSelectedList {

    public static boolean isFilterCalled = false;
    public static String filterJson = "";
    public static int minPrice = 0, maxPrice = 0;
    public static String cat_id = "";

    public static List<FilterOtherOption> selectedColorOptionList = new ArrayList<>();
    public static List<FilterOtherOption> selectedOtherOptionList = new ArrayList<>();



    public static void clearFilter() {
        minPrice = 0;
        maxPrice = 0;
        selectedOtherOptionList = new ArrayList<>();
        selectedColorOptionList = new ArrayList<>();

    }


}
