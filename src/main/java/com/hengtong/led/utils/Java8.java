package com.hengtong.led.utils;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @date: 2019/5/8 17:31
 * @author: rain
 * @description: led
 */
@Component
public class Java8 {

    /**
     * 用于java8根据对象属性去重
     * */
    public <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
