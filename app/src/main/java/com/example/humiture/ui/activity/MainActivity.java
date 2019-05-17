package com.example.humiture.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;

import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.ui.fragment.IndexFragment;
import com.example.humiture.ui.fragment.MineFragment;
import com.example.humiture.ui.fragment.OperateFragment;
import com.example.humiture.ui.fragment.StatisticFragment;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by 许格.
 * Date on 2019/5/14.
 */
public class MainActivity extends BaseActivity {

    @BindView(R2.id.navigationBar)
    EasyNavigationBar bar;

    private String[] tabText = {"首页", "操作", "语音控", "统计","我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.homepage_normal, R.mipmap.operate_normal, R.mipmap.speech_control_normal,
            R.mipmap.statistics_normal,R.mipmap.my_normal};
    //选中时icon
    private int[] selectIcon = {R.mipmap.homepage_click, R.mipmap.operate_click, R.mipmap.speech_control_normal,
            R.mipmap.statistics_click,R.mipmap.my_click};

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void processBeforeInitView() {
        super.processBeforeInitView();
        fragments.add(new IndexFragment());
        fragments.add(new OperateFragment());
        fragments.add(new StatisticFragment());
        fragments.add(new MineFragment());
    }

    @Override
    protected void initView() {
        super.initView();
                bar.titleItems(tabText)      //必传  Tab文字集合
                .normalIconItems(normalIcon)   //必传  Tab未选中图标集合
                .selectIconItems(selectIcon)   //必传  Tab选中图标集合
                .fragmentList(fragments)       //必传  fragment集合
                .fragmentManager(getSupportFragmentManager())     //必传
                .iconSize(20)     //Tab图标大小
                .tabTextSize(10)   //Tab文字大小
                .tabTextTop(2)     //Tab文字距Tab图标的距离
                .addIconSize(40)
                .mode(EasyNavigationBar.MODE_ADD)
                .addLayoutRule(EasyNavigationBar.RULE_BOTTOM)
                .normalTextColor(Color.parseColor("#969696"))   //Tab未选中时字体颜色
                .selectTextColor(Color.parseColor("#33A3F4"))   //Tab选中时字体颜色
                .navigationBackground(Color.parseColor("#FFFFFF"))
                        .onTabClickListener((view, i) -> {
                            if (i==2){
                                skipAnotherActivity(LoginActivity.class);
                                return true;
                            }
                            return false;
                        })
                .build();
    }
}
