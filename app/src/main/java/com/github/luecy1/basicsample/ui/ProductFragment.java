package com.github.luecy1.basicsample.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by you on 2018/03/04.
 */
// TODO
public class ProductFragment extends Fragment {

    private static final String KEY_PRODUCT_ID = "product_id";

    public static ProductFragment forProduct(int productId) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_PRODUCT_ID, productId);
        fragment.setArguments(args);
        return fragment;
    }
}
