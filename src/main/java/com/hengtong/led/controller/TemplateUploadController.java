package com.hengtong.led.controller;

import com.alipay.api.domain.Person;
import com.hengtong.led.templateUpload.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public static void main(String[] args) {
        //模拟数据
        //oracle数据列
        List<Map<String, Object>> oracleList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> oracleMap = new HashMap<>();
            oracleMap.put("DWH", "00" + i);
            oracleMap.put("DWMH", "办公室" + i);
            oracleList.add(oracleMap);
        }
        //mysql数据列
        List<Map<String, Object>> mysqlList = new ArrayList<>();
        for (int i = 3; i < 6; i++) {
            Map<String, Object> mysqlMap = new HashMap<>();
            mysqlMap.put("org_code", "00" + i);
            mysqlMap.put("departname", "机构" + i);
            mysqlList.add(mysqlMap);
        }

        //遍历比较
        //取所有的key
        List<String> oracleDWHKey = oracleList.stream().map(o ->{
            return o.get("DWH").toString();
        }).collect(Collectors.toList());

        List<String> mysqlOrgKey = mysqlList.stream().map(m ->{
            return m.get("org_code").toString();
        }).collect(Collectors.toList());

        oracleDWHKey.forEach(oracle -> {
            if (!mysqlOrgKey.contains(oracle)) {
                System.out.println("这个没有：" + oracle);
            }
        });
    }
}
