package com.yinglan.mvpexample.ui.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yinglan.mvpexample.R;
import com.yinglan.mvpexample.base.BaseFragment;
import com.yinglan.mvpexample.model.bean.MovieBean;
import com.yinglan.mvpexample.presenter.MoviePresenter;
import com.yinglan.mvpexample.ui.adapter.MovieAdapter;
import com.yinglan.mvpexample.ui.view.MovieView;

import java.util.List;

import butterknife.Bind;

/**
 * @function ${desc}
 * @auther: Created by yinglan
 * @time: 16/10/18
 */


public class MovieFragment extends BaseFragment<MoviePresenter> implements MovieView {

    @Bind(R.id.movie_list)
    RecyclerView movieList;

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void initView(View view) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        movieList.setLayoutManager(mLayoutManager);
    }

    @Override
    protected MoviePresenter getPresenter() {
        return new MoviePresenter(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void loadData(List<MovieBean.MovieInfo> data) {
        movieList.setAdapter(new MovieAdapter(mContext, data) {
            @Override
            protected void onItemClick(View v, int position) {

            }
        });
    }
}
