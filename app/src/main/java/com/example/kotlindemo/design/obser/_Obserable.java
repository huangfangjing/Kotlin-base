package com.example.kotlindemo.design.obser;

import java.util.ArrayList;
import java.util.List;

public class _Obserable <T>{

    private List<_Observer<T>>  observers = new ArrayList<>();

    public void regist(_Observer<T> observer){
        if (!observers.contains(observer)){
            observers.add(observer);
        }
    }

    public void unRegist(_Observer<T> observer){
        if (!observers.contains(observer)){
            observers.remove(observer);
        }
    }

    public void notfyObserver(T t){
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).onUpdate(t);
        }
    }

}
