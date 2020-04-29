package com.hengtong.led.controller;

import com.hengtong.led.templateUpload.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/template")
@Slf4j
public class TemplateUploadController {

    @Autowired
    private ReportService reportService;


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Object illegalBuildingUpload(MultipartFile file, @RequestParam String type) {
        log.info("文件上传---------");
        return reportService.dataUpload(file, type);
    }
}
