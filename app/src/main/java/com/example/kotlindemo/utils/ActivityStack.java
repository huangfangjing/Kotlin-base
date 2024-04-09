package com.example.kotlindemo.utils;

import android.app.Activity;

import java.util.Stack;

public class ActivityStack {
    private static Stack<Activity> mActivityStack = new Stack<>();
    private static ActivityStack instance = new ActivityStack();

    private ActivityStack() {
    }

    public static ActivityStack getScreenManager() {
        return instance;
    }

    public Stack<Activity> activityStack(){
        return mActivityStack;
    }

    public void popActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            mActivityStack.remove(activity);
            activity = null;
        }
    }

    //
    public void pushActivity(Activity activity) {
        mActivityStack.add(activity);
    }

    //Activity
    public void popAllActivityExceptOne() {
        while (mActivityStack.size() > 0) {
                Activity activity = mActivityStack.pop();
                if (activity != null) {
                    activity.finish();
                }
        }
    }

    public  void finishAll(){
        if(mActivityStack.size()>0){
            for (Activity activity:mActivityStack){
                if (!activity.isFinishing()){
                    activity.finish();
                }
            }
        }

    }

    public void popAllActivityExceptOne(Class cls) {
        while (mActivityStack.size() > 0) {
            Activity activity = mActivityStack.pop();
            if (activity != null) {
                if (!activity.getClass().equals(cls)){
                    activity.finish();
                }
            }
        }
    }

    public void popAllActivityExceptOne(Activity tarActivity){
        while (mActivityStack.size() > 0) {
            Activity activity = mActivityStack.pop();
            if (activity != null) {
                if (!activity.equals(tarActivity)){
                    activity.finish();
                }
            }
        }
        pushActivity(tarActivity);
    }

    /**
     * 获得当前的activity(即最上层)
     *
     * @return
     */
    public Activity currentActivity() {
        Activity activity = null;
        if (!mActivityStack.empty())
            activity = mActivityStack.lastElement();
        return activity;
    }

}