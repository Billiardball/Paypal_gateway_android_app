package com.shop.arram.utils;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.shop.arram.R;
import com.shop.arram.customview.textview.TextViewRegular;

/**
 * Created by Bhumi Shah on 11/28/2017.
 */

public class CustomToast {
    private Activity context;
    private TextViewRegular tvTitle;
    private LinearLayout toast_layout_root;
    Toast toast;
    private Animation animFadein;


    public CustomToast(Activity context) {
        this.context = context;
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER | Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(setLayout());

    }

    public void showToast(String toastString) {
        if (toastString != null) {
            tvTitle.setText("" + toastString);
        }
        toast_layout_root.setBackgroundColor(Color.BLACK);

        toast.show();
        animFadein = AnimationUtils.loadAnimation(context,
                R.anim.slide_up);
        toast_layout_root.startAnimation(animFadein);
    }

    public void cancelToast() {
        toast.cancel();
    }

    public void showRedbg() {
        toast_layout_root.setBackgroundColor(Color.RED);
    }

    public void showBlackbg() {
        toast_layout_root.setBackgroundColor(Color.BLACK);
    }

    public void showPrimarybg() {
        toast_layout_root.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
    }

    public View setLayout() {
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.layout_toast,
                (ViewGroup) context.findViewById(R.id.toast_layout_root));
        tvTitle = layout.findViewById(R.id.tvTitle);
        toast_layout_root = layout.findViewById(R.id.toast_layout_root);
        return layout;
    }

}
