package com.yinglan.mvpexample.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.yinglan.mvpexample.R;
import com.yinglan.mvpexample.base.BaseFragment;
import com.yinglan.mvpexample.presenter.DouBanPresenter;
import com.yinglan.mvpexample.ui.activity.ImageViewerActivity;
import com.yinglan.mvpexample.ui.adapter.ImageRecyclerViewAdapter;
import com.yinglan.mvpexample.ui.view.DouBanView;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * @function ${desc}
 * @auther: Created by yinglan
 * @time: 16/10/19
 */

public class DouBanFragment extends BaseFragment<DouBanPresenter> implements DouBanView {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_meitu;
    }

    @Override
    protected void initView(View view) {
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
    }

    @Override
    protected DouBanPresenter getPresenter() {
        return new DouBanPresenter(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void loadData(final ArrayList<String> urls) {
        recyclerview.setAdapter(new ImageRecyclerViewAdapter(mContext, urls) {
            @Override
            protected void onItemClick(View v, int position) {
                Intent intent = new Intent(getActivity(), ImageViewerActivity.class);
                intent.putExtra("CURRENT_INDEX", position);
                intent.putStringArrayListExtra("IMAGES", urls);
                startActivity(intent);
            }
        });
    }

}