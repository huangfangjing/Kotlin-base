package com.example.kotlindemo.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.RefreshViewLayoutBinding
import com.example.kotlindemo.presenter.base.BasePresent
import com.scwang.smartrefresh.header.TaurusHeader
import com.scwang.smartrefresh.layout.api.RefreshHeader

abstract class RefreshBindingFragment<P : BasePresent<*>, D : ViewDataBinding> :MvpBindindFragment<P,D>() {

    lateinit var mRefreshView: RefreshViewLayoutBinding

    override fun setContentView(inflater: LayoutInflater, container: ViewGroup?) {
        mPresent = providePresent()
        if (mPresent != null) {
            mPresent!!.onCreate()
        }
        mBaseView = DataBindingUtil.inflate(inflater, R.layout.base_view, container, false)
        mRefreshView = DataBindingUtil.inflate(inflater, R.layout.refresh_view_layout, mBaseView.rlContainer, true)
        mContentView= DataBindingUtil.inflate(inflater, getContentViewId(), mRefreshView.smartLayout, true)

        mRefreshView.smartLayout.setPrimaryColorsId(R.color.c1, R.color.white)
        mRefreshView.smartLayout.setRefreshHeader(getRefreshHeader())
        mRefreshView.smartLayout.setOnRefreshListener{ loadData() }
    }

    open fun getRefreshHeader(): RefreshHeader {
        return TaurusHeader(mActivity)
    }

    open fun finishRefresh() {
        if (mRefreshView.smartLayout != null) {
            mRefreshView.smartLayout.finishRefresh()
            mRefreshView.smartLayout.finishLoadMore()
        }
    }
}