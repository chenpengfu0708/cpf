package com.hengtong.led.dto;

import lombok.Data;


@Data
public class Resource implements Cloneable {
    private Integer id;
    private String name;
    private String type;
    private String resourceUrl;
    private PushBean pushBean;

    @Override
    public Resource clone() {
        Resource resource = null;
        try {
            resource = (Resource) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        resource.pushBean = pushBean.clone();
        return resource;
    }
}
