/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.yinglan.mvpexample.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 2015-08-07T03:57:47.229Z
 * Created by drakeet on 8/9/15.
 */
public class MyRetrofit {

    static ApiService apiService;

    final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();

    public static ApiService getGankService() {
        OkHttpClient client = OkHttpUtils.getInstance().getOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news.at.zhihu.com/api/4/news/before/")
                .client(OkHttpUtils.getInstance().getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiService = retrofit.create(ApiService.class);
        return apiService;
    }
}
