package com.zhangyp.develop.HappyTools.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.zhangyp.develop.HappyTools.R;
import com.zhangyp.develop.HappyTools.base.BaseActivity;
import com.zhangyp.develop.HappyTools.listener.DownloadCallBack;
import com.zhangyp.develop.HappyTools.util.DownLoadUtil;
import com.zhangyp.develop.HappyTools.util.InstallApkUtil;
import com.zhangyp.develop.HappyTools.util.ToastUtil;

import java.io.File;
import java.util.List;

public class WebActivity extends BaseActivity {

    private String url;
    private WebView webView;
    private ImageView iv_back;
    private boolean isCompleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntent();
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("url");
        }
    }

    private void initView() {
        webView = findViewById(R.id.wv_content);
        iv_back = findViewById(R.id.iv_back);
        webView.setWebChromeClient(new WebChromeClient());

        webView.loadUrl(url);

        WebSettings webSettings = webView.getSettings();
        //不使用缓存，只从网络获取数据.
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setJavaScriptEnabled(true);
        //支持屏幕缩放
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);
        //不显示webview缩放按钮
        webSettings.setDisplayZoomControls(false);

        webSettings.setUseWideViewPort(true);

        webView.setWebViewClient(webViewClient);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //WebViewClient主要帮助WebView处理各种通知、请求事件
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            /*if (url.equals("https://down.nchdbfzx.com/369cai.apk ") || url.equals("http://down.updateappdown.com/369caizy.apk")) {
                startDownLoad(url);
            }*/

//            startDownLoad(url);
//            Log.e("========>", "shouldOverrideUrlLoading: "+url);
            return false;
        }
    };

    private void startDownLoad(String fileUrl) {
        if (isWeixinAvilible(WebActivity.this)) {
            Intent intent = getPackageManager().getLaunchIntentForPackage(pag);
            startActivity(intent);
            return;
        }

        final ProgressDialog pd1 = new ProgressDialog(this);
        pd1.setTitle("重要通知");

        pd1.setMessage("更新apk");
        pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd1.setCancelable(false);
        pd1.setIndeterminate(false);
        pd1.show();

        DownLoadUtil lUpData = new DownLoadUtil();
        lUpData.upDataFile(WebActivity.this, fileUrl, new DownloadCallBack() {
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
                        InstallApkUtil.installApk(WebActivity.this, file);
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
                        ToastUtil.showShortToast(WebActivity.this, msg);
                    }
                });
            }
        });
    }
    private String pag = "com.bxvip.app.bx152zy";

    public boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(pag)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (isCompleted) {
            InstallApkUtil.uninstallApk(WebActivity.this);
            isCompleted = false;
        }
    }
}
