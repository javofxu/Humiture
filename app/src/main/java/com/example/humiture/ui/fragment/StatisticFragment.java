package com.example.humiture.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.mvp.contract.StatisticContract;
import com.example.humiture.mvp.presenter.IndexPresent;
import com.example.humiture.mvp.presenter.StatisticPresent;
import com.example.humiture.ui.activity.DateChooseActivity;
import com.example.humiture.ui.activity.MineInfoActivity;
import com.example.humiture.ui.activity.PlayActivity;
import com.example.humiture.ui.activity.StatAlarmActivity;
import com.example.humiture.ui.view.RadarData;
import com.example.humiture.ui.view.RadarView;
import com.example.humiture.utils.ToastUtils;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.addapp.pickers.picker.DatePicker;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticFragment extends BaseFragment<StatisticPresent> implements StatisticContract.mView {

    @BindView(R2.id.radarView)
    RadarView radarView;
    @BindView(R2.id.pie_chart)
    PieChart pieChart;
    @BindView(R2.id.stat_refresh)
    PullRefreshLayout pullRefreshLayout;
    @BindView(R2.id.stat_date)
    TextView stat_date;
    @BindView(R2.id.stat_play)
    TextView stat_play;

    private Drawable drawable;
    private String resultTime = null;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_statistic;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new StatisticPresent(mContext);
    }

    @Override
    protected void initView() {
        super.initView();

        //雷达图数据
        RadarData();
        //饼图
        mPresent.showPieChart(pieChart, getPieChartData());
        //设置时间选择按钮
        initTextView();
    }

    @Override
    protected void initData() {
        super.initData();
        /**
         * 动态刷新数据
         */
        pullRefreshLayout.setOnRefreshListener(() -> {
            pullRefreshLayout.postDelayed(() -> pullRefreshLayout.setRefreshing(false), 1000);
        });
    }

    @OnClick({R2.id.stat_date,R2.id.stat_more,R2.id.stat_play,R2.id.alarm})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.stat_date:
                //时间选择
//                skipAnotherActivity(DateChooseActivity.class);
                Intent intent = new Intent(getActivity(),DateChooseActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
                break;
            case R.id.stat_more:
                skipAnotherActivity(StatAlarmActivity.class);
                break;
            case R.id.stat_play:
                skipAnotherActivity(PlayActivity.class);
                break;
            case R.id.alarm:
                skipAnotherActivity(MineInfoActivity.class);
                break;
        }
    }

    /**
     * 雷达图的数据
     */
    private void RadarData() {
        List<RadarData> dataList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            //传入数据
            RadarData data = new RadarData("标题" + i, i * 11);
            dataList.add(data);
        }
        radarView.setDataList(dataList);
    }

    /**
     * 获取饼图的数据
     *
     * @return
     */
    private List<PieEntry> getPieChartData() {
        List<PieEntry> dataList = new ArrayList<>();
        dataList.add(new PieEntry(20, "菌落"));
        dataList.add(new PieEntry(30, "有害气体"));
        dataList.add(new PieEntry(40, "设备"));
        dataList.add(new PieEntry(50, "EOC2"));
        dataList.add(new PieEntry(60, "温度"));
        dataList.add(new PieEntry(70, "甲醛"));
        dataList.add(new PieEntry(80,"TVOC"));
        dataList.add(new PieEntry(90,"PM2.5"));
        return dataList;
    }

    /**
     * TextView时间选择的样式
     */
    @Override
    public void initTextView() {
        drawable=getResources().getDrawable(R.mipmap.stat_date_normal);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        stat_date.setPadding(30,0,30,0);
        stat_date.setCompoundDrawables(null,null,drawable,null);
        //设置间距
        stat_date.setCompoundDrawablePadding(15);
        stat_date.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.stat_date_normal, 0);
        stat_play.setPadding(30,0,30,0);
        stat_play.setCompoundDrawables(null,null,drawable,null);
        //设置间距
        stat_play.setCompoundDrawablePadding(15);
        stat_play.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.stat_date_normal, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            Bundle bundle = data.getExtras();
            resultTime = bundle.getString("time");
            stat_date.setText(resultTime);
        }
    }
}

