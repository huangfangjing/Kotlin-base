package com.example.kotlindemo.model.entity;


import com.example.kotlindemo.http.response.ListResponse;

public interface RefreshBaseView<T> {

    void onSuccess(ListResponse<T> t);

    void onNetworkError(String result);

    void onServiceError(String result);

    void onNoData(String tip);

}
