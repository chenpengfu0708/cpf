package com.hengtong.led.heibaidiedai;

import com.hengtong.led.dto.CommonResponseDto;
import com.hengtong.led.dto.HbddRequestDto;
import com.hengtong.led.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HbddController {

    @Autowired
    private HbddMain hbddMain;
    @Autowired
    private RedisUtils redisUtils;


    @PostMapping("ew/con/hbdd")
    public CommonResponseDto hbdd(@RequestBody HbddRequestDto requestDto) {
        Map<String, String> map = new HashMap<>();
        int[][] myHbdd = hbddMain.mapToArray(requestDto.getToken());
        HbddMain.initMain();
        return hbddMain.sout(requestDto.getNum(), requestDto.getX(), requestDto.getY(), requestDto.getToken(), map, myHbdd);
    }

    @PostMapping("ew/con/checkStatus")
    public CommonResponseDto checkStatus(@RequestBody HbddRequestDto requestDto) {
        if (redisUtils.exitst("finish_" + requestDto.getToken())) {
            return new CommonResponseDto().code(0).finish(1);
        } else {
            return new CommonResponseDto().code(0).finish(0);
        }
    }


}
