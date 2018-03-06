package com.github.luecy1.basicsample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.github.luecy1.basicsample.db.AppDatabase;
import com.github.luecy1.basicsample.db.entry.CommentEntry;
import com.github.luecy1.basicsample.db.entry.ProductEntry;

import java.util.List;

/**
 * Created by you on 2018/03/05.
 */
// TODO
public class DataRepository {

    private static DataRepository sInstance;

    private final AppDatabase mDatabase;
    private MediatorLiveData<List<ProductEntry>> mObserverProducts;

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
        mObserverProducts = new MediatorLiveData<>();

    }

    public static DataRepository getsInstance(final AppDatabase database) {

        return null;
    }

    public LiveData<List<ProductEntry>> getProducts() {
        return null;
    }

    public LiveData<ProductEntry> loadProduct(final int productId) {
        return null;
    }

    public LiveData<List<CommentEntry>> loadComments(final int productId) {
        return null;
    }
}
