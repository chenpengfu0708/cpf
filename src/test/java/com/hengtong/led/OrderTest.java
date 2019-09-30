package com.hengtong.led;

import com.hengtong.led.entity.Order;
import com.hengtong.led.enu.OrderStatusEnum;
import com.hengtong.led.service.OrderTimer;
import com.hengtong.led.service.TestOrderTimerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderTest {

    @Autowired
    private TestOrderTimerService testOrderTimerService;


    @Test
    public void saveOrder(){
        Order order = new Order();
        order.setName("订单1");
        order.setStatus(OrderStatusEnum.NO_PAY.name());
        order.setCreateTime(new Date());
        System.out.println(order);
        testOrderTimerService.saveOrder(order);
        System.out.println(order);

    }


    @Test
    public void getOrderStatus(){
        System.out.println(testOrderTimerService.getOrderStatus(1));
    }
}
