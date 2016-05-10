package com.sgj.wangyi.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import com.sgj.wangyi.listener.CallBackListener;
import com.sgj.wangyi.util.GenericsUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Map;
import java.util.Set;


/**
 * Created by John on 2016/5/4.
 */
public class BaseHttp<T> {
    private static final String TAG = "BaseHttp";
    private static BaseHttp mBaseHttp;
    private Gson mGson;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public BaseHttp() {
        this.mGson = new Gson();
        this.mOkHttpClient = new OkHttpClient();
        this.mHandler = new Handler(Looper.getMainLooper());

        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
    }
    protected static BaseHttp getInstance(){
        if(mBaseHttp == null){
            mBaseHttp = new BaseHttp();
        }
        return mBaseHttp;
    }

    protected OkHttpClient getmOkHttpClient(){
        return mOkHttpClient;
    }

    protected void baseGet(String url, CallBackListener<T> listener){
        Request request = getBaseRequest(url);
        doRequest(request, listener);
    }

    protected void basePost(String url, Map<String, String> params, CallBackListener<T> listener){
        if(params == null){
            baseGet(url, listener);
            return;
        }
        FormEncodingBuilder builder = new FormEncodingBuilder();

        Set<Map.Entry<String, String>> entrySet = params.entrySet();
        for(Map.Entry<String, String> entry : entrySet){
            builder.add(entry.getKey(), entry.getValue());
        }

        RequestBody body = builder.build();
        Request request = new Request.Builder().url(url).post(body).tag(url).build();
        doRequest(request, listener);
    }

    private void doRequest(final Request request, final CallBackListener<T> listener) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String result = response.body().string();
                if (isListenerNotNull(listener)) {
                    listener.onStringResult(result);
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Class<T> clazz = GenericsUtils.getSuperClassGenricType(listener.getClass());
                            if(clazz == String.class){
                                if(isListenerNotNull(listener)){
                                    Log.e(TAG, result + "");
                                    listener.onSuccess((T)result);

                                }
                            }else {
                                if(isListenerNotNull(listener)){
                                    Log.e(TAG, result + "");
                                    listener.onSuccess(mGson.fromJson(result, clazz));

                                }
                            }
                        }catch (Exception e){
                            Log.e(TAG, e + "");
                        }
                    }
                });
            }
        });
    }

    private boolean isListenerNotNull(CallBackListener<T> listener) {
        if(listener != null){
            return true;
        }
        return false;
    }

    private Request getBaseRequest(String url) {
        Request request = new Request.Builder().url(url).tag(url).build();
        return request;
    }
}
