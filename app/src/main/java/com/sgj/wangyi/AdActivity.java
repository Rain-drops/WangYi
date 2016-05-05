package com.sgj.wangyi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by John on 2016/4/20.
 */
public class AdActivity extends AppCompatActivity implements Runnable{

    private static final int START_MAIN_ACTIVITY = 0x10;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        mContext = this;

        new Thread(this).start();
    }
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);

            switch (msg.what) {
                case START_MAIN_ACTIVITY:
                    startActivity(new Intent(AdActivity.this, MainActivity.class));

                    AdActivity.this.finish();
                    break;

                default:
                    break;
            }
        }
    };

    public void run() {
        try {
            // 3秒后跳转到登录界面
            Thread.sleep(3000);

            Message message = Message.obtain();
            message.what = START_MAIN_ACTIVITY;
            handler.sendMessage(message);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
