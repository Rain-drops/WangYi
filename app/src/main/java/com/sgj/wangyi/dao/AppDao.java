package com.sgj.wangyi.dao;

import com.sgj.wangyi.http.Http;
import com.sgj.wangyi.listener.CallBackListener;
import com.sgj.wangyi.model.TouTiaoModel;
import com.sgj.wangyi.url.HttpUrl;


/**
 * Created by John on 2016/5/9.
 */
public class AppDao {
    private static AppDao instance;

    public static AppDao getInstance() {
        if (instance == null) {
            instance = new AppDao();
        }
        return instance;
    }

    private AppDao() {
    }

    /**
     * 获取
     * @param listener
     */
    public void getAllTouTiao(CallBackListener<TouTiaoModel> listener, int pages)
    {
        Http.post(HttpUrl.TOUTIAO_URL + pages + ".html", null, listener);
    }
}
