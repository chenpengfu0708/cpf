package com.hengtong.led.OkHttpClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class OKClientController {

    @Autowired
    private MyOkService service;

    @RequestMapping(value = "/httpclient/upload", method = RequestMethod.POST)
    public Object illegalBuildingUpload(MultipartFile file, @RequestParam String type) {
        return service.callExcel(file, type, ZhangDaPao.class);
    }
}
