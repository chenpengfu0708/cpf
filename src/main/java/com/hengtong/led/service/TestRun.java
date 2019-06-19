package com.hengtong.led.service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class TestRun implements Runnable {

    @Override
    public void run() {
        System.out.println("UDP聊天工具开启。。。");
        try{
            //建立udp的服务
            DatagramSocket ds = new DatagramSocket(9999);
            while (true){
                //创建字节数据
                byte[] data = new byte[1024];
                // 创建数据包对象，传递字节数组
                DatagramPacket dp = new DatagramPacket(data, data.length);
                //调用ds对象的方法receive传递数据包
                ds.receive(dp);

                //获取发送端的IP地址对象
                String ip = dp.getAddress().getHostAddress();
                int port = dp.getPort();

                //获取接收到的字节个数
                int length = dp.getLength();
                System.out.println("对方：" + new String(data, 0, length));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public TestRun(){
        super();
    }


}
