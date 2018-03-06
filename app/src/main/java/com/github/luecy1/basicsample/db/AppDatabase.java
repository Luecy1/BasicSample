package com.github.luecy1.basicsample.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.github.luecy1.basicsample.AppExecutors;
import com.github.luecy1.basicsample.db.converter.DateConverter;
import com.github.luecy1.basicsample.db.entry.CommentEntry;
import com.github.luecy1.basicsample.db.entry.ProductEntry;

/**
 * Created by you on 2018/03/05.
 */
// TODO
@Database(entities = {ProductEntry.class, CommentEntry.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;

    public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                }
            }
        }
        return null;
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return null;
    }
}
