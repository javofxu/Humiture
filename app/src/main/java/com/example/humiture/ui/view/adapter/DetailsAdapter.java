package com.example.humiture.ui.view.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.humiture.ui.fragment.DetailsFragment;
import com.example.humiture.ui.fragment.TrendFragment;
import com.example.humiture.utils.helper.DataTypeHelper;

import java.util.List;

/**
 * Created by 许格.
 * Date on 2019/5/18.
 * dec: 趋势图适配器
 */
public class DetailsAdapter extends FragmentPagerAdapter {

    private String[] titles = new String[]{"今日数据","昨日数据"};
    private int mStoreId;
    private String mTime;

    public DetailsAdapter(int storeId, FragmentManager fm) {
        super(fm);
        this.mStoreId = storeId;
    }

    public void setDateTime(String time){
        this.mTime = time;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("storeId", mStoreId);
        bundle.putString("time", mTime);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
