package com.example.kotlindemo.presenter.base;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;


import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by hfj on 2018/5/24
 */
public abstract class BasePresent<D extends BaseManager> extends  Presenter {

    protected D mManager;
    protected CompositeDisposable mCompositeSubscription;
    protected Context mContext;


    public BasePresent(Context mContext) {
        this.mContext = mContext;
    }

    public abstract D privadeManager();

    @Override
    public void onCreate() {
        mManager = privadeManager();
        mCompositeSubscription = new CompositeDisposable();
    }

    @Override
    public void onStop() {
        if (!mCompositeSubscription.isDisposed()) {
            mCompositeSubscription.dispose();
        }
    }
}