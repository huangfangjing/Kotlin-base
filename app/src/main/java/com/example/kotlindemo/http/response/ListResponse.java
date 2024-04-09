package com.example.kotlindemo.http.response;


import java.util.List;

/**
 * Created by hfj on 2018/5/23
 */
public class ListResponse<T> {
    public int total;
    public List<T> list;
}
