package com.android.develop.DevelopDemo.listener;

import java.io.IOException;

public interface CallBackListener<T> {
    void onSuccess(T response) throws IOException;
    void onError(int code, String message);
}
