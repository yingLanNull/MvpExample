package com.yinglan.mvpexample.presenter;

import com.yinglan.mvpexample.base.BasePresenter;
import com.yinglan.mvpexample.model.MyApi;
import com.yinglan.mvpexample.model.MyCallBack;
import com.yinglan.mvpexample.model.MyRetrofit;
import com.yinglan.mvpexample.ui.view.DouBanView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import retrofit2.Call;

/**
 * @function ${desc}
 * @auther: Created by sufei
 * @time: 16/10/19
 */

public class DouBanPresenter extends BasePresenter<DouBanView> {

    public DouBanPresenter(DouBanView mView) {
        super(mView);
        getMeiTu(1);
    }

    public void getMeiTu(int page) {
        Call<String> call = MyRetrofit.getMeiTuService().getMeiTu(MyApi.DouBanYanZhiUrl + page);
        call.enqueue(new MyCallBack<String>() {
            @Override
            public void requestSuccess(String result) {
                HandleDoubanResponseFromHttp(result);
            }

            @Override
            public void requestError(String errorMessage) {

            }
        });

    }

    public void HandleDoubanResponseFromHttp(String httpResponse) {

        ArrayList<String> list = new ArrayList<>();
        Document document = Jsoup.parse(httpResponse);
        Elements elements = document.select("div[class=thumbnail]>div[class=img_single]>a>img");
        for (Element e : elements) {
            String url = e.attr("src");
            list.add(url);
        }

        mView.loadData(list);
    }


}
