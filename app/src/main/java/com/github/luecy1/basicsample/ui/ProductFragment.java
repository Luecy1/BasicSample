package com.github.luecy1.basicsample.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.luecy1.basicsample.R;
import com.github.luecy1.basicsample.databinding.ProductFragmentBinding;
import com.github.luecy1.basicsample.db.entry.CommentEntry;
import com.github.luecy1.basicsample.db.entry.ProductEntry;
import com.github.luecy1.basicsample.model.Comment;
import com.github.luecy1.basicsample.viewmodel.ProductViewModel;

import java.util.List;

/**
 * Created by you on 2018/03/04.
 */
public class ProductFragment extends Fragment {

    private static final String KEY_PRODUCT_ID = "product_id";

    private ProductFragmentBinding mBinding;

    private CommentAdapter mCommentAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.product_fragment, container, false);

        mCommentAdapter = new CommentAdapter(mCommentClickCallback);
        mBinding.commentList.setAdapter(mCommentAdapter);
        return mBinding.getRoot();
    }

    private final CommentClickCallback mCommentClickCallback = new CommentClickCallback() {
        @Override
        public void onClick(Comment comment) {
            // no-op
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ProductViewModel.Factory factory = new ProductViewModel.Factory(
                getActivity().getApplication(), getArguments().getInt(KEY_PRODUCT_ID)
        );

        final ProductViewModel model = ViewModelProviders.of(this, factory)
                .get(ProductViewModel.class);

        mBinding.setProductViewModel(model);

    }

    private void subscribeToModel(final ProductViewModel model) {

        model.getObservableProduct().observe(this, new Observer<ProductEntry>() {
            @Override
            public void onChanged(@Nullable ProductEntry productEntry) {
                model.setProduct(productEntry);
            }
        });

        model.getComments().observe(this, new Observer<List<CommentEntry>>() {
            @Override
            public void onChanged(@Nullable List<CommentEntry> commentEntries) {
                if (commentEntries != null) {
                    mBinding.setIsLoading(false);
                    mCommentAdapter.setmCommentList(commentEntries);
                } else {
                    mBinding.setIsLoading(true);
                }
            }
        });
    }

    public static ProductFragment forProduct(int productId) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_PRODUCT_ID, productId);
        fragment.setArguments(args);
        return fragment;
    }
}
