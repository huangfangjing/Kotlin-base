package com.example.kotlindemo.http;

import android.content.Context;

import com.example.kotlindemo.model.constant.ParamsKey;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hfj on 2018/5/24
 */
public class RetrofitHelper<T> {

    private Context mCntext;
    private OkHttpClient client = null;
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;

    public static RetrofitHelper getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitHelper(context);
        }
        return instance;
    }

    private RetrofitHelper(Context mContext) {
        mCntext = mContext;
        init();
    }

    private RetrofitHelper(Context mContext, String ip) {
        mCntext = mContext;
        init(ip);
    }

    private void init() {
        String ip = ParamsKey.FIRST_ADDRESS;
        httmInit(ip);
    }

    private void init(String ip) {
        httmInit(ip);
    }

    private void httmInit(String ip) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(30 * 1000, TimeUnit.MILLISECONDS);
        builder.addInterceptor(new CommonInterceptor(mCntext));
        client = builder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(ip)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public RetrofitHelper reSetBaseUrl() {
        instance = new RetrofitHelper(mCntext);
        return instance;
    }

    public RetrofitHelper reSetBaseUrl(String ip) {
        instance = new RetrofitHelper(mCntext, ip);
        return instance;
    }

    public T privideServer(Class<T> service) {

        return mRetrofit.create(service);
    }

}
