package com.example.kotlindemo.http.observer;


import android.content.Context;


import com.example.kotlindemo.common.LoadingDialogUtil;
import com.example.kotlindemo.http.response.BaseResponse;
import com.example.kotlindemo.http.response.ListResponse;
import com.example.kotlindemo.model.entity.RefreshBaseView;
import com.example.kotlindemo.utils.AspLog;
import com.example.kotlindemo.utils.CollectionUtil;

import io.reactivex.disposables.Disposable;

/**
 * Created by hfj on 2019/3/6
 */
public class RefreshObserver<T> extends BaseListObserver<T> {

    private String tip = "拼命加载中...";
    private boolean isShowloadingDialog = true;
    private RefreshBaseView mRefreshBaseView;


    public RefreshObserver(Context context) {
        super(context);
    }

    public RefreshObserver(Context context, RefreshBaseView baseView) {
        super(context);
        this.mRefreshBaseView = baseView;
    }

    public RefreshObserver(Context context, RefreshBaseView baseView, boolean isShowloadingDialog) {
        super(context);
        this.mRefreshBaseView = baseView;
        this.isShowloadingDialog = isShowloadingDialog;
    }

    public RefreshObserver(Context context, boolean isShowloadingDialog) {
        super(context);
        this.isShowloadingDialog = isShowloadingDialog;
    }

    public RefreshObserver(Context context, String tip) {
        super(context);
        this.tip = tip;
    }


    @Override
    public void onSubscribe(Disposable d) {
        if (isShowloadingDialog) {
            LoadingDialogUtil.showLoadingDialog(mContext, tip);
        }
    }

    @Override
    public void onNext(BaseResponse<ListResponse<T>> t) {
        if (isShowloadingDialog) {
            LoadingDialogUtil.closeLoadingDialog();
        }

        if (!t.state) {
            if (mRefreshBaseView != null) {
                mRefreshBaseView.onServiceError(t.message);
            } else {
                ((RefreshBaseView) mContext).onServiceError(t.message);
            }
        } else {
            if (CollectionUtil.isEmpty(t.data.list)) {
                if (mRefreshBaseView != null) {
                    mRefreshBaseView.onNoData("");
                } else {
                    ((RefreshBaseView) mContext).onNoData("");
                }
            } else {
                if (mRefreshBaseView != null) {
                    mRefreshBaseView.onSuccess(t.data);
                } else {
                    ((RefreshBaseView) mContext).onSuccess(t.data);
                }
            }
        }
    }

    @Override
    public void onComplete() {
        if (isShowloadingDialog) {
            LoadingDialogUtil.closeLoadingDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        AspLog.e("onError_tag", e.getMessage());
        if (isShowloadingDialog) {
            LoadingDialogUtil.closeLoadingDialog();
        }
        if (mRefreshBaseView != null) {
            mRefreshBaseView.onNetworkError("网络异常，请检查您的网络");
        } else {
            ((RefreshBaseView) mContext).onNetworkError("网络异常，请检查您的网络");
        }
    }
}
