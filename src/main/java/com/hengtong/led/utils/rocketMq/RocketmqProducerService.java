package com.hengtong.led.utils.rocketMq;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.OnExceptionContext;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 消息生产者，可与消费者分离；
 * 消息生产的三种方式：可靠同步发送，可靠异步发送，单向发送
 * @author cpf
 * @Date 2019年6月25日
 *
 */
@Service
public class RocketmqProducerService {

    private Logger logger = LoggerFactory.getLogger(RocketmqProducerService.class);

    @Value("${rocketmq.msgTopic}")
    private String msgTopic;

    @Autowired
    private RocketmqProducerInit rocketmqProducerInit;

    public String tag = "*";//生产标签，可自定义，默认通配

    /**
     * 同步发送实体对象消息
     * 可靠同步发送：同步发送是指消息发送方发出数据后，会在收到接收方发回响应之后才发下一个数据包的通讯方式；
     * 特点：速度快；有结果反馈；数据可靠；
     * 应用场景：应用场景非常广泛，例如重要通知邮件、报名短信通知、营销短信系统等；
     */
    public boolean sendMsg(String msg) {
        Long startTime = System.currentTimeMillis();
        Message message = new Message(msgTopic, tag, msg.getBytes());
        SendResult sendResult = rocketmqProducerInit.getProducer().send(message);
        if (sendResult != null) {
            System.out.println("消息发送成功，Topic:" + message.getTopic() + " ，msgId: " + sendResult.getMessageId());
        } else {
            logger.warn(".sendResult is null.........");
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("单次生产耗时："+(endTime-startTime)/1000);
        return true;
    }

    /**
     * 异步发送消息
     * 可靠异步发送：发送方发出数据后，不等接收方发回响应，接着发送下个数据包的通讯方式；
     * 特点：速度快；有结果反馈；数据可靠；
     * 应用场景：异步发送一般用于链路耗时较长,对 rt响应时间较为敏感的业务场景,例如用户视频上传后通知启动转码服务,转码完成后通知推送转码结果等；
     * @param msg
     * @return
     */
    public boolean sendMsgAsy(String msg) {
        Long startTime = System.currentTimeMillis();
        Message message = new Message(msgTopic, tag, msg.getBytes());
        rocketmqProducerInit.getProducer().sendAsync(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                ///消息发送成功
                System.out.println("send message success. topic=" + sendResult.getMessageId());
            }
            @Override
            public void onException(OnExceptionContext context) {
                //消息发送失败
                System.out.println("send message failed. execption=" + context.getException());
            }
        });
        Long endTime = System.currentTimeMillis();
        System.out.println("单次生产耗时："+(endTime-startTime)/1000);
        return true;
    }

    /**
     * 单向发送
     * 单向发送：只负责发送消息，不等待服务器回应且没有回调函数触发，即只发送请求不等待应答；此方式发送消息的过程耗时非常短，一般在微秒级别；
     * 特点：速度最快，耗时非常短，毫秒级别；无结果反馈；数据不可靠，可能会丢失；
     * 应用场景：适用于某些耗时非常短，但对可靠性要求并不高的场景，例如日志收集；
     * @return
     */
    public boolean sendMsgOneway(String msg) {
        Long startTime = System.currentTimeMillis();
        Message message = new Message(msgTopic, tag, msg.getBytes());
        rocketmqProducerInit.getProducer().sendOneway(message);
        Long endTime = System.currentTimeMillis();
        System.out.println("单次生产耗时："+(endTime-startTime)/1000);
        return true;
    }

}
