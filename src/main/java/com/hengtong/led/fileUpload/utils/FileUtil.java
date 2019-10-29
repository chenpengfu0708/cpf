package com.hengtong.led.fileUpload.utils;

import com.hengtong.led.excetion.BusinessRuntimeException;
import com.hengtong.led.excetion.CommonErrorCode;
import com.hengtong.led.fileUpload.dto.FileUploadResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Date : 2019/7/17 10:39
 **/
public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
    private static final Integer SIZE = 100 * 1024 * 1024;

    private static String server = null;

    private FileUtil() {

    }

    /**
     * 文件上传方法
     *
     * @param multipartFile  文件
     * @param uploadFilePath 文件路径
     * @param servers        访问接口
     * @return 返回 文件名和文件路径
     */
    public static FileUploadResponse uploadFile(MultipartFile multipartFile, String uploadFilePath, String servers) {
        server = servers;
        return update(multipartFile, uploadFilePath);
    }

    /**
     * 上传
     *
     * @param multipartFile  文件
     * @param uploadFilePath 文件路径
     * @return 返回文件名和文件路径
     */
    private static FileUploadResponse update(MultipartFile multipartFile, String uploadFilePath) {
        //判断文件是否为空
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new BusinessRuntimeException(CommonErrorCode.FILE_IS_NULL);
        }
        //判断文件大小是否超过100M
        if (multipartFile.getSize() > SIZE) {
            throw new BusinessRuntimeException(CommonErrorCode.FILE_SIZE_ERROR);
        }
        //获取新的文件名
        String fileName = getAfterChangeName(multipartFile.getOriginalFilename());
        //判断上传目录是否存在
        makeDirPath(uploadFilePath);

        String path = uploadFilePath + fileName;

        try {
            //上传文件
            multipartFile.transferTo(new File(path));
        } catch (IOException e) {
            logger.error("文件上传错误：{}", e.getMessage());
            throw new BusinessRuntimeException(CommonErrorCode.DATA_REPORT_ERROR);
        }
        return returnData(fileName, uploadFilePath);
    }

    /**
     * 获取新名字
     *
     * @param originalFilename 原文件名
     * @return 返回新名字
     */
    private static String getAfterChangeName(String originalFilename) {
        return UUID.randomUUID() + "." + originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
    }

    /**
     * 判断路径是否存在，不存在就创建
     *
     * @param path 文件路径
     */
    private static void makeDirPath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            boolean flag = file.mkdirs();
            logger.info("创建文件{}", flag);
        }
    }


    /**
     * 返回参数
     */
    private static FileUploadResponse returnData(String fileName, String path) {
        return new FileUploadResponse(fileName, server + path + fileName);
    }


}
