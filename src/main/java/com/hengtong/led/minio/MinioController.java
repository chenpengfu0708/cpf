package com.hengtong.led.minio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class MinioController {

    @Autowired
    private MinioService minioService;


    @RequestMapping(value = "/minio/upload", method = RequestMethod.POST)
    public String illegalBuildingUpload(MultipartFile file) {
        log.info("文件上传---------");
        return minioService.uploadToMinio(file);
    }


}
