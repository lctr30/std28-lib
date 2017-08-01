package com.std28.lib.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.std28.lib.defs.Consts;
import com.std28.lib.implementations.CommonsDialogsImp;
import com.std28.lib.implementations.PreferencesImp;
import com.std28.lib.interfaces.CommonsDialogs;
import com.std28.lib.interfaces.CommonsPreferences;


public class BaseFragment
    extends Fragment
    implements CommonsDialogs, CommonsPreferences, Consts
{

    protected PreferencesImp mPrefs;
    protected CommonsDialogsImp mDialogs;
    protected View mRootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPrefs = new PreferencesImp(getContext());
        this.mDialogs = new CommonsDialogsImp(getContext());
    }


    @Override
    public void hideDialog() {
        this.mDialogs.hideDialog();
    }

    @Override
    public void showIndeterminateDialog(int strIdTitle, int strIdMessage) {
        this.mDialogs.showIndeterminateDialog(strIdTitle, strIdMessage);
    }

    @Override
    public void showIndeterminateDialog(CharSequence title, CharSequence message) {
        this.mDialogs.showIndeterminateDialog(title, message);
    }

    @Override
    public void showIndeterminateDialog(int strIdMessage) {
        this.mDialogs.showIndeterminateDialog(strIdMessage);
    }

    @Override
    public void showIndeterminateDialog(CharSequence message) {
        this.mDialogs.showIndeterminateDialog(message);
    }

    @Override
    public void showIndeterminateDialog() {
        this.mDialogs.showIndeterminateDialog();
    }

    @Override
    public void dismissDialog(int titleId, int messageId, int closeId) {
        this.mDialogs.dismissDialog(titleId, messageId, closeId);
    }

    @Override
    public void dismissDialog(CharSequence title, CharSequence message, CharSequence close) {
        this.mDialogs.dismissDialog(title, message, close);
    }

    @Override
    public void dialog(CharSequence title,
                       CharSequence message,
                       CharSequence positive,
                       MaterialDialog.SingleButtonCallback onPositive) {
        this.mDialogs.dialog(title, message, positive, onPositive);
    }

    @Override
    public void errorDialog(String message) {
        this.mDialogs.errorDialog(message);
    }

    @Override
    public void errorDialog(int resId) {
        this.mDialogs.errorDialog(resId);
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
