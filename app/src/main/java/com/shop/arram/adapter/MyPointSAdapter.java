package com.shop.arram.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shop.arram.R;
import com.shop.arram.customview.textview.TextViewLight;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.model.MyPoint;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ankita on 5/10/2018.
 */

public class MyPointSAdapter extends RecyclerView.Adapter<MyPointSAdapter.MyPointHolder> {

    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;
    private List<MyPoint.Event> list = new ArrayList<>();

    public MyPointSAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }

    public void addAll(List<MyPoint.Event> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public List<MyPoint.Event> getList() {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        return this.list;
    }


    @Override
    public MyPointSAdapter.MyPointHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_points, parent, false);

        return new MyPointHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyPointSAdapter.MyPointHolder holder, final int position) {
        holder.tvDate.setText(list.get(position).dateDisplayHuman + "");
        holder.tvDescription.setText(list.get(position).description + "");
        holder.tvPoint.setText(list.get(position).points + "");
        if(position==list.size()-1) {
            holder.line1.setVisibility(View.GONE);
        }else {
            holder.line1.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyPointHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvDescription)
        TextViewLight tvDescription;

        @BindView(R.id.tvDate)
        TextViewLight tvDate;

        @BindView(R.id.tvPoint)
        TextViewLight tvPoint;

        @BindView(R.id.line1)
        View line1;


        public MyPointHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
