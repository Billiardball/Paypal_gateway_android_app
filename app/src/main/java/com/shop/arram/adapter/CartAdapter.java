package com.shop.arram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.shop.arram.R;
import com.shop.arram.activity.ProductDetailActivity;
import com.shop.arram.customview.MaterialRatingBar;
import com.shop.arram.customview.swipeview.ViewBinderHelper;
import com.shop.arram.customview.textview.TextViewBold;
import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.helper.DatabaseHelper;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.model.Cart;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class CartAdapter extends RecyclerView.Adapter {

    private List<Cart> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;
    private final ViewBinderHelper binderHelper = new ViewBinderHelper();
    private DatabaseHelper databaseHelper;
    String value;
    private int isBuynow = 0;


    public CartAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
        databaseHelper = new DatabaseHelper(activity);
        binderHelper.setOpenOnlyOne(true);
    }

    public void addAll(List<Cart> list) {
        this.list = list;
        getWidthAndHeight();
        notifyDataSetChanged();
    }

    public void isFromBuyNow(int isBuynow) {
        this.isBuynow = isBuynow;
    }

    public List<Cart> getList() {
        return list;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);

        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder h, final int position) {
        final CartViewHolder holder = (CartViewHolder) h;
        if (list != null && 0 <= position && position < list.size()) {
            // bindview start
            final String data = list.get(position).getCartId() + "";

            // Use ViewBindHelper to restore and save the open/close state of the SwipeRevealView
            // put an unique string id as value, can be any string which uniquely define the data
            binderHelper.closeLayout(position + "");
            if (!list.get(position).getCategoryList().averageRating.equals("")) {
                holder.ratingBar.setRating(Float.parseFloat(list.get(position).getCategoryList().averageRating));
            } else {
                holder.ratingBar.setRating(0);
            }
            if (list.get(position).getCategoryList().images.size() > 0) {
                holder.ivImage.setVisibility(View.VISIBLE);
                Picasso.with(activity).load(list.get(position).getCategoryList().appthumbnail).into(holder.ivImage);
            } else {
                holder.ivImage.setVisibility(View.INVISIBLE);
            }

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            tvProductName.setText(categoryList.name + "");
                holder.tvName.setText(Html.fromHtml(list.get(position).getCategoryList().name + "", Html.FROM_HTML_MODE_LEGACY));
            } else {
//            tvProductName.setText(categoryList.name + "");
                holder.tvName.setText(Html.fromHtml(list.get(position).getCategoryList().name + ""));
            }


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.tvPrice.setText(Html.fromHtml(list.get(position).getCategoryList().priceHtml, Html.FROM_HTML_MODE_COMPACT));

            } else {
                holder.tvPrice.setText(Html.fromHtml(list.get(position).getCategoryList().priceHtml));
            }
            holder.tvPrice.setTextSize(15);

            ((BaseActivity) activity).setPrice(holder.tvPrice, holder.tvPrice1, list.get(position).getCategoryList().priceHtml);

            holder.tvQuantity.setTextColor(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
            holder.tvQuantity.setText(list.get(position).getQuantity() + "");

            holder.tvIncrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quntity = Integer.parseInt(holder.tvQuantity.getText().toString());
                    quntity = quntity + 1;

                    if (list.get(position).isManageStock()) {
                        if (quntity > list.get(position).getStockQuantity()) {
                            Toast.makeText(activity, ((BaseActivity) activity).getString(R.string.only) + "" + list.get(position).getStockQuantity() + "" + ((BaseActivity) activity).getString(R.string.quntity_is_avilable), Toast.LENGTH_SHORT).show();
                        } else {

                            holder.tvQuantity.setText(quntity + "");
                            databaseHelper.updateQuantity(quntity, list.get(position).getProductid(), list.get(position).getVariationid() + "");
                            list.get(position).setQuantity(quntity);
                            onItemClickListner.onItemClick(position, RequestParamUtils.increment, quntity);
                        }
                    } else {
                        holder.tvQuantity.setText(quntity + "");
                        databaseHelper.updateQuantity(quntity, list.get(position).getProductid(), list.get(position).getVariationid() + "");
                        list.get(position).setQuantity(quntity);
                        onItemClickListner.onItemClick(position, RequestParamUtils.increment, quntity);
                    }

                }
            });

            holder.tvDecrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quntity = Integer.parseInt(holder.tvQuantity.getText().toString());
                    quntity = quntity - 1;
                    if (quntity < 1) {
                        quntity = 1;
                    }
                    holder.tvQuantity.setText(quntity + "");
                    databaseHelper.updateQuantity(quntity, list.get(position).getProductid(), list.get(position).getVariationid() + "");
                    list.get(position).setQuantity(quntity);
                    onItemClickListner.onItemClick(position, RequestParamUtils.decrement, quntity);
                }
            });

            holder.llMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isBuynow == 0) {
                        if (list.get(position).getCategoryList().type.equals(RequestParamUtils.external)) {

                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getCategoryList().externalUrl));
                            activity.startActivity(browserIntent);
                        } else {
                            Constant.CATEGORYDETAIL = list.get(position).getCategoryList();
                            Intent intent = new Intent(activity, ProductDetailActivity.class);
                            intent.putExtra(RequestParamUtils.ID, list.get(position).getCategoryList().id);
                            activity.startActivity(intent);
                        }

                    }

                }
            });

            try {
                JSONObject jObject = new JSONObject(list.get(position).getVariation());
                Iterator iter = jObject.keys();
                value = "";
                while (iter.hasNext()) {
                    String key = (String) iter.next();
                    if (value.length() == 0) {
                        value = value + key + " : " + jObject.getString(key);
                    } else {
                        value = value + ", " + key + " : " + jObject.getString(key);
                    }
                }
            } catch (Exception e) {
                Log.e("exception is ", e.getMessage());
            }
            holder.txtVariation.setText(value + "");

            holder.ll_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).getCategoryList().type.equals(RequestParamUtils.variable)) {
                        databaseHelper.deleteVariationProductFromCart(list.get(position).getProductid(), list.get(position).getVariationid() + "");
                    } else {
                        databaseHelper.deleteFromCart(list.get(position).getProductid());
                    }
                    list.remove(position);
                    onItemClickListner.onItemClick(position, RequestParamUtils.delete, 0);
                    notifyDataSetChanged();
                }
            });
            //bind view over
        }

    }

    public void saveStates(Bundle outState) {
        binderHelper.saveStates(outState);
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in {@link android.app.Activity#onRestoreInstanceState(Bundle)}
     */
    public void restoreStates(Bundle inState) {
        binderHelper.restoreStates(inState);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.llMain)
        LinearLayout llMain;


        @BindView(R.id.ratingBar)
        MaterialRatingBar ratingBar;

        @BindView(R.id.ivImage)
        ImageView ivImage;

        @BindView(R.id.tvName)
        TextViewRegular tvName;

        @BindView(R.id.tvPrice)
        TextViewRegular tvPrice;

        @BindView(R.id.tvPrice1)
        TextViewRegular tvPrice1;

        @BindView(R.id.txtVariation)
        TextViewLight txtVariation;

        @BindView(R.id.tvQuantity)
        TextViewBold tvQuantity;

        @BindView(R.id.tvIncrement)
        ImageView tvIncrement;

        @BindView(R.id.tvDecrement)
        ImageView tvDecrement;

        @BindView(R.id.ivDelete)
        ImageView ivDelete;

        @BindView(R.id.ll_delete)
        LinearLayout ll_delete;


        public CartViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public void getWidthAndHeight() {
        int height_value = activity.getResources().getInteger(R.integer.height);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels / 2 - 20;
        height = width - height_value;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}