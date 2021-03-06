package com.example.humiture.ui.view;

/**
 * 统计页面雷达图数据实体类
 */
public class RadarData {

    //标题
    private String title;
    //百分比
    private double percentage;

    public RadarData(String title, double percentage) {
        this.title = title;
        this.percentage = percentage;
    }

    public String getTitle() {
        return title;
    }

    public double getPercentage() {
        return percentage;
    }

}
