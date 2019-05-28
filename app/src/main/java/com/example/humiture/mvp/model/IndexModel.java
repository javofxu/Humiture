package com.example.humiture.mvp.model;

import com.example.humiture.data.RealTimeData;
import com.example.humiture.data.TrendData;
import com.example.humiture.data.Warehouse;
import com.example.humiture.http.ApiService;
import com.example.humiture.http.RetrofitClient;
import com.example.humiture.mvp.contract.IndexContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by 许格.
 * Date on 2019/5/16.
 * dec: model实现层
 */
public class IndexModel implements IndexContract.model {

    ApiService api = RetrofitClient.create(ApiService.class);

    @Override
    public Observable<List<Warehouse.Data>> getWarehouse() {
        return api.getWarehouse().map(warehouse -> warehouse.getData()).compose(RetrofitClient.schedulersTransformer);
    }

    @Override
    public Observable<RealTimeData> getRealTimeData(int storeId) {
        return api.getRealTimeData(storeId).map(realTimeData -> realTimeData).compose(RetrofitClient.schedulersTransformer);
    }

    @Override
    public Observable<List<TrendData.Data>> getTrendData(String time, String type, int storeId) {
        return api.getTrendData(time, type, storeId).map(trendData -> trendData.getData()).compose(RetrofitClient.schedulersTransformer);
    }
}
