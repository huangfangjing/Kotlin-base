package com.example.kotlindemo.model.entity;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.kotlindemo.BR;

/**
 * Created by guqingbo on 2017-04-11.
 */

public class LoginInfoBean extends BaseObservable {
    private String name;
    private String password;

    public LoginInfoBean(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}
