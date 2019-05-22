package com.example.humiture.ui.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.data.RealTimeData;
import com.example.humiture.data.TrendData;
import com.example.humiture.mvp.contract.IndexContract;
import com.example.humiture.mvp.presenter.IndexPresent;
import com.example.humiture.ui.activity.EnvironmentActivity;
import com.example.humiture.ui.activity.NewsActivity;
import com.example.humiture.ui.view.adapter.LoopShowAdapter;
import com.example.humiture.utils.ItemDecorationUtils;
import com.example.humiture.utils.LineChartManager;
import com.example.humiture.utils.TimeUtils;
import com.example.humiture.utils.helper.DataTypeHelper;
import com.github.mikephil.charting.charts.LineChart;
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
    DiscreteScrollView mScrollView;
    @BindView(R2.id.index_point)
    LinearLayout point;
    @BindView(R2.id.index_chart)
    LineChart mChartView;
    @BindView(R2.id.index_title)
    TextView title;
    @BindView(R2.id.swipeRefreshLayout)
    PullRefreshLayout mLayout;
    @BindView(R2.id.index_chart_title)
    TextView chart_title;

    private LoopShowAdapter adapter;
    private LineChartManager mChartManager;
    private int pagerNumber;
    private int mStoreId;
    private int mType;

    private HashMap<String, Integer> map;
    private ArrayList<Integer> xValues;
    private List<Float> mToday;
    private List<Float> mYesterday;
    private List<String> mWareHouseList;
    private List<Integer> mStoreIdList;

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
        mChartManager = new LineChartManager(mContext, mChartView);
        pagerNumber = 4;
        map = new HashMap<>();
        map.put(ItemDecorationUtils.LEFT_DECORATION,20);//右间距
        map.put(ItemDecorationUtils.RIGHT_DECORATION,20);//右间距
        adapter = new LoopShowAdapter(mContext,pagerNumber);
        mScrollView.addItemDecoration(new ItemDecorationUtils(map));
        mScrollView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresent.getWareHouse();
        mPresent.timingData(30000, mStoreId);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mLayout.setOnRefreshListener(()-> {
            mPresent.getRealTimeData(mStoreId);
            mLayout.postDelayed(() -> mLayout.setRefreshing(false),1000);
        });
        mScrollView.setCurrentItemChangeListener((viewHolder, adapterPosition) ->
                mPresent.drawPoint(point,pagerNumber,adapterPosition));
    }

    @OnClick({R2.id.index_title, R2.id.index_chart_chose, R2.id.index_more ,R2.id.index_news})
    void onClock(View v){
        switch (v.getId()){
            case R.id.index_title:
                if (mWareHouseList!=null&&mWareHouseList.size()>0) {
                    mPresent.designation(mWareHouseList, 2);
                }else {
                    mPresent.designation(DataTypeHelper.getWarehouse(),2);
                }
                break;
            case R.id.index_chart_chose:
                mPresent.designation(DataTypeHelper.getDataNames(),1);
                break;
            case R.id.index_more:
                skipAnotherActivity(EnvironmentActivity.class);
                break;
            case R.id.index_news:
                skipAnotherActivity(NewsActivity.class);
                break;
        }
    }

    @Override
    public void getWareHouse(List<Integer> storeId, List<String> warehouse) {
        mStoreId = storeId.get(0);
        mStoreIdList = storeId;
        mWareHouseList = warehouse;
        mPresent.getRealTimeData(mStoreId);
        mPresent.getTrendData(TimeUtils.getNowDay(),TimeUtils.getYesterday(),DataTypeHelper.getDataTypes().get(0),mStoreId);
    }

    @Override
    public void showWareHouse(int position,String warehouse) {
        mStoreId = mStoreIdList.get(position);
        title.setText(warehouse);
        mPresent.getRealTimeData(mStoreId);
    }

    @Override
    public void updateRealTimeData(RealTimeData mData) {
        adapter.getRealTime(mData);
        adapter.notifyDataSetChanged();
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
        mPresent.showLineChart(mChartManager,xValues,mToday,mYesterday,mType);
    }

    @Override
    public void showDataType(int index, String title) {
        chart_title.setText(title);
        mType = index;
        mPresent.getTrendData(TimeUtils.getNowDay(),TimeUtils.getYesterday(),DataTypeHelper.getDataTypes().get(index),mStoreId);
    }

    @Override
    public void netWorkError() {
        showToast(getResources().getString(R.string.network_error));
    }

}
