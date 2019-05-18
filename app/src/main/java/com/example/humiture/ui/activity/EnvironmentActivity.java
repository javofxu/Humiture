package com.example.humiture.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.ui.view.adapter.DetailsAdapter;
import com.example.humiture.ui.view.adapter.TrendAdapter;
import com.example.humiture.utils.helper.DataTypeHelper;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class EnvironmentActivity extends BaseActivity {

    @BindView(R2.id.en_tab)
    TabLayout mTabLayout;
    @BindView(R2.id.en_view_pager)
    ViewPager mViewPager;
    @BindView(R2.id.top_navigation)
    BubbleNavigationLinearView mNavigation;
    @BindView(R2.id.en_data_pager)
    ViewPager mDataPager;

    private List<String> title = new ArrayList<>();
    private TrendAdapter mAdapter;
    private DetailsAdapter mDetailsAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_environment;
    }

    @Override
    protected void initView() {
        super.initView();
        title = DataTypeHelper.getDataNames();
        mAdapter = new TrendAdapter(getSupportFragmentManager());
        mDetailsAdapter = new DetailsAdapter(getSupportFragmentManager());
        for (int i = 0; i < title.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title.get(i)));
        }
        mViewPager.setAdapter(mAdapter);
        mDataPager.setAdapter(mDetailsAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mDataPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mNavigation.setCurrentActiveItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mNavigation.setNavigationChangeListener((view, position) -> mDataPager.setCurrentItem(position,true));
    }
}
