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

    public static List<String> getWarehouse(){
        List<String> warehouse = new ArrayList<>();
        warehouse.add("一库房");
        warehouse.add("二库房");
        warehouse.add("三库房");
        return warehouse;
    }
}
