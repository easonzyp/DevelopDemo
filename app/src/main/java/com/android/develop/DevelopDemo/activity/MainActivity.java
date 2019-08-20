package com.android.develop.DevelopDemo.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.android.develop.DevelopDemo.R;
import com.android.develop.DevelopDemo.fragment.HomepageFragment;
import com.android.develop.DevelopDemo.fragment.JokeFragment;
import com.android.develop.DevelopDemo.fragment.ParitiesFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fl_container;
    private RadioGroup rg_main;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Map<String,String> params = new HashMap<>();
        params.put("key","13a4128e0b37c19ef55186ad9e6ac664");
        params.put("type","top");
        HttpAction.getInstance().getNewsList(params).subscribe(new BaseObserver<>(new CallBackListener<NewsListResponse>() {
            @Override
            public void onSuccess(NewsListResponse response) throws IOException {
//                Log.e("======>", "onSuccess: " + response.getResult().getStat());
                ToastUtil.showShortToast(MainActivity.this,  response.getResult().getData().get(0).getAuthor_name());
            }

            @Override
            public void onError(int code, String message) {
                ToastUtil.showShortToast(MainActivity.this, message);
            }
        }));*/

        initView();
        initFragment();
        initClick();
    }

    private void initView() {
        fl_container = findViewById(R.id.fl_container);
        rg_main = findViewById(R.id.rg_main);
    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_container, new HomepageFragment());
        transaction.commit();
    }

    private void initClick() {
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                //根据RadioButton不同的Id来选中不同的Fragment。
                switch (checkedId) {
                    case R.id.rb_home:
                        transaction.replace(R.id.fl_container, new HomepageFragment());
                        break;
                    case R.id.rb_parities:
                        transaction.replace(R.id.fl_container, new ParitiesFragment());
                        break;
                    case R.id.rb_joke:
                        transaction.replace(R.id.fl_container, new JokeFragment());
                        break;
                }
                transaction.commit();
            }
        });
    }
}
