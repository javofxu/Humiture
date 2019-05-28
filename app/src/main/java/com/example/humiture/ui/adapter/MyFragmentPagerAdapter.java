package com.example.humiture.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
            if(position == 1)
            {
                return new MineKuFangFragment();
            }
            return new MineKuFangFragment();
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
}
