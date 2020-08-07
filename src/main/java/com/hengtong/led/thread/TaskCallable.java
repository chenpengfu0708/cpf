package com.hengtong.led.thread;

import com.alibaba.fastjson.JSON;
import com.hengtong.led.mybatisPlusAndCreateBean.entity.User;

import java.util.concurrent.Callable;

public class TaskCallable<T,V> implements Callable<V> {
    private String url;
    private T requestBody;
    private Class<V> response;
    private int key;
    public TaskCallable(int key, String url, T requestBody, Class<V> response){
        this.key = key;
        this.url = url;
        this.requestBody = requestBody;
        this.response = response;
    }
    @Override
    public V call() throws Exception {
        //根据url，requestBody,response调用通用接口
        int a = (int)(Math.random()*10000 + 1);
        System.out.println("a=" + a + "," + key);
        Thread.sleep(a);
        User user = new User("" + key, 20, url);
        String u = JSON.toJSONString(user);
        V resp = JSON.parseObject(u, response);

        return resp;
    }
}
