package com.android.develop.DevelopDemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RadioGroup;

import com.android.develop.DevelopDemo.R;
import com.android.develop.DevelopDemo.base.BaseActivity;
import com.android.develop.DevelopDemo.base.BaseFragment;
import com.android.develop.DevelopDemo.fragment.HomepageFragment;
import com.android.develop.DevelopDemo.fragment.JokeFragment;
import com.android.develop.DevelopDemo.fragment.ExchangeFragment;

public class MainActivity extends BaseActivity {

    private RadioGroup rg_main;
    private BaseFragment[] fragmentArray;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initFragment();
        initClick();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initView() {
        rg_main = findViewById(R.id.rg_main);
    }

    private void initFragment() {

        fragmentArray = new BaseFragment[]{
                new HomepageFragment(),
                new ExchangeFragment(),
                new JokeFragment()
        };

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //设置为默认界面 MainHomeFragment
        ft.add(R.id.fl_container, fragmentArray[0]).commit();
    }

    private void initClick() {

        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        setIndexSelected(0);
                        break;
                    case R.id.rb_exchange:
                        setIndexSelected(1);
                        break;
                    case R.id.rb_joke:
                        setIndexSelected(2);
                        break;
                }
            }
        });
    }

    //设置Fragment页面
    private void setIndexSelected(int index) {
        if (currentIndex == index) {
            return;
        }
        //开启事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //隐藏当前Fragment
        ft.hide(fragmentArray[currentIndex]);
        //判断Fragment是否已经添加
        if (!fragmentArray[index].isAdded()) {
            ft.add(R.id.fl_container, fragmentArray[index]).show(fragmentArray[index]);
        } else {
            //显示新的Fragment
            ft.show(fragmentArray[index]);
        }
        ft.commit();
        currentIndex = index;
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String city = data.getStringExtra("city");
            if (TextUtils.isEmpty(city)) {
                Log.e("=====.", "onActivityResult: " + city);
            }
        }
    }*/
}
