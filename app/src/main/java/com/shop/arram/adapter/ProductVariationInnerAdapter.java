package com.shop.arram.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.shop.arram.R;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class ProductVariationInnerAdapter extends RecyclerView.Adapter<ProductVariationInnerAdapter.ProductColorViewHolder> {

    private String outListId;
    private List<String> list = new ArrayList<>();
    private List<String> variationList = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;
    public int previousSelectionPosition;
    public int outerPosition, outerPreviousSelectedPosition, outerListSize;


    public ProductVariationInnerAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }


    public void getSizePosition(int outerListSize, int outerPreviousSelectedPosition) {
        this.outerListSize = outerListSize;
        this.outerPreviousSelectedPosition = outerPreviousSelectedPosition;

    }

    public void setOutListId(String outListId) {
        this.outListId = outListId;
    }

    public void setOuterPosition(int outerPosition) {
        this.outerPosition = outerPosition;
    }

    public void addAll(List<String> list) {
        this.list = list;
        getWidthAndHeight();
        notifyDataSetChanged();
    }

    public void addAllVariationList(List<String> variationList) {
        this.variationList = variationList;
        notifyDataSetChanged();
    }

    @Override
    public ProductColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_color, parent, false);

        return new ProductColorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProductColorViewHolder holder, final int position) {

        GradientDrawable gd = (GradientDrawable) holder.flTransparent.getBackground();
        gd.setColor(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_TRANSPARENT, Constant.PRIMARY_COLOR)));


        if (variationList != null && variationList.size() > 0) {
//            if (!variationList.contains(list.get(position)) && outerPosition != 0) {
//                gd = (GradientDrawable) holder.llMain.getBackground();
//                gd.setStroke(5, Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_TRANSPARENT_VERY_LIGHT, Constant.PRIMARY_COLOR)));
//
//                holder.tvName.setTextColor(activity.getResources().getColor(R.color.gray_table));
//            } else {
                gd = (GradientDrawable) holder.llMain.getBackground();
                gd.setStroke(5, Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
                holder.tvName.setTextColor(activity.getResources().getColor(R.color.gray));
//            }
        } else {
            if (outerPosition == 0) {
                holder.llMain.setBackgroundResource(R.drawable.primary_strok_button);
                holder.tvName.setTextColor(activity.getResources().getColor(R.color.gray));
            }
        }
        if (previousSelectionPosition == position) {
            holder.flTransparent.setVisibility(View.VISIBLE);
        } else {
            holder.flTransparent.setVisibility(View.GONE);
        }

        holder.llMain.getLayoutParams().height = width;
        holder.llMain.getLayoutParams().width = width;

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previousSelectionPosition = position;
                onItemClickListner.onItemClick(previousSelectionPosition, outListId + "->" + list.get(position), outerPosition);
                notifyDataSetChanged();
            }
        });

        holder.tvName.setText(list.get(position));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ProductColorViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.llMain)
        FrameLayout llMain;

        @BindView(R.id.flTransparent)
        FrameLayout flTransparent;

        @BindView(R.id.tvName)
        TextViewRegular tvName;


        public ProductColorViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void getWidthAndHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels / 6;
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}