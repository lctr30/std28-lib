package com.std28.lib.interfaces;

import android.content.SharedPreferences;


public interface CommonsPreferences {


    SharedPreferences getSharedPreferences();

    SharedPreferences.Editor getEditor();

    String getPreferenceString(String key, String defaultValue);

    String getPreferenceString(String key);

    void setPreferenceString(String key, String value);

    void setPreferenceBoolean(String key, boolean value);

    void setPreferenceInt(String key, int value);

    int getPreferenceInt(String key, int defaultValue);

    int getPreferenceInt(String key);

    boolean getPreferenceBoolean(String key, boolean defaultValue);

}
