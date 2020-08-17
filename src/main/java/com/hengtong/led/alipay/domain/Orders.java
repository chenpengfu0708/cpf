package com.hengtong.led.alipay.domain;

import lombok.*;

/**
 * 订单实体类
 * @author column
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    private String orderno;
    private String subject;
    private Double amount;
    private String body;


}
