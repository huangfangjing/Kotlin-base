package com.example.kotlindemo.presenter

import android.content.Context
import com.example.kotlindemo.http.RetrofitHelper
import com.example.kotlindemo.http.UiSchedulers
import com.example.kotlindemo.http.observer.RefreshObserver
import com.example.kotlindemo.http.response.BaseResponse
import com.example.kotlindemo.http.response.ListResponse
import com.example.kotlindemo.model.entity.Task
import com.example.kotlindemo.model.natives.TaskListRequestBean
import com.example.kotlindemo.presenter.base.BaseManager
import com.example.kotlindemo.presenter.base.BasePresent
import com.example.kotlindemo.view.fragment.TaskListFragment
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

class TeachPresenter(mContext: Context?) : BasePresent<TeachPresenter.TeachManager>(mContext) {

    override fun privadeManager(): TeachManager {
        return TeachManager(mContext)
    }

    class TeachManager(mContext: Context) : BaseManager<TeachServiceApi>(mContext) {
        override fun getBaseService(): TeachServiceApi {
            return RetrofitHelper.getInstance(mContext)
                .privideServer(TeachServiceApi::class.java) as TeachServiceApi
        }
    }

    fun queryJobListApi(taskFragment: TaskListFragment, requstBean: TaskListRequestBean) {
        mManager.baseService.queryJobListApi(requstBean).compose(UiSchedulers.io2main()).subscribe(
            RefreshObserver(mContext, taskFragment, false)
        )
    }


    interface TeachServiceApi {
        @POST("inclass/queryJobListApiNew")
        fun queryJobListApi(@Body requstBean: TaskListRequestBean): Observable<BaseResponse<ListResponse<Task>>>
    }

}