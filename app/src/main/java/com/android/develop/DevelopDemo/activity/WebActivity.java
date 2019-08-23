package com.android.develop.DevelopDemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.android.develop.DevelopDemo.R;
import com.android.develop.DevelopDemo.base.BaseActivity;
import com.android.develop.DevelopDemo.base.BaseObserver;
import com.android.develop.DevelopDemo.http.HttpAction;
import com.android.develop.DevelopDemo.listener.CallBackListener;
import com.android.develop.DevelopDemo.response.TestH5Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:h5页面
 */

public class WebActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }
}
