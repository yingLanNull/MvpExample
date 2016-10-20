package com.yinglan.mvpexample.model;

/**
 * @function ${desc}
 * @auther: Created by yinglan
 * @time: 16/10/18
 */

public class MyApi {
    public static final String ZHIHU_DAILY_NEWS_CONTENT = "http://news.at.zhihu.com/api/4/news/before/";

    public static final String NEWSDETAILS = "http://news-at.zhihu.com/api/4/news/";

    public static final String DouBanYanZhiUrl = "http://www.dbmeinv.com/dbgroup/show.htm?cid=4&pager_offset=";

    public static final String MOVIES = "http://op.juhe.cn/onebox/movie/pmovie?key=101ed3a72665244e147c166362a0c48c&city=北京";

    public static String getNewsContent(int id) {
        return ZHIHU_DAILY_NEWS_CONTENT + id;
    }
}
