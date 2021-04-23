package com.hengtong.led.utils.rocketMqLocal;

import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author fu
 */
public interface MessageProcessor {
    boolean handle(MessageExt messageExt);
}
