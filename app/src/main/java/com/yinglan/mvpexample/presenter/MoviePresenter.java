package com.yinglan.mvpexample.presenter;

import com.yinglan.mvpexample.base.BasePresenter;
import com.yinglan.mvpexample.model.MyApi;
import com.yinglan.mvpexample.model.MyCallBack;
import com.yinglan.mvpexample.model.MyRetrofit;
import com.yinglan.mvpexample.model.bean.MovieBean;
import com.yinglan.mvpexample.ui.view.MovieView;

import java.util.ArrayList;

import retrofit2.Call;

/**
 * @function ${desc}
 * @auther: Created by sufei
 * @time: 16/10/19
 */

public class MoviePresenter extends BasePresenter<MovieView> {

    public MoviePresenter(MovieView mView) {
        super(mView);
        getMovie();
    }

    public void getMovie() {
        Call<MovieBean> call = MyRetrofit.getGankService().getMovie(MyApi.MOVIES);
        call.enqueue(new MyCallBack<MovieBean>() {
            @Override
            public void requestSuccess(MovieBean result) {
                HandleMovieResponseFromHttp(result);
            }

            @Override
            public void requestError(String errorMessage) {

            }
        });

    }

    public void HandleMovieResponseFromHttp(MovieBean httpResponse) {
        ArrayList<MovieBean.MovieInfo> resultDatas = new ArrayList<>();
        resultDatas.addAll(httpResponse.result.data.get(0).data);
        resultDatas.addAll(httpResponse.result.data.get(1).data);
        mView.loadData(resultDatas);
    }


}
