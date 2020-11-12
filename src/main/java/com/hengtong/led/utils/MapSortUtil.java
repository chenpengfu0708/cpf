package com.hengtong.led.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 排序map
 * @author fu
 */
public class MapSortUtil {


    public static Map<String, String> ketSort(Map<String, String> map) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        treeMap.putAll(map);
        return treeMap;
    }


}
