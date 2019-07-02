package com.example.humiture.data;

import java.util.List;

/**
 *Time:2019/6/12
 *Author:冰冰凉
 *dec:报警消息的实体类
 */
public class MessageData {

    private int status;
    private String msg;
    private List<Data> data;
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
    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data {

        private int store_id;
        private int creator;
        private String device_id;
        private String level;
        private String modifier;
        private String alarmTypeName;
        private String storename;
        private String endtime;
        private String remark;
        private String starttime;
        private String mtime;
        private String numerical;
        private String device_name;
        private String category_id;
        private int strore_area_id;
        private int alarm_type;
        private long ctime;
        private int alarm_id;
        private String sort_order;
        private String limits;
        private String status;
        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }
        public int getStore_id() {
            return store_id;
        }

        public void setCreator(int creator) {
            this.creator = creator;
        }
        public int getCreator() {
            return creator;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }
        public String getDevice_id() {
            return device_id;
        }

        public void setLevel(String level) {
            this.level = level;
        }
        public String getLevel() {
            return level;
        }

        public void setModifier(String modifier) {
            this.modifier = modifier;
        }
        public String getModifier() {
            return modifier;
        }

        public void setAlarmTypeName(String alarmTypeName) {
            this.alarmTypeName = alarmTypeName;
        }
        public String getAlarmTypeName() {
            return alarmTypeName;
        }

        public void setStorename(String storename) {
            this.storename = storename;
        }
        public String getStorename() {
            return storename;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }
        public String getEndtime() {
            return endtime;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
        public String getRemark() {
            return remark;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public void setMtime(String mtime) {
            this.mtime = mtime;
        }
        public String getMtime() {
            return mtime;
        }

        public void setNumerical(String numerical) {
            this.numerical = numerical;
        }
        public String getNumerical() {
            return numerical;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }
        public String getDevice_name() {
            return device_name;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }
        public String getCategory_id() {
            return category_id;
        }

        public void setStrore_area_id(int strore_area_id) {
            this.strore_area_id = strore_area_id;
        }
        public int getStrore_area_id() {
            return strore_area_id;
        }

        public void setAlarm_type(int alarm_type) {
            this.alarm_type = alarm_type;
        }
        public int getAlarm_type() {
            return alarm_type;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }
        public long getCtime() {
            return ctime;
        }

        public void setAlarm_id(int alarm_id) {
            this.alarm_id = alarm_id;
        }
        public int getAlarm_id() {
            return alarm_id;
        }

        public void setSort_order(String sort_order) {
            this.sort_order = sort_order;
        }
        public String getSort_order() {
            return sort_order;
        }

        public void setLimits(String limits) {
            this.limits = limits;
        }
        public String getLimits() {
            return limits;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
        }

    }

}
