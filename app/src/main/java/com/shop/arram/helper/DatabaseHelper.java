package com.shop.arram.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shop.arram.model.Cart;
import com.shop.arram.model.CategoryList;
import com.shop.arram.model.SearchLive;
import com.shop.arram.model.WishList;
import com.shop.arram.utils.Debug;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Debugcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ciyashop";

    // Table Names
    private static final String TABLE_SEARCH_HISTORY = "search_history";
    private static final String TABLE_RECENT = "recent";
    private static final String TABLE_CART = "cart";
    private static final String TABLE_WISHLIST = "wishlist";

    // Common column search
    private static final String KEY_SEARCH_HISTORY_ID = "id";
    private static final String KEY_SEARCH_HISTORY_NAME = "name";


    // Common column RecentView
    private static final String KEY_RECENT_VIEW_ID = "id";
    private static final String KEY_PRODUCT_DETAIL = "product_detail";
    private static final String KEY_PRODUCT_ID = "product_id";


    // Common column RecentView
    private static final String KEY_WISHLIST_ID = "whishlist_id";
    private static final String KEY_WISHLIST_PRODUCT = "wishlist_product";


    // CART Table - column names
    private static final String KEY_CART_ID = "cart_id";
    private static final String KEY_QUANTITY = "cart_quantity";
    private static final String KEY_VARIATION = "cart_variation";
    private static final String KEY_VARIATION_ID = "cart_variation_id";
    private static final String KEY_PRODUCT = "cart_product";
    private static final String KEY_BUY_NOW = "is_buy_now";
    private static final String KEY_MANAGE_STOCK = "manage_stock";
    private static final String KEY_STOCK_QUANTITY = "stock_quantity";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_TODO = "CREATE TABLE "
            + TABLE_SEARCH_HISTORY + "(" + KEY_SEARCH_HISTORY_ID + " INTEGER PRIMARY KEY," + KEY_SEARCH_HISTORY_NAME
            + " TEXT" + ")";

    private static final String CREATE_TABLE_RECENT = "CREATE TABLE "
            + TABLE_RECENT + "(" + KEY_RECENT_VIEW_ID + " INTEGER PRIMARY KEY," + KEY_PRODUCT_DETAIL
            + " TEXT," + KEY_PRODUCT_ID
            + " TEXT" + ")";


    // cart_tag table create statement
    private static final String CREATE_TABLE_CART = "CREATE TABLE "
            + TABLE_CART + "(" + KEY_CART_ID + " INTEGER PRIMARY KEY,"
            + KEY_PRODUCT_ID + " TEXT," + KEY_QUANTITY + " INTEGER," + KEY_STOCK_QUANTITY + " INTEGER,"
            + KEY_MANAGE_STOCK + " TEXT," + KEY_PRODUCT + " TEXT,"
            + KEY_VARIATION + " TEXT," + KEY_VARIATION_ID + " INTEGER," + KEY_BUY_NOW + " INTEGER" + ")";


    private static final String CREATE_TABLE_WISHLIST = "CREATE TABLE "
            + TABLE_WISHLIST + "(" + KEY_WISHLIST_ID + " INTEGER PRIMARY KEY," + KEY_WISHLIST_PRODUCT
            + " TEXT," + KEY_PRODUCT_ID
            + " TEXT" + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_TODO);
        db.execSQL(CREATE_TABLE_RECENT);
        db.execSQL(CREATE_TABLE_CART);
        db.execSQL(CREATE_TABLE_WISHLIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEARCH_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WISHLIST);
        // create new tables
        onCreate(db);
    }

    // ------------------------ "todos" table methods ----------------//

    /*
     * Creating a todo
     */
    public long addToSearchHistory(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SEARCH_HISTORY_NAME, name);

        // insert row
        long todo_id = db.insert(TABLE_SEARCH_HISTORY, null, values);

        return todo_id;
    }

    public void clearSearch() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_SEARCH_HISTORY);
    }

    public void clearWhishlist() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_WISHLIST);
    }

    public void clearRecentItem() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_RECENT);
    }

    public boolean getSearchItem(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_SEARCH_HISTORY + " WHERE "
                + KEY_SEARCH_HISTORY_NAME + " = '" + name + "'";

        Debug.e(LOG, selectQuery);

//        Cursor c = db.rawQuery(selectQuery, null);
        Cursor c = db.query(TABLE_SEARCH_HISTORY,
                null,
                KEY_SEARCH_HISTORY_NAME + "=?",
                new String[]{String.valueOf(name)}, null, null, null, null);

        if (c != null) {
            if (c.moveToFirst()) {
                return true;
            }

        }
        return false;
    }

    /*
     * get single todo
     */
