package com.example.humiture.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.mvp.contract.EnvironmentContract;
import com.example.humiture.mvp.presenter.EnvironmentPresent;
import com.example.humiture.ui.fragment.DetailsFragment;
import com.example.humiture.ui.view.adapter.DetailsAdapter;
import com.example.humiture.ui.view.adapter.TrendAdapter;
import com.example.humiture.utils.AnimationUtils;
import com.example.humiture.utils.TimeUtils;
import com.example.humiture.utils.helper.DataTypeHelper;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class EnvironmentActivity extends BaseActivity<EnvironmentPresent> implements EnvironmentContract.mView {

    @BindView(R2.id.en_tab)
    TabLayout mTabLayout;
    @BindView(R2.id.en_view_pager)
    ViewPager mViewPager;
    @BindView(R2.id.top_navigation)
    BubbleNavigationLinearView mNavigation;
    @BindView(R2.id.en_data_pager)
    ViewPager mDataPager;
    @BindView(R2.id.en_time)
    TextView mTime;
    @BindView(R2.id.details_layout)
    RelativeLayout mLayout;
    @BindView(R2.id.tab_layout)
    LinearLayout tabLayout;

    private List<String> title = new ArrayList<>();
    private TrendAdapter mAdapter;
    private DetailsAdapter mDetailsAdapter;
    private int mStoreId;
    private static callBack callBacks;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_environment;
    }

    public static void setCallBacks(callBack backs){
        callBacks = backs;
    }
    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new EnvironmentPresent(this);
    }

    @Override
    protected void initView() {
        super.initView();
        mTime.setText(TimeUtils.getNowDay());
        title = DataTypeHelper.getDataNames();
        mStoreId = getIntent().getIntExtra("storeId",1);
        mAdapter = new TrendAdapter(mStoreId,getSupportFragmentManager());
        mAdapter.setDateTime(TimeUtils.getNowDay());
        mDetailsAdapter = new DetailsAdapter(mStoreId,getSupportFragmentManager());
        mDetailsAdapter.setDateTime(TimeUtils.getNowDay());
        for (int i = 0; i < title.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title.get(i)));
        }
        mViewPager.setAdapter(mAdapter);
        mDataPager.setAdapter(mDetailsAdapter);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(callBacks!=null) callBacks.getTab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mDataPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mNavigation.setCurrentActiveItem(i);
                if (callBacks!=null) callBacks.current(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mNavigation.setNavigationChangeListener((view, position) -> mDataPager.setCurrentItem(position,true));
        mNavigation.setOnTouchListener((v, event) -> {
            switch (event.getAction()){
                case MotionEvent.ACTION_MOVE:
                    tabLayout.setVisibility(View.VISIBLE);
                    tabLayout.setAnimation(AnimationUtils.moveToViewLocation());
                    break;
            }
            return true;
        });
        DetailsFragment.setCallBack(() -> {
            tabLayout.setVisibility(View.GONE);
            tabLayout.setAnimation(AnimationUtils.moveToViewBottom());
        });
    }

    @OnClick({R2.id.en_time,R2.id.en_back})
    void onClick(View view){
        switch (view.getId()){
            case R.id.en_time:
                mPresent.choseDate(this);
                break;
            case R.id.en_back:
                finish();
                break;
        }
    }

    @Override
    public void showDate(String date) {
        mTime.setText(date);
        mAdapter.setDateTime(date);
        mDetailsAdapter.setDateTime(date);
        if (callBacks!=null){
            Log.d(TAG, "showDate: AAA");
            callBacks.refresh(date);
        }
    }

    public interface callBack{
        /**
         * 刷新时间
         * @param newTime
         */
        void refresh(String newTime);

        /**
         * 刷新tab
         * @param select
         */
        void getTab(int select);

        /**
         * 获取当前日或昨日
         * @param state
         */
        void current(int state);
    }
}
