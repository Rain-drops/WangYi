package com.sgj.wangyi;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by John on 2016/4/20.
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;
    public BaseApplication() {
    }

    public static BaseApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        Fresco.initialize(this);
    }

}
