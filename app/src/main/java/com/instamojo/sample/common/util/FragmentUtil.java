package com.instamojo.sample.common.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by manoj on 7/3/17.
 */
public class FragmentUtil {
    /**
     * Replaces fragment without adding it to the back stack .
     */
    public static void replaceFragment(FragmentActivity activity, Fragment fragment,
                                       int containerId) {
        if (!activity.isFinishing()) {
            FragmentManager manager = activity.getSupportFragmentManager();
            manager.beginTransaction().replace(containerId, fragment).commitAllowingStateLoss();
        }
    }

    /**
     * Replaces and adds the fragment to the back stack.
     */
    public static void replaceAndAddFragment(FragmentActivity activity, Fragment fragment, int containerId) {
        if (activity != null && !activity.isFinishing()) {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            transaction.replace(containerId, fragment);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        }
    }

    /**
     * This removes current fragment and adds a new child fragment.
     */
    public static void replaceChildFragment(Fragment parentFragment, int containerId, Fragment childFragment) {
        if (parentFragment != null && !parentFragment.isDetached())
            parentFragment.getChildFragmentManager().beginTransaction().replace(containerId, childFragment).commitAllowingStateLoss();
    }

    public static Fragment getCurrentFragment(FragmentActivity activity, int containerId) {
        if (activity != null)
            return activity.getSupportFragmentManager().findFragmentById(containerId);
        return null;
    }


    public static void popBackStackImmediate(FragmentActivity activity) {
        activity.getSupportFragmentManager().popBackStackImmediate();
    }


}
