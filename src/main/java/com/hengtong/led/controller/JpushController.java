package com.hengtong.led.controller;

import com.hengtong.led.dto.PushBean;
import com.hengtong.led.service.JiGuangPushServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class JpushController {

    @Autowired
    private JiGuangPushServiceImpl jiGuangPushService;

    /**
     * 广播推送
     * */
    @GetMapping("/jpush")
    @ResponseBody
    public boolean test(String title, String content){
        Map<String, String> extrasMap = new HashMap<>();
        extrasMap.put("test", "第一个");
        PushBean pushBean = new PushBean();
        pushBean.setAlert(content);
        pushBean.setTitle(title);
        pushBean.setExtras(extrasMap);
        return jiGuangPushService.pushAndroid(pushBean);
    }

    /**
     * 根据用户下的app唯一regid推送
     * */
    @GetMapping("/jpush_one")
    @ResponseBody
    public boolean test(String title, String content, String id){
        Map<String, String> extrasMap = new HashMap<>();
        extrasMap.put("test", "第一个");
        PushBean pushBean = new PushBean();
        pushBean.setAlert(content);
        pushBean.setTitle(title);
        pushBean.setExtras(extrasMap);
        return jiGuangPushService.pushAndroid(pushBean, id);
    }
}
