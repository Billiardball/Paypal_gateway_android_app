package com.shop.arram.customview.like.animation;

import android.widget.ImageView;

/**
 * @author varun on 07/07/16.
 */
public interface SparkEventListener {
    void onEvent(ImageView button, boolean buttonState);
    void onEventAnimationEnd(ImageView button, boolean buttonState);
    void onEventAnimationStart(ImageView button, boolean buttonState);
}
