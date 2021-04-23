package com.hengtong.led.utils.rocketMqLocal;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author fu
 */
@RestController
public class RocketMqLocalController {

    @Autowired
    @Qualifier("rocketMQProducer")
    RocketMQProducer rocketMQProducer;

    @GetMapping("/myRocketMqLocalTest")
    public void TestSend(String str) {
        DefaultMQProducer producer = rocketMQProducer.getRocketMQProducer();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Message message = new Message("MY_TEST_TOPIC", "test", str.getBytes());
        try {
            producer.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
