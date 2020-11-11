package com.hengtong.led.postFilt;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author fu
 */
public interface TestApi {


    /**
     * 测试
     */
    @PostMapping("admin/company/test/importFile")
    void importFile(@RequestParam(name = "file", required = false) MultipartFile file);


}
