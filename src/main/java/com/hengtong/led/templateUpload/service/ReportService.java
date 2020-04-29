package com.hengtong.led.templateUpload.service;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.shade.org.apache.commons.lang3.StringUtils;
import com.hengtong.led.exception.BusinessRuntimeException;
import com.hengtong.led.templateUpload.entity.DoorplateQrcode;
import com.hengtong.led.templateUpload.entity.FieldTemplate;
import com.hengtong.led.templateUpload.entity.TemplateData;
import com.hengtong.led.templateUpload.entity.ZhangDaPao;
import com.hengtong.led.templateUpload.enu.ReportFileType;
import com.hengtong.led.templateUpload.repository.TemplateDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

@Service
@Slf4j
public class ReportService {

    private static final String YYYYMMDD_1 = "yyyy/MM/dd";

    private static final String YYYYMMDD_2 = "yyyy-MM-dd";

    private static final String YYYYMMDD_3 = "yyyy.MM.dd";

    private static final String YYYYMMDD_4 = "yyyy年MM月dd日";

    private static final String YYYYMMDD_5 = "yyyy-MM-dd hh:mm:ss";

    private static final String YYYYMMDD_6 = "yyyy.MM";

    private static final String YYYYMMDD_7 = "yyyy-MM";

    private static final String YYYYMMDD_8 = "yyyy/MM";

    private static final String YYYYMMDD_9 = "yyyy年MM月";

    private static final String YYYYMMDD_10 = "yyyyMM";


    @Autowired
    private TemplateDataRepository templateDataRepository;


    @Transactional
    public void dataUpload(MultipartFile file, String type) {
        log.info("数据上报------------------type:{}", type);

        if (file == null || file.isEmpty()) {
            log.info("上报文件为空");
            return;
        }

        Workbook workbook = null;

        FormulaEvaluator formulaEvaluator = null;

        try {

            workbook = WorkbookFactory.create(file.getInputStream());
            if (workbook instanceof XSSFWorkbook) {
                formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
            } else if (workbook instanceof HSSFWorkbook) {
                formulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
            }
            System.out.println("cell = " + workbook.getSheetAt(0).getRow(0).getCell(0));
        } catch (Exception e) {
            e.printStackTrace();
//            throw new BusinessRuntimeException(ErrorCode.DATA_REPORT_ERROR);
        }

        if (workbook == null) {
            System.out.println("workbook == null");
//            throw new BusinessRuntimeException(ErrorCode.REPORT_FILE_FORMAT_ERROR);
        }

        dispose(workbook, type, formulaEvaluator);
    }


    /**
     * 上报处理
     */
    public void dispose(Workbook hssfWorkbook, String reportFileType, FormulaEvaluator formulaEvaluator) {

        log.info("上报处理-------------reportFileType:{}", reportFileType);

        TemplateData templateData = null;
        switch (reportFileType) {

            case "ZhangDaPao":
                templateData = templateDataRepository.findByEntityObject("ZhangDaPao");
                List<ZhangDaPao> result = addData(templateData, hssfWorkbook,
                        new ZhangDaPao(), formulaEvaluator);
                System.out.println(result);
                //TODO: 后期处理获取到数据列

                break;
            default:
//                throw new BusinessRuntimeException(ErrorCode.REPORT_FILE_FORMAT_ERROR);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> addData(TemplateData templateData, Workbook hssfWorkbook, T t,
                               FormulaEvaluator formulaEvaluator) {

        JSONArray jsonArray = JSONArray.fromObject(new ArrayList<>());

        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {

            Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);

            if (hssfSheet == null) {
                continue;
            }

            List<FieldTemplate> fieldTemplateList = JSON.parseArray(templateData.getField(), FieldTemplate.class);

            for (int rowNum = templateData.getStartRow(); rowNum <= hssfSheet.getLastRowNum(); rowNum++) {

                Row hssfRow = hssfSheet.getRow(rowNum);

                if (hssfRow != null) {

                    T data = t;
                    for (FieldTemplate ft : fieldTemplateList) {
                        for (Field field : t.getClass().getDeclaredFields()) {
                            if (field.getName().equals(ft.getFieldName())) {
                                Cell cell = hssfRow.getCell(ft.getTemplateColumn());
                                verifyType(cell, ft, field, rowNum, data, formulaEvaluator);
                            }
                        }
                    }
                    jsonArray.add(data);
                }
            }
        }
        return (List<T>) JSONArray.toCollection(jsonArray, t.getClass());
    }

