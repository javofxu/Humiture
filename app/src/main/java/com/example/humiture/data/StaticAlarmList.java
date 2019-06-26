package com.example.humiture.data;

import java.io.Serializable;
import java.util.List;

/**
 *Time:2019/6/18
 *Author:冰冰凉
 *dec: 报警统计的饼图和雷达图
 *
 * {
 *     "status": 0,
 *     "msg": "请求成功",
 *     "data": {
 *         "bjtj": [
 *             {
 *                 "alarmType": "湿度超上限",
 *                 "alarmType_id": 4,
 *                 "count": 1
 *             },
 *             {
 *                 "alarmType": "有害气体超上限",
 *                 "alarmType_id": 9,
 *                 "count": 1
 *             }
 *         ],
 *         "zhtj": [
 *             {
 *                 "wh": 85,
 *                 "hj": 41,
 *                 "wl": 34,
 *                 "ck": 24,
 *                 "sb": 31
 *             }
 *         ]
 *     }
 * }
 *
 */
public class StaticAlarmList implements Serializable {

    private int status;
    private String msg;
    private Data data;
    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }

    public class Data {

        private List<Bjtj> bjtj;
        private List<Zhtj> zhtj;
        public void setBjtj(List<Bjtj> bjtj) {
            this.bjtj = bjtj;
        }
        public List<Bjtj> getBjtj() {
            return bjtj;
        }

        public void setZhtj(List<Zhtj> zhtj) {
            this.zhtj = zhtj;
        }
        public List<Zhtj> getZhtj() {
            return zhtj;
        }

        public class Bjtj {

            private String alarmType;
            private int alarmType_id;
            private int count;
            public void setAlarmType(String alarmType) {
                this.alarmType = alarmType;
            }
            public String getAlarmType() {
                return alarmType;
            }

            public void setAlarmType_id(int alarmType_id) {
                this.alarmType_id = alarmType_id;
            }
            public int getAlarmType_id() {
                return alarmType_id;
            }

            public void setCount(int count) {
                this.count = count;
            }
            public int getCount() {
                return count;
            }

        }

        public class Zhtj {

            private int wh;
            private int hj;
            private int wl;
            private int ck;
            private int sb;
            private int zh;
            public int getZh() {
                return zh;
            }
            public void setZh(int zh) {
                this.zh = zh;
            }
            public void setWh(int wh) {
                this.wh = wh;
            }
            public int getWh() {
                return wh;
            }

            public void setHj(int hj) {
                this.hj = hj;
            }
            public int getHj() {
                return hj;
            }

            public void setWl(int wl) {
                this.wl = wl;
            }
            public int getWl() {
                return wl;
            }

            public void setCk(int ck) {
                this.ck = ck;
            }
            public int getCk() {
                return ck;
            }

            public void setSb(int sb) {
                this.sb = sb;
            }
            public int getSb() {
                return sb;
            }

        }

    }

}
