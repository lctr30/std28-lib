package com.std28.lib.app;

import android.app.Application;
import android.content.Context;


public class BaseApp
    extends Application
{

    private static Application instance;

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    public static void setApplication(Application app) {
        instance = app;
    }

}
