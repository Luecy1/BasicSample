package com.github.luecy1.basicsample.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.luecy1.basicsample.R;
import com.github.luecy1.basicsample.databinding.ListFragmentBinding;
import com.github.luecy1.basicsample.viewmodel.ProductListViewModel;


/**
 * Created by you on 2018/02/24.
 */
public class ProductListFragment extends Fragment {

    public static final String TAG = "ProductListViewModel";

    private ProductAdapter mProductAdapter;

    private ListFragmentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false);

        mProductAdapter = new ProductAdapter(mProductClickCallback);
        mBinding.productsList.setAdapter(mProductAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ProductListViewModel viewModel =
                ViewModelProviders.of(this).get(ProductListViewModel.class);

        subscribeUi(viewModel);
    }

    private void subscribeUi(ProductListViewModel viewModel) {

        viewModel.getProducts().observe(this, myProducts -> {
            if (myProducts != null) {
                mBinding.setIsLoading(false);
                mProductAdapter.setProductList(myProducts);
            } else {
                mBinding.setIsLoading(true);
            }

            mBinding.executePendingBindings();
        });
    }

    private final ProductClickCallback mProductClickCallback = product -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) getActivity()).show(product);
        }
    };
}
