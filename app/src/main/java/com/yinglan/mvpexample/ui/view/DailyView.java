package com.yinglan.mvpexample.ui.view;

import com.yinglan.mvpexample.base.IView;
import com.yinglan.mvpexample.model.bean.Daily;

import java.util.List;

/**
 * @function ${desc}
 * @auther: Created by sufei
 * @time: 16/10/18
 */

public interface DailyView extends IView {

    void loadData(List<Daily> dailies);

}
