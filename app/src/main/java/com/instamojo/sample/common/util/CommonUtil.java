package com.instamojo.sample.common.util;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by manoj on 8/3/17.
 */

public class CommonUtil {

    public static void hideKeyboard(Activity context) {
        if (context != null && context.getCurrentFocus() != null) {
            InputMethodManager keyBoardHandle = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            keyBoardHandle.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(),
                    InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }

    public static String getFormattedListUrl(String url, int pageNumber) {
        if (TextUtils.isEmpty(url))
            return "";
        return url.replace(Constants.Placeholder.PAGE_NO, String.valueOf(pageNumber));
    }

}
