package com.instamojo.sample.common.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;

import com.instamojo.sample.R;

/**
 * Created by manoj on 7/3/17.
 */

public class SplashDialogFragment extends DialogFragment {

    public static SplashDialogFragment newInstance() {
        return new SplashDialogFragment();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getActivity() != null) {
            Dialog dialog = new Dialog(getActivity(), R.style.FullscreenTheme);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_splash_dialog, null, false);
            dialog.setContentView(view);
            return dialog;
        }
        return super.onCreateDialog(savedInstanceState);
    }
}

