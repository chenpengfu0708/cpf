package com.hengtong.led.controller;

import com.hengtong.led.entity.Order;
import com.hengtong.led.enu.OrderStatusEnum;
import com.hengtong.led.service.OrderTimer;
import com.hengtong.led.service.TestOrderTimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 *  模拟场景：订单下单，某个时段后检测订单是否支付
 */
@Controller
public class TestOrderTimerController {

    @Autowired
    private TestOrderTimerService testOrderTimerService;

    @Autowired
    private OrderTimer orderTimer;


    @GetMapping("/saveOrder")
    @ResponseBody
    public Integer saveOrder(){
        for (int i = 0; i < 100000; i++) {
            Integer time = 30 * 1000;
            Order order = new Order();
            order.setName("订单" + i);
            order.setStatus(OrderStatusEnum.NO_PAY.name());
            order.setCreateTime(new Date());
            testOrderTimerService.saveOrder(order);
            orderTimer.checkOrder(order.getId(), time.longValue());
        }
        return 0;
    }


    @GetMapping("/payOrder")
    @ResponseBody
    public Integer payOrder(Integer id){
        testOrderTimerService.payOrder(id);
        return 0;
    }


    @GetMapping("/getOrderStatus")
    @ResponseBody
    public String getOrderStatus(Integer id){
        return testOrderTimerService.getOrderStatus(id);
    }

}
