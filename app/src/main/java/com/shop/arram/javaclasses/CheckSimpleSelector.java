package com.shop.arram.javaclasses;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bhumi Shah on 12/4/2017.
 */

public class CheckSimpleSelector {

    public static Map<String, String> selectedList;

    public static void setSelectList() {
        selectedList = new HashMap<>();
    }

    public static void setSelectedItem(String key, String value) {
        selectedList.put(key, value);
    }


}
