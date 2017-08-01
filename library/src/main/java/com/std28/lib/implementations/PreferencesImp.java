package com.std28.lib.implementations;


import android.content.Context;
import android.content.SharedPreferences;

import com.std28.lib.app.BaseApp;
import com.std28.lib.defs.Consts;
import com.std28.lib.interfaces.CommonsPreferences;


public class PreferencesImp
    implements CommonsPreferences, Consts

{
    public static final String PREF_NAME = PREFS;
    private Context mContext;

    public PreferencesImp(Context context) {
        super();
        this.mContext = context;
    }

    public static PreferencesImp Builder() {
        return new PreferencesImp(BaseApp.getContext());
    }

    public Context getContext() {
        return mContext;
    }

    public SharedPreferences getSharedPreferences() {
        return getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public String getPreferenceString(String key, String defaultValue) {
        return getSharedPreferences().getString(key, defaultValue);
    }

    public SharedPreferences.Editor getEditor() {
        return getSharedPreferences().edit();
    }

    public String getPreferenceString(String key) {
        return getPreferenceString(key, "");
    }


    public void setPreferenceString(String key, String value) {
        getEditor().putString(key, value).apply();
    }

    public boolean getPreferenceBoolean(String key, boolean defaultValue) {
        return getSharedPreferences().getBoolean(key, defaultValue);
    }

    public void setPreferenceBoolean(String key, boolean value) {
        getEditor().putBoolean(key, value).apply();
    }

    public void setPreferenceInt(String key, int value) {
        getEditor().putInt(key, value).apply();
    }

    public int getPreferenceInt(String key, int defaultValue) {
        return getSharedPreferences().getInt(key, defaultValue);
    }

    public int getPreferenceInt(String key) {
        return getPreferenceInt(key, 0);
    }


}
