package com.example.humiture.mvp.model;

import com.example.humiture.data.StaticAlarmList;
import com.example.humiture.http.ApiService;
import com.example.humiture.http.RetrofitClient;
import com.example.humiture.mvp.contract.StatisticContract;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 *Time:2019/5/17
 *Author:冰冰凉
 *dec:
 */
public class StatisticModel implements StatisticContract.Model {

    ApiService api = RetrofitClient.create(ApiService.class);

    /**
     * 报警统计 首页
     * @param type
     * @param date
     * @param storeId
     * @return
     */
    @Override
    public Observable<StaticAlarmList> getStaticAlarm(String type, String date, String storeId) {
        return api.getStaticAlarm(type,date,storeId).map(new Function<StaticAlarmList, StaticAlarmList>() {
            @Override
            public StaticAlarmList apply(StaticAlarmList staticAlarmList) throws Exception {
                return staticAlarmList;
            }
        }).compose(RetrofitClient.schedulersTransformer);
    }
}
