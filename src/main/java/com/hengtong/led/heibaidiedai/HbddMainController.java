package com.hengtong.led.heibaidiedai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HbddMainController {

    @RequestMapping(value = "/hbddMain", method = RequestMethod.GET)
    public String hbddMain(){
        return "hbddMain";
    }

    @RequestMapping(value = "/ew/hbddMain", method = RequestMethod.GET)
    public String hbdd(){
        return "hbdd";
    }

}
