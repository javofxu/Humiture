package com.example.humiture.mvp.contract;

import com.example.base.BaseView;
import com.example.humiture.data.StaticAlarmList;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;

/**
 *Time:2019/5/17
 *Author:冰冰凉
 *dec:
 */
public interface StatisticContract {

    interface Model{
        /**
         * 报警统计  首页
         * @param type
         * @param date
         * @param storeId
         * @return
         */
        Observable<StaticAlarmList> getStaticAlarm(@Field("type") String type, @Field("date") String date, @Field("storeId") String storeId);
    }

    interface mView extends BaseView{
        //时间选择按钮的样式
        void initTextView();
        void onSuccess(StaticAlarmList staticAlarmList);
        void onFail();
    }

    /**
     * 添加饼图的逻辑代码
     */
    interface Present{
        void showPieChart(PieChart pieChart, List<PieEntry> pieList);
        //报警统计  首页
        void getStaticAlarm(String type,String date,String storeId);
    }

}
