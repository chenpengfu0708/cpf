package com.hengtong.led.threadLocal;

import com.hengtong.led.Constants;
import com.hengtong.led.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/threadLocal")
public class UserLoginController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserContext userContext;

    @RequestMapping(name = "用户登录",value = "/login", method = RequestMethod.GET)
    public String login(Integer id) {
        redisUtils.setKey(Constants.TOKEN_PREFIX + id, ""+id, 1L, TimeUnit.DAYS);
        return Constants.TOKEN_PREFIX + id;
    }

    @RequestMapping(value = "/getId", method = RequestMethod.GET)
    public String getUserId() {
        return userContext.getUserId().toString();
    }

}
