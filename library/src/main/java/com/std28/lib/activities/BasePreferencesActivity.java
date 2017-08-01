package com.std28.lib.activities;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.std28.lib.implementations.PreferencesImp;
import com.std28.lib.interfaces.CommonsPreferences;


public class BasePreferencesActivity
    extends BaseActivity
    implements CommonsPreferences
{

    protected PreferencesImp mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPrefs = new PreferencesImp(getBaseContext());
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return mPrefs.getSharedPreferences();
    }

    @Override
    public SharedPreferences.Editor getEditor() {
        return mPrefs.getEditor();
    }

    @Override
    public String getPreferenceString(String key, String defaultValue) {
        return mPrefs.getPreferenceString(key, defaultValue);
    }

    @Override
    public String getPreferenceString(String key) {
        return mPrefs.getPreferenceString(key);
    }

    @Override
    public void setPreferenceString(String key, String value) {
        mPrefs.setPreferenceString(key, value);
    }

    @Override
    public void setPreferenceBoolean(String key, boolean value) {
        mPrefs.setPreferenceBoolean(key, value);
    }

    @Override
    public void setPreferenceInt(String key, int value) {
        mPrefs.setPreferenceInt(key, value);
    }

    @Override
    public int getPreferenceInt(String key, int defaultValue) {
        return mPrefs.getPreferenceInt(key, defaultValue);
    }

    @Override
    public int getPreferenceInt(String key) {
        return mPrefs.getPreferenceInt(key);
    }

    @Override
    public boolean getPreferenceBoolean(String key, boolean defaultValue) {
        return mPrefs.getPreferenceBoolean(key, defaultValue);
    }

}
