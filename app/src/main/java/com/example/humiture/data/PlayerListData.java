package com.example.humiture.data;

import java.util.List;

/**
 * Time:2019/7/5
 * Author:冰冰凉
 * dec:  摄像头实体类
 */
public class PlayerListData {

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

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

    public class Data {

        private String port;
        private String status;
        private String remark;
        private int sortOrder;
        private int categoryId;
        private String mtime;
        private String password;
        private String ctime;
        private String brandId;
        private String brandName;
        private String ip;
        private int creator;
        private int modifier;
        private int stroreAreaId;
        private String pId;
        private String categoryName;
        private String username;
        private String name;
        private String channel;
        private int storeId;
        private int deviceId;

        public void setPort(String port) {
            this.port = port;
        }

        public String getPort() {
            return port;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRemark() {
            return remark;
        }

        public void setSortOrder(int sortOrder) {
            this.sortOrder = sortOrder;
        }

        public int getSortOrder() {
            return sortOrder;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setMtime(String mtime) {
            this.mtime = mtime;
        }

        public String getMtime() {
            return mtime;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getCtime() {
            return ctime;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getIp() {
            return ip;
        }

        public void setCreator(int creator) {
            this.creator = creator;
        }

        public int getCreator() {
            return creator;
        }

        public void setModifier(int modifier) {
            this.modifier = modifier;
        }

        public int getModifier() {
            return modifier;
        }

        public void setStroreAreaId(int stroreAreaId) {
            this.stroreAreaId = stroreAreaId;
        }

        public int getStroreAreaId() {
            return stroreAreaId;
        }

        public void setPId(String pId) {
            this.pId = pId;
        }

        public String getPId() {
            return pId;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getChannel() {
            return channel;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setDeviceId(int deviceId) {
            this.deviceId = deviceId;
        }

        public int getDeviceId() {
            return deviceId;
        }

    }

}
