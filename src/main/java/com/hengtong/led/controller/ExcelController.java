package com.hengtong.led.controller;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@Controller
public class ExcelController {


    @GetMapping(value = "admin/excel")
    public void excel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的名称采用当前时间戳
        Long fileName = new Date().getTime();
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName.toString() + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        //生成表头
        Row row = sheet.createRow(0);
        for (int i=0; i<3; i++){
            row.createCell(i).setCellValue("第" + i +"个单元格");
        }

        //模拟生成三条内容
        for (int a=1; a<4; a++){
            Row row1 = sheet.createRow(a);
            for (int b=0; b<3; b++){
                row1.createCell(b).setCellValue("第" + b +"个数据");
            }
        }
        //输出到浏览器
        workbook.write(response.getOutputStream());
    }


}
