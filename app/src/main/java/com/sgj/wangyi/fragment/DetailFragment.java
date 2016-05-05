package com.sgj.wangyi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.sgj.wangyi.R;

/**
 * Created by John on 2016/5/4.
 */
public class DetailFragment extends Fragment{

    private String mTextviewArray[] = {"新闻", "阅读", "视频", "话题", "我"};
    private int mImageViewArray[] = {R.drawable.biz_navigation_tab_news_selector,
            R.drawable.biz_navigation_tab_read_selector,
            R.drawable.biz_navigation_tab_va_selector,
            R.drawable.biz_navigation_tab_topic_selector,
            R.drawable.biz_navigation_tab_pc_selector};
    //定义数组来存放Fragment界面
    private Class mFragmentArray[] = {FragmentNews.class,FragmentNews.class,FragmentNews.class,FragmentNews.class,FragmentNews.class};

    public static DetailFragment newInstance(){
        DetailFragment detailFragment = new DetailFragment();
        return detailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);

        FragmentTabHost mTabHost = (FragmentTabHost) view.findViewById(R.id.fth_tabhost);
        mTabHost.setup(getActivity(), getActivity().getSupportFragmentManager(), R.id.fl_tabcontent);

        int count = mFragmentArray.length;
        for(int i=0;i<count;i++){
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
            //设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.colorAccent);
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }
    private View getTabItemView(int index) {
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View view = mInflater.inflate(R.layout.navigation_item_layout, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv_naviga);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.tv_naviga);
        textView.setText(mTextviewArray[index]);

        return view;
    }
}
