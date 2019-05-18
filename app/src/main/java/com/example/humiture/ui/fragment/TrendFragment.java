package com.example.humiture.ui.fragment;


import android.support.v4.app.Fragment;

import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.mvp.presenter.TrendPresent;
import com.example.humiture.utils.LineChartManager;
import com.example.humiture.utils.helper.DataTypeHelper;
import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendFragment extends BaseFragment<TrendPresent> {

    @BindView(R2.id.trend_chart)
    LineChart mLineChart;

    private LineChartManager mChartManager;
    private ArrayList<Float> xValues;
    private List<Float> today;
    private List<Float> yesterday;

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
        int number = getArguments().getInt("number");
        showChart(DataTypeHelper.getColors().get(number));
    }

    void showChart(int color){
        xValues = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            xValues.add((float) i);
        }
        //设置Y轴数据
        today = new ArrayList<>();
        yesterday = new ArrayList<>();
        //一条曲线模拟数据
        for (int j = 0; j < 12; j++) {
            today.add((float) (Math.random() * 50));
            yesterday.add((float) (Math.random() * 50));
        }
        mPresent.showLineChart(mChartManager, xValues, today, yesterday, color);
    }
}
