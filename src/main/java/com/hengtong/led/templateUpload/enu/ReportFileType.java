package com.hengtong.led.templateUpload.enu;

/**
 * 上报文件类型
 */
public enum ReportFileType {

    DOORPLATE_QR_CODE("门牌二维码"),
    HOUSEHOLDER("户主信息"),
    INHABITANT_POPULATION("常住人口"),
    STANDARD_CONDITIONS("标准生活生产条件"),
    FLOATING_POPULATION("流动人口");

    private String name;

    public String getName() {
        return name;
    }

    private ReportFileType(String name){
        this.name = name;
    }
}
