package com.github.luecy1.basicsample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.github.luecy1.basicsample.db.AppDatabase;
import com.github.luecy1.basicsample.db.dao.DataGenerator;
import com.github.luecy1.basicsample.db.entry.CommentEntry;
import com.github.luecy1.basicsample.db.entry.ProductEntry;

import java.util.List;

/**
 * Created by you on 2018/03/05.
 */
public class DataRepository {

    private static DataRepository sInstance;

    private final AppDatabase mDatabase;
    private MediatorLiveData<List<ProductEntry>> mObserverProducts;

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
        mObserverProducts = new MediatorLiveData<>();

        mObserverProducts.addSource(mDatabase.productDao().loadAllProducts(),
                productEntries -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObserverProducts.postValue(productEntries);
                    }
                });

    }

    public static DataRepository getsInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (DataGenerator.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<ProductEntry>> getProducts() {
        return mObserverProducts;
    }

    public LiveData<ProductEntry> loadProduct(final int productId) {
        return mDatabase.productDao().loadProduct(productId);
    }

    public LiveData<List<CommentEntry>> loadComments(final int productId) {
        return mDatabase.commentDao().loadComments(productId);
    }
}
