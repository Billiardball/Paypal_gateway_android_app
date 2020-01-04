package com.shop.arram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shop.arram.R;
import com.shop.arram.activity.CategoryListActivity;
import com.shop.arram.activity.SearchCategoryListActivity;
import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.model.Home;
import com.shop.arram.utils.RequestParamUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class SearchInnerCategoryAdapter extends RecyclerView.Adapter<SearchInnerCategoryAdapter.CategoryViewHolder> implements OnItemClickListner {

    private List<Home.AllCategory> list = new ArrayList<>();
    private Map<Integer, List<Home.AllCategory>> childList = new HashMap<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;
    private Map<Integer, CategoryViewHolder> expandList = new HashMap<>();
    private SearchInnerInnerCategoryAdapter searchInnerInnerCategoryAdapter;
    private int previousPosition = -1;
    private Animation rotate;


    public SearchInnerCategoryAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }

    public void addAll(List<Home.AllCategory> list, Map<Integer, List<Home.AllCategory>> childList) {
        this.list = list;
        this.childList = childList;
        for (int i = 0; i < list.size(); i++) {
            expandList.put(i, null);
        }
        notifyDataSetChanged();

    }


    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_catgory, parent, false);

        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, final int position) {
        setInnerAdapter(holder.rvInnerRecycleView, list.get(position).id);


        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (childList.get(list.get(position).id).size() == 0) {
                    Intent intent = new Intent(activity, CategoryListActivity.class);
                    intent.putExtra(RequestParamUtils.CATEGORY, list.get(position).id + "");
                    intent.putExtra(RequestParamUtils.SEARCH, SearchCategoryListActivity.search);
                    intent.putExtra(RequestParamUtils.ORDER_BY, SearchCategoryListActivity.sortBy);
                    intent.putExtra(RequestParamUtils.POSITION,SearchCategoryListActivity.sortPosition);
                    activity.startActivity(intent);
                } else {
                    if (expandList.get(position) != null) {
                        holder.rvInnerRecycleView.setVisibility(View.GONE);
                        rotate = AnimationUtils.loadAnimation(activity,
                                R.anim.anti_click_rotate);

                        holder.ivGo.startAnimation(rotate);


                        expandList.put(position, null);
                    } else {
                        holder.rvInnerRecycleView.setVisibility(View.VISIBLE);
                        rotate = AnimationUtils.loadAnimation(activity,
                                R.anim.click_rotate);
                        holder.ivGo.startAnimation(rotate);

                        expandList.put(position, holder);
                        if (expandList.get(previousPosition) != null &&previousPosition != -1 &&  previousPosition != position) {
                            expandList.get(previousPosition).rvInnerRecycleView.setVisibility(View.GONE);
                            rotate = AnimationUtils.loadAnimation(activity,
                                    R.anim.anti_click_rotate);

                            expandList.get(previousPosition).ivGo.startAnimation(rotate);
                            expandList.put(previousPosition, null);

                        }

                        previousPosition = position;
                    }
                }

            }
        });
        holder.tvName.setText(list.get(position).name);
        if (!list.get(position).image.src.equals("")) {
            Picasso.with(activity).load(list.get(position).image.src + "").into(holder.ivImage);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemClick(int position, String value, int outerPos) {

    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.llMain)
        LinearLayout llMain;

        @BindView(R.id.ivGo)
        ImageView ivGo;

        @BindView(R.id.tvName)
        TextViewLight tvName;

        @BindView(R.id.ivImage)
        ImageView ivImage;

        @BindView(R.id.rvInnerRecycleView)
        RecyclerView rvInnerRecycleView;

        public CategoryViewHolder(View view) {
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

    public void setInnerAdapter(RecyclerView recyclerView, int id) {

        searchInnerInnerCategoryAdapter = new SearchInnerInnerCategoryAdapter(activity, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(searchInnerInnerCategoryAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        searchInnerInnerCategoryAdapter.addAll(childList.get(id));

    }
}