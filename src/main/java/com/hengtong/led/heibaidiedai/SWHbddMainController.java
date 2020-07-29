package com.hengtong.led.heibaidiedai;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SWHbddMainController {

    @RequestMapping(value = "/sw/hbddMain", method = RequestMethod.GET)
    public String test(Model model, @RequestParam("num") String num){
        model.addAttribute("num", num);
        return "swhbdd";
    }

}
