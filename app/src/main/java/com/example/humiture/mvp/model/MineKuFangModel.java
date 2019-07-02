package com.example.humiture.mvp.model;

import android.util.Log;

import com.example.humiture.data.AllList;
import com.example.humiture.data.Common;
import com.example.humiture.data.KuFangData;
import com.example.humiture.data.Warehouse;
import com.example.humiture.http.ApiService;
import com.example.humiture.http.RetrofitClient;
import com.example.humiture.mvp.contract.MineKuFangContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 *Time:2019/6/19
 *Author:冰冰凉
 *dec: 库房环境设置
 */
public class MineKuFangModel implements MineKuFangContract.Model {

    private static final String TAG = "MineKuFangModel";

    ApiService api = RetrofitClient.create(ApiService.class);


    /**
     * 获取库房列表
     * @return
     */
    @Override
    public Observable<List<Warehouse.Data>> getWarehouse() {
        return api.getWarehouse().map(new Function<Warehouse, List<Warehouse.Data>>() {
            @Override
            public List<Warehouse.Data> apply(Warehouse warehouse) throws Exception {
                return warehouse.getData();
            }
        }).compose(RetrofitClient.schedulersTransformer);
    }

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
    @Override
    public Observable<Common> getKuFangData(String storeId, String humUp, String humDown, String temUp, String temDown, String pm2Up, String tvocUp) {
        return api.getKuFangData(storeId,humUp,humDown,temUp,temDown,pm2Up,tvocUp).map(kuFangData -> {
            Log.i(TAG, "apply: " + kuFangData.getStatus());
            return kuFangData;
        }).compose(RetrofitClient.schedulersTransformer);
    }
}
