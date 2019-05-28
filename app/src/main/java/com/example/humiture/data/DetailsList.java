package com.example.humiture.data;

import java.util.Date;
import java.util.List;

/**
 * Created by 许格.
 * Date on 2019/5/23.
 * dec: 详细数据实体类
 */
public class DetailsList {

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

    public class Data{
        private String date;
        private String time;

        public String getDate() {
                return date;
            }
        public void setDate(String date) {
                this.date = date;
            }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
