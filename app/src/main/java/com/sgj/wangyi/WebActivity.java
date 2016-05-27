package com.sgj.wangyi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Method;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/5/13.
 */
public class WebActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "WebActivity";
    private Context mContext;

    public static final String URL_3W = "url_3w";
    public static final String URL = "url";

    String mUrl, mUrl_3w;

    @Bind(R.id.webview)
    WebView mWebView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.et_reply)
    EditText et_reply;
    String et_reply_str;
    boolean et_reply_focus = false;
    @Bind(R.id.tv_reply_count)
    TextView tv_reply_count;
    @Bind(R.id.iv_share)
    ImageView iv_share;
    @Bind(R.id.tv_send)
    TextView tv_send;

    @Bind(R.id.view_hide)
    View view_hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mContext = this;
        ButterKnife.bind(this);

        init();
    }

    private void init() {

        view_hide.setOnClickListener(this);
        mToolbar.setBackgroundColor(getResources().getColor(R.color.navigationTitle2));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        CharSequence sequence = "网易新闻";
//        getSupportActionBar().setTitle(sequence);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


//        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.night_base_action_bar_back));


        Intent intent = getIntent();
        mUrl = intent.getStringExtra(URL);
        mUrl_3w = intent.getStringExtra(URL_3W);

        WebSettings mSettings = mWebView.getSettings();

        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new MyWebClient());

        mWebView.loadUrl(mUrl_3w);

        et_reply.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    tv_reply_count.setVisibility(View.GONE);
                    iv_share.setVisibility(View.GONE);
                    tv_send.setVisibility(View.VISIBLE);
                    et_reply_focus = true;
                } else {
                    if(!et_reply.getText().toString().isEmpty()){
                    }else {

                        tv_reply_count.setVisibility(View.VISIBLE);
                        iv_share.setVisibility(View.VISIBLE);
                        tv_send.setVisibility(View.GONE);
                    }
                    et_reply_focus = false;
                }
            }
        });



    }

    @Override
    protected void onPause() {
        if(mWebView != null){
            mWebView.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWebView != null) mWebView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

        if(mWebView != null){
            mWebView.destroy();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setIconEnable(menu, true);
        getMenuInflater().inflate(R.menu.menu_news_content, menu);
        return true;
    }

    private void setIconEnable(Menu menu, boolean enable)
    {
        try
        {
            Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);
            //下面传入参数
            m.invoke(menu, enable);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e(TAG, view_hide.isShown()+ "");
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(et_reply_focus){
                et_reply.clearFocus();
                return true;
            }


        }

        return super.onKeyDown(keyCode, event);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    private class MyChromeClient extends WebChromeClient{

        public MyChromeClient() {
            super();
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }
    }

    private class MyWebClient extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url != null){
                view.loadUrl(url);
            }
            return true;
        }
    }

}
