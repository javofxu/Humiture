package com.example.humiture.data;

import java.io.Serializable;

/**
 * Created by 许格.
 * Date on 2019/5/21.
 * dec:首页实时温湿度等8个数据
 */
public class RealTimeData implements Serializable {

    private Common common;
    private Data data;

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }


        public class Data implements Serializable{
            private String hcsId;
            private String deviceId;
            private String temperature;
            private String humidity;
            private String pm2;
            private String tvoc;
            private String formaldehyde;
            private String eco2;
            private String colony;
            private String harmful;
            private String collectDate;
            private String remark;
            public void setHcsId(String hcsId) {
                this.hcsId = hcsId;
            }
            public String getHcsId() {
                return hcsId;
            }

            public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
            }
            public String getDeviceId() {
                return deviceId;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }
            public String getTemperature() {
                return temperature;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }
            public String getHumidity() {
                return humidity;
            }

            public void setPm2(String pm2) {
                this.pm2 = pm2;
            }
            public String getPm2() {
                return pm2;
            }

            public void setTvoc(String tvoc) {
                this.tvoc = tvoc;
            }
            public String getTvoc() {
                return tvoc;
            }

            public void setFormaldehyde(String formaldehyde) {
                this.formaldehyde = formaldehyde;
            }
            public String getFormaldehyde() {
                return formaldehyde;
            }

            public void setEco2(String eco2) {
                this.eco2 = eco2;
            }
            public String getEco2() {
                return eco2;
            }

            public void setColony(String colony) {
                this.colony = colony;
            }
            public String getColony() {
                return colony;
            }

            public void setHarmful(String harmful) {
                this.harmful = harmful;
            }
            public String getHarmful() {
                return harmful;
            }

            public void setCollectDate(String collectDate) {
                this.collectDate = collectDate;
            }
            public String getCollectDate() {
                return collectDate;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
            public String getRemark() {
                return remark;
            }

        }
}
