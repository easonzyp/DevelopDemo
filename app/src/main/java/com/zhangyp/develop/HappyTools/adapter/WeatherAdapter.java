package com.zhangyp.develop.HappyTools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangyp.develop.HappyTools.R;
import com.zhangyp.develop.HappyTools.response.WeatherResponse;
import com.zhangyp.develop.HappyTools.util.WeatherUtil;

import java.util.List;

/**
 * Created by zyp on 2019/8/21 0021.
 * class note:
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private Context context;
    private List<WeatherResponse.ResultBean.FutureBean> futureList;

    public WeatherAdapter(Context context, List<WeatherResponse.ResultBean.FutureBean> futureList) {
        this.context = context;
        this.futureList = futureList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_future_weather_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherResponse.ResultBean.FutureBean future = getItem(position);
        holder.tv_weather_info.setText(future.getWeather());

        holder.tv_temperature_value.setText(future.getTemperature());
        String time;
        switch (position) {
            case 0:
                time = "今天";
                break;
            case 1:
                time = "明天";
                break;
            case 2:
                time = "后天";
                break;
            default:
                time = future.getDate();
                break;
        }
        holder.tv_time.setText(time);
        String wid = future.getWid().getDay();
        WeatherUtil.setWeatherIcon(wid, holder.iv_weather_info);
    }

    private WeatherResponse.ResultBean.FutureBean getItem(int position) {
        return futureList.get(position);
    }

    @Override
    public int getItemCount() {
        return futureList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_weather_info;
        private TextView tv_time;
        private TextView tv_weather_info;
        private TextView tv_temperature_value;

        ViewHolder(View itemView) {
            super(itemView);
            iv_weather_info = itemView.findViewById(R.id.iv_weather_info);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_weather_info = itemView.findViewById(R.id.tv_weather_info);
            tv_temperature_value = itemView.findViewById(R.id.tv_temperature_value);
        }
    }
}
