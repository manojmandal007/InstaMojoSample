package com.instamojo.sample.detail.presenter;

import android.content.Context;

import com.instamojo.sample.R;
import com.instamojo.sample.common.net.APIResponseListener;
import com.instamojo.sample.common.util.Constants;
import com.instamojo.sample.common.util.DialogUtil;
import com.instamojo.sample.common.util.NetworkUtil;
import com.instamojo.sample.common.util.VolleyUtil;
import com.instamojo.sample.detail.dto.DetailDTO;
import com.instamojo.sample.detail.interactor.DetailInteractor;
import com.instamojo.sample.detail.interactor.DetailInteractorImpl;
import com.instamojo.sample.detail.ui.DetailView;

/**
 * Created by manoj on 8/3/17.
 */

public class DetailPresenterImpl implements DetailPresenter {

    private DetailView mView;
    private DetailInteractor mInteractor = new DetailInteractorImpl();


    @Override
    public void getQuestionDetail(Context ctx, String tag, String id) {
        if (!NetworkUtil.isAvailable(ctx)) {
            mView.onNetworkError();
            DialogUtil.showNoNetworkAlert(ctx);
            return;
        }
        String url = ctx.getString(R.string.get_questions_details_url).replace(Constants.Placeholder.QUESTION_ID, id);

        mInteractor.getQuestionDetail(ctx, url, new APIResponseListener<DetailDTO>() {

            @Override
            public void onSuccessResponse(DetailDTO response) {
                if (mView == null)
                    return;
                if (response == null) {
                    mView.onDetailDataFetchFailed();
                    return;
                }
                mView.onDetailDataFetchSuccess(response.items);
            }

            @Override
            public void onResponseFailure(String message) {
                if (mView == null)
                    return;
                mView.onDetailDataFetchFailed();
            }
        }, tag);
    }

    @Override
    public void getCommentList(Context ctx, String tag, String id) {
        if (!NetworkUtil.isAvailable(ctx)) {
            mView.onNetworkError();
            DialogUtil.showNoNetworkAlert(ctx);
            return;
        }
        String url = ctx.getString(R.string.get_comments_url).replace(Constants.Placeholder.QUESTION_ID, id);

        mInteractor.getCommentsList(ctx, url, new APIResponseListener<DetailDTO>() {

            @Override
            public void onSuccessResponse(DetailDTO response) {
                if (mView == null)
                    return;
                if (response == null) {
                    mView.onDetailDataFetchFailed();
                    return;
                }
                mView.onCommentListDataFetchSuccess(response.items);
            }

            @Override
            public void onResponseFailure(String message) {
                if (mView == null)
                    return;
                mView.onCommentListDataFetchFailed();
            }
        }, tag);
    }

    @Override
    public void getAnswerList(Context ctx, String tag, String id) {
        if (!NetworkUtil.isAvailable(ctx)) {
            mView.onNetworkError();
            DialogUtil.showNoNetworkAlert(ctx);
            return;
        }
        String url = ctx.getString(R.string.get_answered_for_question_url).replace(Constants.Placeholder.QUESTION_ID, id);

        mInteractor.getAnswersList(ctx, url, new APIResponseListener<DetailDTO>() {

            @Override
            public void onSuccessResponse(DetailDTO response) {
                if (mView == null)
                    return;
                if (response == null) {
                    mView.onDetailDataFetchFailed();
                    return;
                }
                mView.onAnswerListDataFetchSuccess(response.items);
            }

            @Override
            public void onResponseFailure(String message) {
                if (mView == null)
                    return;
                mView.onAnswerListDataFetchFailed();
            }
        }, tag);
    }

    @Override
    public void setView(DetailView view) {
        mView = view;
    }


    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void cancelRequest(Context ctx, String tag) {
        VolleyUtil.getInstance(ctx).cancelRequest(tag);
    }
}
