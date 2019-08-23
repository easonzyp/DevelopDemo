package com.android.develop.DevelopDemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.develop.DevelopDemo.R;
import com.android.develop.DevelopDemo.adapter.JokeAdapter;
import com.android.develop.DevelopDemo.base.BaseFragment;
import com.android.develop.DevelopDemo.base.BaseObserver;
import com.android.develop.DevelopDemo.http.HttpAction;
import com.android.develop.DevelopDemo.listener.CallBackListener;
import com.android.develop.DevelopDemo.response.JokeListResponse;
import com.android.develop.DevelopDemo.util.MyDividerItemDecoration;
import com.android.develop.DevelopDemo.util.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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

    private int page;

    private JokeAdapter adapter;
    private List<JokeListResponse.ResultBean.DataBean> jokeList;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        RecyclerView rv_joke_list = view.findViewById(R.id.rv_joke_list);
        srl_refresh = view.findViewById(R.id.srl_refresh);
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
        param.put("page", String.valueOf(page));
        param.put("pagesize", "10");
        param.put("key", "ea6a0ff7628dee8a08e3590da3456573");
        HttpAction.getInstance().getJokeList(param).subscribe(new BaseObserver<>(new CallBackListener<JokeListResponse>() {
            @Override
            public void onSuccess(JokeListResponse response) throws IOException {
                if (page == 1) {
                    //刷新
                    srl_refresh.finishRefresh();
                    refreshJokeList(response.getResult().getData());
                } else {
                    //加载更多
                    srl_refresh.finishLoadMore();
                    loadMoreJokeList(response.getResult().getData());
                }
            }

            @Override
            public void onError(int code, String message) {
                ToastUtil.showShortToast(getActivity(), message);
            }
        }));
    }

    private void refreshJokeList(List<JokeListResponse.ResultBean.DataBean> data) {
        jokeList.clear();
        jokeList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    private void loadMoreJokeList(List<JokeListResponse.ResultBean.DataBean> data) {
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
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_joke;
    }
}
