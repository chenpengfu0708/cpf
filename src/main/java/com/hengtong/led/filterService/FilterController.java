package com.hengtong.led.filterService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/filter")
public class FilterController {


    @GetMapping(value = "/testFilter")
    public Integer testFilter(){
        return 0;
    }

}
