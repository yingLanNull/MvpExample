package com.yinglan.mvpexample.base;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * @function ${desc}
 * @auther: Created by yinglan
 * @time: 16/8/25
 */
public abstract class BasePresenter<T extends IView> implements IPresenter<T> {

    public AppCompatActivity mActivity;
    protected T mView;
    protected Handler mHandler;

    public BasePresenter(T mView) {
        this.mView = mView;
        this.mActivity = mView.getmActivity();
        this.mHandler = mView.getHandler();
    }

    @Override
    public void detachView() {
        if (null != this.mView) {
            this.mView = null;
        }
    }
}
