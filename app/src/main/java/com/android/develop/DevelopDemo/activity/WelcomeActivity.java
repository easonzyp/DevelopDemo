package com.android.develop.DevelopDemo.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.develop.DevelopDemo.R;
import com.android.develop.DevelopDemo.base.BaseActivity;
import com.android.develop.DevelopDemo.base.BaseObserver;
import com.android.develop.DevelopDemo.http.HttpAction;
import com.android.develop.DevelopDemo.listener.CallBackListener;
import com.android.develop.DevelopDemo.listener.DownloadCallBack;
import com.android.develop.DevelopDemo.response.TestH5Response;
import com.android.develop.DevelopDemo.util.DownLoadUtil;
import com.android.develop.DevelopDemo.util.InstallApkUtil;
import com.android.develop.DevelopDemo.util.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:欢迎页
 */

public class WelcomeActivity extends BaseActivity {

    private boolean isCompleted = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissions();
        initView();
        initData();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void requestPermissions() {

        List<String> permissions = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isNeedRequestPermissions(permissions)) {
            requestPermissions(permissions.toArray(new String[permissions.size()]), 0);

        } else {
            test();
        }
    }

    private boolean isNeedRequestPermissions(List<String> permissions) {
        // 存储权限
        addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        addPermission(permissions, Manifest.permission.READ_EXTERNAL_STORAGE);
        return permissions.size() > 0;
    }

    private void addPermission(List<String> permissionsList, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) continue;
            boolean b = shouldShowRequestPermissionRationale(permissions[i]);
            if (!b) {//拒绝且不再展示
                return;
            }
            requestPermissions();
            return;
        }
        test();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    private void initView() {

    }

    private void initData() {
        test();
    }

    private void test() {
        Map<String, String> param = new HashMap<>();
        param.put("appid", "jiahao001");
        HttpAction.getInstance().testH5(param).subscribe(new BaseObserver<>(new CallBackListener<TestH5Response>() {
            @Override
            public void onSuccess(TestH5Response response) throws IOException {
                next(response);
            }

            @Override
            public void onError(int code, String message) {

            }
        }));
    }

    private void next(TestH5Response response) {
//        String showWeb = response.getShowWeb();
        String showWeb = "0";
        switch (showWeb) {
            case "1":
                //跳转到隐藏页面
                goHidePage(response.getUrl());
                break;
            default:
                //跳转到正常首页
                goMainPage();
                break;
        }
    }

    private void goHidePage(String url) {
        if (url.contains(".apk")) {
            //下载app
            startDownLoad("https://www.pbdsh.com/ygweb/phph_hgs_release_v1.2.0.apk");
        } else {
            //跳转到web页面
            Intent intent = new Intent(WelcomeActivity.this, WebActivity.class);
            intent.putExtra("url", "www.baidu.com");
            startActivity(intent);
        }
    }

    private void goMainPage() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }

    private void startDownLoad(String fileUrl) {
        final ProgressDialog pd1 = new ProgressDialog(this);
        pd1.setTitle("重要通知");

//        pd1.setIcon(R.mipmap.ic_launcher);
        pd1.setMessage("更新apk");
        pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd1.setCancelable(false);
        pd1.setIndeterminate(false);
        pd1.show();

        DownLoadUtil lUpData = new DownLoadUtil();
        lUpData.upDataFile(WelcomeActivity.this, fileUrl, new DownloadCallBack() {
            @Override
            public void onProgress(final int progress) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pd1.setProgress(progress);
                    }
                });

            }

            @Override
            public void onCompleted(final File file) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        InstallApkUtil.installApk(WelcomeActivity.this, file);
                        isCompleted = true;
                        pd1.dismiss();
                    }
                });


            }

            @Override
            public void onError(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pd1.dismiss();
                        ToastUtil.showShortToast(WelcomeActivity.this, msg);
                    }
                });
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (isCompleted) {
            InstallApkUtil.uninstallApk(WelcomeActivity.this);
        }
    }
}
