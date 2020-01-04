package com.shop.arram.model;

/**
 * Created by Bhumi Shah on 12/5/2017.
 */

public class WishList {

    private String productid;
    private CategoryList categoryList;
    private String product;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public CategoryList getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(CategoryList categoryList) {
        this.categoryList = categoryList;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
