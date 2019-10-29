package com.hengtong.led.controller;

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
    public FileUploadResponse illegalBuildingUpload(MultipartFile file) {
        log.info("文件上传---------");
        return fileUploadService.upload(file);
    }
}
