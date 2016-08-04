package com.nightfarmer.satellitebutton;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangfan on 16-8-4.
 */
public abstract class SatelliteAdapter {

    public abstract View createMenuItem(ViewGroup parent, int index);
    public abstract View createToggleItem(ViewGroup parent);

    public abstract int getCount();




    public ObjectAnimator[] appendShowAnimator(View v) {
        return null;
    }

    public ObjectAnimator[] appendHideAnimator(View v) {
        return null;
    }

}
