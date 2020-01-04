package com.shop.arram.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shop.arram.R;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.model.Countrys;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class DialogCountryAdapter extends RecyclerView.Adapter<DialogCountryAdapter.CategoryViewHolder> implements OnItemClickListner {

    private List<Countrys.CountyList> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;
    private int outerPosition;
    private String Country;

    public DialogCountryAdapter(Activity activity, OnItemClickListner onItemClickListner, String country) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
        this.Country = country;
    }

    public void addAll(List<Countrys.CountyList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_country, parent, false);

        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, final int position) {

        if (list.get(position).code != null && list.get(position).code.length() > 0) {
            holder.tv_country_code.setText(list.get(position).code);
        } else {
            holder.tv_country_code.setText("");
        }

        if (list.get(position).name != null && list.get(position).name.length() > 0) {
            holder.tv_country_name.setText(list.get(position).name);
        } else {
            holder.tv_country_name.setText("");
        }

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListner.onItemClick(position, Country, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onItemClick(int position, String value, int outerpos) {

    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.llMain)
        LinearLayout llMain;

        @BindView(R.id.tv_country_code)
        TextView tv_country_code;

        @BindView(R.id.tv_country_name)
        TextView tv_country_name;

        public CategoryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}