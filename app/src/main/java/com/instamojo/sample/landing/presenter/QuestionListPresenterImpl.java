package com.instamojo.sample.landing.presenter;

import android.content.Context;

import com.instamojo.sample.R;
import com.instamojo.sample.common.net.APIResponseListener;
import com.instamojo.sample.common.util.Constants;
import com.instamojo.sample.common.util.DialogUtil;
import com.instamojo.sample.common.util.NetworkUtil;
import com.instamojo.sample.common.util.VolleyUtil;
import com.instamojo.sample.landing.dto.QuestionListDTO;
import com.instamojo.sample.landing.interactor.QuestionListInteractor;
import com.instamojo.sample.landing.interactor.QuestionListInteractorImpl;
import com.instamojo.sample.landing.ui.QuestionListView;

import java.util.List;

/**
 * Created by manoj on 8/3/17.
 */

public class QuestionListPresenterImpl implements QuestionListPresenter {
    private QuestionListView mView;
    private QuestionListInteractor mInteractor = new QuestionListInteractorImpl();

    @Override
    public void setView(QuestionListView view) {
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


    @Override
    public void getQuestionList(Context ctx, String tag, List<String> searchTags, String unformattedUrl) {
        if (!NetworkUtil.isAvailable(ctx)) {
            mView.onNetworkError();
            DialogUtil.showNoNetworkAlert(ctx);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();

        int tagSize = searchTags.size();
        if (tagSize > 1) {
            for (int pos = 0; pos < searchTags.size(); pos++) {
                stringBuilder.append(searchTags.get(pos));
                if (pos < tagSize - 1)
                    stringBuilder.append(";");
            }
        } else

            for (String query : searchTags)
                stringBuilder.append(query);

        String url = unformattedUrl.replace(Constants.Placeholder.TAG, stringBuilder.toString());

        mInteractor.getQuestionList(ctx, url, new APIResponseListener<QuestionListDTO>() {

            @Override
            public void onSuccessResponse(QuestionListDTO response) {
                if (mView == null)
                    return;
                if (response == null) {
                    mView.onListDataFetchFailed();
                    return;
                }
                mView.onAnsweredListDataFetchSuccess(response.items);
            }

            @Override
            public void onResponseFailure(String message) {
                if (mView == null)
                    return;
                mView.onListDataFetchFailed();
            }
        }, tag);
    }

}
