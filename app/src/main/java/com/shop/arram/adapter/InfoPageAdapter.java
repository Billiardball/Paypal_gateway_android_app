package com.shop.arram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shop.arram.R;
import com.shop.arram.activity.InfoPageDetailActivity;
import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.model.InfoPages;
import com.shop.arram.utils.RequestParamUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by User on 17-11-2017.
 */

public class InfoPageAdapter extends RecyclerView.Adapter<InfoPageAdapter.RecentViewHolder> {

    private List<InfoPages.Datum> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;

    public InfoPageAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }

    public void addAll(List<InfoPages.Datum> list) {
        this.list = list;
        getWidthAndHeight();
        notifyDataSetChanged();
    }

    @Override
    public InfoPageAdapter.RecentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_info_pages, parent, false);

        return new InfoPageAdapter.RecentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InfoPageAdapter.RecentViewHolder holder, final int position) {
        final String title;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            title = String.valueOf(Html.fromHtml(list.get(position).title, Html.FROM_HTML_MODE_COMPACT));
        } else {
            title = String.valueOf(Html.fromHtml(list.get(position).title));
        }

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, InfoPageDetailActivity.class);
                intent.putExtra(RequestParamUtils.ID, list.get(position).pageId + "");
                intent.putExtra("title", title);
                activity.startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tvInfoPageTitle.setText(Html.fromHtml(list.get(position).title, Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.tvInfoPageTitle.setText(Html.fromHtml(list.get(position).title));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.llMain)
        LinearLayout llMain;

        @BindView(R.id.tvInfoPageTitle)
        TextViewLight tvInfoPageTitle;

        public RecentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void getWidthAndHeight() {
        int height_value = activity.getResources().getInteger(R.integer.height);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels / 2 - height_value * 2;
        height = width / 2 + height_value;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
