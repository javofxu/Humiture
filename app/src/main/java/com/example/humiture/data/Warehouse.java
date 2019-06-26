package com.example.humiture.data;


import java.util.List;

/**
 * Created by 许格.
 * Date on 2019/5/21.
 * dec:库房列表
 */
public class Warehouse {

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

        private int storeId;
        private String code;
        private String name;
        private String buildingNo;
        private String floorNo;
        private int sortOrder;
        private String status;
        private String remark;
        private int creator;
        private long ctime;
        private int modifier;
        private long mtime;
        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }
        public int getStoreId() {
            return storeId;
        }

        public void setCode(String code) {
            this.code = code;
        }
        public String getCode() {
            return code;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setBuildingNo(String buildingNo) {
            this.buildingNo = buildingNo;
        }
        public String getBuildingNo() {
            return buildingNo;
        }

        public void setFloorNo(String floorNo) {
            this.floorNo = floorNo;
        }
        public String getFloorNo() {
            return floorNo;
        }

        public void setSortOrder(int sortOrder) {
            this.sortOrder = sortOrder;
        }
        public int getSortOrder() {
            return sortOrder;
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

        public void setCreator(int creator) {
            this.creator = creator;
        }
        public int getCreator() {
            return creator;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }
        public long getCtime() {
            return ctime;
        }

        public void setModifier(int modifier) {
            this.modifier = modifier;
        }
        public int getModifier() {
            return modifier;
        }

        public void setMtime(long mtime) {
            this.mtime = mtime;
        }
        public long getMtime() {
            return mtime;
        }

    }
}
