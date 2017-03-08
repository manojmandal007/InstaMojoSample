package com.instamojo.sample.landing.interactor;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.instamojo.sample.common.net.APIResponseListener;
import com.instamojo.sample.common.util.APIRequest;
import com.instamojo.sample.common.util.VolleyUtil;
import com.instamojo.sample.landing.dto.QuestionListDTO;

/**
 * Created by manoj on 8/3/17.
 */

public class QuestionListInteractorImpl implements QuestionListInteractor {

    @Override
    public void getQuestionList(Context ctx, String url, final APIResponseListener<QuestionListDTO> listener, String tag) {

        APIRequest.Builder<QuestionListDTO> builder = new APIRequest.Builder<>(ctx, Request.Method.GET, QuestionListDTO.class, url,

                new Response.Listener<QuestionListDTO>() {
                    @Override
                    public void onResponse(QuestionListDTO response) {
                        listener.onSuccessResponse(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onResponseFailure(error.getLocalizedMessage());
                    }
                });

        APIRequest request = builder.build();
        request.setTag(tag);
        VolleyUtil.getInstance(ctx).addToRequestQueue(request);
    }
}
