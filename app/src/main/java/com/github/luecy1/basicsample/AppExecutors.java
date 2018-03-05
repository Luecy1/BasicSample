package com.github.luecy1.basicsample;


import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by you on 2018/03/05.
 */
public class AppExecutors {

    private final Executor mDiskIO;

    private final Executor mNetworkIO;

    private final Executor mMainThead;

    private AppExecutors(Executor mDiskIO, Executor mNetworkIO, Executor mMainThead) {
        this.mDiskIO = mDiskIO;
        this.mNetworkIO = mNetworkIO;
        this.mMainThead = mMainThead;
    }

    public AppExecutors() {
        this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3),
                new MainTheadExecutor());
    }

    public Executor mDiskIO() {
        return mDiskIO;
    }

    public Executor mNetworkIO() {
        return mNetworkIO;
    }

    public Executor mMainThead() {
        return mMainThead;
    }

    private static class MainTheadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
