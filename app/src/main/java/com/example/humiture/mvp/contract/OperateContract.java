package com.example.humiture.mvp.contract;

import android.app.Activity;

import com.example.base.BaseView;
import com.example.humiture.data.AllList;
import com.example.humiture.data.Common;
import com.example.humiture.data.RealTimeData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;

public interface OperateContract {
    interface Model {
        //获取库房设备和状态
        Observable<List<AllList>> getOperateData(@Field("stroreId") String stroreId);

        //设备控制
        Observable<Common> getOperate(@Field("deviceId") String deviceId, @Field("brandId") String brandId);

        /**
         * 获取首页实时数据（8个）
         * @param storeId
         * @return
         */
        Observable<RealTimeData> getRealTimeData(@Field("storeId") int storeId);

    }

    interface mView extends BaseView {
        /**
         * 库房显示
         *
         * @param warehouse 库房名称
         */
        void showWareHouse(String warehouse);

        /**
         * 成功
         */
        void onSuccesss(List<AllList> lists);

        /**
         * 失败
         */
        void onFails(String msg);

        void onSuccessed(String msg);

        /**
         * 更新首页实时数据
         * @param mData
         */
        void updateRealTimeData(RealTimeData mData,int storeId);

    }

    interface present {
        /**
         * 选择库房
         *
         * @param mActivity 绑定活动
         * @param wareHouse 库房列表
         */
        void choseWareHouse(Activity mActivity, List<String> wareHouse);

        /**
         * 库房设备列表和状态
         *
         * @param stroreId
         * @return
         */
        void getOperateData(String stroreId);

        /**
         * 设备控制
         *
         * @param deviceId
         * @param brandId
         */
        void getOperate(String deviceId, String brandId);

        /**
         * 获取实时数据（8个）
         * @param storeId
         */
        void getRealTimeData(int storeId);

    }
}
