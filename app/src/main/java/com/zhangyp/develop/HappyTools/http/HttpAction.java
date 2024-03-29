package com.zhangyp.develop.HappyTools.http;


import android.util.Log;

import com.zhangyp.develop.HappyTools.response.CityListResponse;
import com.zhangyp.develop.HappyTools.response.DreamDetailResponse;
import com.zhangyp.develop.HappyTools.response.DreamResponse;
import com.zhangyp.develop.HappyTools.response.ExchangeCurrencyResponse;
import com.zhangyp.develop.HappyTools.response.ExchangeListResponse;
import com.zhangyp.develop.HappyTools.response.JokeListResponse;
import com.zhangyp.develop.HappyTools.response.NewsListResponse;
import com.zhangyp.develop.HappyTools.response.RandJokeResponse;
import com.zhangyp.develop.HappyTools.response.SearchCityResponse;
import com.zhangyp.develop.HappyTools.response.UpdateInfoResponse;
import com.zhangyp.develop.HappyTools.response.UpdateInfoResponse1;
import com.zhangyp.develop.HappyTools.response.WeatherResponse;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


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

    /*public Flowable<UpdateInfoResponse1> getUpdateInfo(Map<String, String> params) {
        return applySchedulers(HttpClient.getHttpService().getUpdateInfo("https://appid-apkk.xx-app.com/frontApi/getAboutUs", params));
    }*/

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

    public Flowable<RandJokeResponse> getRandJoke(Map<String, String> params) {
        return applySchedulers(HttpClient.getHttpService().getRandJoke("http://v.juhe.cn/joke/randJoke.php", params));
    }

    public Flowable<DreamResponse> getDreamList(Map<String, String> params) {
        return applySchedulers(HttpClient.getHttpService().getDreamList("http://v.juhe.cn/dream/query", params));
    }

    public Flowable<DreamDetailResponse> getDreamDetail(Map<String, String> params) {
        return applySchedulers(HttpClient.getHttpService().getDreamDetail("http://v.juhe.cn/dream/queryid", params));
    }

    public Flowable<SearchCityResponse> searchCity(Map<String, String> params) {
        return applySchedulers(HttpClient.getHttpService().searchCity("https://search.heweather.net/find", params));
    }
}
