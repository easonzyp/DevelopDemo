package com.zhangyp.develop.HappyTools.http;


import com.zhangyp.develop.HappyTools.response.CityListResponse;
import com.zhangyp.develop.HappyTools.response.DreamDetailResponse;
import com.zhangyp.develop.HappyTools.response.DreamResponse;
import com.zhangyp.develop.HappyTools.response.ExchangeCurrencyResponse;
import com.zhangyp.develop.HappyTools.response.ExchangeListResponse;
import com.zhangyp.develop.HappyTools.response.JokeListResponse;
import com.zhangyp.develop.HappyTools.response.NewsListResponse;
import com.zhangyp.develop.HappyTools.response.RandJokeResponse;
import com.zhangyp.develop.HappyTools.response.UpdateInfoResponse;
import com.zhangyp.develop.HappyTools.response.WeatherResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface HttpService {


    @Streaming
    @GET
    Flowable<ResponseBody> downloadFile(@Url String fileUrl);

    @Multipart
    @POST("File/UploadSampleImage")
    Flowable<ResponseBody> uploadFiles(@Part MultipartBody.Part file);

    @Multipart
    @POST()
    Flowable<ResponseBody> uploadFiles(@Url String url, @Part MultipartBody.Part file);

    @Multipart
    @POST()
    Flowable<ResponseBody> UploadCompleteImage(@Url String url, @Part MultipartBody.Part file);

    @Multipart
    @POST("File/UploadSampleImage")
    Flowable<ResponseBody> uploadFiles(@Part List<MultipartBody.Part> parts);

    @POST()
    Flowable<NewsListResponse> getNewsList(@Url String url, @QueryMap Map<String, String> map);

    @POST()
    Flowable<UpdateInfoResponse> getUpdateInfo(@Url String url, @QueryMap Map<String, String> map);

    @POST()
    Flowable<UpdateInfoResponse> testDownload(@Url String url, @QueryMap Map<String, String> map);

    @POST()
    Flowable<WeatherResponse> getWeatherByCity(@Url String url, @QueryMap Map<String, String> map);

    @POST()
    Flowable<ExchangeListResponse> getExchangeList(@Url String url, @QueryMap Map<String, String> map);

    @POST()
    Flowable<ExchangeCurrencyResponse> getExchangeCurrency(@Url String url, @QueryMap Map<String, String> map);

    @POST()
    Flowable<JokeListResponse> getJokeList(@Url String url, @QueryMap Map<String, String> map);

    @POST()
    Flowable<CityListResponse> getCityList(@Url String url, @QueryMap Map<String, String> map);

    @POST()
    Flowable<RandJokeResponse> getRandJoke(@Url String url, @QueryMap Map<String, String> map);

    @POST()
    Flowable<DreamResponse> getDreamList(@Url String url, @QueryMap Map<String, String> map);

    @POST()
    Flowable<DreamDetailResponse> getDreamDetail(@Url String url, @QueryMap Map<String, String> map);
}
