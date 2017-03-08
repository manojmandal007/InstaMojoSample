package com.instamojo.sample.common.net;

/**
 * Created by manoj on 8/3/17.
 */

/**
 * This interface act as Base listener for every api response
 */
public interface APIResponseListener<T> {

    void onSuccessResponse(T response);

    void onResponseFailure(String message);
}
