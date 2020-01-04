package com.shop.arram.adapter;

import android.app.Activity;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shop.arram.R;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.model.SearchLive;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class SearchHomeAdapter extends RecyclerView.Adapter<SearchHomeAdapter.SearchHomeViewHolder> {

    private List<SearchLive> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;

    public SearchHomeAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }


    public void addAll(List<SearchLive> list) {
        this.list = list;
        getWidthAndHeight();
        notifyDataSetChanged();
    }

    public void updateList(List<SearchLive> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<SearchLive> getList() {
        return list;
    }

    @Override
    public SearchHomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search, parent, false);

        return new SearchHomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchHomeViewHolder holder, final int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tvTitle.setText(Html.fromHtml(list.get(position).name, Html.FROM_HTML_MODE_COMPACT));

        } else {
            holder.tvTitle.setText(Html.fromHtml(list.get(position).name));
        }
        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListner.onItemClick(position, list.get(position).name, list.get(position).id);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SearchHomeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle)
        TextViewRegular tvTitle;

        @BindView(R.id.llMain)
        LinearLayout llMain;

        public SearchHomeViewHolder(View view) {
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