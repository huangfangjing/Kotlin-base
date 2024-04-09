package com.example.kotlindemo.presenter.base;

import android.content.Context;

/**
 * Created by hfj on 2018/5/24
 */
public abstract class BaseManager<T> {

    protected Context mContext;
    public T mService;

    public BaseManager(Context context){
        this.mContext = context;
        mService =getBaseService();
    }

    public abstract T getBaseService();


}

