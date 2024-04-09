package com.example.kotlindemo

import android.app.Application
import android.content.Context
import com.example.kotlindemo.utils.DeviceUtils

class MyApplication : Application() {

    //aaaaa

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun getApplicationContext(): Context {
        return super.getApplicationContext()
    }

    companion object {
        private lateinit var instance: MyApplication
        @JvmStatic
        fun getInstance() = instance
    }

    fun getChannelName(): String? {
        return DeviceUtils.getChannelName(instance)
    }
}