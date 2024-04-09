package com.example.kotlindemo.http.response;


/**
 * Created by hfj on 2018/10/24
 */
public class BaseResponse<T> {
    public boolean state;
    public T data;
    public String message;

}
