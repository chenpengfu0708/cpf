package com.hengtong.led.templateExport.dto;

import lombok.Data;

@Data
public class DataField {

    /**
     * 是否时间类型
     */
    private String isTime;

    /**
     * 时间表达式
     */
    private String pattern;

    /**
     * 属性名
     */
    private String fieldName;

    /**
     * 列
     */
    private Integer templateColumn;


    private String isMergeCell;

    private Integer firstCol;

    private Integer lastCol;

}
