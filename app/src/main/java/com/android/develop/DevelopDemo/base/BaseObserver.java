package com.android.develop.DevelopDemo.base;

import android.content.Context;


import com.android.develop.DevelopDemo.listener.CallBackListener;
import com.android.develop.DevelopDemo.util.ErrorUtils;

import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;

public class BaseObserver<T extends BaseResponse> implements FlowableSubscriber<T> {
    private CallBackListener<T> callBack = null;
    private Subscription s;

    public BaseObserver(CallBackListener<T> callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onSubscribe(Subscription s) {
        this.s = s;
        s.request(Integer.MAX_VALUE);
    }

    @Override
    public void onNext(T t) {
        try {
            callBack.onSuccess(t);
        } catch (Exception e) {
            callBack.onError(t.getError_code(),t.getReason());
            e.printStackTrace();
        }
        s.cancel();
    }

    @Override
    public void onError(Throwable t) {
        s.cancel();
        t.printStackTrace();
        ErrorUtils.paserError(t, callBack);
    }

    @Override
    public void onComplete() {

    }
}
