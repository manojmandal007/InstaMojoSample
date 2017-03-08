package com.instamojo.sample.landing.ui;

import com.instamojo.sample.landing.dto.ItemDTO;

import java.util.List;

/**
 * Created by manoj on 8/3/17.
 */

public interface QuestionListView {

    void onNetworkError();

    void onAnsweredListDataFetchSuccess(List<ItemDTO> dataList);

    void onListDataFetchFailed();

}
