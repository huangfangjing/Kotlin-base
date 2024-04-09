package com.example.kotlindemo.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.BaseViewBinding
import com.scwang.smartrefresh.header.TaurusHeader

abstract class DataBindingFragment<DBinding : ViewDataBinding> : Fragment() {

    open var mActivity: FragmentActivity? = null
    open var mRootView: View? = null
    lateinit var mBaseView: BaseViewBinding
    lateinit var mContentView: DBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = activity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initParams(savedInstanceState)

        setContentView(inflater,container)
        mRootView = mBaseView.root
        initView(mRootView)
        loadData()

        return mRootView
    }

    open abstract fun getContentViewId(): Int

    open fun setContentView(inflater: LayoutInflater, container: ViewGroup?){
        mBaseView = DataBindingUtil.inflate(inflater, R.layout.base_view, container, false)
        mContentView = DataBindingUtil.inflate(inflater, getContentViewId(), mBaseView.rlContainer, true)
        mBaseView.llNetworkError.llNetworkInvalid.setOnClickListener{loadData()}
        mBaseView.llEmptyContent.refresh.setRefreshHeader(TaurusHeader(mActivity))
        mBaseView.llEmptyContent.refresh.setPrimaryColorsId(R.color.c1, R.color.white)
        mBaseView.llEmptyContent.refresh.setOnRefreshListener{
            loadData()
            mBaseView.llEmptyContent.refresh.finishRefresh()
        }
    }

    open fun initParams(savedInstanceState: Bundle?) {}

    open fun initView(view: View?) {}

    open fun loadData() {}

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

}