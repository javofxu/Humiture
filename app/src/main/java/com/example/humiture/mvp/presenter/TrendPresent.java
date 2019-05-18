package com.example.humiture.mvp.presenter;

import android.content.Context;

import com.example.base.rx.RxPresenter;
import com.example.humiture.mvp.contract.TrendContract;
import com.example.humiture.utils.LineChartManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 许格.
 * Date on 2019/5/16.
 * dec:
 */
public class TrendPresent extends RxPresenter<TrendContract.mView> implements TrendContract.present {

    private Context mContext;

    public TrendPresent(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void showLineChart(LineChartManager manager, ArrayList<Float> xValues, List<Float> toadyValues, List<Float> yesterdayValues, int typeColor) {
        manager.showLineChart(xValues, toadyValues, yesterdayValues, typeColor,true);
        manager.setYAxis(60, 10, 6);
    }
}
