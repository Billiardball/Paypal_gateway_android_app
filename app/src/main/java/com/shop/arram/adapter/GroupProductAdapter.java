package com.shop.arram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shop.arram.R;
import com.shop.arram.activity.ProductDetailActivity;
import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.model.CategoryList;
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

public class GroupProductAdapter extends RecyclerView.Adapter<GroupProductAdapter.GroupProductViewHolder> {

    private List<CategoryList> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;

    public GroupProductAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;

    }

    public void addAll(List<CategoryList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<CategoryList> getList() {
        return list;
    }

    @Override
    public GroupProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_group_product, parent, false);

        return new GroupProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GroupProductViewHolder holder, final int position) {
        if (position == list.size() - 1) {
            holder.tvDivider.setVisibility(View.GONE);
        } else {
            holder.tvDivider.setVisibility(View.VISIBLE);
        }

        holder.tvName.setText(list.get(position).name);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tvPrice.setText(Html.fromHtml(list.get(position).priceHtml, Html.FROM_HTML_MODE_COMPACT));

        } else {
            holder.tvPrice.setText(Html.fromHtml(list.get(position).priceHtml));
        }
        holder.tvPrice.setTextColor(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        holder.tvPrice.setTextSize(15);

        ((BaseActivity) activity).setPrice(holder.tvPrice, holder.tvPrice1, list.get(position).priceHtml);


        holder.tvDetail.setTextColor(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));



        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.CATEGORYDETAIL = list.get(position);
                Intent intent = new Intent(activity, ProductDetailActivity.class);
                intent.putExtra(RequestParamUtils.ID, list.get(position).id);
                activity.startActivity(intent);

            }
        });

        if (list.get(position).images.size() > 0) {
            holder.ivImage.setVisibility(View.VISIBLE);
            Picasso.with(activity).load(list.get(position).images.get(0).src).into(holder.ivImage);
        } else {
            holder.ivImage.setVisibility(View.INVISIBLE);
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GroupProductViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.ivImage)
        ImageView ivImage;

        @BindView(R.id.llMain)
        LinearLayout llMain;

        @BindView(R.id.tvName)
        TextViewRegular tvName;

        @BindView(R.id.tvPrice)
        TextViewRegular tvPrice;

        @BindView(R.id.tvPrice1)
        TextViewRegular tvPrice1;

        @BindView(R.id.tvDivider)
        TextViewRegular tvDivider;

        @BindView(R.id.tvDetail)
        TextViewLight tvDetail;



        public GroupProductViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}