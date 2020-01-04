package com.shop.arram.javaclasses;

import android.util.Log;

import com.shop.arram.model.CategoryList;
import com.shop.arram.model.Variation;
import com.shop.arram.utils.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Bhumi Shah on 11/29/2017.
 */

public class CheckIsVariationAvailable {
    private List<CategoryList.Attribute> list = new ArrayList<>();

    private int tempPosition = -1;
    public static String pricehtml;
    public static float price;
    public static float taxPrice;
    public static int stockQuantity;
    public static boolean inStock = false;
    public static String imageSrc;
    public static boolean isManageStock = false;
    List<CategoryList.Attribute> tempAttrList = new ArrayList<>();
    public boolean isAnyAddedInList = false;
    List<Variation> attributeList = new ArrayList<>();


    public boolean isVariationAvailbale(Map<Integer, String> combination, List<Variation> variationList, List<CategoryList.Attribute> list) {
        this.list = list;
        String comb = "";
        for (int i = 0; i < combination.size(); i++) {
            if (comb.equals("")) {
                comb = comb + combination.get(i);
            } else {
                if (combination.get(i) != null) {
                    if (!combination.get(i).equals(""))
                        comb = comb + "!" + combination.get(i);
                }
            }
        }

        if (getVariationList(variationList, comb, list).size() > 0) {

            return true;
        }
        return false;
    }


    public List<CategoryList.Attribute> getVariationList(List<Variation> list, String name, List<CategoryList.Attribute> catlist) {
        this.list = catlist;
        attributeList = list;
        tempAttrList = new ArrayList<>();
        isAnyAddedInList = false;
        for (int i = 0; i < list.size(); i++) {

            if (containOption(list.get(i).attributes, name)) {
                for (int j = 0; j < this.list.size(); j++) {
                    try {
                        if (containsTempList(tempAttrList, list.get(i).attributes.get(j).id)) {
                            if (!tempAttrList.get(tempPosition).options.contains(list.get(i).attributes.get(j).option))
                                tempAttrList.get(tempPosition).options.add(list.get(i).attributes.get(j).option);

                        } else {
                            CategoryList.Attribute object = new CategoryList().getAttributeInstance();
                            object.id = list.get(i).attributes.get(j).id;
                            object.name = list.get(i).attributes.get(j).name;
                            List<String> optionList = new ArrayList<>();
                            optionList.add(list.get(i).attributes.get(j).option);
                            object.options = optionList;
                            tempAttrList.add(object);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        if (!tempAttrList.contains(this.list.get(j)))
                            tempAttrList.add(this.list.get(j));
                    }

                }
            }
        }

        return tempAttrList;
    }

    public boolean containsTempList(List<CategoryList.Attribute> list, long id) {
        tempPosition = -1;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).id == id) {
                tempPosition = i;
                return true;
            }
        }

        tempPosition = -1;
        return false;
    }

    public boolean containOption(List<Variation.Attribute> list, String name) {
        String[] nameArray = name.split("!");
        if (nameArray.length > 0) {
            for (int i = 0; i < nameArray.length; i++) {

                if (!isVariationContain(list, nameArray[i])) {
                    return false;
                }
            }
        }

        return true;
    }


    public boolean isVariationContain(List<Variation.Attribute> list, String name) {

        for (int j = 0; j < this.list.size(); j++) {
            try {
                if ((list.get(j).name + "->" + list.get(j).option).equals(name)) {
                    return true;
                }
            } catch (IndexOutOfBoundsException e) {

                for (int i = 0; i < this.list.get(j).options.size(); i++) {
                    if ((this.list.get(j).name + "->" + this.list.get(j).options.get(i)).equals(name)) {
                        return true;
                    } else if (!name.contains(this.list.get(j).name)) {
                        if (!isAttributeContain(name)) {
                            if (!isAnyAddedInList) {
                                if (name.contains("->")) {
                                    Log.e("Any Varation ", "Called");
                                    String[] array = name
                                            .split("->");
                                    if (array.length > 0) {
                                        CategoryList.Attribute object = new CategoryList().getAttributeInstance();
                                        object.id = 0;
                                        object.name = array[0];
                                        List<String> optionList = new ArrayList<>();
                                        optionList.add(array[1]);
                                        object.options = optionList;
                                        tempAttrList.add(object);
                                    }
                                }
                                isAnyAddedInList = true;
                            }


                            return true;
                        } else {
                            return false;
                        }

                    }
                }
            }

        }
        return false;
    }

    public boolean isAttributeContain(String name) {
        int count = 0;
        for (int i = 0; i < this.attributeList.size(); i++) {
            for (int j = 0; j < attributeList.get(i).attributes.size(); j++) {
                if (name.contains(this.attributeList.get(i).attributes.get(j).name)) {
                    return true;
                }
            }

        }
        return false;
    }

    public int getVariationid(List<Variation> variationList, List<String> selectAttribute) {

        int count = 0;
        for (int i = 0; i < variationList.size(); i++) {
            count = 0;
            for (int j = 0; j < variationList.get(i).attributes.size(); j++) {

                if (selectAttribute.contains(variationList.get(i).attributes.get(j).name + "->" + variationList.get(i).attributes.get(j).option)) {
                    count = count + 1;
                }
            }

            if (count == variationList.get(i).attributes.size() && count != 0) {
                pricehtml = variationList.get(i).priceHtml;
                stockQuantity = variationList.get(i).stockQuantity;
                imageSrc = variationList.get(i).image.src;
                isManageStock = variationList.get(i).manageStock;
                inStock = variationList.get(i).inStock;
                if (!variationList.get(i).price.equals("")) {
                    price = Float.parseFloat(getPrice(variationList.get(i).price));
                } else {
                    price = 0;
                }
                if (variationList.get(i).taxPrice != null && !variationList.get(i).taxPrice.equals("") && variationList.get(i).taxStatus.equals("taxable")) {
                    try {

                        taxPrice = Float.parseFloat(getPrice(variationList.get(i).taxPrice));

                    } catch (Exception e) {
                        Log.e("Exception is ", e.getMessage());
                        taxPrice = 0;
                    }
                } else {
                    taxPrice = 0;
                }

                return variationList.get(i).id;
            } else if (count == 0 && variationList.get(i).attributes.size() == 0) {
                pricehtml = variationList.get(i).priceHtml;
                stockQuantity = variationList.get(i).stockQuantity;
                imageSrc = variationList.get(i).image.src;
                isManageStock = variationList.get(i).manageStock;
                if (!variationList.get(i).price.equals("")) {
                    price = Float.parseFloat(getPrice(variationList.get(i).price));
                } else {
                    price = 0;
                }
                if (variationList.get(i).taxPrice != null && !variationList.get(i).taxPrice.equals("") && variationList.get(i).taxStatus.equals("taxable")) {
                    try {
                        taxPrice = Float.parseFloat(getPrice(variationList.get(i).taxPrice));
                    } catch (Exception e) {
                        Log.e("Exception is ", e.getMessage());
                        taxPrice = 0;
                    }
                } else {
                    taxPrice = 0;
                }

            }

        }


        return 0;
    }

    public String getPrice(String price) {
        price = price.replace("\\s+", "");
        price = price.replace(Constant.THOUSANDSSEPRETER, "");
        return price;
    }
}
