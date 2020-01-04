package com.shop.arram.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shop.arram.R;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.helper.DatabaseHelper;
import com.shop.arram.model.Home;
import com.shop.arram.model.NavigationList;
import com.shop.arram.utils.RequestParamUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UV on 29-Nov-16.
 */
public class NavigationDrawerAdapter extends BaseAdapter {
    private Activity context;
    private List<Home.MainCategory> list = new ArrayList<>();
    private LayoutInflater inflater;
    private int seprater;
    private DatabaseHelper databaseHelper;

    public NavigationDrawerAdapter(Activity context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        databaseHelper = new DatabaseHelper(context);
    }

    public void addAll(List<Home.MainCategory> list) {
        this.list = list;
        notifyDataSetChanged();

    }


    public List<Home.MainCategory> getDrawerList() {
        return list;
    }

    public void setSepreter(int seprater) {
        this.seprater = seprater;
    }

    public int getSeprater() {
        return this.seprater;
    }

    public List<Home.MainCategory> getList() {
        return this.list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        NavigationDrawerViewHolder listViewHolder;
        listViewHolder = new NavigationDrawerViewHolder();
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_nav, viewGroup, false);

            listViewHolder.tvName = (TextViewRegular) convertView.findViewById(R.id.tvName);
            listViewHolder.tvCart = convertView.findViewById(R.id.tvToolCart);
            listViewHolder.llMain = convertView.findViewById(R.id.llMain);
            listViewHolder.tvDivider = (TextViewRegular) convertView.findViewById(R.id.tvDivider);
            listViewHolder.tvDividerGray = (TextViewRegular) convertView.findViewById(R.id.tvDividerGray);
            listViewHolder.ivLeft = (ImageView) convertView.findViewById(R.id.ivLeft);
            convertView.setTag(listViewHolder);

        } else {
            listViewHolder = (NavigationDrawerViewHolder) convertView.getTag();
        }


        if (i != seprater) {
            listViewHolder.tvDivider.setVisibility(View.VISIBLE);
            listViewHolder.tvDividerGray.setVisibility(View.GONE);
        } else {
            listViewHolder.tvDivider.setVisibility(View.GONE);
            listViewHolder.tvDividerGray.setVisibility(View.VISIBLE);
        }
        if (list.get(i).mainCatName != null && !list.get(i).mainCatName.equals("")) {
            listViewHolder.tvName.setText(list.get(i).mainCatName);
        }
        if (i == seprater) {
            listViewHolder.ivLeft.setImageResource(R.drawable.ic_more_white);

        } else if (i < seprater) {
            if (list.get(i).mainCatImage != null && !list.get(i).mainCatImage.equals("")) {
                Picasso.with(context).load(list.get(i).mainCatImage).into(listViewHolder.ivLeft);
            }
        } else {
            listViewHolder.ivLeft.setImageResource(NavigationList.getInstance(context).getImageList().get(Integer.parseInt(list.get(i).mainCatId)));
        }

        if (list.get(i).mainCatName.equals(RequestParamUtils.myCart)) {

            if (databaseHelper.getFromCart(0).size() > 0) {
                listViewHolder.tvCart.setText(databaseHelper.getFromCart(0).size() + "");
                listViewHolder.tvCart.setVisibility(View.VISIBLE);
            } else {
                listViewHolder.tvCart.setVisibility(View.GONE);
            }

        } else {
            listViewHolder.tvCart.setVisibility(View.GONE);
        }

        Log.e("Value of navigation ", "is " + i);

//        if (i < seprater) {
//            listViewHolder.llMain.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, CategoryListActivity.class);
//                    intent.putExtra(RequestParamUtils.CATEGORY, list.get(i).mainCatId);
//                    intent.putExtra(RequestParamUtils.IS_WISHLIST_ACTIVE, Constant.IS_WISH_LIST_ACTIVE);
//                    context.startActivity(intent);
//                }
//            });
//
//        } else if (i == seprater) {
//            listViewHolder.llMain.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, SearchCategoryListActivity.class);
//                    context.startActivity(intent);
//
//                }
//            });
//
//        }

        return convertView;
    }
}

class NavigationDrawerViewHolder {

    TextViewRegular tvName, tvDivider, tvDividerGray;
    ImageView ivLeft;
    LinearLayout llMain;
    TextViewRegular tvCart;
}