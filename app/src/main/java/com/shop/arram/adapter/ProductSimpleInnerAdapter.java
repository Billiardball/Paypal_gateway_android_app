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
import com.shop.arram.javaclasses.CheckSimpleSelector;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class ProductSimpleInnerAdapter extends RecyclerView.Adapter<ProductSimpleInnerAdapter.ProductSimpleViewHolder> {

    private List<String> list;
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;
    private int outerPosition;
    private String outerPositionValue;

    public ProductSimpleInnerAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }

    public void addAll(List<String> list) {
        this.list = list;
        getWidthAndHeight();
        notifyDataSetChanged();
    }

    public void setOuterPosition(int outerPosition) {
        this.outerPosition = outerPosition;
    }

    public void setOuterPositionValue(String outerPositionValue) {
        this.outerPositionValue = outerPositionValue;
    }

    @Override
    public ProductSimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_color, parent, false);

        return new ProductSimpleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductSimpleViewHolder holder, final int position) {

        holder.llMain.getLayoutParams().height = width;
        holder.llMain.getLayoutParams().width = width;
        GradientDrawable drawable = (GradientDrawable) holder.llMain.getBackground();
        drawable.setStroke(5, Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_TRANSPARENT_VERY_LIGHT, Constant.PRIMARY_COLOR)));

        drawable = (GradientDrawable) holder.flTransparent.getBackground();
        drawable.setColor(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_TRANSPARENT, Constant.PRIMARY_COLOR)));

        if (list.get(position).equals(CheckSimpleSelector.selectedList.get(outerPositionValue))) {
            holder.flTransparent.setVisibility(View.VISIBLE);
        } else {
            holder.flTransparent.setVisibility(View.GONE);
        }

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListner.onItemClick(position, RequestParamUtils.strtrue, outerPosition);
                notifyDataSetChanged();
            }
        });
        holder.tvName.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
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

    public class ProductSimpleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.llMain)
        FrameLayout llMain;

        @BindView(R.id.flTransparent)
        FrameLayout flTransparent;

        @BindView(R.id.tvName)
        TextViewRegular tvName;


        public ProductSimpleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}