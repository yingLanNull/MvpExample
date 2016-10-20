package com.yinglan.mvpexample.presenter;

import com.yinglan.mvpexample.base.BasePresenter;
import com.yinglan.mvpexample.model.MyApi;
import com.yinglan.mvpexample.model.MyCallBack;
import com.yinglan.mvpexample.model.MyRetrofit;
import com.yinglan.mvpexample.model.bean.DailyResult;
import com.yinglan.mvpexample.ui.view.DailyView;

import retrofit2.Call;

/**
 * @function ${desc}
 * @auther: Created by yinglan
 * @time: 16/10/18
 */
public class DailyPresenter extends BasePresenter<DailyView> {


    public DailyPresenter(DailyView mViewfinal, int date) {
        super(mViewfinal);
        getNews(date);
    }

    private void getNews(int date){
        Call<DailyResult> call = MyRetrofit.getGankService().getNews(MyApi.ZHIHU_DAILY_NEWS_CONTENT + date);
        call.enqueue(new MyCallBack<DailyResult>(){

            @Override
            public void requestSuccess(DailyResult result) {
                mView.loadData(result.getStories());
            }

            @Override
            public void requestError(String errorMessage) {

            }
        });
    }
}