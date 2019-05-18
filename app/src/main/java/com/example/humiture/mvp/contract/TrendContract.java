package com.example.humiture.mvp.contract;

import com.example.base.BaseView;
import com.example.humiture.utils.LineChartManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 许格.
 * Date on 2019/5/16.
 * dec:
 */
public interface TrendContract {

    interface model{

    }

    interface mView extends BaseView{

    }

    interface present{
        /**
         * 动态显示曲线
         * @param manager 初始化曲线
         * @param xValues X轴数据
         * @param toadyValues 今日数据
         * @param yesterdayValues 昨日数据
         * @param typeColor 数据类型颜色
         */
        void showLineChart(LineChartManager manager, ArrayList<Float> xValues, List<Float> toadyValues, List<Float> yesterdayValues, int typeColor);
    }
}
