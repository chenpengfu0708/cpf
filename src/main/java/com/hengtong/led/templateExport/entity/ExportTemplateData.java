package com.hengtong.led.templateExport.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class ExportTemplateData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 实体对象
     */
    private String entityObject;

    /**
     * 表头开始行（从0开始数）
     */
    private Integer headStartRow;

    /**
     * 表头
     */
    private String headFiled;

    /**
     * 数据开始行（从0开始数）
     */
    private Integer dataStartRow;

    /**
     * 数据
     */
    private String dataField;

}
