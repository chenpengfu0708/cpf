package com.hengtong.led.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 上传文件工具
 */
@Slf4j
@Component
public class UploadImgUtil {

    private static final String basePath = "C:/img";


    public String upload(MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        String filePath = basePath + fileName;
        File directory = new File(basePath);
        File uploadFile = new File(filePath);
        try {
            if (!directory.exists() && !directory.isDirectory()) {
                directory.mkdir();
            }
            file.transferTo(uploadFile);
            return filePath;
        } catch (IOException e) {
            log.error("文件上传失败: " + e);
        }
        return "上传失败!";
    }

}
