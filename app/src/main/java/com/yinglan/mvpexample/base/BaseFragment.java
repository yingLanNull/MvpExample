package com.yinglan.mvpexample.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

/**
 * Created by yinglan on 16/5/30.
 */
public abstract class BaseFragment<T extends IPresenter> extends Fragment implements IView {

    protected T mPresenter;
    protected View mView;
    protected AppCompatActivity mContext;
    protected Handler mHandler;

    @Override
    public void onAttach(Activity activity) {
        mContext = (AppCompatActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEventAndData(savedInstanceState);
        mHandler = new Handler();
        EventBus.getDefault().register(this);
        mContext.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this, mView);
        initView(mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = getPresenter();
        initData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 网络状态改变,可以在网络重有时加载数据
     * @param e
     */
    @Subscribe
    public void netWorkChanged(String e){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public AppCompatActivity getmActivity() {
        return mContext;
    }

    @Override
    public Handler getHandler() {
        return mHandler;
    }

    public View getRootView() {
        return mView;
    }

    protected abstract void initEventAndData(Bundle savedInstanceState);

    protected abstract int getLayoutId();

    protected abstract void initView(View view);

    protected abstract T getPresenter();

    protected abstract void initData();

}
