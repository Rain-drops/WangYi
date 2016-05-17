package com.sgj.wangyi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.sgj.wangyi.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by John on 2016/5/17.
 */
public class FragmentColumn extends Fragment {

    String[] title = {"通讯录", "日历", "照相机", "时钟", "游戏", "短信", "铃声",
            "设置", "语音", "天气", "浏览器", "视频"};

    SimpleAdapter mAdapter;

    GridView gv_gview1;

    public static FragmentColumn getInstance(){
        FragmentColumn fragmentColumn = new FragmentColumn();
        return fragmentColumn;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_column, container, false);
        gv_gview1 = (GridView) view.findViewById(R.id.gv_gview1);
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i<title.length; i++){
            Map<String, Object> map = new HashMap<>();
            map.put("column", title[i]);
            data.add(map);
        }
        mAdapter = new SimpleAdapter(getContext(), data, R.layout.item_column, new String[]{"column"}, new int[]{R.id.tv_column});
        gv_gview1.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
