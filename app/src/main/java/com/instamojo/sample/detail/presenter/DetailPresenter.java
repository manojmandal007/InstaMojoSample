package com.instamojo.sample.detail.presenter;

import android.content.Context;

import com.instamojo.sample.common.presenter.BasePresenter;
import com.instamojo.sample.detail.ui.DetailView;

/**
 * Created by manoj on 8/3/17.
 */

public interface DetailPresenter extends BasePresenter {


    void setView(DetailView view);

    void getQuestionDetail(Context ctx, String tag, String id);

    void getCommentList(Context ctx, String tag, String id);

    void getAnswerList(Context ctx, String tag, String id);

}
