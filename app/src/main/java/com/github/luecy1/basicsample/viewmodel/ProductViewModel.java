package com.github.luecy1.basicsample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.github.luecy1.basicsample.BasicApp;
import com.github.luecy1.basicsample.DataRepository;
import com.github.luecy1.basicsample.db.entry.CommentEntry;
import com.github.luecy1.basicsample.db.entry.ProductEntry;

import java.util.List;

/**
 * Created by you on 2018/03/10.
 */
public class ProductViewModel extends AndroidViewModel {

    private final LiveData<ProductEntry> mObservableProduct;

    public ObservableField<ProductEntry> product = new ObservableField<>();

    private final int mProductId;

    private final LiveData<List<CommentEntry>> mObservableComments;

    public ProductViewModel(@NonNull Application application, DataRepository repository,
                            final int productId) {
        super(application);
        mProductId = productId;

        mObservableComments = repository.loadComments(mProductId);
        mObservableProduct = repository.loadProduct(mProductId);
    }

    public LiveData<List<CommentEntry>> getComments() {
        return mObservableComments;
    }

    public LiveData<ProductEntry> getObservableProduct() {
        return mObservableProduct;
    }

    public void setProduct(ProductEntry product) {
        this.product.set(product);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mProductId;

        private final DataRepository mRepository;

        public Factory(@NonNull Application application, int productId) {
            this.mApplication = application;
            this.mProductId = productId;
            this.mRepository = ((BasicApp) application).getRepository();
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ProductViewModel(mApplication, mRepository, mProductId);
        }
    }

}
