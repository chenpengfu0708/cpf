package com.hengtong.led.templateUpload.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class DoorplateQrcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**姓名*/
    private String name;

    /**地址*/
    private String address;

    /**二维码编号*/
    private String serialNumber;

    /**经度*/
    private String longitude;

    /**纬度*/
    private String latitude;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    public DoorplateQrcode(){}

    public DoorplateQrcode(String address){
        this.address = address;
        this.createTime = new Date();
        this.updateTime = new Date();
    }

}
