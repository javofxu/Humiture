package com.example.humiture.utils;

import java.util.HashMap;
import java.util.Map;

public class NumCountUtil {

    public static String numTo(int num) {

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "一");
        map.put(2, "二");
        map.put(3, "三");
        map.put(4, "四");
        map.put(5, "五");
        map.put(6, "六");
        map.put(7, "七");
        map.put(8, "八");
        map.put(9, "九");
        map.put(0, "十");
        //        String str = String.valueOf(num);

        StringBuilder stringBuilder = new StringBuilder();


        return map.get(num);
    }

    public static String numToDoor(int num) {

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "玻璃门");
        map.put(2, "木门");
        map.put(3, "库房门");
        map.put(4, "前台门");

        //        String str = String.valueOf(num);

        StringBuilder stringBuilder = new StringBuilder();


        return map.get(num);
    }

}
