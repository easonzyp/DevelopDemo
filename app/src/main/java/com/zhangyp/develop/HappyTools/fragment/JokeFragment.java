package com.zhangyp.develop.HappyTools.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhangyp.develop.HappyTools.R;
import com.zhangyp.develop.HappyTools.adapter.JokeAdapter;
import com.zhangyp.develop.HappyTools.base.BaseFragment;
import com.zhangyp.develop.HappyTools.base.BaseObserver;
import com.zhangyp.develop.HappyTools.http.HttpAction;
import com.zhangyp.develop.HappyTools.listener.CallBackListener;
import com.zhangyp.develop.HappyTools.listener.OnItemClickListener;
import com.zhangyp.develop.HappyTools.response.JokeListResponse;
import com.zhangyp.develop.HappyTools.response.RandJokeResponse;
import com.zhangyp.develop.HappyTools.util.MyDividerItemDecoration;
import com.zhangyp.develop.HappyTools.util.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhangyp.develop.HappyTools.wight.CustomDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyp on 2019/7/19 0019.
 * class note:
 */

public class JokeFragment extends BaseFragment {

    private SmartRefreshLayout srl_refresh;
    private TextView tv_whew;

    private int page = 1;

    private JokeAdapter adapter;
    private List<RandJokeResponse.ResultBean> jokeList;

    private CustomDialog dialog;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        RecyclerView rv_joke_list = view.findViewById(R.id.rv_joke_list);
        srl_refresh = view.findViewById(R.id.srl_refresh);
        tv_whew = view.findViewById(R.id.tv_whew);
        srl_refresh.setEnableAutoLoadMore(false);
        jokeList = new ArrayList<>();
        adapter = new JokeAdapter(getContext(), jokeList);
        rv_joke_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_joke_list.addItemDecoration(new MyDividerItemDecoration(
                getActivity(), MyDividerItemDecoration.HORIZONTAL_LIST, 1, ContextCompat.getColor(getActivity(), R.color.line)));
        rv_joke_list.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        getJokeList();
    }

    private void getJokeList() {
        final Map<String, String> param = new HashMap<>();
//        param.put("page", String.valueOf(page));
//        param.put("pagesize", "10");
        param.put("key", "ea6a0ff7628dee8a08e3590da3456573");
        HttpAction.getInstance().getRandJoke(param).subscribe(new BaseObserver<>(new CallBackListener<RandJokeResponse>() {
            @Override
            public void onSuccess(RandJokeResponse response) throws IOException {
                if (page == 1) {
                    //刷新
                    srl_refresh.finishRefresh();
                    refreshJokeList(response.getResult());
                } else {
                    //加载更多
                    srl_refresh.finishLoadMore();
                    loadMoreJokeList(response.getResult());
                }
            }

            @Override
            public void onError(int code, String message) {
                ToastUtil.showShortToast(getActivity(), message);
            }
        }));
    }

    private void refreshJokeList(List<RandJokeResponse.ResultBean> data) {
        jokeList.clear();
        jokeList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    private void loadMoreJokeList(List<RandJokeResponse.ResultBean> data) {
        jokeList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initClick() {
        srl_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getJokeList();
            }
        });

        srl_refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getJokeList();
            }
        });

        adapter.setOnItemClickListener(new OnItemClickListener<RandJokeResponse.ResultBean>() {
            @Override
            public void onClick(RandJokeResponse.ResultBean obj, int position) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.joke_detail_dialog, null);
                TextView tv_content = view.findViewById(R.id.tv_content);
                tv_content.setText(obj.getContent());
                if (dialog == null) {
                    dialog = new CustomDialog(getActivity(), view);
                }

                dialog.setContentView(view);
                dialog.setCancelable(true);
                dialog.show();
            }
        });

        tv_whew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_joke;
    }
}
