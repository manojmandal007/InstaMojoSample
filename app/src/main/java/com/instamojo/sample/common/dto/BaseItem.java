package com.instamojo.sample.common.dto;


/**
 * Created by manoj on 8/3/17.
 */

import com.instamojo.sample.common.view.ViewType;

/**
 * BaseDTO is the root/base class for other listing DTO class. It contains different view type
 */
public class BaseItem {
    public ViewType viewType = ViewType.ANSWER;

    public BaseItem(ViewType type) {
        viewType = type;
    }

    public BaseItem() {
    }

    @Override
    public boolean equals(Object o) {
        return ((BaseItem) o).viewType == viewType;
    }
}
