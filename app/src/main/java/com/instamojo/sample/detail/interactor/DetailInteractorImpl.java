package com.instamojo.sample.detail.interactor;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.instamojo.sample.common.net.APIResponseListener;
import com.instamojo.sample.common.util.APIRequest;
import com.instamojo.sample.common.util.VolleyUtil;
import com.instamojo.sample.detail.dto.DetailDTO;

/**
 * Created by manoj on 8/3/17.
 */

public class DetailInteractorImpl implements DetailInteractor {
    @Override
    public void getQuestionDetail(Context ctx, String url, final APIResponseListener<DetailDTO> listener, String tag) {
        APIRequest.Builder<DetailDTO> builder = new APIRequest.Builder<>(ctx, Request.Method.GET, DetailDTO.class, url,

                new Response.Listener<DetailDTO>() {
                    @Override
                    public void onResponse(DetailDTO response) {
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

    @Override
    public void getCommentsList(Context ctx, String url, final APIResponseListener<DetailDTO> listener, String tag) {
        APIRequest.Builder<DetailDTO> builder = new APIRequest.Builder<>(ctx, Request.Method.GET, DetailDTO.class, url,

                new Response.Listener<DetailDTO>() {
                    @Override
                    public void onResponse(DetailDTO response) {
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

    @Override
    public void getAnswersList(Context ctx, String url, final APIResponseListener<DetailDTO> listener, String tag) {
        APIRequest.Builder<DetailDTO> builder = new APIRequest.Builder<>(ctx, Request.Method.GET, DetailDTO.class, url,

                new Response.Listener<DetailDTO>() {
                    @Override
                    public void onResponse(DetailDTO response) {
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
