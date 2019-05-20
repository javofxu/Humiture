package com.example.humiture.mvp.contract;

import android.widget.LinearLayout;

import com.example.base.BaseView;
import com.example.humiture.utils.LineChartManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 许格.
 * Date on 2019/5/16.
 * dec:
 */
public interface IndexContract {

    interface model{

    }

    interface mView extends BaseView{
        /**
         * 库房显示
         * @param warehouse 库房名称
         */
        void showWareHouse(String warehouse);

        /**
         * 数据类型显示
         * @param title 如温度/湿度
         */
        void showDataType(int index, String title);
    }

    interface present{
        /**
         * 动态添加指示器
         * @param layout
         * @param position
         */
        void drawPoint(LinearLayout layout, int pagerNumber, int position);

        /**
         * 库房选择
         * @param name 库房列表
         * @param type 1：数据类型选择 2：库房选择（因用同一个控件，用type来初始化）
         */
        void designation(List<String> name, int type);

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
