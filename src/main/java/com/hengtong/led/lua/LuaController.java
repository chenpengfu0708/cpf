package com.hengtong.led.lua;

import com.hengtong.led.utils.IpAddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author cpf
 * @since 2020-04-09
 */
@Slf4j
@RestController
@RequestMapping("/lua")
public class LuaController {

    @Autowired
    private LusService lusService;

    @Autowired
    private IpAddressUtil ipAddressUtil;

    @Value("${lp.key}")
    private String lpKey;


    @GetMapping(value = "/test")
    public String test(String data, HttpServletRequest request, HttpServletResponse response) {
        String url = ipAddressUtil.getIpAddr(request);
        System.out.println("ip = " + url);
        String hash = String.valueOf(data.hashCode());
        List lp = lusService.initLp(hash, lpKey, url);
        if (lp.get(0).toString().equals("1")) {
            return "相同的请求！";
        } else if ((Long)lp.get(1) < 0) {
            return "限流中，请稍后！";
        } else if (lp.get(2).toString().equals("-1")) {
            return "请求过于频繁，请稍后再试！";
        }

        //do some service...

        return "消耗一个令牌，剩余令牌数：" + lp.get(1).toString();
    }
}
