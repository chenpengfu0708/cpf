package com.hengtong.led.controller;

import com.hengtong.led.dto.HandlerDto;
import com.hengtong.led.fileUpload.dto.FileUploadResponse;
import com.hengtong.led.fileUpload.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/file")
@Slf4j
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;


    @RequestMapping(value = "/illegalBuilding/upload", method = RequestMethod.POST)
    public FileUploadResponse illegalBuildingUpload(MultipartFile file, HandlerDto request) {
        log.info("文件上传---------，body:{}", request);
        //2020.4.7 新增一个body，用于记录MultipartFile 与 body公用，去掉@RequestBody注解即可共用
        return fileUploadService.upload(file);
    }
}
