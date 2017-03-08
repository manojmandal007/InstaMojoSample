package com.instamojo.sample.detail.ui;

import com.instamojo.sample.detail.dto.QuestionDetailDTO;

import java.util.List;

/**
 * Created by manoj on 8/3/17.
 */

public interface DetailView {

    void onNetworkError();

    void onDetailDataFetchSuccess(List<QuestionDetailDTO> list);

    void onDetailDataFetchFailed();

    void onCommentListDataFetchSuccess(List<QuestionDetailDTO> list);

    void onCommentListDataFetchFailed();

    void onAnswerListDataFetchSuccess(List<QuestionDetailDTO> list);

    void onAnswerListDataFetchFailed();

}
