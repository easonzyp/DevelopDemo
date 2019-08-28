package com.zhangyp.develop.HappyTools.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhangyp.develop.HappyTools.R;
import com.zhangyp.develop.HappyTools.base.BaseActivity;
import com.zhangyp.develop.HappyTools.base.BaseObserver;
import com.zhangyp.develop.HappyTools.http.HttpAction;
import com.zhangyp.develop.HappyTools.listener.CallBackListener;
import com.zhangyp.develop.HappyTools.response.DreamDetailResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DreamDetailActivity extends BaseActivity {

    private TextView tv_back;
    private TextView tv_id;
    private TextView tv_title;
    private TextView tv_content;
    private LinearLayout ll_content;

    private String id;
    private DreamDetailResponse.ResultBean resultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntentData();
        initView();
        initData();
        initClick();
    }

    private void initIntentData() {
        id = getIntent().getStringExtra("id");
    }

    private void initView() {
        tv_back = findViewById(R.id.tv_back);
        tv_id = findViewById(R.id.tv_id);
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);
        ll_content = findViewById(R.id.ll_content);
    }

    private void initData() {
        Map<String, String> param = new HashMap<>();
        param.put("key", "c6a7982ce6d6b47bc36052d19b446b79");
        param.put("id", id);
        HttpAction.getInstance().getDreamDetail(param).subscribe(new BaseObserver<>(new CallBackListener<DreamDetailResponse>() {
            @Override
            public void onSuccess(DreamDetailResponse response) throws IOException {
                resultBean = response.getResult();
                setDreamDream();
            }

            @Override
            public void onError(int code, String message) {

            }
        }));
    }

    private void setDreamDream() {
        tv_id.setText(String.format("id：%s", resultBean.getId()));
        tv_title.setText(String.format("标题：%s", resultBean.getTitle()));
        tv_content.setText(resultBean.getDes());

        List<String> list = resultBean.getList();
        if (list == null || list.size() == 0) {
            ll_content.setVisibility(View.GONE);
            return;
        }

        ll_content.setVisibility(View.VISIBLE);
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            TextView textView = new TextView(this);
//            ViewGroup.LayoutParams params = textView.getLayoutParams();
            textView.setPadding(0, 20, 0, 0);

            textView.setText(String.format("%s、 %s", index, list.get(i)));
            ll_content.addView(textView);
        }
    }

    private void initClick() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dream_detail;
    }
}
