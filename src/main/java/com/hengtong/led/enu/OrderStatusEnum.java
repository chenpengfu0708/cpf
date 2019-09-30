package com.hengtong.led.enu;

public enum OrderStatusEnum {
    CLOSE("已关闭"),
    NO_PAY("未支付"),
    PAY("已支付");

    private String label;

    public String getLabel() {
        return label;
    }

    OrderStatusEnum(String label) {
        this.label = label;
    }


    public static String getNameByLabel(String label) {
        for (OrderStatusEnum status : OrderStatusEnum.values()) {
            if (label.equals(status.getLabel())) {
                return status.name();
            }
        }
        return "";
    }


    public static String getLabelByName(String name) {
        for (OrderStatusEnum status : OrderStatusEnum.values()) {
            if (name.equals(status.name())) {
                return status.getLabel();
            }
        }
        return "";
    }
}
