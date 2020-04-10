package com.hengtong.led.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Component
public class IpAddressUtil {

    public String getIP(HttpServletRequest request) {
        //识别通过HTTP代理或负载均衡方式
        String ip = request.getHeader("x-forwarded-for");
        if (!checkIP(ip)) {
            //apache http做代理时一般会加上Proxy-Client-IP
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            //weblogic插件加上的头
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            ip = request.getRemoteAddr();
        }
        if (!checkIP(ip)) {
            //从命令行获取本机IP
            ip = getLocalIPForCMD();
        }
        return ip;
    }

    private static boolean checkIP(String ip) {
        if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)
                || ip.split(".").length != 4) {
            return false;
        }
        return true;
    }


    public static String getLocalIPForCMD() {
        StringBuilder sb = new StringBuilder();
        String command = "cmd.exe /c ipconfig | findstr IPv4";
        try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                line = line.substring(line.lastIndexOf(":") + 2, line.length());
                sb.append(line);
            }
            br.close();
            p.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
