package com.example.humiture.data;

import java.util.List;

/**
 * Created by 许格.
 * Date on 2019/5/21.
 * dec:趋势数据
 */
public class TrendData {

    private Common common;
    private List<Data> data;

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data {

        private float avgDate;
        private int timeYMD;

        public float getAvgDate() {
            return avgDate;
        }

        public void setAvgDate(float avgDate) {
            this.avgDate = avgDate;
        }

        public int getTimeYMD() {
            return timeYMD;
        }

        public void setTimeYMD(int timeYMD) {
            this.timeYMD = timeYMD;
        }
    }
}
