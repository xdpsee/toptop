package com.zhenhui.apps.toptop.utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.zhenhui.apps.toptop.modules.app.GlideApp;


public class ImageHelper {

    @BindingAdapter(value = {"url", "placeholder", "error"}, requireAll = false)
    public static void loadImage(ImageView imageView, String url, Drawable placeholder, Drawable error) {
        GlideApp.with(imageView.getContext())
                .load(url)
                .placeholder(placeholder)
                .error(error)
                .into(imageView);

    }
}





