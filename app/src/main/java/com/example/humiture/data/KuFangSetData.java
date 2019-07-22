package com.example.humiture.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;

/**
 *Time:2019/6/24
 *Author:冰冰凉
 *dec:    库房环境设置需要保存的参数
 *  这些参数需要保存在数据库中，所以需要进行序列化处理
 */

@Entity(indexes = {@Index(value = "name",unique = true)})
public class KuFangSetData{

    @Id(autoincrement = true)
    private Long id;
    private int storeId;
    private String name;
    private String up_WD;
    private String down_WD;
    private String up_SD;
    private String down_SD;
    private String up_PM;
    private String down_PM;
    private String up_TVOC;
    private String down_TVOC;
    private String up_JL;
    private String down_JL;
    private String up_JQ;
    private String down_JQ;
    private String up_EOC2;
    private String down_EOC2;
    private String up_GAS;
    private String down_GAS;


    @Generated(hash = 1907561161)
    public KuFangSetData(Long id, int storeId, String name, String up_WD,
            String down_WD, String up_SD, String down_SD, String up_PM,
            String down_PM, String up_TVOC, String down_TVOC, String up_JL,
            String down_JL, String up_JQ, String down_JQ, String up_EOC2,
            String down_EOC2, String up_GAS, String down_GAS) {
        this.id = id;
        this.storeId = storeId;
        this.name = name;
        this.up_WD = up_WD;
        this.down_WD = down_WD;
        this.up_SD = up_SD;
        this.down_SD = down_SD;
        this.up_PM = up_PM;
        this.down_PM = down_PM;
        this.up_TVOC = up_TVOC;
        this.down_TVOC = down_TVOC;
        this.up_JL = up_JL;
        this.down_JL = down_JL;
        this.up_JQ = up_JQ;
        this.down_JQ = down_JQ;
        this.up_EOC2 = up_EOC2;
        this.down_EOC2 = down_EOC2;
        this.up_GAS = up_GAS;
        this.down_GAS = down_GAS;
    }

    @Generated(hash = 1913900389)
    public KuFangSetData() {
    }


    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getUp_WD() {
        return up_WD;
    }

    public void setUp_WD(String up_WD) {
        this.up_WD = up_WD;
    }

    public String getDown_WD() {
        return down_WD;
    }

    public void setDown_WD(String down_WD) {
        this.down_WD = down_WD;
    }

    public String getUp_SD() {
        return up_SD;
    }

    public void setUp_SD(String up_SD) {
        this.up_SD = up_SD;
    }

    public String getDown_SD() {
        return down_SD;
    }

    public void setDown_SD(String down_SD) {
        this.down_SD = down_SD;
    }

    public String getUp_PM() {
        return up_PM;
    }

    public void setUp_PM(String up_PM) {
        this.up_PM = up_PM;
    }

    public String getDown_PM() {
        return down_PM;
    }

    public void setDown_PM(String down_PM) {
        this.down_PM = down_PM;
    }

    public String getUp_TVOC() {
        return up_TVOC;
    }

    public void setUp_TVOC(String up_TVOC) {
        this.up_TVOC = up_TVOC;
    }

    public String getDown_TVOC() {
        return down_TVOC;
    }

    public void setDown_TVOC(String down_TVOC) {
        this.down_TVOC = down_TVOC;
    }

    public String getUp_JL() {
        return up_JL;
    }

    public void setUp_JL(String up_JL) {
        this.up_JL = up_JL;
    }

    public String getDown_JL() {
        return down_JL;
    }

    public void setDown_JL(String down_JL) {
        this.down_JL = down_JL;
    }

    public String getUp_JQ() {
        return up_JQ;
    }

    public void setUp_JQ(String up_JQ) {
        this.up_JQ = up_JQ;
    }

    public String getDown_JQ() {
        return down_JQ;
    }

    public void setDown_JQ(String down_JQ) {
        this.down_JQ = down_JQ;
    }

    public String getUp_EOC2() {
        return up_EOC2;
    }

    public void setUp_EOC2(String up_EOC2) {
        this.up_EOC2 = up_EOC2;
    }

    public String getDown_EOC2() {
        return down_EOC2;
    }

    public void setDown_EOC2(String down_EOC2) {
        this.down_EOC2 = down_EOC2;
    }

    public String getUp_GAS() {
        return up_GAS;
    }

    public void setUp_GAS(String up_GAS) {
        this.up_GAS = up_GAS;
    }

    public String getDown_GAS() {
        return down_GAS;
    }

    public void setDown_GAS(String down_GAS) {
        this.down_GAS = down_GAS;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
