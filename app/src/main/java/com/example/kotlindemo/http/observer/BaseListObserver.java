package com.example.kotlindemo.http.observer;

import android.content.Context;

import com.example.kotlindemo.http.response.BaseResponse;
import com.example.kotlindemo.http.response.ListResponse;

import io.reactivex.Observer;


public abstract class BaseListObserver<T> implements Observer<BaseResponse<ListResponse<T>>> {

    protected Context mContext;

    public BaseListObserver(Context context) {

        this.mContext = context;
    }

    @Override
    public void onError(Throwable e) {

        e.printStackTrace();
    }
}

