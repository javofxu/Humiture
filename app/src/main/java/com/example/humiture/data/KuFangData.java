package com.example.humiture.data;

import java.io.Serializable;
import java.util.List;

/**
 *Time:2019/6/19
 *Author:冰冰凉
 *dec:  库房设置的实体类
 */
public class KuFangData implements Serializable {

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

        private List<AllList> allList;
        private int maxLength;
        public void setAllList(List<AllList> allList) {
            this.allList = allList;
        }
        public List<AllList> getAllList() {
            return allList;
        }

        public void setMaxLength(int maxLength) {
            this.maxLength = maxLength;
        }
        public int getMaxLength() {
            return maxLength;
        }

    }

}
