package com.yinglan.mvpexample.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yinglan.mvpexample.R;
import com.yinglan.mvpexample.model.MyApi;
import com.yinglan.mvpexample.model.MyCallBack;
import com.yinglan.mvpexample.model.MyRetrofit;
import com.yinglan.mvpexample.model.bean.NewsDetail;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;

public class NewsDetailActivity extends AppCompatActivity {

    @Bind(R.id.news_header)
    ImageView newsHeader;
    @Bind(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.web_fl)
    FrameLayout webFl;
    @Bind(R.id.nsv_content)
    NestedScrollView nsvContent;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private WebView wb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
    }

    private void initData() {
        int id = getIntent().getIntExtra("URL", 0);
        Call<NewsDetail> c = MyRetrofit.getGankService().getNewsDetail(MyApi.NEWSDETAILS + id);
        c.enqueue(new MyCallBack<NewsDetail>() {
            @Override
            public void requestSuccess(NewsDetail result) {
                showDetails(result);
            }

            @Override
            public void requestError(String errorMessage) {

            }
        });
    }

    public void showDetails(final NewsDetail newsDetail) {
        collapsingToolbarLayout.setTitle(newsDetail.title);
        Glide.with(this)
                .load(newsDetail.image)
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(newsHeader);
        initWebView(newsDetail.body);
    }

    private void initWebView(String body) {
        wb = new WebView(this);
        webFl.addView(wb);
        WebSettings settings = wb.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        // 设置webview的缩放级别
        //settings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        settings.setLoadWithOverviewMode(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        wb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setMax(100);
                //设置当前的进度
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    //隐藏进度条
                    progressBar.setVisibility(View.GONE);

                }
                super.onProgressChanged(view, newProgress);
            }
        });
        wb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null) view.loadUrl(url);
                return true;
            }
        });
        wb.loadDataWithBaseURL(MyApi.NEWSDETAILS, body, "text/html", "uft-8", null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    finish();
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wb != null) {
            wb.removeAllViews();
            wb.destroy();
        }
        if (webFl != null) {
            webFl.clearAnimation();
        }
    }
}
