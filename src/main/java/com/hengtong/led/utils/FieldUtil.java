package com.hengtong.led.utils;

import com.hengtong.led.dto.TestJsonDto;
import com.hengtong.led.entity.FanShe;
import org.springframework.stereotype.Component;

import java.io.*;
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

    /**
     * 流转文件
     *
     * @param ins
     * @param file
     * @throws Exception
     */
    private static void inputStreamToFile(InputStream ins, File file) throws Exception {
        try {
            ByteArrayOutputStream baos = cloneInputStream(ins);
            // 打开两个新的输入流
            InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = stream1.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
            stream1.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static ByteArrayOutputStream cloneInputStream(InputStream input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
