package com.hengtong.led.utils;

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
        String a = "757f180173ecc4fca21f73a8d02fb722" + "zsddcxzcz3jzczxffrdfsf48jnsdfsdf98798l";//测试：zsddcxzcz3jzczxffrdfsf48jnsdfsdf98798l
        //展翅md5
        String a2 = "zc000000002" + "zsddcxzcz3jzczxffrdfsf48jnsdfsdf98798l";
        //百万三者
        String a3 = "00000033-36be-49c0-bc33-08b23821ea02" + "laymedtest8ghjloaid0dk9dka456wq";
        //中山校付通
        String a4 = "00000003-36be-49c0-bc33-08b23821ea04" + "aie9ass9da03jdloaidnc0dk9dkag9bfrs";
        //华康酒保
        String a5 = "00000009-36be-49c0-bc33-08b23821ea12" + "2b6c06752e9ef47a823b8750cf4874ac";
        //邮政
        String a6 = "10000001122" + "473f83c94c2eeb375c6dbfc84425b674";
        //LDO-保之力
        String a7 = "zlh_002" + "ead9ass9da8ghjloaid0dk9dka456wq";
        byte[] c = a.getBytes();
        System.out.println("潮州baq："+md5(a));
        System.out.println("展翅：" + md5(a2));
        System.out.println("百万三者：" + md5(a3));
        System.out.println("中山校付通：" + md5(a4));
        System.out.println("华康酒保：" + md5(a5));
        System.out.println("邮政：" + md5(a6));
        System.out.println("保网：" + md5(a7));
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
