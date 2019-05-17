package com.example.humiture.mvp.contract;

import com.example.base.BaseView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;

import java.util.List;

/**
 *Time:2019/5/17
 *Author:冰冰凉
 *dec:
 */
public interface StatisticContract {

    interface model{

    }

    interface mView extends BaseView{

    }

    /**
     * 添加饼图的逻辑代码
     */
    interface present{

    void showPieChart(PieChart pieChart, List<PieEntry> pieList);

    }

}
