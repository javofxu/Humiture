package com.example.humiture.utils.helper;

import java.io.Serializable;

/**
 *Time:2019/6/11
 *Author:冰冰凉
 *dec:存储时间
 */
public class SpSaveModel<T> implements Serializable {

    private int saveTime;
    private T value;
    private long currentTime;

    public SpSaveModel() {
    }

    public SpSaveModel(int saveTime, T value, long currentTime) {
        this.saveTime = saveTime;
        this.value = value;
        this.currentTime=currentTime;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public int getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(int saveTime) {
        this.saveTime = saveTime;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    
}
