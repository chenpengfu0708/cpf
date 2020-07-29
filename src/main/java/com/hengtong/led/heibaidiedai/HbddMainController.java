package com.hengtong.led.heibaidiedai;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HbddMainController {

    @RequestMapping(value = "/hbddMain", method = RequestMethod.GET)
    public String hbddMain(){
        return "hbddMain";
    }

    @RequestMapping(value = "/hbdd", method = RequestMethod.GET)
    public String hbdd(Model model, @RequestParam("num") String num){
        model.addAttribute("num", num);
        return "hbdd";
    }

}
