package com.example.humiture.ui.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.data.TrendData;
import com.example.humiture.mvp.contract.TrendContract;
import com.example.humiture.mvp.presenter.TrendPresent;
import com.example.humiture.utils.LineChartManager;
import com.example.humiture.utils.TimeUtils;
import com.example.humiture.utils.helper.DataTypeHelper;
import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendFragment extends BaseFragment<TrendPresent> implements TrendContract.mView{

    @BindView(R2.id.trend_chart)
    LineChart mLineChart;

    private LineChartManager mChartManager;
    private ArrayList<Integer> xValues;
    private List<Float> mToday;
    private List<Float> mYesterday;

    private String dateTime;
    private String mType;
    private int mNumber;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dateTime = getArguments().getString("time");
        mType = getArguments().getString("type");
        mNumber = getArguments().getInt("number");
        Log.i(TAG, "onAttach: "+dateTime+mType+mNumber);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_trend;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new TrendPresent(mContext);
    }

    @Override
    protected void initView() {
        super.initView();
        mChartManager = new LineChartManager(mContext, mLineChart);
        mPresent.getTrendData(dateTime, TimeUtils.dataForYesterday(dateTime), mType, mNumber);
    }

    @Override
    public void showTrendData(List<TrendData.Data> today, List<TrendData.Data> yesterday) {
        xValues = new ArrayList<>();
        mToday = new ArrayList<>();
        mYesterday = new ArrayList<>();
        for (int i = 0; i < today.size(); i++) {
            xValues.add(today.get(i).getTimeYMD());
            mToday.add(today.get(i).getAvgDate());
            mYesterday.add(yesterday.get(i).getAvgDate());
        }
        mPresent.showLineChart(mChartManager,xValues,mToday,mYesterday,mNumber);
    }

    @Override
    public void netWorkError() {
        showToast(getResources().getString(R.string.network_error));
    }
}
