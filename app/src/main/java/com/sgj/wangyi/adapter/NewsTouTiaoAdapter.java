package com.sgj.wangyi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sgj.wangyi.R;
import com.sgj.wangyi.model.NewsTouTiaoModel;
import com.sgj.wangyi.model.TouTiaoModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/5/9.
 */
public class NewsTouTiaoAdapter extends RecyclerView.Adapter<NewsTouTiaoAdapter.ViewHolder> {

    private static final String TAG = "NewsTouTiaoAdapter";

    private Context mContext;
    private ArrayList<TouTiaoModel.TouTiao> mDatas;

    public static final int VALUE_TIME_TIP = 0;// 7种不同的布局
    public static final int VALUE_LEFT_TEXT = 1;
    public static final int VALUE_LEFT_IMAGE = 2;

    public NewsTouTiaoAdapter(Context mContext, ArrayList<TouTiaoModel.TouTiao> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.news_toutiao_list_item, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TouTiaoModel.TouTiao model = mDatas.get(position);
        Log.e(TAG, model.toString());
        if(model.imgsrc != null){
            Glide.with(mContext).load(model.imgsrc).centerCrop().into(holder.iv_cover);
        }
        if(model.title != null){
            holder.tv_title.setText(model.title);
        }
        if(model.tags != null){
            holder.tv_tname.setText(model.tags);
        }else if(model.source != null){
            holder.tv_tname.setText(model.source);
        }
        if(model.replyCount != null){
            holder.tv_reply_count.setText(model.replyCount + "跟帖");
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void updateData(ArrayList<TouTiaoModel.TouTiao> mDataSet) {
        this.mDatas = mDataSet;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.iv_cover)
        ImageView iv_cover;
        @Bind(R.id.tv_title)
        TextView tv_title;
        @Bind(R.id.tv_tname)
        TextView tv_tname;
        @Bind(R.id.tv_reply_count)
        TextView tv_reply_count;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
