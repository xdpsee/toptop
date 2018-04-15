package com.zhenhui.apps.toptop.utils;

import android.databinding.BindingAdapter;
import android.view.View;
import android.view.ViewGroup;

public class LayoutUtils {

    @BindingAdapter("android:layout_marginTop")
    public static void adjustTopMargin(View view, int topMargin) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin
                , topMargin
                , layoutParams.rightMargin
                , layoutParams.bottomMargin);
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("android:paddingTop")
    public static void adjustTopPadding(View view, int topPadding) {

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams.height > 0) {
            layoutParams.height += topPadding;
            view.setLayoutParams(layoutParams);
        }

        view.setPadding(view.getPaddingLeft()
                , topPadding
                , view.getPaddingRight()
                , view.getPaddingBottom());

    }

}
