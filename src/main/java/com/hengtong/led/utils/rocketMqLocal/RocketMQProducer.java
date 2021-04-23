package com.hengtong.led.utils.rocketMqLocal;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 生产者配置
 * @author fu
 */
@Configuration
@Slf4j
public class RocketMQProducer {

    @Value("${rocketmq.producer.groupName}")
    private String groupName;

    @Value("${rocketmq.producer.namesrvAddr}")
    private String nameserAddr;

    @Value("${rocketmq.producer.instanceName}")
    private String instanceName;

    @Value("${rocketmq.producer.maxMessageSize}")
    private int maxMessageSize;

    @Value("${rocketmq.producer.sendMsgTimeout}")
    private int sendMsgTimeout;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public DefaultMQProducer getRocketMQProducer() {
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(nameserAddr);
        producer.setInstanceName(instanceName);
        producer.setMaxMessageSize(maxMessageSize);
        producer.setSendMsgTimeout(sendMsgTimeout);
        producer.setVipChannelEnabled(false);
        log.info("RocketMq-Local================>生产者创建完成，ProducerGroupName{}<================", groupName);
        return producer;
    }

}
