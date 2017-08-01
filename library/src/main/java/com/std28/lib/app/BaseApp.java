package com.std28.lib.app;

import android.app.Application;
import android.content.Context;


public class BaseApp
    extends Application
{

    private static BaseApp instance;

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

}
