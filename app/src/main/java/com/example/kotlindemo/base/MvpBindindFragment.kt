package com.example.kotlindemo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.example.kotlindemo.presenter.base.BasePresent

abstract class MvpBindindFragment<P : BasePresent<*>, D : ViewDataBinding> :DataBindingFragment<D>() {

    var mPresent: P? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mPresent =providePresent()
        mPresent?.onCreate()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        mPresent?.onStop()
        super.onDestroyView()
    }

    abstract fun providePresent(): P
}