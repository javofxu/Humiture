package com.example.humiture.data;

import com.contrarywind.interfaces.IPickerViewData;

/**
 *Time:2019/6/21
 *Author:冰冰凉
 *dec:  库房环境数字选择
 */
public class NumberData implements IPickerViewData {

    private int id;

    private String number;

    public NumberData(int id, String number) {
        this.id = id;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    @Override
    public String getPickerViewText() {
        return number;
    }
}
