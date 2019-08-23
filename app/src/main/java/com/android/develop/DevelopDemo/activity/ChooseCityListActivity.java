package com.android.develop.DevelopDemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.android.develop.DevelopDemo.R;
import com.android.develop.DevelopDemo.adapter.CityAdapter;
import com.android.develop.DevelopDemo.base.BaseActivity;
import com.android.develop.DevelopDemo.base.BaseObserver;
import com.android.develop.DevelopDemo.http.HttpAction;
import com.android.develop.DevelopDemo.listener.CallBackListener;
import com.android.develop.DevelopDemo.listener.OnItemClickListener;
import com.android.develop.DevelopDemo.response.CityListResponse;
import com.android.develop.DevelopDemo.util.CityUtil;
import com.android.develop.DevelopDemo.wight.LetterListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:h5页面
 */

public class ChooseCityListActivity extends BaseActivity {

    private LetterListView llv_right;
    private ImageView iv_back;
    private CityAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initClick();
    }

    private void initView() {
        llv_right = findViewById(R.id.llv_right);
        iv_back = findViewById(R.id.iv_back);
        RecyclerView rv_hot_city = findViewById(R.id.rv_hot_city);

        adapter = new CityAdapter(this, CityUtil.getHotCityList());
        rv_hot_city.setLayoutManager(new LinearLayoutManager(this));
        rv_hot_city.setAdapter(adapter);
    }

    private void initClick() {
        adapter.setOnItemClickListener(new OnItemClickListener<String>() {
            @Override
            public void onClick(String obj, int position) {
                Intent intent = new Intent();
                intent.putExtra("city", obj);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_choose_city_list;
    }
}
