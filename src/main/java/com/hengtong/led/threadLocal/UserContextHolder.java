package com.hengtong.led.threadLocal;

import org.springframework.stereotype.Component;

/**
 * 保存上下文的ThreadLocal
 */
@Component
public class UserContextHolder {

    public static ThreadLocal<UserContext> context = new ThreadLocal<>();

    public UserContext getContext() {
        return context.get();
    }

    public void setContext(UserContext userContext) {
        context.set(userContext);
    }

}
