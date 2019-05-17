package com.example.humiture.ui.fragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.mvp.contract.IndexContract;
import com.example.humiture.mvp.presenter.IndexPresent;
import com.example.humiture.ui.activity.EnvironmentActivity;
import com.example.humiture.ui.activity.LoginActivity;
import com.example.humiture.ui.view.adapter.LoopShowAdapter;
import com.example.humiture.utils.ItemDecorationUtils;
import com.example.humiture.utils.LineChartUtils;
import com.example.humiture.utils.helper.DataTypeHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 许格.
 * Date on 2019/5/14.
 * A simple {@link Fragment} subclass.
 * 首页
 */
public class IndexFragment extends BaseFragment<IndexPresent> implements IndexContract.mView{

    @BindView(R2.id.picker)
    DiscreteScrollView picker;
    @BindView(R2.id.index_point)
    LinearLayout point;
    @BindView(R2.id.index_chart)
    LineChart chartView;
    @BindView(R2.id.index_title)
    TextView title;
    @BindView(R2.id.swipeRefreshLayout)
    PullRefreshLayout layout;
    @BindView(R2.id.index_chart_title)
    TextView chart_title;

    private LoopShowAdapter adapter;
    private LineChartUtils chartUtils;
    private int pagerNumber;

    private HashMap<String, Integer> map;
    private ArrayList<Float> xValues;
    private List<Float> today;
    private List<Float> yesterday;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new IndexPresent(mContext);
    }

    @Override
    protected void initView() {
        super.initView();
        chartUtils = new LineChartUtils(mContext, chartView);
        pagerNumber = 4;
        map = new HashMap<>();
        map.put(ItemDecorationUtils.LEFT_DECORATION,20);//右间距
        map.put(ItemDecorationUtils.RIGHT_DECORATION,20);//右间距
        adapter = new LoopShowAdapter(mContext,pagerNumber);
        picker.addItemDecoration(new ItemDecorationUtils(map));
        picker.setAdapter(adapter);
        picker.setCurrentItemChangeListener((viewHolder, adapterPosition) -> {
            mPresent.drawPoint(point,pagerNumber,adapterPosition);
        });
    }

    @Override
    protected void initData() {
        super.initData();
        layout.setOnRefreshListener(()-> layout.postDelayed(() -> layout.setRefreshing(false),1000));
        //设置X轴数据
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
        mPresent.showLineChart(chartUtils,xValues,today,yesterday,R.color.index_colony);
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
        mPresent.showLineChart(chartUtils, xValues, today, yesterday, color);
    }

    @OnClick({R2.id.index_title, R2.id.index_chart_chose, R2.id.index_more})
    void onClock(View v){
        switch (v.getId()){
            case R.id.index_title:
                mPresent.designation(DataTypeHelper.getWarehouse(),DataTypeHelper.getWarehouse().size());
                break;
            case R.id.index_chart_chose:
                mPresent.designation(DataTypeHelper.getDataNames(),DataTypeHelper.getDataNames().size());
                break;
            case R.id.index_more:
                skipAnotherActivity(EnvironmentActivity.class);
                break;
        }
    }

    @Override
    public void showWareHouse(String warehouse) {
        title.setText(warehouse);
    }

    @Override
    public void showDataType(int index, String title) {
        chart_title.setText(title);
        showChart(DataTypeHelper.getColors().get(index));
    }

}
