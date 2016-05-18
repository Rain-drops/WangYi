package com.sgj.wangyi;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sgj.wangyi.util.ScreenUtil;

/**
 * Created by John on 2016/4/20.
 */
public class BaseApplication extends Application {

    public static int width = 0;
    public static int height = 0;
    public static float density = 0;

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

        width = ScreenUtil.getWidth(this);
        height = ScreenUtil.getHeight(this);
        density = ScreenUtil.getDensity(this);
    }

}
