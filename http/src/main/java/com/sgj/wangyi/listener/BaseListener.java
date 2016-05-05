package com.sgj.wangyi.listener;

/**
 * Created by John on 2016/5/4.
 */
public abstract class BaseListener<T> {
    public abstract void onError(Exception e);
    public abstract void onSuccess(T result);
    public abstract void onStringResult(String result);
}
