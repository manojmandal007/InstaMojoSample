package com.instamojo.sample.common.util;

import android.content.Context;
import android.view.WindowManager;

import com.instamojo.sample.R;

/**
 * Created by manoj on 8/3/17.
 */

public class DialogUtil {
    /**
     * Displays no network alert dialog.
     *
     * @param ctx
     */
    public static void showNoNetworkAlert(Context ctx) {
        try {
            new android.app.AlertDialog.Builder(ctx).setTitle(R.string.app_name).setMessage(R.string.no_internet)
                    .setPositiveButton(R.string.ok, null).create().show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }
}
