/*
 * Copyright © 2016, Dubber
 * Written under contract by Robosoft Technologies Pvt. Ltd.
 */

package com.instamojo.sample.common.view;

/**
 * Created by Mahesh Nayak on 13-07-2016.
 */

/**
 * Created by manoj on 8/3/17.
 */

public enum ViewType {
    DETAIL(0), ANSWER(1), HEADER_VIEW(2);
    private final int value;

    ViewType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ViewType valueOf(int value) {
        for (ViewType view : ViewType.values()) {
            if (view.value == value)
                return view;
        }
        return HEADER_VIEW;
    }
}
