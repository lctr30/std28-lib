package com.std28.lib.activities;

import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.std28.lib.implementations.CommonsDialogsImp;
import com.std28.lib.interfaces.CommonsDialogs;


public class BaseDialogActivity
    extends BaseActivity
    implements CommonsDialogs
{

    private CommonsDialogs mDialogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mDialogs = new CommonsDialogsImp(this);
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
}
