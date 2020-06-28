package com.hengtong.led.utils.TCUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TCMain {
    private final static String TITLE="友情提示";

    private final static TcUtil tcUtil = new TcUtil();


    public static void main(String[] arg) {
        while (true) {
            try {
                tcUtil.show(TITLE, "喝水啊！");
                Thread.sleep(10 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
