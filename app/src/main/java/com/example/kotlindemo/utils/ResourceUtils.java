package com.example.kotlindemo.utils;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.kotlindemo.MyApplication;

/**
 * Created by guqingbo on 2016-12-23.
 */

public class ResourceUtils {
    public static Resources getResources() {
        return MyApplication.getInstance().getResources();
    }

    public static String getString(int stringId) {
        return getResources().getString(stringId);
    }

    public static int getColor(int colorId) {
        return getResources().getColor(colorId);
    }

    public static Drawable getDrawable(int drawableId) {
        return getResources().getDrawable(drawableId);
    }
}
