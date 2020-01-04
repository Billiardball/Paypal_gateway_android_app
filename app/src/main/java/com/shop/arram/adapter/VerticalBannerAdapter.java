package com.shop.arram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shop.arram.R;
import com.shop.arram.activity.CategoryListActivity;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.model.Home;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class VerticalBannerAdapter extends RecyclerView.Adapter<VerticalBannerAdapter.CategoryViewHolder> {

    private List<Home.BannerAd> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;

    public VerticalBannerAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }

    public void addAll(List<Home.BannerAd> list) {
        this.list = list;
        getWidthAndHeight();
        notifyDataSetChanged();
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vertical_banner, parent, false);

        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, final int position) {
        holder.llMain.getLayoutParams().height = height;
        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, CategoryListActivity.class);
                intent.putExtra(RequestParamUtils.CATEGORY, list.get(position).bannerAdCatId);
                intent.putExtra(RequestParamUtils.IS_WISHLIST_ACTIVE, Constant.IS_WISH_LIST_ACTIVE);
                activity.startActivity(intent);
            }
        });

        if (list.get(position).bannerAdImageUrl != null && !list.get(position).bannerAdImageUrl.equals("")) {
            Picasso.with(activity).load(list.get(position).bannerAdImageUrl).error(R.drawable.no_image_available).into(holder.ivBanner);
        } else {
            holder.ivBanner.setBackgroundResource(R.drawable.no_image_available);
        }

//        Picasso.with(activity).load(list.get(position).bannerAdImageUrl).error(R.drawable.banner).into(holder.ivBanner);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getWidthAndHeight() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        height = width / 3;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.llMain)
        LinearLayout llMain;

        @BindView(R.id.ivBanner)
        ImageView ivBanner;


        public CategoryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}