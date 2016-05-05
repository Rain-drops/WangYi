package com.sgj.wangyi.http;

import com.sgj.wangyi.listener.CallBackListener;

import java.util.Map;

/**
 * Created by John on 2016/5/4.
 */
public class Http extends BaseHttp {

    public static void get(String url, CallBackListener<?> listener){
        getInstance().baseGet(url, listener);
    }

    public static void post(String url, Map<String, String> params,CallBackListener<?> listener){
        getInstance().basePost(url, params, listener);
    }

    public static void post(String url, CallBackListener<?> listener){
        getInstance().basePost(url, null, listener);
    }

    public static void cancel(String url){
        getInstance().getmOkHttpClient().cancel(url);
    }
}
