package com.shop.arram.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.shop.arram.R;
import com.shop.arram.model.Countrys;

import java.util.List;

public class CountryAdapter extends ArrayAdapter<String> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<Countrys.CountyList> items;
    private final int mResource;

    public CountryAdapter(@NonNull Context context, @LayoutRes int resource,@NonNull List objects) {
        super(context, resource, 0, objects);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull
    View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        final View view = mInflater.inflate(mResource, parent, false);

        TextView tv_country_name = (TextView) view.findViewById(R.id.tv_country_name);
        TextView tv_country_code = (TextView) view.findViewById(R.id.tv_country_code);

        Countrys.CountyList offerData = items.get(position);

        tv_country_name.setText(offerData.name);
        tv_country_code.setText(offerData.code);

        return view;
    }
}
