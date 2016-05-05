package com.sgj.wangyi.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sgj.wangyi.R;
import com.sgj.wangyi.adapter.NewsPageAdapter;

import java.util.ArrayList;

/**
 * Created by John on 2016/4/21.
 */
public class FragmentNews extends Fragment implements View.OnClickListener{

    private ArrayList<Fragment> mFragments;

    private HorizontalScrollView mHorizontalScrollView ;
    private LinearLayout mLinearLayout;

    private ViewPager mViewPager;
    private ImageView mImageView;

    private int mScreenWidth;
    private int item_width;

    private int endPosition;
    private int beginPosition;
    private int currentFragmentIndex;
    private boolean isEnd;

    public static FragmentNews newInstance(){
        FragmentNews fragmentNews = new FragmentNews();
        return fragmentNews;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        init(view);
        initNav();
        initViewPager();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        NewsPageAdapter adapter = new NewsPageAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);

    }

    private void init(View view) {
        mLinearLayout = (LinearLayout) view.findViewById(R.id.hsv_content);
        mHorizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.hsv_view);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_news);
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;

        mImageView = (ImageView) view.findViewById(R.id.img1);
        item_width = (int) ((mScreenWidth / 4.0 + 0.5f));
        mImageView.getLayoutParams().width = item_width;
    }

    private void initNav() {
        for (int i = 0 ; i < 7 ; i++) {
            RelativeLayout layout = new RelativeLayout(getActivity());
            TextView view = new TextView(getActivity());
            view.setText("第"+(i+1)+"个");
            RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            layout.addView(view, params);
            mLinearLayout.addView(layout, (int)(mScreenWidth/4 + 0.5f), 50);
            layout.setOnClickListener(this);
            layout.setTag(i);
        }

    }

    private void initViewPager() {

        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {

    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(final int position) {
            Animation animation = new TranslateAnimation(endPosition, position* item_width, 0, 0);

            beginPosition = position * item_width;

            currentFragmentIndex = position;
            if (animation != null) {
                animation.setFillAfter(true);
                animation.setDuration(0);
                mImageView.startAnimation(animation);
                mHorizontalScrollView.smoothScrollTo((currentFragmentIndex - 1) * item_width , 0);
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            if(!isEnd){
                if(currentFragmentIndex == position){
                    endPosition = item_width * currentFragmentIndex +
                            (int)(item_width * positionOffset);
                }
                if(currentFragmentIndex == position+1){
                    endPosition = item_width * currentFragmentIndex -
                            (int)(item_width * (1-positionOffset));
                }

                Animation mAnimation = new TranslateAnimation(beginPosition, endPosition, 0, 0);
                mAnimation.setFillAfter(true);
                mAnimation.setDuration(0);
                mImageView.startAnimation(mAnimation);
                mHorizontalScrollView.invalidate();
                beginPosition = endPosition;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                isEnd = false;
            } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
                isEnd = true;
                beginPosition = currentFragmentIndex * item_width;
                if (mViewPager.getCurrentItem() == currentFragmentIndex) {
                    // 未跳入下一个页面
                    mImageView.clearAnimation();
                    Animation animation = null;
                    // 恢复位置
                    animation = new TranslateAnimation(endPosition, currentFragmentIndex * item_width, 0, 0);
                    animation.setFillAfter(true);
                    animation.setDuration(1);
                    mImageView.startAnimation(animation);
                    mHorizontalScrollView.invalidate();
                    endPosition = currentFragmentIndex * item_width;
                }
            }
        }

    }
}
