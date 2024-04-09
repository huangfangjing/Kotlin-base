package com.example.kotlindemo.presenter

import android.content.Context
import com.example.kotlindemo.http.RetrofitHelper
import com.example.kotlindemo.http.UiSchedulers
import com.example.kotlindemo.http.observer.DialogObserver
import com.example.kotlindemo.model.entity.UserDataBean
import com.example.kotlindemo.presenter.base.BaseManager
import com.example.kotlindemo.presenter.base.BasePresent
import com.example.kotlindemo.view.activity.LoginActivity
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

class LoginPresenter(mContext: Context) : BasePresent<LoginPresenter.LoginManager>(mContext) {

    override fun privadeManager(): LoginManager {
        return LoginManager(mContext)
    }

    class LoginManager(mContext: Context) : BaseManager<LoginServiceApi>(mContext) {
        override fun getBaseService(): LoginServiceApi {
            return RetrofitHelper.getInstance(mContext)
                .privideServer(LoginServiceApi::class.java) as LoginServiceApi
        }
    }

    fun login(loginBean: LoginServiceApi.LoginRequestBean) {
        mManager.baseService.requestLogin(loginBean).compose(UiSchedulers.io2main())
            .subscribe(object : DialogObserver<UserDataBean>(mContext){

                override fun onNext(userDataBean: UserDataBean) {
                    if ("S1000" == userDataBean.status) {
                      onSuccess(userDataBean)
                    } else if ("E1001" == userDataBean.status) {
                        Toast("帐号不存在")
                    } else if ("E1002" == userDataBean.status) {
                        Toast("帐号或密码错误")
                    } else if ("E1003" == userDataBean.status) {
                        Toast("帐号被锁定")
                    } else if ("E1004" == userDataBean.status) {
                        Toast("系统异常")
                    } else if ("E1005" == userDataBean.status) {
                        Toast("帐号暂时被锁定，5分钟后重试")
                    } else if ("E1008" == userDataBean.status) {
                        Toast("登录失败，请更新至最新版本")
                    } else {
                        Toast("系统异常")
                    }

                }

                override fun onSuccess(t: UserDataBean) {
                    (mContext as LoginActivity).loginSuccess(t)
                }

                override fun onFailure(e: Throwable?) {
                  Toast("网络异常")
                }
            })
    }

    interface LoginServiceApi {
        //登录
        @POST("user/userloginApi")
        fun requestLogin(@Body loginBean: LoginRequestBean): Observable<UserDataBean>
        open class LoginRequestBean(var username:String, var password: String){
        }
    }


}



