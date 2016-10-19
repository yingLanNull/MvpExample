package com.yinglan.mvpexample.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yinglan.mvpexample.R;
import com.yinglan.mvpexample.base.BaseLazyFragment;
import com.yinglan.mvpexample.model.bean.Daily;
import com.yinglan.mvpexample.presenter.DailyPresenter;
import com.yinglan.mvpexample.ui.adapter.DailyListAdapter;
import com.yinglan.mvpexample.ui.view.DailyView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DailyListFragment extends BaseLazyFragment<DailyPresenter> implements SwipeRefreshLayout.OnRefreshListener, DailyView {
    private int date;
    private List<Daily> mNewsList = new ArrayList<>();
    private DailyListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Bind(R.id.cardList)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static DailyListFragment newInstance() {
        DailyListFragment fragment = new DailyListFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onRefresh();  //Snackbar的显示依赖于当前View，所以在View创建之后刷新
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle bundle = getArguments();
            date = bundle.getInt("DATE");
            setRetainInstance(true);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily_list;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.addItemDecoration(new RecyclerViewItemDecoration(
//                getActivity()
//        ));

        mSwipeRefreshLayout.setOnRefreshListener(this);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.primary);
        mAdapter = new DailyListAdapter(getActivity(), mNewsList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected DailyPresenter getPresenter() {
        return new DailyPresenter(this, date);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    public void onRefresh() {
//        mPresenter.getNews(date);
//        if (NetworkUtils.isNetworkConnected()) {
//            showProgress();
//        } else if (!NetworkUtils.isNetworkConnected() && PrefUtils.isEnableCache()) {
//            Snackbar.make(getView(), R.string.snack_network_error_load_cache, Snackbar.LENGTH_SHORT).show();
//        } else if (!NetworkUtils.isNetworkConnected() && !PrefUtils.isEnableCache()) {
//            Snackbar.make(getView(), R.string.snack_network_error, Snackbar.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void loadData(List<Daily> dailies) {
        mNewsList.clear();
        mNewsList.addAll(dailies);
        hideProgress();
        mAdapter.notifyDataSetChanged();
    }

    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
