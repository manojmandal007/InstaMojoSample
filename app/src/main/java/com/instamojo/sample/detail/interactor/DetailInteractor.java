package com.instamojo.sample.detail.interactor;

import android.content.Context;

import com.instamojo.sample.common.net.APIResponseListener;
import com.instamojo.sample.detail.dto.DetailDTO;

/**
 * Created by manoj on 8/3/17.
 */

public interface DetailInteractor {

    void getQuestionDetail(Context ctx, String url, APIResponseListener<DetailDTO> listener, String tag);

    void getCommentsList(Context ctx, String url, APIResponseListener<DetailDTO> listener, String tag);

    void getAnswersList(Context ctx, String url, APIResponseListener<DetailDTO> listener, String tag);
}
