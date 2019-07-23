package com.example.humiture.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.data.AllList;
import com.example.humiture.data.KuFangSetData;
import com.example.humiture.data.StaticAlarmList;
import com.example.humiture.mvp.contract.StatisticContract;
import com.example.humiture.mvp.presenter.StatisticPresent;
import com.example.humiture.ui.activity.DateChooseActivity;
import com.example.humiture.ui.activity.MineInfoActivity;
import com.example.humiture.ui.activity.StatAlarmActivity;
import com.example.humiture.ui.view.RadarData;
import com.example.humiture.ui.view.RadarView;
import com.example.humiture.utils.helper.GreenDaoHelp;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticFragment extends BaseFragment<StatisticPresent> implements StatisticContract.mView {

    @BindView(R2.id.radarView)
    RadarView radarView;
    @BindView(R2.id.pie_chart)
    PieChart pieChart;
    @BindView(R2.id.stat_refresh)
    PullRefreshLayout pullRefreshLayout;
    @BindView(R2.id.stat_date)
    TextView stat_date;
    @BindView(R2.id.stat_zh)
    TextView stat_zh;
    @BindView(R2.id.scrollView)
    ScrollView scrollView;
    @BindView(R2.id.index_title)
    TextView mTitle;
    private int zh = 0;

    private Drawable drawable;
    private String resultTime = null;

    private List<StaticAlarmList.Data.Bjtj> bjtjs = null;

    private List<StaticAlarmList.Data.Zhtj> zhtjs = null;
    private List<KuFangSetData> list = null;
    //头部库房
    private String wareHouseName = null;
    private List<String> wareHouseList = null;
    private List<KuFangSetData> queryList = null;
    private String wareHouseId = null;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_statistic;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new StatisticPresent(mContext);
    }

    @Override
    protected void initView() {
        super.initView();
        GreenDaoHelp.getInstance(getActivity()).initGreenDao(getActivity());
        //设置时间选择按钮
        initTextView();
    }

    @Override
    protected void initData() {
        super.initData();
        list = GreenDaoHelp.getInstance(mContext).queryAllList();
        mPresent.getStaticAlarm("1","2019","1");
        if (list.size() <= 0) {
            mTitle.setText("一库房");
            wareHouseName = "一库房";
        } else {
            mTitle.setText(list.get(0).getName());
            wareHouseName = list.get(0).getName();
        }
        /**
         * 动态刷新数据
         */
        pullRefreshLayout.setOnRefreshListener(() -> pullRefreshLayout.postDelayed(() -> {
            mPresent.getStaticAlarm("1","2019","1");
            pullRefreshLayout.setRefreshing(false);
        }, 1000));
    }

    /**
     * 解决下拉刷新和ScrollView的冲突
     */
    @Override
    protected void initListener() {
        super.initListener();
        if (scrollView != null){
            scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    if(pullRefreshLayout != null){
                        pullRefreshLayout.setEnabled(scrollView.getScrollY() == 0);
                    }
                }
            });
        }
    }

    @OnClick({R2.id.stat_date,R2.id.stat_more,R2.id.alarm,R2.id.index_title})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.stat_date:
                //时间选择
                Intent intent = new Intent(getActivity(),DateChooseActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
                break;
            case R.id.stat_more:
                Bundle bundle1 = new Bundle();
                bundle1.putString("wareHouseId",wareHouseId);
                skipAnotherActivity(bundle1,StatAlarmActivity.class);
                break;
            case R.id.alarm:
                skipAnotherActivity(MineInfoActivity.class);
                break;
            case R.id.index_title:
                //库房选择,在本地数据库中加载本地数据
                wareHouseList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    wareHouseList.add(list.get(i).getName());
                }
                mPresent.choseWareHouse(mContext, wareHouseList);
                break;
        }
    }

    /**
     * 雷达图的数据
     */
    private void RadarData() {
        List<RadarData> dataList = new ArrayList<>();
        for (int i = 0; i < zhtjs.size(); i++) {
            //传入数据
            dataList.add(new RadarData("维护", zhtjs.get(i).getWh()));
            dataList.add(new RadarData("环境",zhtjs.get(i).getHj()));
            dataList.add(new RadarData("网络",zhtjs.get(i).getWl()));
            dataList.add(new RadarData("操控",zhtjs.get(i).getCk()));
            dataList.add(new RadarData("设备",zhtjs.get(i).getSb()));
            zh = zhtjs.get(i).getZh();
        }
        radarView.setDataList(dataList);
        stat_zh.setText(zh + "%");
    }

    /**
     * 获取饼图的数据
     *
     * @return
     */
    private List<PieEntry> getPieChartData() {
        List<PieEntry> dataList = new ArrayList<>();
        for(int i=0;i<bjtjs.size();i++){
            dataList.add(new PieEntry(bjtjs.get(i).getCount(), bjtjs.get(i).getAlarmType().substring(0,bjtjs.get(i).getAlarmType().length()-3)));
        }
        return dataList;
    }

    /**
     * TextView时间选择的样式
     */
    @Override
    public void initTextView() {
        drawable=getResources().getDrawable(R.mipmap.stat_date_normal);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        stat_date.setPadding(30,0,30,0);
        stat_date.setCompoundDrawables(null,null,drawable,null);
        //设置间距
        stat_date.setCompoundDrawablePadding(15);
        stat_date.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.stat_date_normal, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            Bundle bundle = data.getExtras();
            resultTime = bundle.getString("time");
            stat_date.setText(resultTime);
            Log.i(TAG, "onActivityResult: " + resultTime + "----" + resultTime.length());
            //判断时间的类型
            if (resultTime.length() <5){
                //这属于第一类型 ：2019
                mPresent.getStaticAlarm("1",resultTime,wareHouseId);
            }else if (resultTime.length() > 5 && resultTime.length() < 8){
                //这属于第二类型：2019-07
                mPresent.getStaticAlarm("2",resultTime,wareHouseId);
            }else if(resultTime.length() == 10){
                mPresent.getStaticAlarm("3",resultTime,wareHouseId);
            }
        }
    }

    @Override
    public void onSuccess(StaticAlarmList staticAlarmList) {
        //解析获取到的数据
        bjtjs = new ArrayList<>();
        zhtjs = new ArrayList<>();
        //饼图数据
        for(int i =0;i<staticAlarmList.getData().getBjtj().size();i++){
            StaticAlarmList.Data.Bjtj bjtj = new StaticAlarmList().new Data().new Bjtj();
            bjtj.setAlarmType_id(staticAlarmList.getData().getBjtj().get(i).getAlarmType_id());
            bjtj.setAlarmType(staticAlarmList.getData().getBjtj().get(i).getAlarmType());
            bjtj.setCount(staticAlarmList.getData().getBjtj().get(i).getCount());
            bjtjs.add(bjtj);
        }
        //饼图
        mPresent.showPieChart(pieChart, getPieChartData());
        //雷达图数据
        for(int j = 0;j<staticAlarmList.getData().getZhtj().size();j++){
            StaticAlarmList.Data.Zhtj zhtj = new StaticAlarmList().new Data().new Zhtj();
            zhtj.setWh(staticAlarmList.getData().getZhtj().get(j).getWh());
            zhtj.setZh(staticAlarmList.getData().getZhtj().get(j).getZh());
            zhtj.setHj(staticAlarmList.getData().getZhtj().get(j).getHj());
            zhtj.setWl(staticAlarmList.getData().getZhtj().get(j).getWl());
            zhtj.setCk(staticAlarmList.getData().getZhtj().get(j).getCk());
            zhtj.setSb(staticAlarmList.getData().getZhtj().get(j).getSb());
            zhtjs.add(zhtj);
        }

        //雷达图数据
        RadarData();

    }

    @Override
    public void onFail() {
        showToast("获取数据失败");
    }

    /**
     * 显示选择的库房
     * @param warehouse 库房名称
     */
    @Override
    public void showWareHouse(String warehouse) {
        mTitle.setText(warehouse);
        wareHouseName = warehouse;
        //查出选中的库房的id然后获取库房列表和状态
        queryList = GreenDaoHelp.getInstance(mContext).getStorId(warehouse);
        for (int i = 0; i < queryList.size(); i++) {
            Log.i(TAG, "showWareHouse: " + queryList.get(i).getStoreId());
//            mPresent.getRealTimeData(queryList.get(i).getStoreId());
            wareHouseId = String.valueOf(queryList.get(i).getStoreId());
            //获取综合统计和报警统计的数据
            mPresent.getStaticAlarm("1","2019",String.valueOf(queryList.get(i).getStoreId()));
        }
    }



}

