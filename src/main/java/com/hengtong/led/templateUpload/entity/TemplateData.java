package com.hengtong.led.templateUpload.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 模板数据
 */
@Entity
@Data
public class TemplateData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**实体对象*/
    private String entityObject;
    
    /**开始行*/
    private int startRow;
    
    /**字段*/
    private String field;
}
