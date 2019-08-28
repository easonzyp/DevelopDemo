package com.zhangyp.develop.HappyTools.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangyp.develop.HappyTools.R;
import com.zhangyp.develop.HappyTools.activity.DreamDetailActivity;
import com.zhangyp.develop.HappyTools.activity.MainActivity;
import com.zhangyp.develop.HappyTools.activity.WelcomeActivity;
import com.zhangyp.develop.HappyTools.adapter.DreamAdapter;
import com.zhangyp.develop.HappyTools.base.BaseFragment;
import com.zhangyp.develop.HappyTools.base.BaseObserver;
import com.zhangyp.develop.HappyTools.http.HttpAction;
import com.zhangyp.develop.HappyTools.listener.CallBackListener;
import com.zhangyp.develop.HappyTools.listener.OnItemClickListener;
import com.zhangyp.develop.HappyTools.response.DreamResponse;
import com.zhangyp.develop.HappyTools.util.MyDividerItemDecoration;
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

public class DreamFragment extends BaseFragment {

    private TextView tv_search;
    private TextView et_search;
    private RecyclerView rv_dream_list;
    private DreamAdapter adapter;
    private List<DreamResponse.ResultBean> dreamList;

    private View view_empty;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        view_empty = view.findViewById(R.id.view_empty);

        tv_search = view.findViewById(R.id.tv_search);
        et_search = view.findViewById(R.id.et_search);
        rv_dream_list = view.findViewById(R.id.rv_dream_list);

        dreamList = new ArrayList<>();
        adapter = new DreamAdapter(getActivity(), dreamList);
        rv_dream_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_dream_list.addItemDecoration(new MyDividerItemDecoration(
                getActivity(), MyDividerItemDecoration.HORIZONTAL_LIST, 1, ContextCompat.getColor(getActivity(), R.color.line)));

        rv_dream_list.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        isShowEmptyView();
    }

    @Override
    protected void initClick() {
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String q = et_search.getText().toString().trim();
                if (TextUtils.isEmpty(q)) {
                    ToastUtil.showShortToast(getActivity(), "请输入要搜索的内容");
                    return;
                }
                getDreamList(q);
            }
        });

        adapter.setOnItemClickListener(new OnItemClickListener<DreamResponse.ResultBean>() {
            @Override
            public void onClick(DreamResponse.ResultBean obj, int position) {
                Intent intent = new Intent(getActivity(), DreamDetailActivity.class);
                intent.putExtra("id",obj.getId());
                startActivity(intent);
            }
        });
    }

    private void getDreamList(String q) {
        dreamList.clear();
        Map<String, String> param = new HashMap<>();
        param.put("key", "c6a7982ce6d6b47bc36052d19b446b79");
        param.put("q", q);
        HttpAction.getInstance().getDreamList(param).subscribe(new BaseObserver<>(new CallBackListener<DreamResponse>() {
            @Override
            public void onSuccess(DreamResponse response) throws IOException {
                dreamList.addAll(response.getResult());
                adapter.notifyDataSetChanged();
                isShowEmptyView();
            }

            @Override
            public void onError(int code, String message) {
//                ToastUtil.showShortToast(getActivity(), message);
                rv_dream_list.setVisibility(View.GONE);
                view_empty.setVisibility(View.VISIBLE);
            }
        }));
    }

    private void isShowEmptyView() {
        if (dreamList == null || dreamList.size() == 0) {
            rv_dream_list.setVisibility(View.GONE);
            view_empty.setVisibility(View.VISIBLE);
        } else {
            rv_dream_list.setVisibility(View.VISIBLE);
            view_empty.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dream;
    }

}
