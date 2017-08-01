package com.std28.lib.interfaces;


import com.afollestad.materialdialogs.MaterialDialog;

public interface CommonsDialogs {

    void hideDialog();


    // Indeterminate dialogs
    void showIndeterminateDialog(int strIdTitle, int strIdMessage);

    void showIndeterminateDialog(CharSequence title, CharSequence message);

    void showIndeterminateDialog(int strIdMessage);

    void showIndeterminateDialog(CharSequence message);

    void showIndeterminateDialog();


    // Dismiss dialog
    void dismissDialog(int titleId, int messageId, int closeId);

    void dismissDialog(CharSequence title, CharSequence message, CharSequence close);

    void dialog(CharSequence title,
                CharSequence message,
                CharSequence positive,
                MaterialDialog.SingleButtonCallback onPositive);


    // Errors
    void errorDialog(String message);

    void errorDialog(int resId);

}
