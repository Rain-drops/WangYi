package com.sgj.wangyi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sgj.wangyi.fragment.FragmentTest;

/**
 * Created by John on 2016/5/5.
 */
public class NewsPageAdapter extends FragmentPagerAdapter {

    private static final String TAG = "NewsPageAdapter";

    public NewsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentTest();
            case 1:
                return new FragmentTest();
            case 2:
                return new FragmentTest();
            case 3:
                return new FragmentTest();
            case 4:
                return new FragmentTest();
            case 5:
                return new FragmentTest();
            case 6:
                return new FragmentTest();
            case 7:
                return new FragmentTest();
            case 8:
                return new FragmentTest();
            case 9:
                return new FragmentTest();
            case 10:
                return new FragmentTest();
            case 11:
                return new FragmentTest();
            default:
                break;
        }
        return new FragmentTest();
    }

    @Override
    public int getCount() {
        return 11;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return " 1 ";
            case 1:
                return " 2 ";
            case 2:
                return " 3 ";
            case 3:
                return " 4 ";
            case 4:
                return " 5 ";
            case 5:
                return " 6 ";
            case 6:
                return "7";
            case 7:
                return "8";
            case 8:
                return "9";
            case 9:
                return "10";
            case 10:
                return "11";
            case 11:
                return "12";
            default:
                break;
        }
        return "0";
    }
}
