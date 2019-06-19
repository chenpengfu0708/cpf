package com.hengtong.led.utils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class UDPSendUtils {

    public static void main(String[] args) {
        try{
            //建立udp的服务
            DatagramSocket datagramSocket = new DatagramSocket();
            String text = "hello";
            //创建了一个数据包
            DatagramPacket packetLeft = new DatagramPacket(text.getBytes(), text.getBytes().length, InetAddress.getByName("192.168.1.201") , 9998);
            datagramSocket.send(packetLeft);
            datagramSocket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
