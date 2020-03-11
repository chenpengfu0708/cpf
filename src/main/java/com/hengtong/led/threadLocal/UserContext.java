package com.hengtong.led.threadLocal;

import com.hengtong.led.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户上下文，保存登陆id
 */
@Component
public class UserContext {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public Long getUserId() {

        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader(Constants.TOKEN);
        if (!StringUtils.isBlank(token)) {
            String userId = redisTemplate.opsForValue().get(Constants.TOKEN_PREFIX + token);
            if (StringUtils.isBlank(userId)) {
                return 0L;
            }
            return Long.valueOf(userId);
        }
        return 0L;
    }
}
