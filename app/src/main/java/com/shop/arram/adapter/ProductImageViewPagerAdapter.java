package com.shop.arram.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.shop.arram.R;
import com.shop.arram.activity.ImageViewerActivity;
import com.shop.arram.activity.VideoViewActivity;
import com.shop.arram.model.CategoryList;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhumi Shah on 11/9/2017.
 */

public class ProductImageViewPagerAdapter extends PagerAdapter {
    public static List<CategoryList.Image> list = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Activity activity;
    private int id;
    private int imagewidth, imageHeight;

    public ProductImageViewPagerAdapter(Activity activity, int id) {
        this.activity = activity;
        this.id = id;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    public void addAll(List<CategoryList.Image> list) {
        this.list = list;
        notifyDataSetChanged();

    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_top_banner, container, false);
        final ImageView imageView = view.findViewById(R.id.ivBanner);
        final ImageView iv_play = view.findViewById(R.id.iv_play);
        final ProgressBar progress_bar = view.findViewById(R.id.progress_bar);
        container.addView(view);

        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (list.get(position).type != null && list.get(position).type.equals("Video")) {
                    Intent intent = new Intent(activity, VideoViewActivity.class);
                    intent.putExtra(RequestParamUtils.pos, position);
                    intent.putExtra(RequestParamUtils.VIDEO_URL, list.get(position).url);
                    activity.startActivity(intent);
                }else {
                    Intent intent = new Intent(activity, ImageViewerActivity.class);
                    intent.putExtra(RequestParamUtils.pos, position);
                    intent.putExtra(RequestParamUtils.cat_id, id);
                    activity.startActivity(intent);
                }

            }
        });

        progress_bar.getIndeterminateDrawable().setColorFilter(Color.parseColor(((BaseActivity) activity).getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)), android.graphics.PorterDuff.Mode.MULTIPLY);


        if (list.get(position).type != null && list.get(position).type.equals("Video")) {

            Picasso.with(activity)
                    .load(list.get(position).src)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            progress_bar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            // TODO Auto-generated method stub

                        }
                    });

            iv_play.setVisibility(View.VISIBLE);

        } else {

            Picasso.with(activity)
                    .load(list.get(position).src)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            progress_bar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            // TODO Auto-generated method stub

                        }
                    });

            iv_play.setVisibility(View.GONE);
        }


        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                imageView.getViewTreeObserver().removeOnPreDrawListener(this);
                imagewidth = imageView.getMeasuredHeight();
                imageHeight = imageView.getMeasuredHeight();
//                Log.e("Product Image  Height: " + imageView.getMeasuredHeight(), "Product Image  Width: " + imageView.getMeasuredWidth());

                return true;
            }
        });


        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
