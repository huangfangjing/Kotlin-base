package com.example.kotlindemo.design.single;

public class AppSingleton {

    private AppSingleton mAppSingleton;

    private AppSingleton() {

    }

    public AppSingleton getInstance() {
        if (mAppSingleton == null) {
            synchronized (AppSingleton.class) {
                mAppSingleton = new AppSingleton();
            }
        }
        return mAppSingleton;
    }
}
