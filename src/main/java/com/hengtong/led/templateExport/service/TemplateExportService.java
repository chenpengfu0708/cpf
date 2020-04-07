package com.hengtong.led.templateExport.service;

import com.alibaba.fastjson.JSON;
import com.hengtong.led.CommonErrorCode;
import com.hengtong.led.exception.BusinessRuntimeException;
import com.hengtong.led.templateExport.dto.DataField;
import com.hengtong.led.templateExport.dto.FirstTest;
import com.hengtong.led.templateExport.dto.HeadFiled;
import com.hengtong.led.templateExport.entity.ExportTemplateData;
import com.hengtong.led.templateExport.repository.ExportTemplateDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TemplateExportService {

    @Autowired
    private ExportTemplateDataRepository exportTemplateDataRepository;


    public void export(HttpServletRequest servletRequest,
                       HttpServletResponse servletResponse) {
        servletResponse.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的名称采用当前时间戳
        Long fileName = new Date().getTime();
        servletResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName.toString() + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        XSSFSheet sheet = workbook.createSheet();
        CellStyle cellstyle = workbook.createCellStyle();
        cellstyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellstyle.setAlignment(HorizontalAlignment.CENTER);

        ExportTemplateData templateData = exportTemplateDataRepository.findByEntityObject("firstTest");

        List<HeadFiled> headFiledList = JSON.parseArray(templateData.getHeadFiled(), HeadFiled.class);

        createHeadRow(sheet, headFiledList, templateData.getHeadStartRow(), cellstyle);

        List<FirstTest> dataList = new ArrayList<>();
        dataList.add(new FirstTest("测试1", 20, new Date()));
        dataList.add(new FirstTest("测试2", 25, new Date()));
        dataList.add(new FirstTest("测试3", 40, new Date()));

        List<DataField> dataFieldList = JSON.parseArray(templateData.getDataField(), DataField.class);

        createDataRow(sheet, dataList, templateData.getDataStartRow(), dataFieldList, cellStyle);

        try {
            workbook.write(servletResponse.getOutputStream());
        } catch (IOException e) {
            log.error("文件导出异常，e={}", e);
            throw new BusinessRuntimeException(CommonErrorCode.FILE_EXPORT_ERROR);
        }

    }


    /**
     * 生成表头
     */
    public static void createHeadRow(XSSFSheet sheet, List<HeadFiled> headList, Integer startRow, CellStyle cellstyle) {
        XSSFRow row = sheet.createRow(startRow);
        for (HeadFiled filed : headList) {
            if (filed.getIsMergeCell().equals("是")) {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(filed.getFirstRow(), filed.getLastRow(),
                        filed.getFirstCol(), filed.getLastCol());
                sheet.addMergedRegion(cellRangeAddress);
            }
            XSSFCell cell = row.createCell(filed.getTemplateColumn());
            cell.setCellValue(filed.getValue());
            cell.setCellStyle(cellstyle);
            sheet.setColumnWidth(filed.getTemplateColumn(), 256 * filed.getColumnWidth() + 184);
        }
    }


    /**
     * 生成数据
     */
    public <T> void createDataRow(XSSFSheet sheet, List<T> dataList, Integer startRow, List<DataField> dataFieldList,
                                  CellStyle cellstyle) {
        for (T t : dataList) {
            XSSFRow row = sheet.createRow(startRow);
            for (DataField dataField : dataFieldList) {
                if (dataField.getIsMergeCell().equals("是")) {
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(startRow, startRow,
                            dataField.getFirstCol(), dataField.getLastCol());
                    sheet.addMergedRegion(cellRangeAddress);
                }
                for (Field field : t.getClass().getDeclaredFields()) {
                    if (field.getName().equals(dataField.getFieldName())) {
                        try {
                            field.setAccessible(true);
                            XSSFCell cell = row.createCell(dataField.getTemplateColumn());
                            cell.setCellStyle(cellstyle);
                            Object value = field.get(t);
                            if (dataField.getIsTime().equals("是")) {
                                Date date = (Date) value;
                                cell.setCellValue(getTimeByPattern(date, dataField.getPattern()));
                            } else {
                                cell.setCellValue(value.toString());
                            }
                        } catch (IllegalAccessException e) {
                            log.info("字段获取value出错" + e);
                            throw new BusinessRuntimeException(CommonErrorCode.FILE_EXPORT_ERROR);
                        }
                        break;
                    }
                }
            }
            startRow += 1;
        }

    }


    /**
     * Date 转 String 格式
     */
    public static String getTimeByPattern(Date date, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(date);
    }


}
