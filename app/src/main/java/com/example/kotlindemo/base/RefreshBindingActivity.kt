package com.example.kotlindemo.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.RefreshViewLayoutBinding
import com.example.kotlindemo.presenter.base.BasePresent
import com.scwang.smartrefresh.header.TaurusHeader
import com.scwang.smartrefresh.layout.api.RefreshHeader

abstract class RefreshBindingActivity<P : BasePresent<*>, D : ViewDataBinding> : MvpBindingActivity<P, D>() {

    lateinit var mRefreshView: RefreshViewLayoutBinding

    override fun setContentView() {
        mBaseView = DataBindingUtil.setContentView(this, R.layout.base_view)
        mRefreshView = DataBindingUtil.inflate(
            layoutInflater, R.layout.refresh_view_layout, mBaseView.rlContainer, true
        )
        mContentView = DataBindingUtil.inflate(
            layoutInflater,
            getContentViewId(),
            mRefreshView.smartLayout,
            true
        )
        if (getTopbarViewId() > 0) {
            mTopbarView =
                DataBindingUtil.inflate(layoutInflater, getTopbarViewId(), mBaseView.flTitle, true)
        }
        mBaseView.llNetworkError.llNetworkInvalid.setOnClickListener { loadData() }
        mBaseView.llEmptyContent.refresh.setRefreshHeader(TaurusHeader(this))
        mBaseView.llEmptyContent.refresh.setPrimaryColorsId(
            R.color.c1,
            android.R.color.white
        )
        mBaseView.llEmptyContent.refresh.setOnRefreshListener {
            loadData()
            mBaseView.llEmptyContent.refresh.finishRefresh()
        }
    }

    protected open fun getRefreshHeader(): RefreshHeader? {
        return TaurusHeader(this)
    }

    open fun finishRefresh() {
        mRefreshView.smartLayout?.finishRefresh()
    }
}