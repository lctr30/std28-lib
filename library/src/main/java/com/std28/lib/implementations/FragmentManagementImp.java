package com.std28.lib.implementations;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.std28.lib.interfaces.CommonsFragmentManagement;

/**
 * Created by Mauricio Barcelo on 11/02/18.
 * email: mauricio.barcelo30@gmail.com
 */

public class FragmentManagementImp
    implements CommonsFragmentManagement
{

    private FragmentManager fragmentManager;
    private int mainContainerId;

    public FragmentManagementImp(FragmentManager fragmentManager, int mainContainerId) {
        super();
        this.fragmentManager = fragmentManager;
        this.mainContainerId = mainContainerId;
    }

    @Override
    public void addFragment(Fragment fragment, String tag) {
        fragmentManager.beginTransaction()
                .add(mainContainerId, fragment, tag)
                .addToBackStack(tag)
                .commit();

    }

    @Override
    public void replaceFragment(Fragment fragment, String tag) {
        replaceFragment(fragment, tag, true);
    }

    @Override
    public void replaceFragment(Fragment fragment, String tag, boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(mainContainerId, fragment, tag);
        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }

    @Override
    public boolean isBackStackEmpty() {
        return fragmentManager.getBackStackEntryCount() == 0;
    }

    @Override
    public void clearBackStack() {
        for(int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
            fragmentManager.popBackStack();
        }
    }
}
