package com.example.kotlindemo.design.obser;

public class TV {

    public static void main(String[] args) {

        _Obserable<Weather> obserable = new _Obserable<>();

        _Observer<Weather> observer1 = data -> System.out.println("观察者1：" + data.getAction());
        _Observer<Weather> observer2 = data -> System.out.println("观察者2：" + data.getAction());
        obserable.regist(observer1);
        obserable.regist(observer2);

        Weather weather = new Weather("晴转多云");
        obserable.notfyObserver(weather);

        Weather weather2 = new Weather("多云转阴");
        obserable.notfyObserver(weather2);

    }
}
