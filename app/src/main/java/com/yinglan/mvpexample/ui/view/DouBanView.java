package com.yinglan.mvpexample.ui.view;

import com.yinglan.mvpexample.base.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * @function ${desc}
 * @auther: Created by yinglan
 * @time: 16/10/19
 */

public interface DouBanView extends IView {
   void loadData(ArrayList<String> urls);
}
