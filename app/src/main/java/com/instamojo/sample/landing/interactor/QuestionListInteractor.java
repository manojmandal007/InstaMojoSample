package com.instamojo.sample.landing.interactor;

import android.content.Context;

import com.instamojo.sample.common.net.APIResponseListener;
import com.instamojo.sample.landing.dto.QuestionListDTO;

/**
 * Created by manoj on 8/3/17.
 */

public interface QuestionListInteractor {

    void getQuestionList(Context ctx, String url, APIResponseListener<QuestionListDTO> listener, String tag);

}
