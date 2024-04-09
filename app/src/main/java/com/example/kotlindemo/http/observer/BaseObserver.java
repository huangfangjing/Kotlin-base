package com.example.kotlindemo.http.observer;

import android.content.Context;

import com.example.kotlindemo.http.response.BaseResponse;

import io.reactivex.Observer;


/**
 * Created by hfj on 2018/5/24
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    protected Context mContext;

    public BaseObserver(Context context) {

        this.mContext = context;
    }

    @Override
    public void onError(Throwable e) {

        e.printStackTrace();
    }
}

