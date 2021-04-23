package com.hengtong.led.utils.rocketMqLocal;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author fu
 */
@Component
@Slf4j
public class MessageListen implements MessageListenerConcurrently {

    @Autowired
    private MessageProcessor messageProcessor;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        log.info("rocketMq：MessageListen.consumeMessage。。。");
        boolean result = false;
        try {
            MessageExt ext = list.get(0);
            result = messageProcessor.handle(ext);
        } catch (Exception e) {
            log.info("消费异常：" + e.toString());
        }
        if (!result) {
            log.info("RocketMq-local：消费失败!!!");
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        } else {
            log.info("RocketMq-local：消费成功!!!");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
    }

}
