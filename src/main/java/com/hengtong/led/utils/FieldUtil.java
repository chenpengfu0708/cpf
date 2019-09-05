package com.hengtong.led.utils;

import com.hengtong.led.dto.TestJsonDto;
import com.hengtong.led.entity.FanShe;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class FieldUtil {


    public static void filedTest() {
        List<FanShe> fanSheList = new ArrayList<>();
        fanSheList.add(new FanShe("name", "姓名"));
        fanSheList.add(new FanShe("sex", "性别"));
        fanSheList.add(new FanShe("age", 20));
        TestJsonDto dto = new TestJsonDto();
        for (FanShe fanShe : fanSheList) {
            for (Field field : dto.getClass().getDeclaredFields()) {
                System.out.println("filed = " + field);
                System.out.println("fanShe = " + field);
            }
        }
    }


}
