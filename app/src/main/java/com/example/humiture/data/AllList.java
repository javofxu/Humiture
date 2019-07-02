package com.example.humiture.data;

import java.util.List;

/**
 *Time:2019/6/19
 *Author:冰冰凉
 *dec: 库房设置类中的设备信息   现在还没有用到
 *
 */
public class AllList {

    private List<DeviceData> deviceData;
    private String name;
    public void setDeviceData(List<DeviceData> deviceData) {
        this.deviceData = deviceData;
    }
    public List<DeviceData> getDeviceData() {
        return deviceData;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public class DeviceData {

        private String brandName;
        private int creator;
        private int modifier;
        private int pId;
        private String remark;
        private int storeId;
        private int mtime;
        private int deviceId;
        private String categoryName;
        private int stroreAreaId;
        private int brandId;
        private String sortOrder;
        private String name;
        private String statusName;
        private int ctime;
        private int categoryId;
        private String status;
        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }
        public String getBrandName() {
            return brandName;
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

        public void setPId(int pId) {
            this.pId = pId;
        }
        public int getPId() {
            return pId;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
        public String getRemark() {
            return remark;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }
        public int getStoreId() {
            return storeId;
        }

        public void setMtime(int mtime) {
            this.mtime = mtime;
        }
        public long getMtime() {
            return mtime;
        }

        public void setDeviceId(int deviceId) {
            this.deviceId = deviceId;
        }
        public int getDeviceId() {
            return deviceId;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }
        public String getCategoryName() {
            return categoryName;
        }

        public void setStroreAreaId(int stroreAreaId) {
            this.stroreAreaId = stroreAreaId;
        }
        public int getStroreAreaId() {
            return stroreAreaId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }
        public int getBrandId() {
            return brandId;
        }

        public void setSortOrder(String sortOrder) {
            this.sortOrder = sortOrder;
        }
        public String getSortOrder() {
            return sortOrder;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }
        public String getStatusName() {
            return statusName;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }
        public long getCtime() {
            return ctime;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }
        public int getCategoryId() {
            return categoryId;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
        }

    }

}
