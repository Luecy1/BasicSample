package com.github.luecy1.basicsample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MediatorLiveData;

import com.github.luecy1.basicsample.db.entry.ProductEntry;

import java.util.List;

/**
 * Created by you on 2018/03/05.
 */
// TODO
public class ProductListViewModel extends AndroidViewModel {

    private final MediatorLiveData<List<ProductEntry>> mObservableProducts;

    public ProductListViewModel(Application application) {
        super(application);

        mObservableProducts = new MediatorLiveData<>();
        mObservableProducts.setValue(null);

    }
}
