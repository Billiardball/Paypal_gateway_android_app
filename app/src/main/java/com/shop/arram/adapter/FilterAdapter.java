package com.shop.arram.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.shop.arram.R;
import com.shop.arram.activity.FilterActivity;
import com.shop.arram.customview.MaterialRatingBar;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.javaclasses.FilterSelectedList;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterViewHolder> {

    private List<String> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;
    private int outerPosition;
    private String name;


    public FilterAdapter(Activity activity, OnItemClickListner onItemClickListner, String name) {
        this.activity = activity;
        this.name = name;

        this.onItemClickListner = onItemClickListner;
    }

    public void addAll(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOuterListPosition(int outerPosition) {
        this.outerPosition = outerPosition;

    }


    @Override
    public FilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filter, parent, false);

        return new FilterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FilterViewHolder holder, final int position) {


        if (name.toLowerCase().equals("rating")) {
            try {
                if (!list.get(position).equals("")) {
                    holder.ratingBar.setRating(Float.parseFloat(list.get(position)));
                } else {
                    holder.ratingBar.setRating(0);
                }
            } catch (Exception e) {
                holder.ratingBar.setRating(0);
            }
            holder.tvName.setVisibility(View.GONE);
            holder.llRating.setVisibility(View.VISIBLE);


        } else {
            holder.tvName.setVisibility(View.VISIBLE);
            holder.llRating.setVisibility(View.GONE);


            holder.tvName.setText(list.get(position));

        }
        if (FilterSelectedList.selectedOtherOptionList.get(outerPosition).options.size() > 0 && !FilterActivity.clearFilter) {
            if (FilterSelectedList.selectedOtherOptionList.get(outerPosition).options.contains(list.get(position))) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.ckSelect.getButtonDrawable().setColorFilter(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)), PorterDuff.Mode.SRC_IN);
                }
                holder.ckSelect.setChecked(true);

            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.ckSelect.getButtonDrawable().setColorFilter(activity.getColor(R.color.gray_light), PorterDuff.Mode.SRC_IN);
                }
                holder.ckSelect.setChecked(false);
            }
        } else {
            holder.ckSelect.setChecked(false);
        }
        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!holder.ckSelect.isChecked()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        holder.ckSelect.getButtonDrawable().setColorFilter(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)), PorterDuff.Mode.SRC_IN);
                    }
                    holder.ckSelect.setChecked(true);
                    onItemClickListner.onItemClick(position, RequestParamUtils.strtrue, outerPosition);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        holder.ckSelect.getButtonDrawable().setColorFilter(activity.getColor(R.color.gray_light), PorterDuff.Mode.SRC_IN);
                    }
                    holder.ckSelect.setChecked(false);
                    onItemClickListner.onItemClick(position, RequestParamUtils.strfalse, outerPosition);
                }
            }
        });


        holder.ckSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        holder.ckSelect.getButtonDrawable().setColorFilter(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)), PorterDuff.Mode.SRC_IN);
                    }
                    onItemClickListner.onItemClick(position, RequestParamUtils.strtrue, outerPosition);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        holder.ckSelect.getButtonDrawable().setColorFilter(activity.getColor(R.color.gray_light), PorterDuff.Mode.SRC_IN);
                    }
                    onItemClickListner.onItemClick(position, RequestParamUtils.strfalse, outerPosition);
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class FilterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.llMain)
        LinearLayout llMain;

        @BindView(R.id.tvName)
        TextViewRegular tvName;

        @BindView(R.id.ckSelect)
        CheckBox ckSelect;

        @BindView(R.id.llRating)
        LinearLayout llRating;

        @BindView(R.id.ratingBar)
        MaterialRatingBar ratingBar;


        public FilterViewHolder(View view) {
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