package com.hengtong.led.utils.rocketMqLocal;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;

/**
 * @author fu
 */
@Service
@Slf4j
public class MessageProcessorImpl implements MessageProcessor {
    @Override
    public boolean handle(MessageExt messageExt) {
        // 收到的body（消息体），字节类型，需转为String
        String result = new String(messageExt.getBody());
        log.info("本地RocketMq：监听到了消息，消息为："+ result);
        return "成功".equals(result);
    }
}
