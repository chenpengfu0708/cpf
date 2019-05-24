package com.hengtong.led.controller;

import com.hengtong.led.entity.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @date: 2019/5/14 15:46
 * @author: rain
 * @description: led
 */
@Controller
public class PdfController {

    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/pdf");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename="+new Date().getTime()+".pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        List<User> list = new ArrayList<>();
        for (int i=0; i<3;i++){
            User user = new User();
            user.setId(1);
            user.setName("pdf"+i);
            list.add(user);
        }
        Paragraph paragraph = new Paragraph();
        Font font = new Font(BaseFont.createFont("/my.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED));
        font.setColor(0,0,255);
        font.setSize(16);
        document.add(new Paragraph("你好\n",font));
        for (User user1 : list) {
            PdfPTable table = new PdfPTable(3);
            PdfPCell cell = new PdfPCell();
            cell.setPhrase(new Paragraph(user1.getId().toString(), font));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(user1.getName(), font));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(user1.getId().toString(), font));
            table.addCell(cell);
            document.add(table);
        }
        document.close();
    }
}
