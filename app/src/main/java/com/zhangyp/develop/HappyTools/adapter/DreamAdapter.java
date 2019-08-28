package com.zhangyp.develop.HappyTools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangyp.develop.HappyTools.R;
import com.zhangyp.develop.HappyTools.listener.OnItemClickListener;
import com.zhangyp.develop.HappyTools.response.DreamResponse;
import com.zhangyp.develop.HappyTools.response.RandJokeResponse;

import java.util.List;

/**
 * Created by zyp on 2019/8/21 0021.
 * class note:
 */

public class DreamAdapter extends RecyclerView.Adapter<DreamAdapter.ViewHolder> {

    private Context context;
    private List<DreamResponse.ResultBean> jokeList;
    private OnItemClickListener<DreamResponse.ResultBean> listener;


    public DreamAdapter(Context context, List<DreamResponse.ResultBean> jokeList) {
        this.context = context;
        this.jokeList = jokeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dream_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final DreamResponse.ResultBean joke = getItem(position);

        holder.tv_id.setText(String.format("id：%s", joke.getId()));
        holder.tv_title.setText(String.format("标题：%s", joke.getTitle()));
        holder.tv_content.setText(joke.getDes());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(joke, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return jokeList.size();
    }

    private DreamResponse.ResultBean getItem(int position) {
        return jokeList.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_id;
        private TextView tv_title;
        private TextView tv_content;

        ViewHolder(View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);

        }
    }

    public void setOnItemClickListener(OnItemClickListener<DreamResponse.ResultBean> listener) {
        this.listener = listener;
    }
}
