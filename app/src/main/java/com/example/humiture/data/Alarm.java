package com.example.humiture.data;


import java.io.Serializable;
import java.util.List;

/**
 *Time:2019/6/14
 *Author:冰冰凉
 *dec:报警统计 更多
 */
public class Alarm implements Serializable {

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

        private List<ListAlarm> list;
        public void setList(List<ListAlarm> list) {
            this.list = list;
        }
        public List<ListAlarm> getList() {
            return list;
        }

        public class ListAlarm {

            private String created;
            private String alarm_value;
            private int alarmId;
            private String alarmType;
            public void setCreated(String created) {
                this.created = created;
            }
            public String getCreated() {
                return created;
            }

            public void setAlarm_value(String alarm_value) {
                this.alarm_value = alarm_value;
            }
            public String getAlarm_value() {
                return alarm_value;
            }

            public void setAlarmId(int alarmId) {
                this.alarmId = alarmId;
            }
            public int getAlarmId() {
                return alarmId;
            }

            public void setAlarmType(String alarmType) {
                this.alarmType = alarmType;
            }
            public String getAlarmType() {
                return alarmType;
            }

        }

    }
}
