package com.zhangyp.develop.HappyTools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangyp.develop.HappyTools.R;
import com.zhangyp.develop.HappyTools.listener.OnItemClickListener;

import java.util.List;

/**
 * Created by zyp on 2019/8/21 0021.
 * class note:
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private Context context;
    private List<String> cityList;
    private OnItemClickListener<String> listener;

    public CityAdapter(Context context, List<String> cityList) {
        this.context = context;
        this.cityList = cityList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_city_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final String city = getItem(position);
        holder.tv_name.setText(city);
        holder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(city, holder.getAdapterPosition());
            }
        });
    }

    private String getItem(int position) {
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

    public void setOnItemClickListener(OnItemClickListener<String> listener) {
        this.listener = listener;
    }
}
