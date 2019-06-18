package com.example.humiture.mvp.presenter;

import android.content.Context;
import android.graphics.Color;

import com.example.base.rx.RxPresenter;
import com.example.humiture.mvp.contract.StatisticContract;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

/**
 *Time:2019/5/17
 *Author:冰冰凉
 *dec:
 */
public class StatisticPresent extends RxPresenter<StatisticContract.mView> implements StatisticContract.present{

    private Context mContext;
    /**
     * #BDD25A : 菌落
     * #A15EC7 : 有毒气体
     * #1048E9 : 设备
     * #F1B34E : EOC2
     * #F4407E : 温度
     * #108EE9 : 甲醛
     * #84D32F : TVOC
     *
     */
    public static final int[] MATERIAL_CLOLOR = {
            rgb("#BDD25A"), rgb("#A15EC7"), rgb("#1048E9"),
            rgb("#F1B34E"), rgb("#F4407E"), rgb("#108EE9"),
            rgb("#84D32F"),rgb("#4AB35C")
    };

    public StatisticPresent(Context mContext){
        this.mContext = mContext;
    }

    //也可以包装一下把颜色传过来，要不先这样吧，颜色不一定会改
    @Override
    public void showPieChart(PieChart pieChart, List<PieEntry> pieList) {
        PieDataSet dataSet = new PieDataSet(pieList,"");
        //不显示图例
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);
        //设置颜色list,让不同的块显示不同的颜色
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
        //设置半透明圆环的半径,0为透明
        pieChart.setTransparentCircleRadius(0f);

        //设置旋转的角度
        pieChart.setRotationAngle(-15);
        //设置显示百分比
        pieChart.setUsePercentValues(false);
        //数据连接线距图形片内部边界的距离，为百分数
        dataSet.setValueLinePart1OffsetPercentage(80f);
        //连接线在饼状图的外面
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        //设置饼块之间的距离
        dataSet.setSliceSpace(1f);
        dataSet.setHighlightEnabled(true);
        //设置连线的颜色
        dataSet.setValueLineColor(Color.parseColor("#a1a1a1"));
        //设置Y轴描述线和填充区域的颜色一致
        dataSet.setUsingSliceColorAsValueLineColor(true);
        //线前半部分的长度
        dataSet.setValueLinePart1Length(0.5f);
        //线后半部分的长度
        dataSet.setValueLinePart2Length(0.8f);
        //和四周相隔一段距离，显示数据
        pieChart.setExtraOffsets(26,5,26,5);
        //设置pieChart图表是否可以手动旋转
        pieChart.setRotationEnabled(false);
        //设置piechart图表点击Item高亮是否可用
        pieChart.setHighlightPerTapEnabled(true);
//        pieChart.animateY(1400,Easing.EasingOption.E);
        //设置pieChart是否只显示饼图上百分比不显示文字
        pieChart.setDrawEntryLabels(true);
        //是否绘制pieChart内部中心文本
        pieChart.setDrawCenterText(true);

        pieChart.setCenterText("总计2014");
        //添加动画效果
//        pieChart.animateXY(2000,2000);
        pieChart.animateY(3000);
        //文字的位移
//        pieChart.setCenterTextOffset(5,20);
        //绘制内容value,设置字体颜色大小
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(14f);
//        pieData.setValueTextColor(Color.DKGRAY);
        pieData.setValueTextColors(colors);
        pieChart.setData(pieData);
        //更新视图
        pieChart.postInvalidate();
    }
}
