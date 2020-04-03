package com.hengtong.led.mybatisPlus.factory;

import com.hengtong.led.dto.HandlerDto;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "factory.handler")
public class HandlerFactory {
    private Map<String, HandlerDto> factory;
}
