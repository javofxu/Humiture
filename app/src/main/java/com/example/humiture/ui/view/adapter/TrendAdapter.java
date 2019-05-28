package com.example.humiture.ui.view.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.humiture.ui.fragment.TrendFragment;
import com.example.humiture.utils.helper.DataTypeHelper;

import java.util.List;

/**
 * Created by 许格.
 * Date on 2019/5/18.
 * dec: 趋势图适配器
 */
public class TrendAdapter extends FragmentPagerAdapter {

    private List<String> titles = DataTypeHelper.getDataNames();
    private String mTime;
    private int mStoreId;

    public TrendAdapter(int storeId, FragmentManager fm) {
        super(fm);
        this.mStoreId = storeId;
    }

    public void setDateTime(String time){
        this.mTime = time;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new TrendFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type",DataTypeHelper.getDataTypes().get(i));
        bundle.putInt("storeId", mStoreId);
        bundle.putString("time", mTime);
        bundle.putInt("number",i);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
