package com.example.humiture.mvp.model;

import com.example.humiture.data.AllList;
import com.example.humiture.data.Common;
import com.example.humiture.data.KuFangData;
import com.example.humiture.data.RealTimeData;
import com.example.humiture.http.ApiService;
import com.example.humiture.http.RetrofitClient;
import com.example.humiture.mvp.contract.OperateContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Time:2019/7/2
 * Author:冰冰凉
 * dec:  设备列表和设备操作
 */
public class OperateModel implements OperateContract.Model {

    ApiService api = RetrofitClient.create(ApiService.class);

    /**
     * 库房设备列表和状态
     *
     * @param stroreId
     * @return
     */
    @Override
    public Observable<List<AllList>> getOperateData(String stroreId) {
        return api.getOperateData(stroreId).map(kuFangData -> kuFangData.getData().getAllList()).compose(RetrofitClient.schedulersTransformer);
    }

    /**
     * 设备控制
     *
     * @param deviceId
     * @param brandId
     * @return
     */
    @Override
    public Observable<Common> getOperate(String deviceId, String brandId) {
        return api.getOperate(deviceId, brandId).map(common -> common).compose(RetrofitClient.schedulersTransformer);
    }

    @Override
    public Observable<RealTimeData> getRealTimeData(int storeId) {
        return api.getRealTimeData(storeId).map(realTimeData -> realTimeData).compose(RetrofitClient.schedulersTransformer);
    }
}
