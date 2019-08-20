package com.android.develop.DevelopDemo.http;


import android.util.Log;

import com.android.develop.DevelopDemo.request.NewsListRequest;
import com.android.develop.DevelopDemo.response.NewsListResponse;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;


public class HttpAction {

    public static HttpAction getInstance() {
        return HttpActionHolder.instance;
    }

    private static class HttpActionHolder {
        private static HttpAction instance = new HttpAction();
    }

    private RequestBody getBody(String request) {
        Log.e("getBody", request);
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), request);
    }

    private <T> Flowable<T> applySchedulers(Flowable<T> responseFlowable) {
        return responseFlowable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<NewsListResponse> getNewsList(Map<String, String> params) {
        return applySchedulers(HttpClient.getHttpService().getNewsList("http://v.juhe.cn/toutiao/index",  params));
    }
}
