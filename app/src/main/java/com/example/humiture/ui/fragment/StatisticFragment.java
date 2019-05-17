package com.example.humiture.ui.fragment;

import android.support.v4.app.Fragment;
import android.widget.ScrollView;

import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.mvp.contract.StatisticContract;
import com.example.humiture.mvp.presenter.IndexPresent;
import com.example.humiture.mvp.presenter.StatisticPresent;
import com.example.humiture.ui.view.RadarData;
import com.example.humiture.ui.view.RadarView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticFragment extends BaseFragment<StatisticPresent> implements StatisticContract.mView {

    @BindView(R2.id.radarView)
    RadarView radarView;
    @BindView(R2.id.pie_chart)
    PieChart pieChart;

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
        mPresent.showPieChart(pieChart,getPieChartData());
    }

    @Override
    protected void initData() {
        super.initData();
    }

    private void RadarData(){
        List<RadarData> dataList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            //传入数据
            RadarData data = new RadarData("标题" + i, i * 11);
            dataList.add(data);
        }
        radarView.setDataList(dataList);
    }

    private List<PieEntry> getPieChartData(){
        List<PieEntry> dataList = new ArrayList<>();
        dataList.add(new PieEntry(20,"菌落"));
        dataList.add(new PieEntry(30,"有害气体"));
        dataList.add(new PieEntry(40,"设备"));
        dataList.add(new PieEntry(50,"EOC2"));
        dataList.add(new PieEntry(60,"温度"));
        dataList.add(new PieEntry(70,"甲醛"));
        return dataList;
    }

}
