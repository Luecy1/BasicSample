package com.github.luecy1.basicsample.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.github.luecy1.basicsample.AppExecutors;
import com.github.luecy1.basicsample.db.converter.DateConverter;
import com.github.luecy1.basicsample.db.dao.CommentDao;
import com.github.luecy1.basicsample.db.dao.ProductDao;
import com.github.luecy1.basicsample.db.entry.CommentEntry;
import com.github.luecy1.basicsample.db.entry.ProductEntry;

import java.util.List;

/**
 * Created by you on 2018/03/05.
 */
// TODO
@Database(entities = {ProductEntry.class, CommentEntry.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;

    @VisibleForTesting
    public static final String DATABASE_NAME = "basic-sample-db";

    public abstract ProductDao productDao();

    public abstract CommentDao commentDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                }
            }
        }
        return null;
    }

    private static AppDatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.mDiskIO().execute(() -> {

                            addDelay();

                            AppDatabase database = AppDatabase.getInstance(appContext, executors);
//                            List<ProductEntry> products =
//                            List<CommentEntry> comment =
                        });
                    }
                }).build();
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored){
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return null;
    }
}
