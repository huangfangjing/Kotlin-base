package com.example.kotlindemo.http.observer;


import android.content.Context;
import android.widget.Toast;

import com.example.kotlindemo.base.RefreshBindingActivity;
import com.example.kotlindemo.base.RefreshBindingFragment;
import com.example.kotlindemo.common.LoadingDialogUtil;
import com.example.kotlindemo.http.response.BaseResponse;
import com.example.kotlindemo.utils.AspLog;

import io.reactivex.disposables.Disposable;

/**
 * Created by hfj on 2019/3/6
 */
public abstract class SimpleObserver<T> extends BaseObserver<T> {

    private String tip = "拼命加载中...";
    private boolean isShowDialog = true;
    private RefreshBindingFragment fragment;


    public SimpleObserver(Context context) {
        super(context);
    }

    public SimpleObserver(RefreshBindingFragment fragment) {
        super(fragment.getContext());
        this.fragment = fragment;
    }

    public SimpleObserver(Context context, boolean isShowloadingDialog) {
        super(context);
        this.isShowDialog = isShowloadingDialog;
    }

    public SimpleObserver(Context context, String tip) {
        super(context);
        this.tip = tip;
    }


    @Override
    public void onSubscribe(Disposable d) {
        if (isShowDialog) {
            LoadingDialogUtil.showLoadingDialog(mContext, tip);
        }
    }

    @Override
    public void onNext(BaseResponse<T> t) {
        if (isShowDialog) {
            LoadingDialogUtil.closeLoadingDialog();
        }
        if (!t.state) {
            Toast.makeText(mContext,t.message,Toast.LENGTH_SHORT).show();
            finishRefresh();
        } else {
            onSuccess(t.data);
            finishRefresh();
        }
    }

    @Override
    public void onComplete() {
        if (isShowDialog) {
            LoadingDialogUtil.closeLoadingDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        AspLog.e("onError_tag", e.getMessage());
        if (isShowDialog) {
            LoadingDialogUtil.closeLoadingDialog();
        }
        finishRefresh();
        Toast.makeText(mContext, "网络异常，请检查您的网络",Toast.LENGTH_SHORT).show();
    }

    private void finishRefresh() {
        if (mContext instanceof RefreshBindingActivity) {
            ((RefreshBindingActivity) mContext).finishRefresh();
            return;
        }
        if (fragment != null) {
            fragment.finishRefresh();
        }
    }

    protected abstract void onSuccess(T t);
}
