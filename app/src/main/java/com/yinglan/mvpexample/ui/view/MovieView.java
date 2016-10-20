package com.yinglan.mvpexample.ui.view;

import com.yinglan.mvpexample.base.IView;
import com.yinglan.mvpexample.model.bean.MovieBean;

import java.util.List;

/**
 * @function ${desc}
 * @auther: Created by sufei
 * @time: 16/10/20
 */

public interface MovieView extends IView {
    void loadData(List<MovieBean.MovieInfo> data);
}
