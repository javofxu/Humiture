package com.example.humiture.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.mvp.contract.MineInfoContract;
import com.example.humiture.mvp.presenter.MineInfoPresenter;
import com.example.humiture.ui.adapter.MineInfoAdapter;
import com.example.humiture.ui.adapter.MyFragmentPagerAdapter;
import com.github.mikephil.charting.data.LineData;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.humiture.utils.DensityUtils.dip2px;

/**
 *Time:2019/5/22
 *Author:冰冰凉
 *dec:我的信息功能  提取了MyFragmentPagerAdapter以实现公用，在库房环境中还使用了it
 */
public class MineInfoActivity extends BaseActivity<MineInfoPresenter> implements MineInfoContract.View {

    @BindView(R2.id.viewPager)
    ViewPager viewPager;

    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private TabLayout mTabLayout;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private List<String> list;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_info;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new MineInfoPresenter(this);
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
        list.add("报警信息");
        list.add("系统信息");
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),list,"a");
        viewPager.setAdapter(myFragmentPagerAdapter);
    }

    @OnClick({R.id.mine_info_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.mine_info_back:
                finish();
                break;
        }
    }

    private void initViews() {
        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(viewPager);
        mPresent.reflex(mTabLayout);
        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
    }

}
