package com.instamojo.sample.landing.presenter;

import android.content.Context;

import com.instamojo.sample.common.presenter.BasePresenter;
import com.instamojo.sample.landing.ui.QuestionListView;

import java.util.List;

/**
 * Created by manoj on 8/3/17.
 */

public interface QuestionListPresenter extends BasePresenter {

    void setView(QuestionListView view);

    void getQuestionList(Context ctx, String tag, List<String> searchTags, String unformattedUrl);

}
