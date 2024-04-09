package com.example.kotlindemo.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.example.kotlindemo.presenter.base.BasePresent

abstract class MvpBindingActivity<P : BasePresent<*>, D : ViewDataBinding> : DatabindingActivity<D>() {

    var mPresent: P? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        mPresent = providePresent()
        mPresent?.onCreate()
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresent?.onStop()
    }

    abstract fun providePresent(): P
}