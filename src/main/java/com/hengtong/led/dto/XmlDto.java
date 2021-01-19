package com.hengtong.led.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author fu
 */
@Data
@XmlRootElement(name = "request")
public class XmlDto {
    private Resource resource;
}
