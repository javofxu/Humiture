package com.example.humiture.ui.activity;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.utils.CacheDataManager;
import com.example.humiture.utils.ToastUtils;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

/**
 *Time:2019/5/27
 *Author:冰冰凉
 *dec:清除缓存
 */
public class CacheDataActivity extends BaseActivity {

    @BindView(R2.id.pie_chart)
    PieChart pieChart;
    private String Cache = null;

    private String kb = null;

    public static final int[] MATERIAL_CLOLOR = {
            rgb("#078FEA"), rgb("#D5D5D5")
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cache_clear;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
    }

    @Override
    protected void initView() {
        super.initView();

        try{
            Cache = CacheDataManager.getTotalCacheSize(this);
            boolean status = Cache.contains("KB");
            boolean statusMB = Cache.contains("MB");
            if(status){
                kb = Cache.substring(0,Cache.length()-2).trim();
                ToastUtils.showToast(kb);
            }
            if (statusMB){
                kb = Cache.substring(0,Cache.length()-2).trim();
                ToastUtils.showToast(kb);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        showPieChart(pieChart,getPieChartData());
    }

    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R2.id.btn_cache_clear,R2.id.cache_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_cache_clear:
                //清除缓存
                CacheDataManager.clearCache(CacheDataActivity.this,handler);
                break;
            case R.id.cache_back:
                finish();
                break;
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(CacheDataActivity.this, "清理完成", Toast.LENGTH_SHORT).show();
                    //加上这句有时候有bug，是否不用加？？测试
//                    showPieChart(pieChart,getPieChartData());
            }
        }
    };

    /**
     * 获取饼图的数据
     *
     * @return
     */
    private List<PieEntry> getPieChartData() {
        List<PieEntry> dataList = new ArrayList<>();
        if (kb == null){
            dataList.add(new PieEntry(0,""));
        }else{
            dataList.add(new PieEntry(Float.parseFloat(kb), ""));
        }
        dataList.add(new PieEntry(1024,""));
        return dataList;
    }

    public void showPieChart(PieChart pieChart, List<PieEntry> pieList) {
        PieDataSet dataSet = new PieDataSet(pieList,"");
        //不显示图例
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int c : MATERIAL_CLOLOR){
            colors.add(c);
        }
        dataSet.setColors(colors);
        PieData pieData = new PieData(dataSet);
        //设置描述，我设置的是不显示
        Description description = new Description();
        description.setEnabled(false);
        pieChart.setDescription(description);
        //显示中间的洞
        pieChart.setDrawHoleEnabled(true);
        //中间洞的比例
        pieChart.setHoleRadius(80f);
        dataSet.setHighlightEnabled(false);
        //设置pieChart图表是否可以手动旋转
        pieChart.setRotationEnabled(false);
        //设置piechart图表点击Item高亮是否可用
        pieChart.setHighlightPerTapEnabled(false);
        //是否绘制pieChart内部中心文本
        pieChart.setDrawCenterText(true);
        pieChart.setCenterTextSize(30f);
        pieChart.setCenterText(pieList.get(0).getValue()+ "M");
//        ToastUtils.showToast(pieList.get(0).getValue() + "这是");
        //绘制内容value,设置字体颜色大小
        pieData.setDrawValues(false);
        pieChart.setData(pieData);
        //更新视图
        pieChart.postInvalidate();
    }

}
