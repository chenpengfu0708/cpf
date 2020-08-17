package com.hengtong.led.alipay.service;


import com.hengtong.led.alipay.domain.Orders;

/**
 * @author column
 */
public interface PayService {

    /**
     * 支付实现
     * @param order
     * @return
     */
    String pay(Orders order);

}
