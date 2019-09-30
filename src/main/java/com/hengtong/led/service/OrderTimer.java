package com.hengtong.led.service;

import com.hengtong.led.Constants;
import com.hengtong.led.entity.Order;
import com.hengtong.led.enu.OrderStatusEnum;
import com.hengtong.led.mapper.OrderMapper;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Service
public class OrderTimer {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RedissonClient redissonClient;

    public void checkOrder(Integer orderId, Long time) {
        System.out.println("订单检测开始......");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String key = String.format(Constants.ORDER_CHECK_KEY, orderId);
                RLock lock = redissonClient.getLock(key);
                boolean locked = false;
                try {
                    locked = lock.tryLock(10, TimeUnit.SECONDS);
                    if (locked) {
                        System.out.println("调度任务：获得锁...");
                        Order order = orderMapper.getById(orderId);
                        //如果订单存在并且订单状态为未支付，关闭订单
                        if (order != null && order.getStatus().equals(OrderStatusEnum.NO_PAY.name())) {
                            System.out.println("调度任务：关闭订单...");
                            orderMapper.updateOrderStatus(OrderStatusEnum.CLOSE.name(), orderId);
                        } else {
                            System.out.println("调度任务：订单不存在或订单状态不为 未支付...");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (locked) {
                        System.out.println("调度任务：释放锁...");
                        lock.unlock();
                    }
                }
                System.out.println("订单检测结束......");
            }
        }, time);
    }
}
