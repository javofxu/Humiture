package com.example.humiture.mvp.contract;

import android.widget.LinearLayout;

import com.example.base.BaseView;
import com.example.humiture.data.RealTimeData;
import com.example.humiture.data.TrendData;
import com.example.humiture.data.Warehouse;
import com.example.humiture.utils.LineChartManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;

/**
 * Created by 许格.
 * Date on 2019/5/16.
 * dec:
 */
public interface IndexContract {

    interface model{
        /**
         * 获取库房列表
         * @return
         */
        Observable<List<Warehouse.Data>> getWarehouse();

        /**
         * 获取首页实时数据（8个）
         * @param storeId
         * @return
         */
        Observable<RealTimeData> getRealTimeData(@Field("storeId") int storeId);

        /**
         * 获取库房数据趋势
         * @param time 时间
         * @param type 数据类型（温度、湿度）
         * @param storeId 库房ID
         * @return
         */
        Observable<List<TrendData.Data>> getTrendData(@Field("strTime") String time, @Field("type") String type, @Field("storeId") int storeId);
    }

    interface mView extends BaseView{

        /**
         * 获取库房和库房ID集合
         * @param storeId 库房ID集合
         * @param warehouse 库房集合
         */
        void getWareHouse(List<Integer> storeId, List<String> warehouse);

        /**
         * 库房显示
         * @param storeId 库房ID
         * @param warehouse 库房名称
         */
        void showWareHouse(int storeId, String warehouse);

        /**
         * 更新首页实时数据
         * @param mData
         */
        void updateRealTimeData(RealTimeData mData);

        /**
         * 获得今日数据和昨日数据用于画图
         * @param today 今日趋势
         * @param yesterday 昨日趋势
         */
        void showTrendData(List<TrendData.Data> today, List<TrendData.Data> yesterday);

        /**
         * 数据类型显示
         * @param title 如温度/湿度
         */
        void showDataType(int index, String title);

        /**
         * 网络异常
         */
        void netWorkError();
    }

    interface present{
        /**
         * 获取库房列表
         */
        void getWareHouse();

        /**
         * 获取实时数据（8个）
         * @param storeId
         */
        void getRealTimeData(int storeId);

        /**
         * 定时获取实时数据
         * @param time
         */
        void timingData(int time,int storeId);

        /**
         * 获取今日趋势
         * @param today 今日时间
         * @param yesterday 昨日数据
         * @param type 数据类型
         * @param storeId 当前库房ID
         */
        void getTrendData(String today, String yesterday, String type, int storeId);

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
        void showLineChart(LineChartManager manager, ArrayList<Integer> xValues, List<Float> toadyValues,
                           List<Float> yesterdayValues, int typeColor);


        void setGreenDao(List<Integer> storeId,List<String> wareHouseName);
    }
}
