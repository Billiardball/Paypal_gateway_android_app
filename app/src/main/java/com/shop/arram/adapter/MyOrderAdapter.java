package com.shop.arram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shop.arram.R;
import com.shop.arram.activity.OrderDetailActivity;
import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.customview.textview.TextViewMedium;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.model.Orders;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.RecentViewHolde> {

    private List<Orders> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;

    public MyOrderAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }

    public void addAll(List<Orders> list) {
        this.list = list;
        getWidthAndHeight();
        notifyDataSetChanged();
    }

    @Override
    public RecentViewHolde onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_order, parent, false);

        return new RecentViewHolde(itemView);
    }

    @Override
    public void onBindViewHolder(RecentViewHolde holder, final int position) {

//        holder.llMain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Constant.ORDERDETAIL = list.get(position);
//                Intent intent = new Intent(activity, OrderDetailActivity.class);
//                intent.putExtra(RequestParamUtils.ID, list.get(position).id);
//                activity.startActivity(intent);
//            }
//        });

        holder.tvView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.ORDERDETAIL = list.get(position);
                Intent intent = new Intent(activity, OrderDetailActivity.class);
                intent.putExtra(RequestParamUtils.ID, list.get(position).id);
                activity.startActivity(intent);
            }
        });

        if (list.get(position).lineItems.get(0).productImage.equals("")) {
            holder.ivImage.setVisibility(View.INVISIBLE);
        } else {
            holder.ivImage.setVisibility(View.VISIBLE);
            Picasso.with(activity).load(list.get(position).lineItems.get(0).productImage).into(holder.ivImage);
        }

        holder.tvView.setBackgroundColor(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));

        String title = new String();
        for (int i = 0; i < list.get(position).lineItems.size(); i++) {
            if (i == 0) {
                title = list.get(position).lineItems.get(i).name;
            } else {
                title = title + " & " + list.get(position).lineItems.get(i).name;
            }
        }
//        holder.tvTitle.setText(title);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            tvProductName.setText(categoryList.name + "");
            holder.tvTitle.setText(Html.fromHtml(title + "", Html.FROM_HTML_MODE_LEGACY));
        } else {
//            tvProductName.setText(categoryList.name + "");
            holder.tvTitle.setText(Html.fromHtml(title + ""));
        }

        String upperString = list.get(position).status.substring(0, 1).toUpperCase() + list.get(position).status.substring(1);
        holder.tvStatus.setText(upperString);
        holder.tvStatus.setTextColor(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        String idAndDate = activity.getString(R.string.on) + " " + Constant.setDate(list.get(position).dateCreated) + "  " + activity.getString(R.string.order_id) + list.get(position).id;
        holder.tvOrderDateAndId.setText(idAndDate);
        String statusDesc = new String();
        if (list.get(position).status.toLowerCase().equals(RequestParamUtils.any)) {
            holder.tvStatusDesc.setText(R.string.delivered_soon);
        } else if (list.get(position).status.toLowerCase().equals(RequestParamUtils.pending)) {
            holder.tvStatusDesc.setText(R.string.order_is_in_pending_state);
        } else if (list.get(position).status.toLowerCase().equals(RequestParamUtils.processing)) {
            holder.tvStatusDesc.setText(R.string.order_is_under_processing);
        } else if (list.get(position).status.toLowerCase().equals(RequestParamUtils.onHold)) {
            holder.tvStatusDesc.setText(R.string.order_is_on_hold);
        } else if (list.get(position).status.toLowerCase().equals(RequestParamUtils.completed)) {
            holder.tvStatusDesc.setText(R.string.delivered);
        } else if (list.get(position).status.toLowerCase().equals(RequestParamUtils.cancelled)) {
            holder.tvStatusDesc.setText(R.string.order_is_cancelled);
        } else if (list.get(position).status.toLowerCase().equals(RequestParamUtils.refunded)) {
            holder.tvStatusDesc.setText(R.string.you_are_refunded_for_this_order);
        } else if (list.get(position).status.toLowerCase().equals(RequestParamUtils.failed)) {
            holder.tvStatusDesc.setText(R.string.order_is_failed);
        } else if (list.get(position).status.toLowerCase().equals(RequestParamUtils.shipping)) {
            holder.tvStatusDesc.setText(R.string.delivered_soon);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
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

    public class RecentViewHolde extends RecyclerView.ViewHolder {

        @BindView(R.id.llMain)
        LinearLayout llMain;

        @BindView(R.id.ivImage)
        ImageView ivImage;

        @BindView(R.id.tvTitle)
        TextViewRegular tvTitle;

        @BindView(R.id.tvView)
        TextViewRegular tvView;

        @BindView(R.id.tvStatus)
        TextViewMedium tvStatus;

        @BindView(R.id.tvStatusDesc)
        TextViewLight tvStatusDesc;

        @BindView(R.id.tvOrderDateAndId)
        TextViewLight tvOrderDateAndId;

        public RecentViewHolde(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}