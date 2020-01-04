package com.shop.arram.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shop.arram.R;
import com.shop.arram.activity.FilterActivity;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.javaclasses.FilterSelectedList;
import com.shop.arram.model.FilterColorOption;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {

    private List<FilterColorOption.Option> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;
    private Map<Integer, Integer> selectList = new HashMap<>();

    public ColorAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;

    }

    public void addAll(List<FilterColorOption.Option> list) {
        this.list = list;
        for (int i = 0; i < list.size(); i++) {
            selectList.put(i, 0);
        }
        getWidthAndHeight();
        notifyDataSetChanged();
    }

    public List<FilterColorOption.Option> getList() {
        return list;
    }

    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_color, parent, false);

        return new ColorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ColorViewHolder holder, final int position) {

        if (list.get(position).colorCode.equals("") && list.get(position).colorName.equals("")) {
            holder.tvColor.setText(list.get(position).colorName);
            holder.tvColor.setBackgroundColor(0x00000000);
        } else if (list.get(position).colorCode.equals("") && !list.get(position).colorName.equals("")) {
            holder.tvColor.setText("");
            holder.tvColor.setBackgroundColor(Color.BLACK);
        } else {
            holder.tvColor.setText("");
            holder.tvColor.setBackgroundColor(Color.parseColor(list.get(position).colorCode));
        }

        GradientDrawable drawable = (GradientDrawable) holder.tvSelect.getBackground();
        drawable.setColor(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_TRANSPARENT, Constant.PRIMARY_COLOR)));

        if (FilterSelectedList.selectedColorOptionList.get(0).options.size() > 0 &&
                FilterSelectedList.selectedColorOptionList.get(0).options.contains(list.get(position).colorName)
                && !FilterActivity.clearFilter) {
            holder.tvSelect.setVisibility(View.VISIBLE);
        } else {

            holder.tvSelect.setVisibility(View.GONE);
        }
        holder.tvColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FilterActivity.clearFilter) {
                    for (int k = 0; k < FilterSelectedList.selectedColorOptionList.get(0).options.size(); k++) {
                        FilterSelectedList.selectedColorOptionList.get(0).options.set(k, "");
                    }
                    for (int i = 0; i < list.size(); i++) {
                        selectList.put(i, 0);
                    }

                    FilterActivity.clearFilter = false;
                }
                if (selectList.get(position) == 1) {
                    selectList.put(position, 0);
                    holder.tvSelect.setVisibility(View.GONE);
                    onItemClickListner.onItemClick(position, RequestParamUtils.strfalse, 0);
                } else {
                    selectList.put(position, 1);
                    holder.tvSelect.setVisibility(View.VISIBLE);
                    onItemClickListner.onItemClick(position, RequestParamUtils.strtrue, 0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ColorViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvSelect)
        TextView tvSelect;

        @BindView(R.id.tvColor)
        TextView tvColor;

        public ColorViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void getWidthAndHeight() {
        int height_value = activity.getResources().getInteger(R.integer.height);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels / 2 - height_value;
        height = width - height_value;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}