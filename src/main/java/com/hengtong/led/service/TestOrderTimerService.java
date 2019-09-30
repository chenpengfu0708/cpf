package com.hengtong.led.service;

import com.hengtong.led.Constants;
import com.hengtong.led.entity.Order;
import com.hengtong.led.enu.OrderStatusEnum;
import com.hengtong.led.mapper.OrderMapper;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TestOrderTimerService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RedissonClient redissonClient;


    /**
     * 新建订单
     */
    public void saveOrder(Order order) {
        orderMapper.save(order);
    }


    /**
     * 支付订单
     */
    public void payOrder(Integer orderId) {
        String key = String.format(Constants.ORDER_CHECK_KEY, orderId);
        RLock lock = redissonClient.getLock(key);
        boolean locked = false;
        try {
            locked = lock.tryLock(10, TimeUnit.SECONDS);
            if (locked) {
                System.out.println("支付订单...");
                //支付订单
                orderMapper.updateOrderStatus(OrderStatusEnum.PAY.name(), orderId);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }


    /**
     * 获取订单状态
     */
    public String getOrderStatus(Integer id) {
        Order order = orderMapper.getById(id);
        if (order != null) {
            return OrderStatusEnum.getLabelByName(order.getStatus());
        }
        return "没有这个订单";
    }
}
