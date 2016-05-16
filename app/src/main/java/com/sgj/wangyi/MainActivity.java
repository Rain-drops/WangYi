package com.sgj.wangyi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.sgj.wangyi.fragment.DetailFragment;
import com.sgj.wangyi.fragment.FragmentMine;
import com.sgj.wangyi.fragment.FragmentNews;
import com.sgj.wangyi.fragment.FragmentTest;
import com.sgj.wangyi.fragment.FragmentTest2;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //定义数组来存放Fragment界面
    private Class mFragmentArray[] = {FragmentNews.class,FragmentNews.class,FragmentNews.class,FragmentNews.class,FragmentNews.class};

    private String hideTag;
    public static final String MINE_TAG = "mine_tag";
    public static final String NEWS_TAG = "news_tag";
    public static final String READ_TAG = "read_tag";
    public static final String VEDIO_TAG = "vedio_tag";
    public static final String TOPIC_TAG = "topic_tag";



    FragmentManager mFragmentManager ;
    FragmentTransaction mTransaction;

    @Bind(R.id.ll_mine)
    LinearLayout ll_mine;
    @Bind(R.id.ll_news)
    LinearLayout ll_news;
    @Bind(R.id.ll_read)
    LinearLayout ll_read;
    @Bind(R.id.ll_vedio)
    LinearLayout ll_vedio;
    @Bind(R.id.ll_topic)
    LinearLayout ll_topic;

    @Bind(R.id.iv_mine)
    ImageView iv_mine;
    @Bind(R.id.iv_news)
    ImageView iv_news;
    @Bind(R.id.iv_read)
    ImageView iv_read;
    @Bind(R.id.iv_vedio)
    ImageView iv_vedio;
    @Bind(R.id.iv_topic)
    ImageView iv_topic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment f = mFragmentManager.findFragmentByTag(MINE_TAG);
        /*然后在碎片中调用重写的onActivityResult方法*/
        f.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void init() {

        ll_mine.setOnClickListener(this);
        ll_news.setOnClickListener(this);
        ll_read.setOnClickListener(this);
        ll_vedio.setOnClickListener(this);
        ll_topic.setOnClickListener(this);

        switchFragment(FragmentNews.newInstance(), NEWS_TAG);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ll_mine:
                switchFragment(FragmentMine.newInstance(), MINE_TAG);
                break;
            case R.id.ll_news:
                switchFragment(FragmentNews.newInstance(), NEWS_TAG);
                break;
            case R.id.ll_read:
                switchFragment(FragmentTest.newInstance(), READ_TAG);
                break;
            case R.id.ll_vedio:
                switchFragment(FragmentTest2.newInstance(), VEDIO_TAG);
                break;
            case R.id.ll_topic:
                switchFragment(FragmentTest.newInstance(), TOPIC_TAG);
                break;
        }
    }

    private void switchFragment(Fragment fragment, String tag){

        if(hideTag == tag){
            return;
        }

        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        Fragment tagFragment = mFragmentManager.findFragmentByTag(tag);
        if(tagFragment == null){
            mTransaction.add(R.id.fl_main_content, fragment, tag);
        }else {
            mTransaction.show(tagFragment);
        }

        tagFragment = mFragmentManager.findFragmentByTag(hideTag);
        if(tagFragment != null){
            mTransaction.hide(tagFragment);

            switch (hideTag){
                case MINE_TAG:
                    iv_mine.setImageResource(R.drawable.biz_navigation_tab_pc);
                    break;
                case NEWS_TAG:
                    iv_news.setImageResource(R.drawable.biz_navigation_tab_news);
                    break;
                case READ_TAG:
                    iv_read.setImageResource(R.drawable.biz_navigation_tab_read);
                    break;
                case VEDIO_TAG:
                    iv_vedio.setImageResource(R.drawable.biz_navigation_tab_va);
                    break;
                case TOPIC_TAG:
                    iv_topic.setImageResource(R.drawable.biz_navigation_tab_topic);
                    break;
                default:
                    break;
            }
        }

        switch (tag){
            case MINE_TAG:
                iv_mine.setImageResource(R.drawable.biz_navigation_tab_pc_selected);
                break;
            case NEWS_TAG:
                iv_news.setImageResource(R.drawable.biz_navigation_tab_news_selected);
                break;
            case READ_TAG:
                iv_read.setImageResource(R.drawable.biz_navigation_tab_read_selected);
                break;
            case VEDIO_TAG:
                iv_vedio.setImageResource(R.drawable.biz_navigation_tab_va_selected);
                break;
            case TOPIC_TAG:
                iv_topic.setImageResource(R.drawable.biz_navigation_tab_topic_selected);
                break;
        }

        hideTag = tag;



        mTransaction.commit();

    }

}
