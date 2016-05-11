package com.sgj.wangyi.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sgj.wangyi.R;
import com.sgj.wangyi.model.TouTiaoModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/5/11.
 */
public class TouTiaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "TouTiaoAdapter";
    private Context mContext;
    private ArrayList<TouTiaoModel.TouTiao> mDatas;

    public static final int SKIP_TYPE_SPECIAL = 0;
    public static final int SKIP_TYPE_PHOTOSET = 1;
    public static final int SKIP_TYPE_XXX = 2;
    public static final int HAS_HEAD = 3;


    public TouTiaoAdapter(Context mContext, ArrayList<TouTiaoModel.TouTiao> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    public void updateData(ArrayList<TouTiaoModel.TouTiao> datas){
        this.mDatas = datas;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        if(viewType == HAS_HEAD){
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_hashead, null);
            return new HeadViewHolder(view);
        }

        if(viewType == SKIP_TYPE_SPECIAL){
            view = LayoutInflater.from(mContext).inflate(R.layout.news_toutiao_list_item, null);
            return new TypeOneViewHolder(view);
        }else if(viewType == SKIP_TYPE_PHOTOSET){
            view = LayoutInflater.from(mContext).inflate(R.layout.toutiao_list_item_photoset, null);
            return new TypeTwoViewHolder(view);
        }else {
            view = LayoutInflater.from(mContext).inflate(R.layout.news_toutiao_list_item, null);
            return new TypeOneViewHolder(view);
        }
    }

    TouTiaoModel.TouTiao model2;
    HeadViewHolder viewHolder1;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TouTiaoModel.TouTiao model = mDatas.get(position);
        Log.e(TAG, model.toString());

        if(holder instanceof HeadViewHolder){
            model2 = mDatas.get(position);
            viewHolder1 = (HeadViewHolder)holder;
            ViewPagerAdapter adapter = new ViewPagerAdapter(mContext, model.ads);

            ((HeadViewHolder)holder).vp_head.setAdapter(adapter);
            ((HeadViewHolder)holder).tv_title.setText(model2.ads.get(position).title);


            return;
        }

        if(holder instanceof TypeOneViewHolder){

            if(model.imgsrc != null){
                Glide.with(mContext).load(model.imgsrc).centerCrop().into(((TypeOneViewHolder)holder).iv_cover);
            }
            if(model.title != null){
                ((TypeOneViewHolder)holder).tv_title.setText(model.title);
            }
            if(model.tags != null){
                ((TypeOneViewHolder)holder).tv_tname.setText(model.tags);
            }else if(model.source != null){
                ((TypeOneViewHolder)holder).tv_tname.setText(model.source);
            }
            if(model.replyCount != null){
                ((TypeOneViewHolder)holder).tv_reply_count.setText(model.replyCount + "跟帖");
            }
        }else if (holder instanceof TypeTwoViewHolder){
            if(model.imgextra != null){
                for(int i=0; i<model.imgextra.size(); i++){
                    if(i==0){
                        Glide.with(mContext).load(model.imgextra.get(i).imgsrc).centerCrop().into(((TypeTwoViewHolder) holder).iv_photo_one);
                    }
                    if(i==1){
                        Glide.with(mContext).load(model.imgextra.get(i).imgsrc).centerCrop().into(((TypeTwoViewHolder) holder).iv_photo_two);
                    }
                }

            }
            if(model.imgsrc != null){
                Glide.with(mContext).load(model.imgsrc).centerCrop().into(((TypeTwoViewHolder)holder).iv_photo_three);
            }
            if(model.title != null){
                ((TypeTwoViewHolder)holder).tv_title.setText(model.title);
            }
        }
    }

    @Override
    public int getItemCount() {
        return (mDatas == null ? 0 : mDatas.size());
    }

    @Override
    public int getItemViewType(int position) {
        if(1 ==  mDatas.get(position).hasHead){
            return HAS_HEAD;
        }
        if(null == mDatas.get(position).skipType){
            return SKIP_TYPE_SPECIAL;
        }
        if(mDatas.get(position).skipType.equals("special")){
            return SKIP_TYPE_SPECIAL;
        }else if(mDatas.get(position).skipType.equals("photoset")){
            return SKIP_TYPE_PHOTOSET;
        }else {
            return super.getItemViewType(position);
        }
    }

    public class TypeOneViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.iv_cover)
        ImageView iv_cover;
        @Bind(R.id.tv_title)
        TextView tv_title;
        @Bind(R.id.tv_tname)
        TextView tv_tname;
        @Bind(R.id.tv_reply_count)
        TextView tv_reply_count;

        public TypeOneViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class TypeTwoViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.iv_photo_one)
        ImageView iv_photo_one;
        @Bind(R.id.iv_photo_two)
        ImageView iv_photo_two;
        @Bind(R.id.iv_photo_three)
        ImageView iv_photo_three;
        @Bind(R.id.tv_title)
        TextView tv_title;

        @Bind(R.id.tv_tname)
        TextView tv_tname;
        @Bind(R.id.tv_reply_count)
        TextView tv_reply_count;

        public TypeTwoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class TypeThreeViewHolder extends RecyclerView.ViewHolder{

        public TypeThreeViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class HeadViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.vp_head)
        ViewPager vp_head;
        @Bind(R.id.tv_title)
        TextView tv_title;

        public HeadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            vp_head.addOnPageChangeListener(new ViewPageChangeListener());
        }
    }

    private class ViewPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        //监听页面改变事件来改变viewIndicator中的指示图片
        @Override
        public void onPageSelected(int position) {
            viewHolder1.tv_title.setText(model2.ads.get(position).title);

        }

    }
}
