package com.example.humiture.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.data.StaticAlarmList;
import com.example.humiture.mvp.contract.StatisticContract;
import com.example.humiture.mvp.presenter.IndexPresent;
import com.example.humiture.mvp.presenter.StatisticPresent;
import com.example.humiture.ui.activity.DateChooseActivity;
import com.example.humiture.ui.activity.MineInfoActivity;
import com.example.humiture.ui.activity.PlayActivity;
import com.example.humiture.ui.activity.StatAlarmActivity;
import com.example.humiture.ui.view.RadarData;
import com.example.humiture.ui.view.RadarView;
import com.example.humiture.utils.ToastUtils;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.addapp.pickers.picker.DatePicker;

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
    private int zh = 0;

    private Drawable drawable;
    private String resultTime = null;

    private List<StaticAlarmList.Data.Bjtj> bjtjs = null;

    private List<StaticAlarmList.Data.Zhtj> zhtjs = null;


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
        //设置时间选择按钮
        initTextView();
    }

    @Override
    protected void initData() {
        super.initData();
        mPresent.getStaticAlarm("1","2019","1");
        /**
         * 动态刷新数据
         */
        pullRefreshLayout.setOnRefreshListener(() -> pullRefreshLayout.postDelayed(() -> {
            mPresent.getStaticAlarm("1","2019","1");
            pullRefreshLayout.setRefreshing(false);
        }, 1000));
    }

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

    @OnClick({R2.id.stat_date,R2.id.stat_more,R2.id.alarm})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.stat_date:
                //时间选择
//                skipAnotherActivity(DateChooseActivity.class);
                Intent intent = new Intent(getActivity(),DateChooseActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
                break;
            case R.id.stat_more:
                skipAnotherActivity(StatAlarmActivity.class);
                break;
            case R.id.alarm:
                skipAnotherActivity(MineInfoActivity.class);
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

}

