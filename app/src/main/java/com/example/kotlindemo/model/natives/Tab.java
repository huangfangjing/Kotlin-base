package com.example.kotlindemo.model.natives;

/**
 * Created by Administrator on 2016/12/2.
 */

public class Tab {

    private Class fragment;
    private String text;
    private int icon;

    public Tab(Class fragment, String text) {
        this.fragment = fragment;
        this.text = text;
    }

    public Tab(Class fragment, String text, int icon) {
        this.fragment = fragment;
        this.text = text;
        this.icon = icon;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
