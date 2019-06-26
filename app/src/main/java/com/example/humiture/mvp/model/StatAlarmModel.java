package com.example.humiture.mvp.model;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.humiture.data.Alarm;
import com.example.humiture.data.StaticAlarmList;
import com.example.humiture.http.ApiService;
import com.example.humiture.http.RetrofitClient;
import com.example.humiture.mvp.contract.StatAlarmContract;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 *Time:2019/5/21
 *Author:冰冰凉
 *dec:报警统计的数据处理
 */
public class StatAlarmModel implements StatAlarmContract.Model {

    ApiService api = RetrofitClient.create(ApiService.class);

    /**
     * 报警统计 更多
     * @param type
     * @param date
     * @param storeId
     * @return
     */
    @Override
    public Observable<Alarm> getStaticAlarmList(String type, String date, String storeId) {
        return api.getStaticAlarmList(type,date,storeId).map(new Function<Alarm, Alarm>() {
            @Override
            public Alarm apply(Alarm alarm) throws Exception {
                return alarm;
            }
        }).compose(RetrofitClient.schedulersTransformer);
    }
}
