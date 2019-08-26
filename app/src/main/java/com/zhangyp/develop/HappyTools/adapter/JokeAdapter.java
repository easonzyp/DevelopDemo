package com.zhangyp.develop.HappyTools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangyp.develop.HappyTools.R;
import com.zhangyp.develop.HappyTools.response.JokeListResponse;

import java.util.List;

/**
 * Created by zyp on 2019/8/21 0021.
 * class note:
 */

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder> {

    private Context context;
    private List<JokeListResponse.ResultBean.DataBean> jokeList;


    public JokeAdapter(Context context, List<JokeListResponse.ResultBean.DataBean> jokeList) {
        this.context = context;
        this.jokeList = jokeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_joke_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JokeListResponse.ResultBean.DataBean joke = getItem(position);
        holder.tv_joke.setText(joke.getContent());
    }

    @Override
    public int getItemCount() {
        return jokeList.size();
    }

    private JokeListResponse.ResultBean.DataBean getItem(int position){
        return jokeList.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_joke;

        ViewHolder(View itemView) {
            super(itemView);
            tv_joke = itemView.findViewById(R.id.tv_joke);
        }
    }
}
