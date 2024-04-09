package com.example.kotlindemo.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.BaseViewBinding
import com.example.kotlindemo.utils.ActivityStack
import com.scwang.smartrefresh.header.TaurusHeader


open abstract class DatabindingActivity<T : ViewDataBinding> : AppCompatActivity() {


    lateinit var mBaseView: BaseViewBinding
    lateinit var mContentView: T
    lateinit var mTopbarView: ViewDataBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSystemUiVisibility()
        ActivityStack.getScreenManager().pushActivity(this)
        setContentView()
        initParams()
        initViews()
        loadData()
    }

    open fun setContentView() {
        mBaseView = DataBindingUtil.setContentView(this, R.layout.base_view)
        mContentView =
            DataBindingUtil.inflate(layoutInflater, getContentViewId(), mBaseView.rlContainer, true)
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


    abstract fun getContentViewId(): Int

    open fun initParams() {}
    open fun initViews() {}
    open fun loadData() {}

    open fun getTopbarViewId(): Int {
        return 0
    }

    open fun showEmptyView() {
        mBaseView.rlContainer.visibility = View.GONE
        mBaseView.llEmptyContent.llEmptyContent.visibility = View.VISIBLE
        mBaseView.llNetworkError.llNetworkInvalid.visibility = View.GONE
    }

    open fun showContentView() {
        mBaseView.rlContainer.visibility = View.VISIBLE
        mBaseView.llEmptyContent.llEmptyContent.visibility = View.GONE
        mBaseView.llNetworkError.llNetworkInvalid.visibility = View.GONE
    }

    open fun showErrorView() {
        mBaseView.rlContainer.visibility = View.GONE
        mBaseView.llEmptyContent.llEmptyContent.visibility = View.GONE
        mBaseView.llNetworkError.llNetworkInvalid.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityStack.getScreenManager().popActivity(this)
    }

    private fun setSystemUiVisibility() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

}