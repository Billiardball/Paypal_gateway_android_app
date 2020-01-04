package com.shop.arram.adapter;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ciyashop.library.apicall.PostApi;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shop.arram.R;
import com.shop.arram.customview.scratchview.ScratchTextView;
import com.shop.arram.customview.textview.TextViewBold;
import com.shop.arram.customview.textview.TextViewMedium;
import com.shop.arram.customview.textview.TextViewRegular;
import com.shop.arram.interfaces.OnItemClickListner;
import com.shop.arram.javaclasses.FilterSelectedList;
import com.shop.arram.model.Rewards;
import com.shop.arram.model.Success;
import com.shop.arram.utils.BaseActivity;
import com.shop.arram.utils.Constant;
import com.shop.arram.utils.RequestParamUtils;
import com.shop.arram.utils.Utils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 16-11-2017.
 */

public class MyRewardsAdapter extends RecyclerView.Adapter<MyRewardsAdapter.RecentViewHolder> implements OnResponseListner {
    private List<Rewards.Datum> list = new ArrayList<>();
    private Activity activity;
    private OnItemClickListner onItemClickListner;
    private int width = 0, height = 0;

    public MyRewardsAdapter(Activity activity, OnItemClickListner onItemClickListner) {
        this.activity = activity;
        this.onItemClickListner = onItemClickListner;
    }


    public void addAll(List<Rewards.Datum> list) {
        this.list.addAll(list);
        getWidthAndHeight();
        notifyDataSetChanged();
    }

    public List<Rewards.Datum> getList() {
        return this.list;
    }

    @Override
    public RecentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reward, parent, false);

        return new RecentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecentViewHolder holder, final int position) {

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClipboard(activity, list.get(position).code);
            }
        });

        holder.tvCouponCode.setText(list.get(position).code.toUpperCase());
        holder.tvCouponDesc.setText(list.get(position).description);

        if (position % 3 == 0) {
            holder.llReward.setBackgroundResource(R.drawable.reward1);
        } else if (position % 3 == 1) {
            holder.llReward.setBackgroundResource(R.drawable.reward2);
        } else {
            holder.llReward.setBackgroundResource(R.drawable.reward3);
        }

        if (list.get(position).isCouponScratched.equals(RequestParamUtils.strno)) {
            holder.tvScretchText.setVisibility(View.VISIBLE);
            holder.llScratched.setVisibility(View.GONE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showScratchCoupanDialog(list.get(position).code.toUpperCase(), list.get(position).description, position);
                }
            });

        } else {
            holder.tvScretchText.setVisibility(View.GONE);
            holder.llScratched.setVisibility(View.VISIBLE);
        }


    }

    private void setClipboard(Context context, String text) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText(RequestParamUtils.copiedText, text);
            clipboard.setPrimaryClip(clip);
        }

        Toast.makeText(activity, R.string.coupon_code_is_copied_to_clipboard, Toast.LENGTH_SHORT).show();
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

    //TODO:Show Dialog Of Finish Job
    public void showScratchCoupanDialog(String code, String discount, final int position) {
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_scratch_card);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        final ScratchTextView scratch_view = dialog.findViewById(R.id.scratch_view);
        ImageView ivClose = dialog.findViewById(R.id.ivClose);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        TextViewBold tvCouponCode = dialog.findViewById(R.id.tvCouponCode);
        TextViewMedium tvCouponDesc = dialog.findViewById(R.id.tvCouponDesc);
        tvCouponCode.setText(code + "");
        tvCouponDesc.setText(discount + "");
        scratch_view.setRevealListener(new ScratchTextView.IRevealListener() {
            @Override
            public void onRevealed(ScratchTextView tv) {
                if (Constant.DEVICE_TOKEN == null || Constant.DEVICE_TOKEN.equals("")) {
                    String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                    Constant.DEVICE_TOKEN = refreshedToken;
                }
                scretched(false, Constant.DEVICE_TOKEN, list.get(position).id + "", position);

            }

            @Override
            public void onRevealPercentChangedListener(ScratchTextView stv, float percent) {
                // on percent reveal.
            }
        });
        dialog.show();
    }

    public void scretched(Boolean dialog, String devicetoken, String couponid, int position) {
        if (Utils.isInternetConnected(activity)) {
            if (dialog) {
                ((BaseActivity) activity).showProgress("");
            }
            PostApi postApi = new PostApi(activity, RequestParamUtils.scretched + position, this, ((BaseActivity) activity).getlanuage());
            try {
                JSONObject jsonObject;
                if (FilterSelectedList.filterJson.equals("")) {
                    jsonObject = new JSONObject();
                } else {
                    jsonObject = new JSONObject(FilterSelectedList.filterJson);
                }
                jsonObject.put(RequestParamUtils.COUPON_ID, couponid);
                jsonObject.put(RequestParamUtils.ISCOUPON_SCRATCH, "yes");
                jsonObject.put(RequestParamUtils.DEVICE_TOKEN, devicetoken);
                jsonObject.put(RequestParamUtils.USER_ID, ((BaseActivity) activity).getPreferences().getString(RequestParamUtils.ID, ""));
                postApi.callPostApi(new URLS().SCRATCH_COUPON, jsonObject.toString());
            } catch (Exception e) {
                Log.e("Json Exception", e.getMessage());
            }

        } else {
            Toast.makeText(activity, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onResponse(String response, String methodName) {
        Log.e(methodName + "Response is ", response);
        if (methodName.contains(RequestParamUtils.scretched)) {
            if (response != null && response.length() > 0 && !response.equals("null")) {
                if (methodName.contains("-")) {
                    String[] array = methodName.split("-");
                    if (array.length > 0) {
                        try {
                            int position = Integer.parseInt(array[1]);
                            Success rewardsRider = new Gson().fromJson(
                                    response, new TypeToken<Success>() {
                                    }.getType());
                            if (rewardsRider.status.equals("success")) {
//                                Toast.makeText(activity, rewardsRider.message, Toast.LENGTH_LONG).show();
                                list.get(position).isCouponScratched = RequestParamUtils.yes;
                                notifyDataSetChanged();
                            }

                        } catch (Exception e) {
                            Log.e("Exception is ", e.getMessage());
                        }

                    }
                }
            }
        }
    }

    public class RecentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.llMain)
        LinearLayout llMain;

        @BindView(R.id.llReward)
        LinearLayout llReward;

        @BindView(R.id.llScratched)
        LinearLayout llScratched;

        @BindView(R.id.tvCouponCode)
        TextViewBold tvCouponCode;

        @BindView(R.id.tvCouponDesc)
        TextViewRegular tvCouponDesc;

        @BindView(R.id.tvScretchText)
        TextViewRegular tvScretchText;


        public RecentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
