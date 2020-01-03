package com.hengtong.led.templateExport.controller;

import com.hengtong.led.templateExport.service.TemplateExportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class TemplateExportController {

    @Autowired
    private TemplateExportService templateExportService;


    @GetMapping(value = "/templateExport")
    public void templateExport(HttpServletRequest servletRequest,
                               HttpServletResponse servletResponse) {
        log.info("通用模板导出---------");
        templateExportService.export(servletRequest, servletResponse);
    }
}
