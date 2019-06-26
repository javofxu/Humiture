package com.example.humiture.ui.view.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.example.humiture.data.Warehouse;
import com.example.humiture.ui.fragment.AlarmInfoFragment;
import com.example.humiture.ui.fragment.MineInfoFragment;
import com.example.humiture.ui.fragment.MineKuFangFragment;

import java.util.List;

/**
 * Created by Carson_Ho on 16/7/22.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<String> list;
    private String type;
    private static final String TAG = "MyFragmentPagerAdapter";

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyFragmentPagerAdapter(FragmentManager fm, List<String> list,String type)
    {
        super(fm);
        this.list = list;
        this.type = type;
    }

    @Override
    public Fragment getItem(int position) {
        if(type.equals("a")){
            if (position == 1) {
                return new AlarmInfoFragment();
            }
            return new MineInfoFragment();
        }else if(type.equals("b")){
            Fragment mineKuFang = new MineKuFangFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name",list.get(position));
            mineKuFang.setArguments(bundle);
            return mineKuFang;
        }else{
        }
        return null;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    }
}
