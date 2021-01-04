package com.hengtong.led.utils;

import com.hengtong.led.dto.PushBean;
import com.hengtong.led.dto.Resource;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class Md5Util {


    /**
     * md5加密
     */
    public static String md5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public static void main(String[] args) {
        String a = "1429abdc-36be-49c0-bc33-08b23821ea00" + "ead9ass9da8ghjloaid0dk9dka456wq";
        //展翅md5
        String a2 = "6624abdc-36be-49c0-bc33-08b23821ea02" + "zsddcxzcz3jzczxffrdfsf48jnsdfsdf98798l";
        byte[] c = a.getBytes();
        System.out.println(c.toString());
        System.out.println(md5(a));
        System.out.println(md5(a2));
//        System.out.println(StringEscapeUtils.unescapeHtml("https://cash.yeepay.com/cashier/std?sign=xFR6d-VooiId6WolFYU_u_zlO4Vccg5-h9q5O-XIXpMStdwizYQylBbUR_CtskDDbE1PWK1mKSBVsZi6ERKYlkppwC5vaev12HYAHpR6WHTvv-Jp9nkS27QhTWJzIlqJHvIkDp9mdEm8hRJ_ldNMa6U3PpMEnQSoAxVVXAVkYt65dcbTl6qUaT6VUqfsLMpp5gGrSjUgTgLKF9214PHuOLqbzArICqIuCAkUCafCKk1IoFBZ9sCVd1OYFfhzmmgM1JGpEhZjY9IdsZhSx2BXw-kV2NsrnIaDBmE6LjRVGdgL3Z_FFNOqBIYlr9c0IdFMDApIVXXpxyovAM14Rv5AFQ$SHA256&amp;merchantNo=10000466938&amp;token=CD5DAAA0C8EB19254859C0E82EB27EDB29A0DF177F4092634244B931456EDA1A&amp;timestamp=1607676060&amp;directPayType=&amp;cardType=&amp;userNo=83045E7A82B2F13A9E2EEAC9405975B2&amp;userType=USER_ID&amp;ext={&quot;appId&quot;:&quot;&quot;,&quot;openId&quot;:&quot;&quot;,&quot;clientId&quot;:&quot;&quot;}"));
//        System.out.println("=====================================");
//        Resource resource = new Resource();
//        resource.setName("123");
//        PushBean pushBean = new PushBean();
//        pushBean.setTitle("title");
//        resource.setPushBean(pushBean);
//        Resource resource1 = resource.clone();
////        BeanUtils.copyProperties(resource, resource1);
//        System.out.println(resource);
//        System.out.println(resource1);
////        PushBean pushBean1 = new PushBean();
////        BeanUtils.copyProperties(resource.getPushBean(), pushBean1);
////        resource1.setPushBean(pushBean1);
//        resource1.getPushBean().setTitle("title2222222222222222");
//        System.out.println(resource);
//        System.out.println(resource1);
    }
}
