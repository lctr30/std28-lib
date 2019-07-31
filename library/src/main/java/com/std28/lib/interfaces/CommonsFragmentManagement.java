package com.std28.lib.interfaces;

import androidx.fragment.app.Fragment;

/**
 * Created by Mauricio Barcelo on 11/02/18.
 * email: mauricio.barcelo30@gmail.com
 */

public interface CommonsFragmentManagement {

    void addFragment(Fragment fragment, String tag);

    void replaceFragment(Fragment fragment, String tag);

    void replaceFragment(Fragment fragment, String tag, boolean addToBackStack);

    boolean isBackStackEmpty();

    void clearBackStack();

}
