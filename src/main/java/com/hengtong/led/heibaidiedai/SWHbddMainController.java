package com.hengtong.led.heibaidiedai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SWHbddMainController {

    @RequestMapping(value = "/sw/hbddMain", method = RequestMethod.GET)
    public String test(){
        return "swhbdd";
    }

}
