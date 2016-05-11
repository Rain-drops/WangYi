package com.sgj.wangyi;

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

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //定义数组来存放Fragment界面
    private Class mFragmentArray[] = {FragmentNews.class,FragmentNews.class,FragmentNews.class,FragmentNews.class,FragmentNews.class};

    private String hideTag;
    public static final String MINE_TAG = "mine_tag";
    public static final String NEWS_TAG = "news_tag";


    FragmentManager mFragmentManager ;
    FragmentTransaction mTransaction;

    @Bind(R.id.ll_mine)
    LinearLayout ll_mine;
    @Bind(R.id.ll_news)
    LinearLayout ll_news;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void init() {

        ll_mine.setOnClickListener(this);
        ll_news.setOnClickListener(this);



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
        }

        hideTag = tag;

        mTransaction.commit();


    }
}
