package com.std28.lib.interfaces;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by Mauricio Barcelo on 11/02/18.
 * email: mauricio.barcelo30@gmail.com
 */

public interface CommonsFragmentManagement {

    void initialize(FragmentManager fragmentManager, int mainContainerId);

    void addFragment(Fragment fragment, String tag);

    void replaceFragment(Fragment fragment, String tag);

    void replaceFragment(Fragment fragment, String tag, boolean addToBackStack);


    boolean isBackStackEmpty();

    void clearBackStack();


}
