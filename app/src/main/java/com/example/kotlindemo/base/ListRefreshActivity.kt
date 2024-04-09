package com.example.kotlindemo.base

import android.text.TextUtils
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindemo.R
import com.example.kotlindemo.adapter.base.BaseRecyclerAdapter
import com.example.kotlindemo.http.response.ListResponse
import com.example.kotlindemo.model.entity.RefreshBaseView
import com.example.kotlindemo.presenter.base.BasePresent
import com.example.kotlindemo.widget.MultipleStatusRecylerview
import com.example.kotlindemo.widget.RecyclerViewDivider
import com.scwang.smartrefresh.header.DeliveryHeader
import com.scwang.smartrefresh.header.TaurusHeader
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import org.jetbrains.anko.toast

abstract class ListRefreshActivity<P : BasePresent<*>, M, D : ViewDataBinding> :
    MvpBindingActivity<P, D>(), OnRefreshListener, OnLoadMoreListener, RefreshBaseView<M> {

    var mPage = 1
    var mPageSize = 15
    var mTotal = 0
    var mIsPullDown = true
    var mCanLoadMore = true
    open var mDatas: MutableList<M> = mutableListOf()

    lateinit var mRefreshLayout: RefreshLayout
    lateinit var mRecyclerView: MultipleStatusRecylerview
    lateinit var mAdapter: BaseRecyclerAdapter<M>

    override fun getContentViewId(): Int {
        return R.layout.base_refresh_activity;
    }

    override fun initViews() {
        super.initViews()
        mRefreshLayout = findViewById(R.id.refresh)
        mRecyclerView = findViewById(R.id.recycler)
        setLayoutManager()
        mAdapter = getAdapter(mDatas)
        mRecyclerView.setAdapter(mAdapter)
        setItemDecoration()
        mRefreshLayout.setOnRefreshListener(this)
        mRefreshLayout.setOnLoadMoreListener(this)
        mRefreshLayout.setEnableLoadMore(mCanLoadMore)
        mRefreshLayout.setRefreshHeader(getRefreshHeader())
    }

    open fun setLayoutManager() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView.setLayoutManager(layoutManager)
    }

    //如不需要分割线，复写该方法即可
    open fun setItemDecoration() {
        mRecyclerView.addItemDecoration(RecyclerViewDivider(this))
    }

    abstract fun getAdapter(datas: MutableList<M>): BaseRecyclerAdapter<M>

    open fun getRefreshHeader(): RefreshHeader {
        return DeliveryHeader(this)
    }

    open fun onRefresh() {
        onRefresh(mRefreshLayout)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mPage = 1
        mIsPullDown = true
        loadData()
        mRefreshLayout.setEnableLoadMore(mCanLoadMore)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        mIsPullDown = false
        val size = mDatas.size
        if (mTotal <= 0 || mTotal <= size) {
            toast(getString(R.string.no_data))
            mRefreshLayout.finishLoadMore()
            mRefreshLayout.setEnableLoadMore(false)
            return
        }
        loadData()
    }

    override fun onSuccess(mData: ListResponse<M>?) {
        if (mData == null) {
            mRefreshLayout.finishRefresh()
            mRefreshLayout.finishLoadMore()
            mRecyclerView.showEmptyView()
            return
        }
        showContentView()
        if (mIsPullDown) {
            mDatas.clear()
            addData(mData)
            mRefreshLayout.finishRefresh()
        } else {
            addData(mData)
            mRefreshLayout.finishLoadMore()
        }
        mPage++
        mAdapter.notifyDataSetChanged()

        if (mIsPullDown) {
            mRecyclerView.scrollToPosition(0)
        }
    }

    override fun onNoData(tip: String) {
        if (mIsPullDown) {
            showEmptyView()
            mRefreshLayout.finishRefresh()
        } else {
            if (!TextUtils.isEmpty(tip)) {
                toast(tip)
            }
            mRefreshLayout.setEnableLoadMore(false)
            mRefreshLayout.finishLoadMore()
        }
    }

    override fun onServiceError(result: String) {
        toast(result)
        mRecyclerView.showServiceError()
        mRefreshLayout.finishLoadMore()
        mRefreshLayout.finishRefresh()
    }

    override fun onNetworkError(result: String) {
        toast(result)
        mRecyclerView.showErrorView()
        mRefreshLayout.finishLoadMore()
        mRefreshLayout.finishRefresh()
    }

    open fun addData(info: ListResponse<M>) {
        mTotal = info.total
        val newDatas: List<M> = info.list
        if (newDatas.isNotEmpty()) {
            mDatas.addAll(newDatas)
            mAdapter.notifyDataSetChanged()
        }
    }


}