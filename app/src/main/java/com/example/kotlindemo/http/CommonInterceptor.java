package com.example.kotlindemo.http;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.kotlindemo.MyApplication;
import com.example.kotlindemo.model.constant.ParamsKey;
import com.example.kotlindemo.model.entity.SharedPrefeInfo;
import com.example.kotlindemo.utils.AspLog;
import com.example.kotlindemo.utils.DeviceUtils;
import com.example.kotlindemo.utils.Encryption;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;


/**
 * Created by hfj on 2018/6/6
 */

public class CommonInterceptor implements Interceptor {

    private static final String TAG = "CommonInterceptor";

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private Context mContext;

    public CommonInterceptor(Context context) {
        this.mContext = context;
        gsonBuilder.disableHtmlEscaping();
        gson = gsonBuilder.create();
    }

    @Override
    public Response intercept(Chain chain) {

        Request request = chain.request();

        String requestBodyString = null;

        //TODO 后台方法路径
        String methodPath = null;

        try {
            String method = request.method();
            //拦截的请求的路径
            String oldUrl = request.url().toString();

            RequestBody requestBody = request.body();

            Map<String, String> commomParamsMap = new HashMap<>();

            if (method.equals("GET")) {

                //暂不处理
                Toast.makeText(mContext, "get请求", Toast.LENGTH_SHORT).show();

            } else if (method.equals("POST")) {

                if (requestBody instanceof FormBody) {
                    //前边是不是后边的子类/实例对象
                    FormBody oldBody = (FormBody) requestBody;//keywords...page

                    FormBody.Builder builder = new FormBody.Builder();

                    //先添加之前的参数
                    for (int i = 0; i < oldBody.size(); i++) {
                        //键值对的形式添加
                        builder.add(oldBody.name(i), oldBody.value(i));
                    }
                    //添加公共参数
                    for (Map.Entry<String, String> entry : commomParamsMap.entrySet()) {
                        builder.add(entry.getKey(), entry.getValue());
                    }

                    //构建一个新的请求
                    request = request.newBuilder().url(oldUrl).post(builder.build()).build();

                } else {

                    Buffer buffer = new Buffer();

                    requestBody.writeTo(buffer);

                    String oldJsonParams = buffer.readUtf8();

                    if (!TextUtils.isEmpty(oldJsonParams)) {

                        methodPath = oldUrl.replaceFirst("http:\\/\\/[^\\/]*\\/", "");
                        methodPath = "/" + methodPath;
                        AspLog.e(TAG, "发送请求 methodPath--->" + methodPath);
                        JSONObject jsonObject = new JSONObject(oldJsonParams);

                        jsonObject.put("method", methodPath);//公共参数~
                        jsonObject.put("userId", SharedPrefeInfo.getInstance().getUserId());//公共参数~


                        JSONObject deviceInfo = new JSONObject();

                        //只有同意隐私政策才加载
                        deviceInfo.put("channel", MyApplication.getInstance().getChannelName());
                        deviceInfo.put("os", "ANDROID");//公共参数~
                        deviceInfo.put("imei", DeviceUtils.getIMEI(mContext));//公共参数~
                        deviceInfo.put("product", DeviceUtils.getDeviceBrand());//公共参数~
                        deviceInfo.put("mac", DeviceUtils.getMacAddress(mContext));//公共参数~
                        deviceInfo.put("osVersion", DeviceUtils.getOS());//公共参数~
                        deviceInfo.put("model", DeviceUtils.getPhoneModel());//公共参数~
                        deviceInfo.put("netType", DeviceUtils.getNetMode(mContext));//公共参数~
                        deviceInfo.put("clientVersion", DeviceUtils.getAppVersionName(mContext));//公共参数~
                        deviceInfo.put("clientVersionCode", DeviceUtils.getVersionCode(mContext));//公共参数~

                        jsonObject.put("deviceInfo", deviceInfo);

                        oldJsonParams = jsonObject.toString();
                        String ip = ParamsKey.FIRST_ADDRESS;
                        String newUrl = ip + "base/intoApi";

                        HashMap<String, Object> resultMap = new HashMap<>();

                        //防止特殊字符串,URLEncoder一下
                        resultMap.put("requestMsg", URLEncoder.encode(Encryption.encryption(oldJsonParams)));

                        requestBodyString = gson.toJson(resultMap);

                        Request.Builder builder = request.newBuilder().url(newUrl).post(RequestBody.create(JSON, oldJsonParams));
                        String token = SharedPrefeInfo.getInstance().getToken();
                        if (token != null) {
                            //请求头增加token参数
                            builder.addHeader("token", token);
                        }
                        request = builder.build();

                        AspLog.e(TAG, "发送请求 url--->" + request.url() + "   methodPath-->" + methodPath + "  json--->" + oldJsonParams);
                    }
                }
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Response response = null;
        try {
            response = chain.proceed(request);
            final Charset UTF8 = Charset.forName("UTF-8");
            ResponseBody responseBody = response.body();
            String rBody = null;
            if (responseBody != null) {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();

                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    try {
                        charset = contentType.charset(UTF8);
                    } catch (UnsupportedCharsetException e) {
                        e.printStackTrace();
                    }
                }
                rBody = buffer.clone().readString(charset);
            }

            AspLog.e(TAG, "收到响应  methodPath-->" + methodPath + "   json--->" + rBody);

        } catch (IOException e) {
            AspLog.e(TAG, "请求失败  url -->" + request.url() + "    methodPath-->" + methodPath + "    json--->" + requestBodyString);
            e.printStackTrace();
        }
        return response;
    }


    private GsonBuilder gsonBuilder = new GsonBuilder()
            .registerTypeAdapter(
                    new TypeToken<TreeMap<String, Object>>() {
                    }.getType(),
                    new JsonDeserializer<TreeMap<String, Object>>() {
                        @Override
                        public TreeMap<String, Object> deserialize(
                                JsonElement json, Type typeOfT,
                                JsonDeserializationContext context) throws JsonParseException {

                            TreeMap<String, Object> treeMap = new TreeMap<>();
                            JsonObject jsonObject = json.getAsJsonObject();
                            Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                            for (Map.Entry<String, JsonElement> entry : entrySet) {
                                Object ot = entry.getValue();
                                if (ot instanceof JsonPrimitive) {
                                    treeMap.put(entry.getKey(), ((JsonPrimitive) ot).getAsString());
                                } else {
                                    treeMap.put(entry.getKey(), ot);
                                }
                            }
                            return treeMap;
                        }
                    });

    private Gson gson;

}
