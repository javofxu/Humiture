package com.example.humiture.mvp.contract;

import android.view.View;
import android.widget.TextView;

import com.example.base.BaseView;
import com.example.humiture.data.AllList;
import com.example.humiture.data.Common;
import com.example.humiture.data.KuFangData;
import com.example.humiture.data.KuFangSetData;
import com.example.humiture.data.NumberData;
import com.example.humiture.data.Warehouse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;

/**
 *Time:2019/6/19
 *Author:冰冰凉
 *dec: 库房环境设置
 */
public interface MineKuFangContract {
    interface Model {
        /**
         * 获取库房列表
         * @return
         */
        Observable<List<Warehouse.Data>> getWarehouse();

        /**
         * 库房环境设置
         * @param storeId
         * @param humUp
         * @param humDown
         * @param temUp
         * @param temDown
         * @param pm2Up
         * @param tvocUp
         * @return
         */
        Observable<Common> getKuFangData(@Field("storeId") String storeId, @Field("humUp") String humUp,
                                                @Field("humDown") String humDown, @Field("temUp") String temUp,
                                                @Field("temDown") String temDown, @Field("pm2Up") String pm2Up,
                                                @Field("tvocUp") String tvocUp);
    }

    interface View extends BaseView {
        //失败
        void onFail(String msg);
        /**
         * 获取库房和库房ID集合
         */
        void getWareHouse(List<Warehouse.Data> data);
        //成功
        void onSuccess();

    }

    interface Presenter {
        /**
         * 获取库房列表
         */
        void getWareHouse();

        /**
         * 库房环境设置
         * @param storeId
         * @param humUp
         * @param humDown
         * @param temUp
         * @param temDown
         * @param pm2Up
         * @param tvocUp
         */
        void getKuFangData(String storeId, String humUp, String humDown,
                           String temUp, String temDown, String pm2Up, String tvocUp);

        /**
         * 加载数据
         * @param list
         */
        void show(List<KuFangSetData> list, String name, TextView up_wendu,TextView down_wendu,
                  TextView up_shidu,TextView down_shidu,TextView up_pm,TextView down_pm,
                  TextView up_tvoc,TextView down_tvoc,TextView up_junluo,TextView down_junluo,
                  TextView up_jiaquan,TextView down_jiaquan,TextView up_eoc2,TextView down_eoc2,
                  TextView up_gas,TextView down_gas);

        /**
         * 初始化选择器的数字
         */
        void getWenDuData(List<NumberData> numberData);
    }
}
