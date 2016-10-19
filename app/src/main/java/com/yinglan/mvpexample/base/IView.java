package com.yinglan.mvpexample.base;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * @function ${desc}
 * @auther: Created by yinglan
 * @time: 16/8/25
 */
public interface IView {
    AppCompatActivity getmActivity();
    Handler getHandler();
}
