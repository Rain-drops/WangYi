package com.sgj.wangyi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.sgj.wangyi.model.imageextra.PhotoSet;
import com.sgj.wangyi.model.imageextra.photos;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/5/20.
 */
public class PhotoSetActivity extends BaseActivity {

    private static final String TAG = "PhotoSetActivity";
    private Context mContext;


    @Bind(R.id.vp_photoset)
    ViewPager mViewPager;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoset);
        mContext = this;
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        Intent intent = getIntent();
//        PhotoSet photoSet = (PhotoSet) intent.getSerializableExtra("photoSet");
        PhotoSet photoSet = new PhotoSet();
        ArrayList<photos> arrayList = new ArrayList<>();
        photos photo = new photos();
        photo.setTimgurl("http://img4.cache.netease.com/photo/0001/2016-05-04/c_BM75ETHD00AP0001.jpg");
        photos photo1 = new photos();
        photo1.setTimgurl("http://img3.cache.netease.com/photo/0001/2016-05-04/t_BM75ETHD00AP0001.jpg");
        photos photo2 = new photos();
        photo2.setTimgurl("http://img4.cache.netease.com/photo/0001/2016-05-04/s_BM75ETHD00AP0001.jpg");
        photos photo3 = new photos();
        photo3.setTimgurl("http://img4.cache.netease.com/photo/0001/2016-05-04/s_BM75ETHD00AP0001.jpg");
        arrayList.add(photo);
        arrayList.add(photo1);
        arrayList.add(photo2);
        arrayList.add(photo3);
        photoSet.setPhotos(arrayList);

        PhotoSetViewPagerAdapter adapter = new PhotoSetViewPagerAdapter(mContext, photoSet);
        mViewPager.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
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

    private static class PhotoSetViewPagerAdapter extends PagerAdapter{

        Context mContext;
        PhotoSet mDatas;

        ImageView imageView;
        List<ImageView> mImageViews;

        ViewGroup.LayoutParams params;

        public PhotoSetViewPagerAdapter(Context context, PhotoSet photoSet) {

            this.mContext = context;
            this.mDatas = photoSet;

            mImageViews = new ArrayList<>();
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            for(int i= 0; i< photoSet.getPhotos().size(); i++){
                ImageView imageView = new ImageView(mContext);
                imageView.setLayoutParams(params);
                mImageViews.add(imageView);
            }

        }

        @Override
        public int getCount() {
            return mDatas.getPhotos().size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            imageView = mImageViews.get(position);
            Glide.with(mContext).load(mDatas.getPhotos()
                    .get(position).getTimgurl()).centerCrop().into(imageView);
            ((ViewPager)container).addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            imageView = mImageViews.get(position);
            ((ViewPager)container).removeView(imageView);

        }
    }

}
