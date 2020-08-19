package com.hengtong.led.html.controller;

import com.alibaba.fastjson.JSONObject;
import com.hengtong.led.dto.CommonResponseDto;
import com.hengtong.led.dto.TestRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class HtmlController {


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){

        return "test";
    }

    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public String video(){

        return "video";
    }

    @RequestMapping(value = "/jdan", method = RequestMethod.GET)
    public String jdan(){

        return "jdan";
    }


    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(){
        return "success";
    }


    @RequestMapping(value = "/echarts", method = RequestMethod.GET)
    public String echarts(){
        return "echarts";
    }


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public  @ResponseBody CommonResponseDto test(@RequestBody String request){
        TestRequestDto requestDto = JSONObject.parseObject(request, TestRequestDto.class);
        System.out.println(requestDto);
        return new CommonResponseDto().code(0).success(true);
    }

    @RequestMapping(value = "/layuiIndex", method = RequestMethod.GET)
    public String layuiIndex(Model model){
        model.addAttribute("name", "林三");
        return "layuiIndex";
    }
}
