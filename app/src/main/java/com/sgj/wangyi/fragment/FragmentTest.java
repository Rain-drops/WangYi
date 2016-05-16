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
public class FragmentTest extends Fragment {

    public static FragmentTest newInstance(){
        FragmentTest fragmentTest = new FragmentTest();
        return fragmentTest;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test1, container, false);
        return view;
    }
}
