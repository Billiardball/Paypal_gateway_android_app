package com.shop.arram.adapter;

import android.app.Activity;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.shop.arram.R;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.model.GateWays;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nirav Shah on 11/7/2017.
 */

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.CategoryViewHolder> implements OnItemClickListner {

    private static final String TAG = PaymentMethodAdapter.class.getSimpleName();
    private List<GateWays.GateWaysList> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;
    private int outerPosition;
    private int lastSelectedPosition = -1;

    public PaymentMethodAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }

    public void addAll(List<GateWays.GateWaysList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_paymentmethod, parent, false);

        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, final int position) {

        Log.e(TAG, "onBindViewHolder: " + list.get(position).id);

        GateWays.GateWaysList method = list.get(position);

        holder.rbMethod.setChecked(position == lastSelectedPosition);

        if (method.title != null && method.title.length() > 0) {
            holder.tvMethodTitle.setText(method.title);
        } else {
            holder.tvMethodTitle.setText("");
        }
        if (method.description != null && method.description.length() > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.tvTitle.setText(Html.fromHtml(method.description, Html.FROM_HTML_MODE_COMPACT));

            } else {
                holder.tvTitle.setText(Html.fromHtml(method.description));
            }

        } else {
            holder.tvTitle.setText("");
        }

        if (holder.rbMethod.isChecked()) {
            holder.tvTitle.setVisibility(View.VISIBLE);
        } else {
            holder.tvTitle.setVisibility(View.GONE);
        }
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

        @BindView(R.id.rbMethod)
        RadioButton rbMethod;

        @BindView(R.id.tvMethodTitle)
        TextViewRegular tvMethodTitle;

        @BindView(R.id.tvTitle)
        TextViewRegular tvTitle;

        public CategoryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListner.onItemClick(getAdapterPosition(), "PaymentMethod", getAdapterPosition());
                    lastSelectedPosition = getAdapterPosition();
                    notifyItemRangeChanged(0, list.size());
                }
            };
            itemView.setOnClickListener(clickListener);
            rbMethod.setOnClickListener(clickListener);


        }


    }
}