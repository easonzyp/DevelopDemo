package com.zhangyp.develop.HappyTools.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.zhangyp.develop.HappyTools.R;
import com.zhangyp.develop.HappyTools.adapter.CityAdapter;
import com.zhangyp.develop.HappyTools.base.BaseActivity;
import com.zhangyp.develop.HappyTools.listener.OnItemClickListener;
import com.zhangyp.develop.HappyTools.util.CityUtil;
import com.zhangyp.develop.HappyTools.util.ToastUtil;
import com.zhangyp.develop.HappyTools.wight.LetterListView;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:h5页面
 */

public class ChooseCityListActivity extends BaseActivity {

    private LetterListView llv_right;
    private ImageView iv_back;
    private ImageView iv_more;
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
        iv_more = findViewById(R.id.iv_more);
        RecyclerView rv_hot_city = findViewById(R.id.rv_hot_city);

        adapter = new CityAdapter(this, CityUtil.getHotCityList());
        rv_hot_city.setLayoutManager(new GridLayoutManager(this,3));
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

        iv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShortToast(ChooseCityListActivity.this,"更多城市正在研发中...");
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_choose_city_list;
    }
}
