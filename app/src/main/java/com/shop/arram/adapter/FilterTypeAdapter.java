package com.shop.arram.adapter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shop.arram.R;
import com.shop.arram.activity.FilterActivity;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.javaclasses.FilterSelectedList;
import com.shop.arram.model.FilterOtherOption;
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

public class FilterTypeAdapter extends RecyclerView.Adapter<FilterTypeAdapter.FilterTypeViewHolder> implements OnItemClickListner {

    private List<FilterOtherOption> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;
    private Map<Integer, Integer> filterTypeList = new HashMap<>();
    FilterAdapter filterTypeAdapter;
    private boolean isRatingEnable;

    public FilterTypeAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }

    public void setRatingEnableOrNot(boolean isRatingEnable) {
        this.isRatingEnable = isRatingEnable;

    }

    public void addAll(List<FilterOtherOption> list) {
        this.list = list;
        getWidthAndHeight();
        filterTypeList.put(0, 1);
        for (int i = 0; i < list.size(); i++) {
            filterTypeList.put(i, 0);
        }
        notifyDataSetChanged();
    }


    @Override
    public FilterTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filter_type, parent, false);

        return new FilterTypeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FilterTypeViewHolder holder, final int position) {
        holder.tvTitle.setText(list.get(position).name + "");
        setFilterTypeAdapter(holder.rvFilter, position);
        if (list.get(position).name.toLowerCase().equals("rating")) {
            if (!isRatingEnable) {
                list.remove(position);
                return;
            }

        }


        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filterTypeList.get(position) == 1) {
                    filterTypeList.put(position, 0);
                    holder.rvFilter.setVisibility(View.GONE);
                } else {
                    filterTypeList.put(position, 1);
                    holder.rvFilter.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemClick(int position, String value, int outerPos) {


        if (FilterActivity.clearFilter) {
            clearFilter();
            FilterActivity.clearFilter = false;
        }
        if (value.equals(RequestParamUtils.strtrue)) {
            FilterOtherOption filterOtherOption = FilterSelectedList.selectedOtherOptionList.get(outerPos);
            filterOtherOption.options.set(position, list.get(outerPos).options.get(position));
        } else {
            FilterOtherOption filterOtherOption = FilterSelectedList.selectedOtherOptionList.get(outerPos);
            filterOtherOption.options.set(position, "");
        }

    }

    public void clearFilter() {
        for (int i = 0; i < FilterSelectedList.selectedOtherOptionList.size(); i++) {
            FilterOtherOption filterOtherOption = FilterSelectedList.selectedOtherOptionList.get(i);
            List<String> option = filterOtherOption.options;
            for (int j = 0; j < option.size(); j++) {
                option.set(j, "");
            }
        }
    }

    public class FilterTypeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rvFilter)
        RecyclerView rvFilter;

        @BindView(R.id.tvTitle)
        TextViewRegular tvTitle;


        public FilterTypeViewHolder(View view) {
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

    public void setFilterTypeAdapter(RecyclerView recyclerView, int pos) {
        if (filterTypeList.get(pos) == 1) {
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
        }
        filterTypeAdapter = new FilterAdapter(activity, this, list.get(pos).name);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(filterTypeAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        filterTypeAdapter.addAll(list.get(pos).options);
        filterTypeAdapter.setOuterListPosition(pos);

    }

}