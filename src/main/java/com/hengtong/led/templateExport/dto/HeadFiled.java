package com.hengtong.led.templateExport.dto;

import lombok.Data;

@Data
public class HeadFiled {

    private String value;

    private Integer templateColumn;

    private Integer columnWidth;

    private String isMergeCell;

    private Integer firstRow;

    private Integer lastRow;

    private Integer firstCol;

    private Integer lastCol;

}
