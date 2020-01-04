package com.shop.arram.adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shop.arram.R;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.model.Home;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;

import java.util.ArrayList;
import java.util.List;

public class ChangeLanguageItemAdapter extends RecyclerView.Adapter<ChangeLanguageItemAdapter.MyViewHolder> {

    public static final String TAG = "ChangeLanguageItemAdapter";
    private final LayoutInflater inflater;
    List<Home.WpmlLanguage> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;

    public ChangeLanguageItemAdapter(Activity activity, List<Home.WpmlLanguage> list, OnItemClickListner onItemClickListner) {
        inflater = LayoutInflater.from(activity);
        this.activity = activity;
        if (list != null) {
            this.list = list;
        }
        this.onItemClickListner = onItemClickListner;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_custom_dialog, parent, false);
        MyViewHolder holer = new MyViewHolder(view);
        return holer;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (list.get(position).dispLanguage != null && list.get(position).dispLanguage.length() > 0) {
            holder.tvDisplayitems.setText(list.get(position).dispLanguage + " ( " + list.get(position).code + " )");
        } else {
            holder.tvDisplayitems.setText("");
        }


        holder.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor pre = ((BaseActivity) activity).getPreferences().edit();

                ((BaseActivity) activity).getPreferences().edit().putString(RequestParamUtils.LANGUAGE, "").commit();
                pre.putString(RequestParamUtils.LANGUAGE, list.get(position).code);
                Constant.IS_RTL = list.get(position).isRtl;
                pre.putBoolean(Constant.RTL, list.get(position).isRtl);
                ((BaseActivity) activity).getPreferences().edit().putBoolean(RequestParamUtils.iSSITELANGUAGECALLED, false).commit();
                //pre.putBoolean(RequestParamUtils.iSSITELANGUAGECALLED, false);
                pre.commit();
                onItemClickListner.onItemClick(position, list.get(position).code, position);

            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvDisplayitems;
        private final LinearLayout ll_main;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvDisplayitems = (TextView) itemView.findViewById(R.id.tvDisplayitems);
            ll_main = (LinearLayout) itemView.findViewById(R.id.ll_main);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}