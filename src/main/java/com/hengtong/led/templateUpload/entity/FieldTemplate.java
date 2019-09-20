package com.hengtong.led.templateUpload.entity;


import lombok.Data;

/**
 * 字段模板
 */
@Data
public class FieldTemplate {

    /**字段名*/
    private String fieldName;
    
    /**模板列*/
    private int templateColumn;
    
    /**字段类型*/
    private String fieldType;

    /**是否非空 (是  否)*/
    private String isNotNull;

    /**字段长度限制*/
    private int fieldLong;
}