//

    /**
     * getting all todos
     */
    public List<SearchLive> getSearchHistoryList() {
        List<SearchLive> search = new ArrayList<SearchLive>();
        String selectQuery = "SELECT  * FROM " + TABLE_SEARCH_HISTORY + " ORDER BY " + KEY_SEARCH_HISTORY_ID + " DESC";

        Debug.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                SearchLive searchLive = new SearchLive(0, (c.getString(c.getColumnIndex(KEY_SEARCH_HISTORY_NAME))));
                search.add(searchLive);
            } while (c.moveToNext());
        }

        return search;
    }

    public long addTorecentView(String recentview, String id) {
        getRecentProduct(id);
        if (!id.equals("0")) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_PRODUCT_DETAIL, recentview);
            values.put(KEY_PRODUCT_ID, id);

            // insert row
            long recent_id = db.insert(TABLE_RECENT, null, values);

            return recent_id;
        }
        return 0;
    }


    public void getRecentProduct(String productid) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_RECENT + " WHERE "
                + KEY_PRODUCT_ID + " = '" + productid + "'";

        Debug.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null) {
            if (c.moveToFirst()) {
                deleteRecentProduct((c.getString(c.getColumnIndex(KEY_PRODUCT_ID))));
            }

        }

    }

    public void deleteRecentProduct(String productid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECENT, KEY_PRODUCT_ID + " = ?",
                new String[]{String.valueOf(productid)});
    }


    public List<CategoryList> getRecentViewList() {

        List<CategoryList> recentList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_RECENT + " ORDER BY " + KEY_RECENT_VIEW_ID + " DESC";

        Debug.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                try {
                    CategoryList categoryListRider = new Gson().fromJson(
                            (c.getString(c.getColumnIndex(KEY_PRODUCT_DETAIL))), new TypeToken<CategoryList>() {
                            }.getType());
                    recentList.add(categoryListRider);

                } catch (Exception e) {
                    Debug.e("Gson Exception", "in Recent Product Get" + e.getMessage());
                }

            } while (c.moveToNext());
        }

        return recentList;
    }


    //CART TABLE METHODS

    public long addToCart(Cart cart) {
        if (cart.getBuyNow() == 1) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_PRODUCT_ID, cart.getProductid());
            values.put(KEY_QUANTITY, cart.getQuantity());
            values.put(KEY_VARIATION, cart.getVariation());
            values.put(KEY_VARIATION_ID, cart.getVariationid());
            values.put(KEY_PRODUCT, cart.getProduct());
            values.put(KEY_BUY_NOW, cart.getBuyNow());
            values.put(KEY_STOCK_QUANTITY, cart.getStockQuantity());
            values.put(KEY_MANAGE_STOCK, cart.isManageStock() + "");


            // insert row
            long cart_id = db.insert(TABLE_CART, null, values);

            return cart_id;
        } else {
            if (getProductFromCart(cart)) {
                return updateCart(cart);
            } else {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(KEY_PRODUCT_ID, cart.getProductid());
                values.put(KEY_QUANTITY, cart.getQuantity());
                values.put(KEY_VARIATION, cart.getVariation());
                values.put(KEY_VARIATION_ID, cart.getVariationid());
                values.put(KEY_PRODUCT, cart.getProduct());
                values.put(KEY_BUY_NOW, cart.getBuyNow());
                values.put(KEY_STOCK_QUANTITY, cart.getStockQuantity());
                values.put(KEY_MANAGE_STOCK, cart.isManageStock() + "");

                // insert row
                long cart_id = db.insert(TABLE_CART, null, values);
                return cart_id;
            }
        }
    }


    public long addVariationProductToCart(Cart cart) {

        if (getVariationProductFromCart(cart)) {
            return updateCart(cart);
        } else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_PRODUCT_ID, cart.getProductid());
            values.put(KEY_QUANTITY, cart.getQuantity());
            values.put(KEY_VARIATION, cart.getVariation());
            values.put(KEY_VARIATION_ID, cart.getVariationid());
            values.put(KEY_PRODUCT, cart.getProduct());
            values.put(KEY_BUY_NOW, cart.getBuyNow());
            values.put(KEY_STOCK_QUANTITY, cart.getStockQuantity());
            values.put(KEY_MANAGE_STOCK, cart.isManageStock() + "");

            // insert row
            long cart_id = db.insert(TABLE_CART, null, values);

            return cart_id;
        }

    }

    public boolean getProductFromCart(Cart cart) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CART + " WHERE "
                + KEY_PRODUCT_ID + " = '" + cart.getProductid() + "'";

        Debug.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null) {
            if (c.moveToFirst()) {
                return true;
            } else {
                return false;
            }

        }
        return false;
    }

    public boolean getVariationProductFromCart(Cart cart) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CART + " WHERE "
                + KEY_PRODUCT_ID + " = '" + cart.getProductid() + "' and "
                + KEY_VARIATION + " = '" + cart.getVariation() + "' and "
                + KEY_VARIATION_ID + " = '" + cart.getVariationid() + "'";

        Debug.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null) {
            if (c.moveToFirst()) {
                return true;
            } else {
                return false;
            }

        }
        return false;
    }


    public CategoryList getProductFromCartById(String product_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CART + " WHERE "
                + KEY_PRODUCT_ID + " = '" + product_id + "'";

        Debug.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {

                Cart cart = new Cart();
                cart.setProductid((c.getString(c.getColumnIndex(KEY_PRODUCT_ID))));
                cart.setQuantity((c.getInt(c.getColumnIndex(KEY_QUANTITY))));
                cart.setVariation((c.getString(c.getColumnIndex(KEY_VARIATION))));
                cart.setVariationid((c.getInt(c.getColumnIndex(KEY_VARIATION_ID))));
                cart.setProduct((c.getString(c.getColumnIndex(KEY_PRODUCT))));
                cart.setBuyNow((c.getInt(c.getColumnIndex(KEY_BUY_NOW))));

                try {
                    CategoryList categoryListRider = new Gson().fromJson(cart.getProduct(), new TypeToken<CategoryList>() {
                    }.getType());
                    return categoryListRider;
                } catch (Exception e) {
                    Debug.e("Gson Exception", "in Recent Product Get" + e.getMessage());
                }
            } while (c.moveToNext());
        }

        return null;
    }


    public int updateQuantity(int quantity, String product_id, String variation_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_ID, product_id);
        values.put(KEY_QUANTITY, quantity);

        // updating row
        return db.update(TABLE_CART, values, KEY_PRODUCT_ID + " = ? AND " + KEY_VARIATION_ID + " = ?",
                new String[]{String.valueOf(product_id), String.valueOf(variation_id)});
    }

    public void deleteFromBuyNow(String productid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, KEY_BUY_NOW + " = ?",
                new String[]{"1"});
    }

    public void deleteFromCart(String productid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, KEY_PRODUCT_ID + " = ?",
                new String[]{String.valueOf(productid)});
    }

    public void deleteVariationProductFromCart(String productid,String variationId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, KEY_PRODUCT_ID + " = ? AND " + KEY_VARIATION_ID + " = ?",
                new String[]{String.valueOf(productid), String.valueOf(variationId)});
    }

    public void clearCart() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from " + TABLE_CART);

    }


    public int updateCart(Cart cart) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_ID, cart.getProductid());
        values.put(KEY_VARIATION, cart.getVariation());
        values.put(KEY_VARIATION_ID, cart.getVariationid());
        values.put(KEY_PRODUCT, cart.getProduct());
        values.put(KEY_BUY_NOW, cart.getBuyNow());

        // updating row
        return db.update(TABLE_CART, values, KEY_PRODUCT_ID + " = ?",
                new String[]{String.valueOf(cart.getProductid())});
    }


    public List<Cart> getFromCart(int buyNow) {

        List<Cart> cartList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CART + " Where " + KEY_BUY_NOW + " = " + buyNow;

        Debug.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Cart cart = new Cart();
                cart.setProductid((c.getString(c.getColumnIndex(KEY_PRODUCT_ID))));
                cart.setQuantity((c.getInt(c.getColumnIndex(KEY_QUANTITY))));
                cart.setVariation((c.getString(c.getColumnIndex(KEY_VARIATION))));
                cart.setVariationid((c.getInt(c.getColumnIndex(KEY_VARIATION_ID))));
                cart.setProduct((c.getString(c.getColumnIndex(KEY_PRODUCT))));
                cart.setBuyNow((c.getInt(c.getColumnIndex(KEY_BUY_NOW))));
                cart.setCartId((c.getInt(c.getColumnIndex(KEY_CART_ID))));
                cart.setStockQuantity((c.getInt(c.getColumnIndex(KEY_STOCK_QUANTITY))));
                cart.setManageStock(Boolean.parseBoolean((c.getString(c.getColumnIndex(KEY_MANAGE_STOCK)))));


                cartList.add(cart);
            } while (c.moveToNext());
        }

        return cartList;
    }


    //WishList Query

    public long addToWishList(WishList wishList) {
        getWishlistProduct(wishList.getProductid());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WISHLIST_PRODUCT, wishList.getProduct());
        values.put(KEY_PRODUCT_ID, wishList.getProductid());

        // insert row
        long wishlist_id = db.insert(TABLE_WISHLIST, null, values);

        return wishlist_id;

    }

    public boolean getWishlistProduct(String productid) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_WISHLIST + " WHERE "
                + KEY_PRODUCT_ID + " = '" + productid + "'";

        Debug.e(LOG, selectQuery);
        Cursor c = null;
        try {
            c = db.rawQuery(selectQuery, null);

            if (c != null) {
                if (c.moveToFirst()) {
                    return true;
                }

            }
        } catch (Exception e) {
            Debug.e("Exception is ", e.getMessage());

        } finally {
            // this gets called even if there is an exception somewhere above
            if (c != null)
                c.close();

        }


        return false;
    }


    public void deleteFromWishList(String productid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WISHLIST, KEY_PRODUCT_ID + " = ?",
                new String[]{String.valueOf(productid)});
    }


    public List<String> getWishList() {

        List<String> cartList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_WISHLIST;

        Debug.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                cartList.add((c.getString(c.getColumnIndex(KEY_PRODUCT_ID))));

            } while (c.moveToNext());
        }

        return cartList;
    }


    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
