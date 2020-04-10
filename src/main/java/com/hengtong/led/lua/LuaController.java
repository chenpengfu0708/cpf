package com.hengtong.led.lua;

import com.hengtong.led.dto.HandlerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author cpf
 * @since 2020-04-09
 */
@Slf4j
@RestController
@RequestMapping("/lua")
public class LuaController {

    @GetMapping(value = "/test")
    public String test(String data) {

        return data;
    }


    @PostMapping(value = "/post")
    public String post(@RequestBody HandlerDto dto) {
        return "post";
    }
}
