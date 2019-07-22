package com.example.humiture.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class DeviceData implements Serializable {

    private String brandName;
    private int creator;
    private int modifier;
    private int pId;
    private String remark;
    private int storeId;
    private String mtime;
    private int deviceId;
    private String categoryName;
    private int stroreAreaId;
    private int brandId;
    private String sortOrder;
    private String name;
    private String statusName;
    private String ctime;
    private int categoryId;
    private String status;

    protected DeviceData(Parcel in) {
        brandName = in.readString();
        creator = in.readInt();
        modifier = in.readInt();
        pId = in.readInt();
        remark = in.readString();
        storeId = in.readInt();
        mtime = in.readString();
        deviceId = in.readInt();
        categoryName = in.readString();
        stroreAreaId = in.readInt();
        brandId = in.readInt();
        sortOrder = in.readString();
        name = in.readString();
        statusName = in.readString();
        ctime = in.readString();
        categoryId = in.readInt();
        status = in.readString();
    }


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

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }
    public String getMtime() {
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

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
    public String getCtime() {
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
