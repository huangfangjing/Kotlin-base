package com.example.kotlindemo.model.entity

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.kotlindemo.MyApplication
import com.example.kotlindemo.model.constant.SharedPrefsKey
import com.google.gson.Gson

class SharedPrefeInfo {


    companion object {
        private var mInstance: SharedPrefeInfo? = null
        private lateinit var mSharedPreferences: SharedPreferences
        private const val SHARED_PREFS_FILE: String = "SharedPrefeInfo"
        private var context: Context = MyApplication.getInstance()

        @JvmStatic
        fun getInstance(): SharedPrefeInfo? {
            if (mInstance == null) {
                mInstance = SharedPrefeInfo()
                mSharedPreferences =
                    context.getSharedPreferences(SHARED_PREFS_FILE, Activity.MODE_PRIVATE)
            }
            return mInstance
        }
    }

    fun saveUserDataBean(userString: String) {
        mSharedPreferences.edit().putString(SharedPrefsKey.KEY_USER_DATA_BEAN, userString).commit()
    }

    fun getUserDataBean(): String? {
        return mSharedPreferences.getString(SharedPrefsKey.KEY_USER_DATA_BEAN, "")
    }


    fun getUserId(): String? {
        var userString = getUserDataBean()
        if (!userString.isNullOrEmpty()) run {
            var userDataBean: UserDataBean = Gson().fromJson(userString, UserDataBean::class.java)
            return userDataBean?.userBean?.userId?.toString()
        }
        return null
    }


    fun getToken(): String? {
        var userString = getUserDataBean()
        if (!userString.isNullOrEmpty()) run {
            var userDataBean: UserDataBean = Gson().fromJson(userString, UserDataBean::class.java)
            return userDataBean?.token
        }
        return null
    }


}