package com.lcw.library.imagepicker.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lcw.library.imagepicker.R;
import com.lcw.library.imagepicker.utils.SystemBarTintManager;

/**
 * BaseActivity基类
 * Create by: chenWei.li
 * Date: 2018/10/9
 * Time: 下午11:34
 * Email: lichenwei.me@foxmail.com
 */
public abstract class BaseActivity extends AppCompatActivity {

    private View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //修改为深色，因为我们把状态栏的背景色修改为主题色白色，默认的文字及图标颜色为白色，导致看不到了。
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState);


        if (mView == null) {
            mView = View.inflate(this, bindLayout(), null);
        }

        setContentView(mView);
        //状态栏默认为蓝色
        setStatusColor(this, R.color.c1);

        initConfig();
        initView();
        initListener();
        getData();
    }


    protected abstract int bindLayout();

    protected void initConfig() {
    }

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void getData();

    public static void setStatusColor(Activity activity, int colorId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //6.0以上版本
                // 设置状态栏底色颜色
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorId));
                //设置状态栏的字体颜色的
                //setStatusBarDarkTheme(activity, true);

                //设置状态栏字体为白色
                View stateView = activity.getWindow().getDecorView();
                if (stateView != null) {
                    int vis = stateView.getSystemUiVisibility();
                    //vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //黑色
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //白色
                    stateView.setSystemUiVisibility(vis);//设置状态栏字体颜色
                }

            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.0以上版本
                Window window = activity.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                //6.0以下设置默认白色状态栏
                window.setStatusBarColor(activity.getResources().getColor(R.color.white));

            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //4.4以上版本
                //使用SystemBarTint库使4.4版本状态栏变色，需要先将状态栏设置为透明
                setTranslucentStatus(activity);
                SystemBarTintManager tintManager = new SystemBarTintManager(activity);
                //显示状态栏
                tintManager.setStatusBarTintEnabled(true);
                //设置状态栏颜色
                tintManager.setStatusBarTintResource(activity.getResources().getColor(R.color.white));

            }
        } catch (Exception e) {

        }

    }

    /**
     * 设置状态栏透明
     */
    @TargetApi(19)
    public static void setTranslucentStatus(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            //导航栏颜色也可以正常设置
            //window.setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            attributes.flags |= flagTranslucentStatus;
            //int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            //attributes.flags |= flagTranslucentNavigation;
            window.setAttributes(attributes);
        }
    }


}
