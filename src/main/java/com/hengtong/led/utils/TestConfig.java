package com.hengtong.led.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "testcon")
@Data
public class TestConfig {

    private String name;

    private String address;
}
