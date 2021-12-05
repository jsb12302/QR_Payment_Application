package com.example.myapplication.user.qr;

import android.app.Application;

import com.android.volley.RequestQueue;

public class MyApplication extends Application {
    private static MyApplication mInstance;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}
