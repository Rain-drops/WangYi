package com.sgj.wangyi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sgj.wangyi.BaseApplication;
import com.sgj.wangyi.R;
import com.sgj.wangyi.fragment.FragmentList;
import com.sgj.wangyi.fragment.FragmentTest;
import com.sgj.wangyi.fragment.FragmentTest2;
import com.sgj.wangyi.model.NewsTouTiaoModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by John on 2016/5/5.
 */
public class NewsPageAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "NewsPageAdapter";

    List<NewsTouTiaoModel> mNewsTouTiaoList;
    List<Fragment> mFragments = new ArrayList<Fragment>();

    public NewsPageAdapter(FragmentManager fm,  String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            return FragmentList.newInsance();
        }

        if(position%2 == 0){
            return FragmentTest.newInstance(new NewsTouTiaoModel());
        }else {
            return FragmentTest2.newInstance(new NewsTouTiaoModel());
        }

//        switch (position){
//            case 0:
//                return FragmentTest.newInstance(new NewsTouTiaoModel());
//            case 1:
//                return FragmentTest.newInstance(new NewsTouTiaoModel());
//            case 2:
//                return FragmentTest.newInstance(new NewsTouTiaoModel());
//        }
//        return FragmentTest.newInstance(new NewsTouTiaoModel());
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment=null;
        try {
            fragment=(Fragment)super.instantiateItem(container,position);
        }catch (Exception e){

        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];
    }

    private String[] titles;
    private LayoutInflater mInflater;

    /**
     * 添加getTabView的方法，来进行自定义Tab的布局View
     * @param position
     * @return
     */
    public View getTabView(int position){
        mInflater=LayoutInflater.from(BaseApplication.getInstance());
        View view=mInflater.inflate(R.layout.news_tab_item_layout,null);
        TextView tv= (TextView) view.findViewById(R.id.textView);
        tv.setText(titles[position]);
        return view;
    }
}
