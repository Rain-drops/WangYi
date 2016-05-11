package com.sgj.wangyi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sgj.wangyi.R;
import com.sgj.wangyi.adapter.NewsTouTiaoAdapter;
import com.sgj.wangyi.adapter.TouTiaoAdapter;
import com.sgj.wangyi.dao.AppDao;
import com.sgj.wangyi.listener.CallBackListener;
import com.sgj.wangyi.materialrefreshlibrery.MaterialRefreshLayout;
import com.sgj.wangyi.materialrefreshlibrery.MaterialRefreshListener;
import com.sgj.wangyi.model.NewsTouTiaoModel;
import com.sgj.wangyi.model.TouTiaoModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by John on 2016/5/9.
 */
public class FragmentList extends Fragment {

    private static final String TAG = "FragmentList";

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.refreshlayout)
    MaterialRefreshLayout mMaterialRefreshLayout;

    NewsTouTiaoAdapter mAdapter;
    TouTiaoAdapter mAdapters;
    ArrayList<TouTiaoModel.TouTiao> mDataSet;

    public static FragmentList newInsance(){
        FragmentList fragmentList = new FragmentList();
        return fragmentList;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMaterialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                    }
                }, 1000);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                handRefreshMore(materialRefreshLayout);
            }

            @Override
            public void onfinish() {
                super.onfinish();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mAdapters != null){
            mAdapters.updateData(mDataSet);
        }
        if(mMaterialRefreshLayout != null){
            mMaterialRefreshLayout.finishRefresh();
        }
    }

    private void handRefreshMore(MaterialRefreshLayout refreshLayout) {
//        mAdapter.updateData(mDataSet);

        mMaterialRefreshLayout.finishRefresh();
        mMaterialRefreshLayout.finishRefreshLoadMore();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        getData();
    }

    private void getData() {
        AppDao.getInstance().getAllTouTiao(new CallBackListener<TouTiaoModel>(){
            @Override
            public void onError(Exception e) {
                super.onError(e);
                Log.e(TAG, e + "");
                mMaterialRefreshLayout.finishRefresh();
            }

            @Override
            public void onSuccess(TouTiaoModel result) {
                super.onSuccess(result);
                Log.e(TAG, "onSuccess() : " + result.toString());
                if(result != null){
                    ArrayList<TouTiaoModel.TouTiao> list = result.T1348647853363;
                    mDataSet = list;

                    if(mAdapters == null){

                        mAdapters = new TouTiaoAdapter(getActivity(), list);
                        recyclerView.setAdapter(mAdapters);
                    }else {
                        mAdapters.updateData(mDataSet);
                    }
                    mMaterialRefreshLayout.finishRefresh();
                }
            }

            @Override
            public void onStringResult(String result) {
                super.onStringResult(result);
                mMaterialRefreshLayout.finishRefresh();
            }
        }, pages );
    }
    int pages = 20;
}
