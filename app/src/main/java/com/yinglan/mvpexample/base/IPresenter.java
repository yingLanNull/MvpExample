package com.yinglan.mvpexample.base;

/**
 * @function ${desc}
 * @auther: Created by yinglan
 * @time: 16/8/25
 */
public interface IPresenter<T extends IView>{
    void detachView();
}
