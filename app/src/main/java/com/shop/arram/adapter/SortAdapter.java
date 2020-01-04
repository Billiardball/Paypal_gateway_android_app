package com.shop.arram.adapter;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.shop.arram.R;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bhumi Shah on 11/7/2017.
 */

public class SortAdapter extends RecyclerView.Adapter<SortAdapter.SortViewHolder> {

    private List<String> list;
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int selectedPosition = -1;

    public SortAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }

    public void addAll(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public SortViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sort, parent, false);

        return new SortViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final SortViewHolder holder, final int position) {

        //check the radio button if both position and selectedPosition matches
        holder.rdSort.setChecked(position == selectedPosition);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.rdSort.setButtonTintList(ColorStateList.valueOf(Color.parseColor(((BaseActivity)activity).getPreferences().getString(Constant.APP_COLOR,Constant.PRIMARY_COLOR))));
        } else {
            ColorStateList sl = new ColorStateList(new int[][]{
                    new int[]{-android.R.attr.state_checked},
                    new int[]{android.R.attr.state_checked}
            },   new int[] {

                    Color.parseColor(((BaseActivity)activity).getPreferences().getString(Constant.APP_COLOR,Constant.PRIMARY_COLOR)) //disabled
                    ,Color.parseColor(((BaseActivity)activity).getPreferences().getString(Constant.APP_COLOR,Constant.PRIMARY_COLOR)) //enabled
            }
            );
            Drawable d = DrawableCompat.wrap(ContextCompat.getDrawable(holder.rdSort.getContext(), R.drawable.abc_btn_radio_material));
            DrawableCompat.setTintList(d, sl);
            holder.rdSort.setButtonDrawable(d);
        }

        //Set the position tag to both radio button and label
        holder.rdSort.setTag(position);
        holder.tvTitle.setTag(position);
        holder.rdSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCheckChanged(v);
            }
        });
        holder.tvTitle.setText(list.get(position));

        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCheckChanged(v);
            }


        });
    }


    public class SortViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rdSort)
        RadioButton rdSort;

        @BindView(R.id.tvTitle)
        TextViewRegular tvTitle;


        public SortViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getItemCount() {
        return (null != list ? list.size() : 0);
    }

    //Return the selectedPosition item
    public String getSelectedItem() {
        if (selectedPosition != -1) {
            Toast.makeText(activity, "Selected Item : " + list.get(selectedPosition), Toast.LENGTH_SHORT).show();
            return list.get(selectedPosition);
        }
        return "";
    }

    public int getSelectedPosition() {
        if (selectedPosition != -1) {
            return selectedPosition;
        }
        return 0;
    }

    //Delete the selected position from the list
    public void deleteSelectedPosition() {
        if (selectedPosition != -1) {
            list.remove(selectedPosition);
            selectedPosition = -1;//after removing selectedPosition set it back to -1
            notifyDataSetChanged();
        }
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;//after removing selectedPosition set it back to -1
        notifyDataSetChanged();
    }

    private void itemCheckChanged(View v) {
        selectedPosition = (Integer) v.getTag();
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}