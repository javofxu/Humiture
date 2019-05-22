package com.example.humiture.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.base.rx.RxPresenter;
import com.example.humiture.data.TrendData;
import com.example.humiture.mvp.contract.IndexContract;
import com.example.humiture.mvp.contract.TrendContract;
import com.example.humiture.mvp.model.IndexModel;
import com.example.humiture.utils.LineChartManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by 许格.
 * Date on 2019/5/16.
 * dec:
 */
public class TrendPresent extends RxPresenter<TrendContract.mView> implements TrendContract.present {

    private Context mContext;
    private IndexContract.model mModel = new IndexModel();

    public TrendPresent(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getTrendData(String today, String yesterday, String type, int storeId) {
        Observable<List<TrendData.Data>> observable = mModel.getTrendData(today,type,storeId);
        Observable<List<TrendData.Data>> observable1 = mModel.getTrendData(yesterday,type,storeId);
        observable.subscribe(data -> observable1.subscribe(data1 ->
                mView.showTrendData(data,data1), throwable ->
                mView.netWorkError()), throwable -> mView.netWorkError());
    }

    @Override
    public void showLineChart(LineChartManager manager, ArrayList<Integer> xValues, List<Float> toadyValues, List<Float> yesterdayValues, int type) {
        manager.showLineChart(xValues, toadyValues, yesterdayValues, type,true);
        manager.setYAxis(60, 10, 6);
    }
}
