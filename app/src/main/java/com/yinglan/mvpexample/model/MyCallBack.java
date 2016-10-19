package com.yinglan.mvpexample.model;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @function ${desc}
 * @auther: Created by yinglan
 * @time: 16/9/20
 */
public abstract class MyCallBack<T> implements Callback<T> {


    public MyCallBack() {

    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            T t = response.body();

            if (null != t) {
                requestSuccess(t);
            } else {
                requestError("");
            }
        } else {

        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof ConnectException) {
            requestError("网络异常");
        } else if (t instanceof SocketTimeoutException) {
            requestError("网络连接超时");
        } else {
            requestError(t.getMessage());
        }
    }

    /**
     * 成功
     */
    public abstract void requestSuccess(T result);

    /**
     * 错误
     */
    public abstract void requestError(String errorMessage);
}