    /**
     * 校验类型
     */
    private <T> T verifyType(Cell cell, FieldTemplate ft, Field field, int rowNum, T data,
                             FormulaEvaluator formulaEvaluator) {
        // 校验非空
        if (ft.getIsNotNull().equals("是")) {
            if (cell == null || StringUtils.isBlank(cell.toString())) {
                throw new BusinessRuntimeException(1,
                        "数据格式错误:行:" + (rowNum + 1) + "；列:" + (ft.getTemplateColumn() + 1) + "，必要数据不能为空");
            }
        }
        // 校验长度
        if (cell != null && cell.toString().length() >= ft.getFieldLong()) {
            throw new BusinessRuntimeException(1,
                    "数据格式错误:行:" + (rowNum + 1) + "；列:" + (ft.getTemplateColumn() + 1) + "，长度超出限制");
        }
        try {

            field.setAccessible(true);

            if (ft.getFieldType().equals("String")) {
                // 读取到的是数字，处理为字符串
                String value;
                if (cell == null) {
                    value = "";
                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    value = cell.toString();
                } else if (cell.getCellTypeEnum() == CellType.FORMULA) {
                    // 读取到的是公式, 计算公式
                    value = String.valueOf(formulaEvaluator.evaluate(cell).getNumberValue());
                } else {
                    value = cell.toString();
                }
                field.set(data, value);
            } else if (ft.getFieldType().equals("Date")) {
                if (cell == null || StringUtils.isBlank(cell.toString())) {
                    //Date为空时不抛出异常，set Date为null，转为实体的时候判断
                    field.set(data, null);
                } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                    // 读取到的时间为数字类型
                    double d = cell.getNumericCellValue();
                    Date date = HSSFDateUtil.getJavaDate(d);
                    field.set(data, date);
                } else if (cell.getCellTypeEnum() == CellType.STRING) {
                    // 读取到的时间为文本类型
                    field.set(data, org.apache.commons.lang3.time.DateUtils.parseDate(cell.toString(), YYYYMMDD_1,
                            YYYYMMDD_2, YYYYMMDD_3, YYYYMMDD_4, YYYYMMDD_5, YYYYMMDD_6, YYYYMMDD_7, YYYYMMDD_8,
                            YYYYMMDD_9,YYYYMMDD_10));
                }
            } else if (ft.getFieldType().equals("BigDecimal")) {
                if (cell == null || StringUtils.isBlank(cell.toString())) {
                    field.set(data, new BigDecimal("0"));
                } else if (cell.getCellTypeEnum() == CellType.FORMULA) {
                    // 读取到的是公式, 计算公式
                    String s = String.valueOf(formulaEvaluator.evaluate(cell).getNumberValue());
                    BigDecimal decimal = new BigDecimal(s);
                    // 保留小数点两位
                    BigDecimal value = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
                    field.set(data, value);
                } else {
                    // 处理 12,234.12 类型的数字
                    String decimal = cell.toString().replaceAll(",", "");
                    field.set(data, new BigDecimal(decimal));
                }
            }
        } catch (Exception e) {
            log.info("" + e);
            throw new BusinessRuntimeException(1, "数据格式错误:行:" + (rowNum + 1) + "；列:" + (ft.getTemplateColumn() + 1));
        }
        return data;
    }
}
