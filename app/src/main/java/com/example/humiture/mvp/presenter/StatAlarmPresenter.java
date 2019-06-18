package com.example.humiture.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.base.rx.RxPresenter;
import com.example.humiture.data.Alarm;
import com.example.humiture.mvp.contract.StatAlarmContract;
import com.example.humiture.mvp.model.StatAlarmModel;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 *Time:2019/5/21
 *Author:冰冰凉
 *dec:处理报警统计的业务逻辑
 */
public class StatAlarmPresenter extends RxPresenter<StatAlarmContract.View> implements StatAlarmContract.Presenter {

    private static final String TAG = "StatAlarmPresenter";
    private Context context;
    private StatAlarmContract.Model model = new StatAlarmModel();

    public StatAlarmPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void getStaticAlarmList(String type, String date, String storeId) {
        Observable<Alarm> observable = model.getStaticAlarmList(type,date,storeId);
        observable.subscribe(alarm -> {
            if (alarm.getStatus() == 0){
                if(alarm.getData().getList().size() > 0){
                    mView.onSuccess(alarm);
                }else {
                    mView.noDetails();
                }

            }else{

            }
        }, throwable -> Log.d(TAG, "loginPresenter:获取数据失败"));

    }
}
