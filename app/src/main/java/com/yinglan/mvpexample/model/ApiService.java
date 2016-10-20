package com.yinglan.mvpexample.model;

import com.yinglan.mvpexample.model.bean.DailyResult;
import com.yinglan.mvpexample.model.bean.MovieBean;
import com.yinglan.mvpexample.model.bean.NewsDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * @function ${desc}
 * @auther: Created by yinglan
 * @time: 16/9/20
 */
public interface ApiService {

    @GET
    Call<DailyResult> getNews(@Url String url);

    @GET
    Call<NewsDetail> getNewsDetail(@Url String url);

    @GET
    Call<String> getMeiTu(@Url String url);

    @GET
    Call<MovieBean> getMovie(@Url String url);
}
