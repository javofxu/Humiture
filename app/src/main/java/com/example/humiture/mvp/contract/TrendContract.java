package com.example.humiture.mvp.contract;

import com.example.base.BaseView;
import com.example.humiture.data.TrendData;
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
        /**
         * 获得今日数据和昨日数据用于画图
         * @param today 今日趋势
         * @param yesterday 昨日趋势
         */
        void showTrendData(List<TrendData.Data> today, List<TrendData.Data> yesterday);
        /**
         * 网络异常
         */
        void netWorkError();
    }

    interface present{
        /**
         * 获取今日趋势
         * @param today 今日时间
         * @param yesterday 昨日数据
         * @param type 数据类型
         * @param storeId 当前库房ID
         */
        void getTrendData(String today, String yesterday, String type, int storeId);

        /**
         * 动态显示曲线
         * @param manager 初始化曲线
         * @param xValues X轴数据
         * @param toadyValues 今日数据
         * @param yesterdayValues 昨日数据
         * @param typeColor 数据类型颜色
         */
        void showLineChart(LineChartManager manager, ArrayList<Integer> xValues, List<Float> toadyValues, List<Float> yesterdayValues, int typeColor);
    }
}
