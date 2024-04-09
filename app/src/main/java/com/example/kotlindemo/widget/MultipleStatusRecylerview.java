package com.example.kotlindemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.example.kotlindemo.R;
import com.example.kotlindemo.utils.AspLog;


/**
 * Create by luweihong on 2018/7/13
 * 包含empty和error、内容状态
 */
public class MultipleStatusRecylerview extends FrameLayout {
    private RecyclerView recyclerView;
    private FrameLayout flError, flEmpty;
    private TextView mTvErrorTip;

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }


    public void scrollToPosition(int position) {
        if (recyclerView != null) {
            recyclerView.scrollToPosition(position);
        }
    }

    public MultipleStatusRecylerview(@NonNull Context context) {
        super(context);
        initView();
    }


    public MultipleStatusRecylerview(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MultipleStatusRecylerview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_mut_recyclerview, this);
        recyclerView = findViewById(R.id.recycler_view);
        flError = findViewById(R.id.fl_error);
        flEmpty = findViewById(R.id.fl_empty);
        mTvErrorTip = findViewById(R.id.tv_error_tip);
    }


    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }


    public void setLayoutManager(RecyclerView.LayoutManager layout) {
        recyclerView.setLayoutManager(layout);
    }

    public void showEmptyView() {
        recyclerView.setVisibility(View.GONE);
        flError.setVisibility(View.GONE);
        flEmpty.setVisibility(View.VISIBLE);
    }


    public void showErrorView() {
        recyclerView.setVisibility(View.GONE);
        flError.setVisibility(View.VISIBLE);
        flEmpty.setVisibility(View.GONE);
        mTvErrorTip.setText(R.string.error_network_tip);
    }

    public void showServiceError() {
        recyclerView.setVisibility(View.GONE);
        flError.setVisibility(View.VISIBLE);
        flEmpty.setVisibility(View.GONE);
        mTvErrorTip.setText(R.string.error_server_tip);
    }

    public void showContentView() {
        recyclerView.setVisibility(View.VISIBLE);
        flError.setVisibility(View.GONE);
        flEmpty.setVisibility(View.GONE);
    }


    public void setEmptyViewOnClickListener(OnClickListener onclickListener) {
        flEmpty.setOnClickListener(onclickListener);
    }

    public void setErrorViewOnClickListener(OnClickListener onclickListener) {
        flError.setOnClickListener(onclickListener);
    }

    public void closeDefaultAnimator(RecyclerView mRvCustomer) {
        if (null == mRvCustomer) return;
        mRvCustomer.getItemAnimator().setAddDuration(0);
        mRvCustomer.getItemAnimator().setChangeDuration(0);
        mRvCustomer.getItemAnimator().setMoveDuration(0);
        mRvCustomer.getItemAnimator().setRemoveDuration(0);
        ((SimpleItemAnimator) mRvCustomer.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    public void removeAnimation() {
        if (recyclerView == null) {
            return;
        }
        recyclerView.getItemAnimator().setAddDuration(0);
        recyclerView.getItemAnimator().setChangeDuration(0);
        recyclerView.getItemAnimator().setMoveDuration(0);
        recyclerView.getItemAnimator().setRemoveDuration(0);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        AspLog.e("taggg", "移除动画");
    }

    /**
     * 自定义EmptyView
     *
     * @param view
     */
    public void setEmptyView(View view) {
        flEmpty.removeAllViews();
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        flEmpty.addView(view);
    }

    /**
     * 自定义ErrorView
     *
     * @param view
     */
    public void setErrorView(View view) {
        flError.removeAllViews();
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        flError.addView(view);
    }


    public void addItemDecoration(RecyclerView.ItemDecoration recyclerViewDivider) {
        recyclerView.addItemDecoration(recyclerViewDivider);
    }
}
