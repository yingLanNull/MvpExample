package com.yinglan.mvpexample.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yinglan.mvpexample.R;
import com.yinglan.mvpexample.model.MyApi;
import com.yinglan.mvpexample.model.bean.Daily;


import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tangqi on 8/20/15.
 */
public class DailyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = "DailyListAdapter";

    private List<Daily> mNewsList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public DailyListAdapter(Context mContext, List<Daily> mNewsList) {
        this.mContext = mContext;
        this.mNewsList = mNewsList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(mLayoutInflater.inflate(R.layout.item_daily_image_info, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Daily news = mNewsList.get(position);
        ((ImageViewHolder) holder).mTitle.setText(news.title);
        Glide.with(mContext).load(news.getImages().get(0)).into(((ImageViewHolder) holder).mCover);
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    private void gotoWebView(Daily news, View v) {
        Log.d("Daily", "id--->" + news.id);
        String news_url = MyApi.getNewsContent(news.id);
//        Intent intent = new Intent(v.getContext(), WebActivity.class);
//        intent.putExtra("URL", news_url);
//        v.getContext().startActivity(intent);
    }

    private void startView(Daily news, View v) {
        gotoWebView(news, v);
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_cover)
        ImageView mCover;
        @Bind(R.id.tv_title)
        TextView mTitle;

        public ImageViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        @OnClick(R.id.ll_card_parent)
        void onClick(View v) {
            Daily news = mNewsList.get(getLayoutPosition());
            startView(news, v);
        }
    }
}
