package com.hengtong.led.controller;

import com.hengtong.led.service.UserService;
import com.hengtong.led.utils.IOUtils;
import com.itextpdf.text.pdf.PdfReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@Controller
public class ExcelController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping(value = "admin/excel")
    public String excel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if (false) {
                userService.test();
            }
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的名称采用当前时间戳
            Long fileName = System.currentTimeMillis();
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName.toString() + ".xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            //生成表头
            Row row = sheet.createRow(0);
            for (int i = 0; i < 3; i++) {
                row.createCell(i).setCellValue("第" + i + "个单元格");
            }

            //模拟生成三条内容
            for (int a = 1; a < 4; a++) {
                Row row1 = sheet.createRow(a);
                for (int b = 0; b < 3; b++) {
                    row1.createCell(b).setCellValue("第" + b + "个数据");
                }
            }
            //输出到浏览器
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            log.error("e:" + e);
            return "失败啦";
        }

        return "成功啦";
    }


    @RequestMapping("/getPdf")
    public void showPdf(HttpServletResponse response) {
        response.setContentType("application/pdf");
        FileInputStream in;
        OutputStream out;
        try {
            in = new FileInputStream(new File("C:\\Users\\fu\\Desktop\\dzfp.pdf"));
            checkPdfCompleteness(in);
            out = response.getOutputStream();
            byte[] b = new byte[512];
            while ((in.read(b)) != -1) {
                out.write(b);
                out.flush();
            }
            in.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkPdfCompleteness(InputStream in){
        boolean result = false;
        PdfReader pdfReader = null;
        try {
            pdfReader = new PdfReader(IOUtils.toBufferedInputStream(in));
            result = !pdfReader.isTampered();
        } catch (IOException e) {
            //needless
        } finally {
            IOUtils.close(pdfReader);
        }
        System.out.println("保函是否完好：" + result);
        return result;
    }

    public static void main(String[] args) {
        FileInputStream in;
        OutputStream out;
        try {
            in = new FileInputStream(new File("C:\\Users\\fu\\Desktop\\mydzfp.pdf"));
            checkPdfCompleteness(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
