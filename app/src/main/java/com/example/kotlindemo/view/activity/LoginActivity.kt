package com.example.kotlindemo.view.activity

import com.example.kotlindemo.R
import com.example.kotlindemo.base.MvpBindingActivity
import com.example.kotlindemo.databinding.ActivityLoginLayoutBinding
import com.example.kotlindemo.model.constant.SharedPrefsKey
import com.example.kotlindemo.model.entity.Preference
import com.example.kotlindemo.model.entity.SharedPrefeInfo
import com.example.kotlindemo.model.entity.UserDataBean
import com.example.kotlindemo.presenter.LoginPresenter
import com.example.kotlindemo.utils.HashUtil
import com.google.gson.Gson
import org.jetbrains.anko.startActivity

class LoginActivity : MvpBindingActivity<LoginPresenter, ActivityLoginLayoutBinding>() {

    override fun providePresent(): LoginPresenter {
        return LoginPresenter(this)
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_login_layout
    }

    override fun initViews() {
        super.initViews()
        mContentView.btLogin.setOnClickListener {
            var username = mContentView.etAccount.text.toString().trim()
            var password = HashUtil.sha1Base64(mContentView.etPassword.text.toString().trim())
            mPresent?.login(object :
                LoginPresenter.LoginServiceApi.LoginRequestBean(username, password) {})
        }
    }

    fun loginSuccess(userBean: UserDataBean) {
        SharedPrefeInfo.getInstance()?.saveUserDataBean(Gson().toJson(userBean))
        startActivity<MainActivity>().also { finish() }
    }
}