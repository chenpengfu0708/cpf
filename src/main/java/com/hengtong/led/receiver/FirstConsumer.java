package com.hengtong.led.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @date: 2019/5/10 17:39
 * @author: rain
 * @description: led
 */
@Slf4j
@Component
public class FirstConsumer {
    private static int firstNum = 0;
    private static int secondNum = 0;
    private static int thirdNum = 0;
    Long starTime = new Date().getTime();


    @RabbitListener(queues = {"first-queue","second-queue"}, containerFactory = "rabbitListenerContainerFactory")
    public void handleMessage(String message) throws Exception {
        // 处理消息
        firstNum++;
        Thread.sleep(1);
        if (firstNum == 200000 || firstNum == 200001 || firstNum == 200002 || firstNum == 200003 || firstNum == 200004){
            System.out.println("第一个消费者 :"+message+"消费了"+firstNum+"次");
            System.out.println("时间："+(new Date().getTime() - starTime));
        }
    }



//    @RabbitListener(queues = {"first-queue","second-queue"}, containerFactory = "rabbitListenerContainerFactory")
//    public void handleMessage2(String message) throws Exception {
//        // 处理消息
//        secondNum++;
//        Thread.sleep(1);
//        if (secondNum == 200000 || secondNum == 200001 || secondNum == 200002 || secondNum == 200003 || secondNum == 400000
//                || secondNum == 400001 || secondNum == 400003 || secondNum == 400002 || secondNum == 500000){
//            System.out.println("第二个消费者 :"+message+"消费了"+secondNum+"次");
//            System.out.println("时间："+(new Date().getTime() - starTime));
//        }
//    }


//    @RabbitListener(queues = {"first-queue","second-queue"}, containerFactory = "rabbitListenerContainerFactory")
//    public void handleMessage3(String message) throws Exception {
//        // 处理消息
//        thirdNum++;
//        System.out.println("第三个消费者 :"+message+"消费了"+thirdNum+"次");
//    }




}
