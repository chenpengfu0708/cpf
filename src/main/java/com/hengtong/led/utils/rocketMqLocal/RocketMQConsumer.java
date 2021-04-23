package com.hengtong.led.utils.rocketMqLocal;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消费者配置
 * @author fu
 */
@Configuration
@Slf4j
public class RocketMQConsumer {

    @Autowired
    private MessageListen messageListen;

    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;

    @Value("${rocketmq.consumer.groupName}")
    private String groupName;

    @Value("${rocketmq.consumer.topic}")
    private String topic;

    @Value("${rocketmq.consumer.tag}")
    private String tag;

    @Value("${rocketmq.consumer.consumeThreadMin}")
    private int consumeThreadMin;

    @Value("${rocketmq.consumer.consumeThreadMax}")
    private int consumeThreadMax;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public DefaultMQPushConsumer getRocketMQConsumer()
    {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setVipChannelEnabled(false);
        // 我们自己实现的监听类
        consumer.registerMessageListener(messageListen);
        try {
            consumer.subscribe(topic,tag);
            log.info("RocketMq-Local================>消费者创建完成，ConsumerGroupName{}<================",groupName);
            log.info("RocketMq-Local============>消费者监听开始,groupName:{},topic:{}<============",groupName,topic);
        } catch (MQClientException e) {
            log.error("消费者启动失败");
            e.printStackTrace();
        }
        return consumer;
    }

}
