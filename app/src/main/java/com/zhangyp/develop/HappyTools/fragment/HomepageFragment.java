package com.zhangyp.develop.HappyTools.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhangyp.develop.HappyTools.R;
import com.zhangyp.develop.HappyTools.activity.ChooseCityListActivity;
import com.zhangyp.develop.HappyTools.adapter.WeatherAdapter;
import com.zhangyp.develop.HappyTools.base.BaseFragment;
import com.zhangyp.develop.HappyTools.base.BaseObserver;
import com.zhangyp.develop.HappyTools.http.HttpAction;
import com.zhangyp.develop.HappyTools.listener.CallBackListener;
import com.zhangyp.develop.HappyTools.response.WeatherResponse;
import com.zhangyp.develop.HappyTools.util.MyDividerItemDecoration;
import com.zhangyp.develop.HappyTools.util.SpUtil;
import com.zhangyp.develop.HappyTools.util.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyp on 2019/7/19 0019.
 * class note:
 */

public class HomepageFragment extends BaseFragment {

    private String city = "哈尔滨";
    private TextView tv_city_name;

    private ImageView iv_city_list;
    private TextView tv_temperature_value;
    private TextView tv_info;
    private TextView tv_direct;
    private TextView tv_power;
    private TextView tv_humidity;
    private TextView tv_aqi;

    private WeatherResponse weatherResponse;

    private List<WeatherResponse.ResultBean.FutureBean> futureList;
    private WeatherAdapter adapter;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        tv_city_name = view.findViewById(R.id.tv_city_name);
        iv_city_list = view.findViewById(R.id.iv_city_list);
        tv_temperature_value = view.findViewById(R.id.tv_temperature_value);
        tv_info = view.findViewById(R.id.tv_info);
        tv_direct = view.findViewById(R.id.tv_direct);
        tv_power = view.findViewById(R.id.tv_power);
        tv_humidity = view.findViewById(R.id.tv_humidity);
        tv_aqi = view.findViewById(R.id.tv_aqi);

        RecyclerView rv_future_weather = view.findViewById(R.id.rv_future_weather);
        futureList = new ArrayList<>();
        adapter = new WeatherAdapter(getActivity(), futureList);
        rv_future_weather.addItemDecoration(new MyDividerItemDecoration(
                getActivity(), MyDividerItemDecoration.HORIZONTAL_LIST, 1, ContextCompat.getColor(getActivity(), R.color.mainBg)));
        rv_future_weather.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_future_weather.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        Map<String, String> param = new HashMap<>();
        param.put("city", city);
        param.put("key", "da2faa6c287f86137c9adb6c46bf81d6");
        HttpAction.getInstance().getWeatherByCity(param).subscribe(new BaseObserver<>(new CallBackListener<WeatherResponse>() {
            @Override
            public void onSuccess(WeatherResponse response) throws IOException {
                int errorCode = response.getError_code();
                if (errorCode != 0) {
                    ToastUtil.showShortToast(getActivity(), response.getReason());
                    weatherResponse = JSON.parseObject(SpUtil.getInstance().getWeather(), WeatherResponse.class);
                }

                weatherResponse = response;
                setCurrentWeather();
                setFutureWeather();

                String weatherString = JSON.toJSONString(response);
                SpUtil.getInstance().setWeather(weatherString);
            }

            @Override
            public void onError(int code, String message) {
                ToastUtil.showShortToast(getActivity(), message);
            }
        }));
    }

    private void setCurrentWeather() {
        WeatherResponse.ResultBean.RealtimeBean realTime = weatherResponse.getResult().getRealtime();
        tv_temperature_value.setText(String.format("%s°C", realTime.getTemperature()));
        tv_info.setText(realTime.getInfo());
        tv_direct.setText(realTime.getDirect());
        tv_power.setText(realTime.getPower());
        tv_humidity.setText(realTime.getHumidity());
        tv_aqi.setText(realTime.getAqi());
    }

    private void setFutureWeather() {
        futureList.clear();
        List<WeatherResponse.ResultBean.FutureBean> future = weatherResponse.getResult().getFuture();
        futureList.addAll(future);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initClick() {
        iv_city_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(HomepageFragment.this.getContext(), ChooseCityListActivity.class), 1001);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            city = data.getStringExtra("city");
            if (!TextUtils.isEmpty(city)) {
                initData();
                tv_city_name.setText(city);
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
    }
}
