package com.hengtong.led.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;


@Component
public class RedisUtils {

    private static final String LOCK_KEY_PREFIX = "redis:lock:";

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 判断key是否存在
     */
    public boolean exitst(String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * 根据key删除
     */
    public void remove(String key) {
        if (exitst(key)) {
            redisTemplate.delete(key);
        }
    }


    /**
     * 根据key - value插入缓存
     *
     * @param key:      具体的key
     * @param value:    具体的值
     * @param time:     缓存过期时间
     * @param timeUnit: 缓存过期时间单位  例如天  TimeUnit.DAYS
     */
    public void setKey(String key, Object value, Long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value.toString(), time, timeUnit);
    }

    /**
     * 根据key获取缓存
     */
    public String getKey(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    /**
     * 缓存value自增
     */
    public Long increment(String key, Long num) {
        return redisTemplate.opsForValue().increment(key, num);
    }


    /**
     *
     */
    public void setBitmap(String key, Long uid) {
        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setBit(((RedisSerializer<String>) redisTemplate.getKeySerializer()).serialize(key), uid,
                        true);
                return true;
            }
        });
        redisTemplate.expire(key, 7, TimeUnit.DAYS);
    }


    /**
     * 统计
     */
    public Long bitCount(String key) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.bitCount(((RedisSerializer<String>) redisTemplate.getKeySerializer()).serialize(key));
            }
        });
    }


    /**
     * 加锁操作
     */
    public boolean tryLock(String key) {
        String lockKey = LOCK_KEY_PREFIX + key;
        return redisTemplate.opsForValue().setIfAbsent(lockKey, key);
    }


    /**
     * 加锁并设置过期时间 - 避免出现锁不释放情况
     */
    public boolean tryLock(String key, Long time, TimeUnit unit) {
        String lockKey = LOCK_KEY_PREFIX + key;
        return redisTemplate.opsForValue().setIfAbsent(lockKey, key, time, unit);
    }


    /**
     * 释放锁服务
     */
    public void unLock(String key) {
        String lockKey = LOCK_KEY_PREFIX + key;
        redisTemplate.delete(lockKey);
    }


    /**
     * map操作
     */
    public void setMap(String key, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(key, map);
        redisTemplate.expire(key, 1L, TimeUnit.DAYS);
    }


    /**
     * 获取map操作
     */
    public Map<Object, Object> getMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }


    /**
     * redis事务
     */
    public void redisSessionCallBack() {
        //创建一个事务
        SessionCallback sessionCallback = new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                //告诉redis，接下来开启事务
                redisOperations.multi();
                //相应的redis操作
                redisOperations.opsForValue().set("key1", "测试1", 1L, TimeUnit.MINUTES);
                redisOperations.delete("key1");
                return redisOperations.exec();
            }
        };
        //执行事务
        redisTemplate.execute(sessionCallback);
    }


}
