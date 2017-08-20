package com.detatocargo.android.detatocargo.app;

import android.app.Application;

import com.detatocargo.android.detatocargo.BuildConfig;

import timber.log.Timber;

/**
 * Created by Aldyaz on 8/11/2017.
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
