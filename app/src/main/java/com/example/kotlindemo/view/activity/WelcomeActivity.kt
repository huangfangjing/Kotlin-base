package com.example.kotlindemo.view.activity

import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import com.example.kotlindemo.R
import com.example.kotlindemo.base.DatabindingActivity
import com.example.kotlindemo.databinding.ActivityWelcomeLayoutBinding
import com.example.kotlindemo.model.entity.SharedPrefeInfo
import com.example.kotlindemo.utils.AspLog
import org.jetbrains.anko.startActivity

class WelcomeActivity : DatabindingActivity<ActivityWelcomeLayoutBinding>() {

    var mHandler: Handler = Handler(Looper.getMainLooper())

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }


    external fun stringFromJNI(): String

    override fun getContentViewId(): Int {
        return R.layout.activity_welcome_layout;
    }

    override fun initViews() {
        mHandler.postDelayed({
            if (TextUtils.isEmpty(SharedPrefeInfo.getInstance()?.getToken()))
                startActivity<LoginActivity>()
            else startActivity<MainActivity>()
            finish()
        }, 500)
        AspLog.e("WelcomeActivity", "result:${stringFromJNI()}")
    }
}