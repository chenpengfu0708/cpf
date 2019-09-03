package com.hengtong.led.service;

import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.hengtong.led.config.JpushConfig;
import com.hengtong.led.dto.PushBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyJiGuangPushServiceImpl {

    @Autowired
    private JpushConfig jPushConfig;


    /**
     * 广播 (所有平台，所有设备, 不支持附加信息)
     * @param pushBean 推送内容
     * @return
     */
    public boolean pushAll(PushBean pushBean){
        return sendPush(PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setNotification(Notification.alert(pushBean.getAlert()))
                .build());
    }

    /**
     * ios广播
     * @param pushBean 推送内容
     * @return
     */
    public boolean pushIos(PushBean pushBean){
        return sendPush(PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.all())
                .setNotification(Notification.ios(pushBean.getAlert(), pushBean.getExtras()))
                .build());
    }

    /**
     * ios通过registid推送 (一次推送最多 1000 个)
     * @param pushBean 推送内容
     * @param registids 推送id
     * @return
     */
    public boolean pushIos(PushBean pushBean, String... registids){
        return sendPush(PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.registrationId(registids))
                .setNotification(Notification.ios(pushBean.getAlert(), pushBean.getExtras()))
                .build());
    }


    /**
     * android广播
     * @param pushBean 推送内容
     * @return
     */
    public boolean pushAndroid(PushBean pushBean){
        return sendPush(PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.android(pushBean.getAlert(), pushBean.getTitle(), pushBean.getExtras()))
                .build());
    }

    /**
     * android通过registid推送 (一次推送最多 1000 个)
     * @param pushBean 推送内容
     * @param registids 推送id
     * @return
     */
    public boolean pushAndroid(PushBean pushBean, String ... registids){
        return sendPush(PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.registrationId(registids))
                .setNotification(Notification.android(pushBean.getAlert(), pushBean.getTitle(), pushBean.getExtras()))
                .build());
    }

    /**
     * 调用api推送
     * @param pushPayload 推送实体
     * @return
     */
    public boolean sendPush(PushPayload pushPayload){
        log.info("发送极光推送请求: {}", pushPayload);
        PushResult result = null;
        try{
            result = jPushConfig.getJPushClient().sendPush(pushPayload);
        } catch (APIConnectionException e) {
            log.error("极光推送连接异常: ", e);
        } catch (APIRequestException e) {
            log.error("极光推送请求异常: ", e);
        }
        if (result!=null && result.isResultOK()) {
            log.info("极光推送请求成功: {}", result);
            return true;
        }else {
            log.info("极光推送请求失败: {}", result);
            return false;
        }
    }

}
