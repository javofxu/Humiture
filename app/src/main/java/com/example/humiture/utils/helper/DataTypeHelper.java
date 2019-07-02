package com.example.humiture.utils.helper;

import com.example.humiture.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 许格.
 * Date on 2019/5/17.
 * dec:保存数据类型的颜色和名字
 */
public class DataTypeHelper {
    /**
     * 获取环境数据颜色
     * @return
     */
    public static List<Integer> getColors(){
        List<Integer> color = new ArrayList<>();
        color.add(R.color.index_tem);
        color.add(R.color.index_hum);
        color.add(R.color.index_pm);
        color.add(R.color.index_colony);
        color.add(R.color.index_formaldehyde);
        color.add(R.color.index_tvoc);
        color.add(R.color.index_harmful);
        color.add(R.color.index_eco);
        return color;
    }

    /**
     * 获取环境数据名称
     * @return
     */
    public static List<String> getDataNames(){
        List<String> name = new ArrayList<>();
        name.add("温度");
        name.add("湿度");
        name.add("PM2.5");
        name.add("菌落");
        name.add("甲醛");
        name.add("TVOC");
        name.add("有害气体");
        name.add("ECO2");
        return name;
    }

    /**
     * 获取环境数据字段名称
     * @return
     */
    public static List<String> getDataTypes(){
        List<String> name = new ArrayList<>();
        name.add("temperature");
        name.add("humidity");
        name.add("pm2");
        name.add("colony");
        name.add("formaldehyde");
        name.add("tvoc");
        name.add("harmful");
        name.add("eco2");
        return name;
    }

    /**
     * 获取环境数据单位
     * @return
     */
    public static List<String> getDataUnit(){
        List<String> unit = new ArrayList<>();
        unit.add("℃");
        unit.add("%RH");
        unit.add("ug/m³");
        unit.add("mg/m³");
        unit.add("cfu/mL");
        return unit;
    }

    /**
     * 获取库房列表
     * @return
     */
    public static List<String> getWarehouse(){
        List<String> warehouse = new ArrayList<>();
        warehouse.add("一库房");
        warehouse.add("二库房");
        warehouse.add("三库房");
        return warehouse;
    }

    /**
     * 获取操作列表背景
     * @return
     */
    public static List<Integer> getBackground(){
        List<Integer> background = new ArrayList<>();
        background.add(R.mipmap.oper_no1);
        background.add(R.mipmap.oper_no6);
        background.add(R.mipmap.oper_no4);
        background.add(R.mipmap.oper_no5);
        background.add(R.mipmap.oper_no2);
        background.add(R.mipmap.oper_no7);
        return background;
    }

    /**
     * 获取视频列表的背景
     * @return
     */
    public static List<Integer> getPlayerBackground(){
        List<Integer> background = new ArrayList<>();
        background.add(R.mipmap.item_play_bg1);
        background.add(R.mipmap.item_play_bg2);
        background.add(R.mipmap.item_play_bg3);
        background.add(R.mipmap.item_play_bg4);
        return background;
    }

}
