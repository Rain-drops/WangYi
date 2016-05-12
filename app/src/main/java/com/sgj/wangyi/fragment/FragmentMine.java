package com.sgj.wangyi.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sgj.wangyi.CropActivity;
import com.sgj.wangyi.R;
import com.sgj.wangyi.imagecrop.Crop;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/11.
 */
public class FragmentMine extends Fragment implements View.OnClickListener{

    @Bind(R.id.sdv_avatar)
    SimpleDraweeView sdv_avatar;

    public static FragmentMine newInstance(){
        FragmentMine fragmentMine = new FragmentMine();
        return fragmentMine;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        sdv_avatar.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sdv_avatar:
                Crop.pickImage(getActivity());
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result) {

        if (requestCode == Crop.REQUEST_PICK && resultCode == getActivity().RESULT_OK) {
            Intent intent = new Intent(getActivity(), CropActivity.class);
            intent.putExtra("source", result.getData().toString());
            startActivityForResult(intent, 100);
        }else if(requestCode == 100){

            if(null == result.getExtras().getString("type")){

                Uri uri = Uri.parse(result.getExtras().getString("source"));
                sdv_avatar.setImageURI(uri);
            }else {
                Toast.makeText(getActivity(), Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
//        super.onActivityResult(requestCode, resultCode, result);


    }

}
