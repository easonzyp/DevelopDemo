package com.zhangyp.develop.HappyTools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangyp.develop.HappyTools.R;
import com.zhangyp.develop.HappyTools.listener.OnItemClickListener;
import com.zhangyp.develop.HappyTools.response.SearchCityResponse;

import java.util.List;

/**
 * Created by zyp on 2019/8/21 0021.
 * class note:
 */

public class SearchCityAdapter extends RecyclerView.Adapter<SearchCityAdapter.ViewHolder> {

    private Context context;
    private List<SearchCityResponse.HeWeather6Bean.BasicBean> cityList;
    private OnItemClickListener<SearchCityResponse.HeWeather6Bean.BasicBean> listener;

    public SearchCityAdapter(Context context, List<SearchCityResponse.HeWeather6Bean.BasicBean> cityList) {
        this.context = context;
        this.cityList = cityList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_city_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final SearchCityResponse.HeWeather6Bean.BasicBean city = getItem(position);

        String name = city.getLocation() + " - " + city.getParent_city() + " - " + city.getCnty();

        holder.tv_name.setText(name);
        holder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(city, holder.getAdapterPosition());
            }
        });
    }

    private SearchCityResponse.HeWeather6Bean.BasicBean getItem(int position) {
        return cityList.get(position);
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;

        ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }

    public void setOnItemClickListener(OnItemClickListener<SearchCityResponse.HeWeather6Bean.BasicBean> listener) {
        this.listener = listener;
    }
}
