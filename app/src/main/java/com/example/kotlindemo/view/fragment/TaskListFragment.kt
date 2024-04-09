package com.example.kotlindemo.view.fragment

import com.example.kotlindemo.R
import com.example.kotlindemo.adapter.TaskListAdapter
import com.example.kotlindemo.adapter.base.BaseRecyclerAdapter
import com.example.kotlindemo.base.ListRefreshFragment
import com.example.kotlindemo.databinding.BaseRefreshActivityBinding
import com.example.kotlindemo.model.entity.SharedPrefeInfo
import com.example.kotlindemo.model.entity.Task
import com.example.kotlindemo.model.natives.TaskListRequestBean
import com.example.kotlindemo.presenter.TeachPresenter

class TaskListFragment : ListRefreshFragment<TeachPresenter, Task, BaseRefreshActivityBinding>() {


    override fun getAdapter(datas: MutableList<Task>): BaseRecyclerAdapter<Task> {
        return TaskListAdapter(mActivity,datas, R.layout.adapter_task_list_item,null)
    }

    override fun providePresent(): TeachPresenter {
        return TeachPresenter(context)
    }

    override fun setItemDecoration() {
    }

    override fun loadData() {
        val requestBean = TaskListRequestBean(SharedPrefeInfo.getInstance()?.getUserId())
        requestBean.classId = "01hgfi7kasapfUQEN276L5I7KB1eL2Ss"
        requestBean.termId = "01ha4f0tk02lzCc10abOs8LOpSbLB7mr"
        requestBean.yearId = "01g12deupu4YzKlH3uhbCaWoNQnZAMh3"
        requestBean.startPage = mPage
        requestBean.pageSize = mPageSize
        requestBean.type = "t"
        requestBean.subjectId = "0qS0VT9f1bK49IN9SE8aW5zXSd9i29"
        mPresent?.queryJobListApi(this, requestBean)
    }
}