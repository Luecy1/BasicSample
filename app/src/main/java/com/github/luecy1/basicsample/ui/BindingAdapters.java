package com.github.luecy1.basicsample.ui;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by you on 2018/03/01.
 */

public class BindingAdapters {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
