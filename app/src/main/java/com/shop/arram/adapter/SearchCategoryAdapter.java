package com.shop.arram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shop.arram.R;
import com.shop.arram.activity.CategoryListActivity;
import com.shop.arram.activity.SearchCategoryInnerListActivity;
import com.shop.arram.activity.SearchCategoryListActivity;
import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.model.Home;
import com.shop.arram.utils.RequestParamUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class SearchCategoryAdapter extends RecyclerView.Adapter<SearchCategoryAdapter.CategoryViewHolder> implements OnItemClickListner {

    private final int REQUEST_CODE = 101;
    SearchInnerCategoryAdapter searchInnerCategoryAdapter;
    private List<Home.AllCategory> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private String from = "";


    public SearchCategoryAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }

    public void addAll(List<Home.AllCategory> list) {
        this.list = list;

        notifyDataSetChanged();
    }


    public void setFrom(String from) {
        if (from != null) {
            this.from = from;
        }

    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_catgory, parent, false);

        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, final int position) {
        if (from.equals(RequestParamUtils.filter)) {
            holder.llMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, CategoryListActivity.class);
                    intent.putExtra(RequestParamUtils.CATEGORY, list.get(position).id + "");
                    intent.putExtra(RequestParamUtils.SEARCH, SearchCategoryListActivity.search);
                    intent.putExtra(RequestParamUtils.ORDER_BY, SearchCategoryListActivity.sortBy);
                    intent.putExtra(RequestParamUtils.POSITION, SearchCategoryListActivity.sortPosition);
                    activity.setResult(RESULT_OK, intent);
                    activity.finish();
                }
            });

        } else {
            holder.llMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, SearchCategoryInnerListActivity.class);
                    intent.putExtra(RequestParamUtils.CATEGORY, list.get(position).id);
                    activity.startActivity(intent);
                }
            });


        }
        holder.tvName.setText(list.get(position).name);
        if (!list.get(position).image.src.equals("")) {
            Picasso.with(activity).load(list.get(position).image.src + "").into(holder.ivImage);
            holder.ivImage.setVisibility(View.VISIBLE);
        } else {
            holder.ivImage.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemClick(int position, String value, int outerPos) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.llMain)
        LinearLayout llMain;

        @BindView(R.id.tvName)
        TextViewLight tvName;

        @BindView(R.id.ivImage)
        ImageView ivImage;

        public CategoryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}