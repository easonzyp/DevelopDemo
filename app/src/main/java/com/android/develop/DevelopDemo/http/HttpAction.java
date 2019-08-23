package com.android.develop.DevelopDemo.http;


import android.util.Log;

import com.android.develop.DevelopDemo.response.CityListResponse;
import com.android.develop.DevelopDemo.response.ExchangeCurrencyResponse;
import com.android.develop.DevelopDemo.response.ExchangeListResponse;
import com.android.develop.DevelopDemo.response.JokeListResponse;
import com.android.develop.DevelopDemo.response.NewsListResponse;
import com.android.develop.DevelopDemo.response.UpdateInfoResponse;
import com.android.develop.DevelopDemo.response.WeatherResponse;

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
        return applySchedulers(HttpClient.getHttpService().getNewsList("http://v.juhe.cn/toutiao/index", params));
    }

    public Flowable<UpdateInfoResponse> getUpdateInfo(Map<String, String> params) {
        return applySchedulers(HttpClient.getHttpService().getUpdateInfo("http://appid.aigoodies.com/getAppConfig.php", params));
    }

    public Flowable<UpdateInfoResponse> testDownload(Map<String, String> params) {
        return applySchedulers(HttpClient.getHttpService().testDownload("https://appid.20pi.com/getAppConfig.php", params));
    }

    public Flowable<WeatherResponse> getWeatherByCity(Map<String, String> params) {
        return applySchedulers(HttpClient.getHttpService().getWeatherByCity("http://apis.juhe.cn/simpleWeather/query", params));
    }

    public Flowable<ExchangeListResponse> getExchangeList(Map<String, String> params) {
        return applySchedulers(HttpClient.getHttpService().getExchangeList("http://op.juhe.cn/onebox/exchange/list", params));
    }

    public Flowable<ExchangeCurrencyResponse> getExchangeCurrency(Map<String, String> params) {
        return applySchedulers(HttpClient.getHttpService().getExchangeCurrency("http://op.juhe.cn/onebox/exchange/currency", params));
    }

    public Flowable<JokeListResponse> getJokeList(Map<String, String> params) {
        return applySchedulers(HttpClient.getHttpService().getJokeList("http://v.juhe.cn/joke/content/text.php", params));
    }

    public Flowable<CityListResponse> getCityList(Map<String, String> params) {
        return applySchedulers(HttpClient.getHttpService().getCityList("http://apis.juhe.cn/simpleWeather/cityList", params));
    }
}
