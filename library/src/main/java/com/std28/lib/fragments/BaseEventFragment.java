package com.std28.lib.fragments;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Mauricio Barcelo on 30/07/19.
 * email: mauricio.barcelo30@gmail.com
 */
public class BaseEventFragment
    extends BaseFragment
{
    private boolean isRegistered = false;

    @Override
    public void onStart() {
        super.onStart();
        if (!isRegistered) {
            EventBus.getDefault().register(this);
            isRegistered = true;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (isRegistered) {
            EventBus.getDefault().unregister(this);
            isRegistered = false;
        }
    }
}
