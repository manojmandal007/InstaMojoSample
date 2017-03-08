package com.instamojo.sample.common.presenter;

import android.content.Context;

/**
 * Created by manoj on 8/3/17.
 */

public interface BasePresenter {

    void onDestroy();

    void cancelRequest(Context ctx, String tag);
}
