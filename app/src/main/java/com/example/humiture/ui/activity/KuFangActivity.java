package com.example.humiture.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.ui.view.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *Time:2019/5/23
 *Author:冰冰凉
 *dec:库房环境设置
 */
public class KuFangActivity extends BaseActivity {

    @BindView(R2.id.viewPager)
    ViewPager viewPager;

    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private TabLayout mTabLayout;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private List<String> list;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_kufang;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
    }

    @Override
    protected void initView() {
        super.initView();
        initViews();
    }

    @Override
    public void initData() {
        super.initData();
        list = new ArrayList<>();
        list.add("一库房");
        list.add("二库房");
        list.add("三库房");
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),list,"b");
        viewPager.setAdapter(myFragmentPagerAdapter);
    }

    private void initViews() {
        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(viewPager);
//        mPresent.reflex(mTabLayout);
        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);
    }

    @OnClick({R.id.mine_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.mine_back:
                finish();
                break;
        }
    }

}
