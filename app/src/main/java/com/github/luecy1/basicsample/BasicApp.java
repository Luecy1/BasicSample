package com.github.luecy1.basicsample;

import android.app.Application;

import com.github.luecy1.basicsample.db.AppDatabase;

/**
 * Created by you on 2018/02/24.
 */
public class BasicApp extends Application {

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = new AppExecutors();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this, mAppExecutors);
    }

    public DataRepository getRepository() {
        return DataRepository.getsInstance(getDatabase());
    }

}
