package com.zhangyp.develop.HappyTools.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangyp.develop.HappyTools.R;
import com.zhangyp.develop.HappyTools.adapter.CityAdapter;
import com.zhangyp.develop.HappyTools.adapter.SearchCityAdapter;
import com.zhangyp.develop.HappyTools.base.BaseActivity;
import com.zhangyp.develop.HappyTools.base.BaseObserver;
import com.zhangyp.develop.HappyTools.http.HttpAction;
import com.zhangyp.develop.HappyTools.listener.CallBackListener;
import com.zhangyp.develop.HappyTools.listener.OnItemClickListener;
import com.zhangyp.develop.HappyTools.response.SearchCityResponse;
import com.zhangyp.develop.HappyTools.util.CityUtil;
import com.zhangyp.develop.HappyTools.util.MyDividerItemDecoration;
import com.zhangyp.develop.HappyTools.util.ToastUtil;
import com.zhangyp.develop.HappyTools.wight.LetterListView;

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
    private TextView tv_back;
    private ImageView iv_more;

    private EditText et_search;
    private TextView tv_search;

    private CityAdapter adapter;
    private SearchCityAdapter searchCityAdapter;
    private List<SearchCityResponse.HeWeather6Bean.BasicBean> cityList;
    private RecyclerView rv_search_city;
    private RecyclerView rv_hot_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initClick();
    }

    private void initView() {
        et_search = findViewById(R.id.et_search);
        tv_search = findViewById(R.id.tv_search);

        llv_right = findViewById(R.id.llv_right);
        tv_back = findViewById(R.id.tv_back);
        iv_more = findViewById(R.id.iv_more);
        rv_hot_city = findViewById(R.id.rv_hot_city);
        rv_search_city = findViewById(R.id.rv_search_city);

        adapter = new CityAdapter(this, CityUtil.getHotCityList());
        rv_hot_city.setLayoutManager(new GridLayoutManager(this, 3));
        rv_hot_city.setAdapter(adapter);

        cityList = new ArrayList<>();
        searchCityAdapter = new SearchCityAdapter(this, cityList);
        rv_search_city.setLayoutManager(new LinearLayoutManager(this));
        rv_search_city.addItemDecoration(new MyDividerItemDecoration(
                this, MyDividerItemDecoration.HORIZONTAL_LIST, 1, ContextCompat.getColor(this, R.color.line)));

        rv_search_city.setAdapter(searchCityAdapter);
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

        searchCityAdapter.setOnItemClickListener(new OnItemClickListener<SearchCityResponse.HeWeather6Bean.BasicBean>() {
            @Override
            public void onClick(SearchCityResponse.HeWeather6Bean.BasicBean obj, int position) {
                Intent intent = new Intent();
                intent.putExtra("city", obj.getParent_city());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShortToast(ChooseCityListActivity.this, "更多城市正在研发中...");
            }
        });

        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_search.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    ToastUtil.showShortToast(ChooseCityListActivity.this, "请输入城市名");
                    rv_hot_city.setVisibility(View.VISIBLE);
                    rv_search_city.setVisibility(View.GONE);
                    return;
                }
                searchCity(name);
            }
        });
    }

    private void searchCity(String text) {
        Map<String, String> param = new HashMap<>();
        param.put("location", text);
        param.put("key", "79d317baa58d4c22a892aa667d38505d");
        param.put("group", "cn");

        cityList.clear();
        HttpAction.getInstance().searchCity(param).subscribe(new BaseObserver<>(new CallBackListener<SearchCityResponse>() {
            @Override
            public void onSuccess(SearchCityResponse response) throws IOException {
                cityList.addAll(response.getHeWeather6().get(0).getBasic());

                if (cityList == null || cityList.size() == 0) {
                    rv_hot_city.setVisibility(View.VISIBLE);
                    rv_search_city.setVisibility(View.GONE);
                } else {
                    rv_hot_city.setVisibility(View.GONE);
                    rv_search_city.setVisibility(View.VISIBLE);
                }

                searchCityAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int code, String message) {

            }
        }));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_choose_city_list;
    }
}
