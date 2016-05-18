package com.sgj.wangyi.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sgj.wangyi.R;
import com.sgj.wangyi.adapter.NewsPageAdapter;
import com.sgj.wangyi.model.NewsTouTiaoModel;
import com.sgj.wangyi.model.TouTiaoModel;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by John on 2016/4/21.
 */
public class FragmentNews extends Fragment implements View.OnClickListener{

    private ArrayList<Fragment> mFragments;

    LinearLayout frameLayout;

    private HorizontalScrollView mHorizontalScrollView ;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ImageView iv_region;

    private NewsPageAdapter mPagerAdater;


    private Animation animEnter = null;
    private Animation animExit = null;
    private boolean isShow = false;

    public static FragmentNews newInstance(){
        FragmentNews fragmentNews = new FragmentNews();
        return fragmentNews;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animEnter = AnimationUtils.loadAnimation(getActivity(), R.anim.vertical_enter);
        animExit = AnimationUtils.loadAnimation(getActivity(), R.anim.vertical_exit);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            regionClickListener = (RegionClickListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement IndexListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.vp_news);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabs);
        iv_region = (ImageView) view.findViewById(R.id.iv_region);
        iv_region.setOnClickListener(this);

        frameLayout = (LinearLayout) view.findViewById(R.id.fl_region);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupViewPager(mViewPager);
        getAvdData();
    }

    private void getAvdData() {

    }

    private void setupViewPager(ViewPager viewPager) {
        String[] titles = getActivity().getResources().getStringArray(R.array.news_title);
        NewsPageAdapter adapter = new NewsPageAdapter(getChildFragmentManager(), titles);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);

        mTabLayout.setupWithViewPager(viewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        for(int i=0;i<titles.length;i++){
//            TabLayout.Tab tab = mTabLayout.getTabAt(i);
//            tab.setCustomView(adapter.getTabView(i));
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_region:
                if (!isShow) {
                    performAnimateOn();

                } else {
                    performAnimateOff();
                }
                regionClickListener.regionClick(isShow);
                break;
        }
    }
    private void performAnimateOn() {
        frameLayout.setAnimation(animEnter);
        animEnter.start();
        frameLayout.setVisibility(View.VISIBLE);
        isShow = true;
    }

    private void performAnimateOff() {
        frameLayout.setAnimation(animExit);
        animExit.start();
        frameLayout.setVisibility(View.GONE);
        isShow = false;
    }

    RegionClickListener regionClickListener = null;
    public interface RegionClickListener {
        void regionClick(boolean isShow);
    }
    public void setRegionClickListener(RegionClickListener listener){
        regionClickListener = listener;
    }
}
