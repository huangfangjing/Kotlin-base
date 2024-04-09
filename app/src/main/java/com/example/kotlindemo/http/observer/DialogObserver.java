package com.example.kotlindemo.http.observer;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;


import com.example.kotlindemo.common.LoadingDialogUtil;
import com.example.kotlindemo.http.response.BaseResponse;
import com.example.kotlindemo.utils.AspLog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by hfj on 2018/5/24
 */
@Deprecated
public abstract class DialogObserver<T> implements Observer<T> {

    protected Context mContext;
    private String tip = "拼命加载中...";
    private boolean isShowloadingDialog = true;

    public DialogObserver(Context context) {
        this.mContext = context;
    }

    public DialogObserver(Context context, boolean isShowloadingDialog) {
        this.mContext = context;
        this.isShowloadingDialog = isShowloadingDialog;
    }
    public DialogObserver(Context context, String tip) {
        this.mContext = context;
        this.tip=tip;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (isShowloadingDialog){
            LoadingDialogUtil.showLoadingDialog(mContext,tip);
        }
    }

    @Override
    public void onNext(T t) {
        if (isShowloadingDialog) {
            LoadingDialogUtil.closeLoadingDialog();
        }
        onSuccess(t);
    }

    @Override
    public void onComplete() {
        if (isShowloadingDialog) {
            LoadingDialogUtil.closeLoadingDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        AspLog.e("onError_tag",e.getMessage());
        if (isShowloadingDialog){
            LoadingDialogUtil.closeLoadingDialog();
        }
        onFailure(e);
    }
    public void Toast(String msg){
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        }
    }

    protected abstract  void onSuccess(T t);
    protected abstract  void onFailure(Throwable e);
}
