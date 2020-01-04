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
import com.shop.arram.model.ShippingCharge;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.RequestParamUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nirav Shah on 11/7/2017.
 */

public class ShippingAdapter extends RecyclerView.Adapter<ShippingAdapter.CategoryViewHolder> implements OnItemClickListner {

    private static final String TAG = ShippingAdapter.class.getSimpleName();
    private List<ShippingCharge.Method> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;
    private int outerPosition;
    private int lastSelectedPosition = -1;

    public ShippingAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }

    public void addAll(List<ShippingCharge.Method> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shipping, parent, false);

        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, final int position) {

        Log.e(TAG, "onBindViewHolder: " + list.get(position).cost);

        ShippingCharge.Method method = list.get(position);

        Log.e(TAG, "onBindViewHolder: " + method.cost);

        if (method.label != null && method.label.length() > 0) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.tvshippingtitle.setText(Html.fromHtml(method.label + ": " + method.costDisplay, Html.FROM_HTML_MODE_COMPACT));

            } else {
                holder.tvshippingtitle.setText(Html.fromHtml(method.label + ": " + method.costDisplay));
            }
        }

        lastSelectedPosition = ((BaseActivity) activity).getPreferences().getInt(RequestParamUtils.Shippingmethod, 0);

        if (lastSelectedPosition == -1) {

        } else {
            holder.rbselect.setChecked(position == lastSelectedPosition);
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

        @BindView(R.id.rbselect)
        RadioButton rbselect;

        @BindView(R.id.tvshippingtitle)
        TextViewRegular tvshippingtitle;

        public CategoryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListner.onItemClick(getAdapterPosition(), "Shipping", getAdapterPosition());
                    lastSelectedPosition = getAdapterPosition();
                    ((BaseActivity) activity).getPreferences().edit().putInt(RequestParamUtils.Shippingmethod, lastSelectedPosition).commit();
                    notifyItemRangeChanged(0, list.size());
                }
            };
            itemView.setOnClickListener(clickListener);
            rbselect.setOnClickListener(clickListener);
        }


    }
}