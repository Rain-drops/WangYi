package com.sgj.wangyi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sgj.wangyi.R;
import com.sgj.wangyi.model.NewsTouTiaoModel;

/**
 * Created by John on 2016/5/5.
 */
public class FragmentTest2 extends Fragment {

    public static FragmentTest2 newInstance(){
        FragmentTest2 fragmentTest = new FragmentTest2();
        return fragmentTest;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test2, container, false);
        return view;
    }
}
