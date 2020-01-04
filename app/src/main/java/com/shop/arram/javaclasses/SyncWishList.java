package com.shop.arram.javaclasses;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.ciyashop.library.apicall.PostApi;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shop.arram.R;
import com.shop.arram.helper.DatabaseHelper;
import com.shop.arram.model.SyncWishListModel;
import com.shop.arram.model.WishList;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.RequestParamUtils;
import com.shop.arram.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Bhumi Shah on 12/7/2017.
 */

public class SyncWishList implements OnResponseListner {

    private Activity context;
    private DatabaseHelper databaseHelper;

    public SyncWishList(Activity context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    public String getWishListData(String userid) {
        JSONObject jsonObject = new JSONObject();
        List<String> localWishListData = databaseHelper.getWishList();

        if (localWishListData != null) {
            try {

                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < localWishListData.size(); i++) {
                    JSONObject object = new JSONObject();
                    object.put(RequestParamUtils.PRODUCT_ID, localWishListData.get(i));
                    object.put(RequestParamUtils.USER_ID, userid);
                    object.put(RequestParamUtils.quantity, 1);
                    object.put(RequestParamUtils.wishlistName, "");
                    jsonArray.put(object);
                }
                jsonObject.put(RequestParamUtils.syncList, jsonArray);
                jsonObject.put(RequestParamUtils.user_id, userid);
            } catch (Exception e) {
                Log.e("Json Exception", e.getMessage());
            }
            return jsonObject.toString();
        }
        return null;
    }

    public void syncWishList(String userid, boolean isDialogShow) {
        if (Utils.isInternetConnected(context)) {
//            ((BaseActivity) context).showProgress("");
            PostApi postApi = new PostApi(context, RequestParamUtils.syncWishList, this, ((BaseActivity) context).getlanuage());
            postApi.callPostApi(new URLS().WISHLIST, getWishListData(userid));
        } else {
            Toast.makeText(context, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onResponse(String response, String methodName) {
        if (methodName.equals(RequestParamUtils.syncWishList)) {
            if (response != null && response.length() > 0) {
                try {
                    SyncWishListModel syncWishListRider = new Gson().fromJson(
                            response, new TypeToken<SyncWishListModel>() {
                            }.getType());

                    for (int i = 0; i < syncWishListRider.syncList.size(); i++) {
                        if (!databaseHelper.getWishlistProduct(syncWishListRider.syncList.get(i).prodId)) {
                            WishList wishList = new WishList();
                            wishList.setProductid(syncWishListRider.syncList.get(i).prodId);
                            databaseHelper.addToWishList(wishList);
                        }
                    }

                    ((BaseActivity) context).dismissProgress();
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }
        }
    }
}
