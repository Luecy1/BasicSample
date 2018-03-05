package com.github.luecy1.basicsample.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.github.luecy1.basicsample.AppExecutors;
import com.github.luecy1.basicsample.db.entry.ProductEntry;

/**
 * Created by you on 2018/03/05.
 */
// TODO
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
}
