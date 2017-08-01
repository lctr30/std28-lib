package com.std28.lib.implementations;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.std28.lib.R;
import com.std28.lib.interfaces.CommonsDialogs;

import org.apache.commons.lang3.StringUtils;


public class CommonsDialogsImp
    implements CommonsDialogs
{

    private MaterialDialog mDialog;
    private Context mContext;

    public CommonsDialogsImp(Context context) {
        super();
        this.mContext = context;
    }

    @Override
    public void hideDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        mDialog = null;
    }

    @Override
    public void showIndeterminateDialog(int strIdTitle, int strIdMessage) {
        showIndeterminateDialog(getString(strIdTitle), getString(strIdMessage));
    }

    @Override
    public void showIndeterminateDialog(CharSequence title, CharSequence message) {
        if (mDialog != null) {
            hideDialog();
        }
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this.mContext);
        if (StringUtils.isNotEmpty(title)) {
            builder.title(title);
        }
        if (StringUtils.isNotEmpty(message)) {
            builder.content(message);
        }
        builder.progress(true, 0);
        mDialog = builder.build();
        mDialog.show();
    }

    @Override
    public void showIndeterminateDialog(int strIdMessage) {
        showIndeterminateDialog(0, strIdMessage);
    }

    @Override
    public void showIndeterminateDialog(CharSequence message) {
        showIndeterminateDialog(null, message);
    }

    @Override
    public void showIndeterminateDialog() {
        showIndeterminateDialog(0, R.string.please_wait);
    }

    @Override
    public void dismissDialog(int titleId, int messageId, int closeId) {
        dismissDialog(getString(titleId), getString(messageId), getString(closeId));
    }

    @Override
    public void dismissDialog(CharSequence title, CharSequence message, CharSequence close) {
        dialog(title, message, close, null);
    }

    @Override
    public void dialog(CharSequence title,
                       CharSequence message,
                       CharSequence positive,
                       MaterialDialog.SingleButtonCallback onPositive)
    {
        if (mDialog != null) {
            hideDialog();
        }
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this.mContext);
        if (StringUtils.isNotBlank(title)) {
            builder.title(title);
        }
        if (StringUtils.isNotBlank(message)) {
            builder.content(message);
        }
        if (StringUtils.isNotBlank(positive)) {
            builder.positiveText(positive);
        } else {
            builder.positiveText(android.R.string.ok);
        }
        if (onPositive != null) {
            builder.onPositive(onPositive);
        }
        mDialog = builder.build();
        mDialog.show();
    }

    @Override
    public void errorDialog(String message) {
        dismissDialog(null, message, getString(android.R.string.ok));
    }

    @Override
    public void errorDialog(int resId) {
        dismissDialog(null, getString(resId), getString(android.R.string.ok));
    }

    private String getString(int resId) {
        if (resId == 0) return null;
        return this.mContext.getString(resId);
    }
}
