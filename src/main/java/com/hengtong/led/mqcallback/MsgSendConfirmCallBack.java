//package com.hengtong.led.mqcallback;
//
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//
//
//public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        if (ack) {
//            System.out.println("消息消费成功");
//        } else {
//            System.out.println("消息消费失败:" + cause+"\n重新发送");
//        }
//    }
//

//}
