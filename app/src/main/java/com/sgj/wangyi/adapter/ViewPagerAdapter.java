package com.sgj.wangyi.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.sgj.wangyi.model.TouTiaoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2016/5/11.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<TouTiaoModel.TouTiao.myAds> mDatas;

    private ImageView mImageView;
    private List<ImageView> mViews;

    ViewGroup.LayoutParams params;
    public ViewPagerAdapter(Context mContext, ArrayList<TouTiaoModel.TouTiao.myAds> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;

        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mViews = new ArrayList<>();
        for(int i = 0; i < mDatas.size(); i++){
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(params);
            mViews.add(imageView);
        }
    }

    @Override
    public int getCount() {
        return (mViews != null ? mViews.size() : 0 );
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        mImageView = mViews.get(position);
        Glide.with(mContext).load(mDatas.get(position).imgsrc).centerCrop().into(mImageView);
        ((ViewPager)container).addView(mImageView);
        return mImageView;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        mImageView = mViews.get(position);
        ((ViewPager)container).removeView(mImageView);
    }


}
