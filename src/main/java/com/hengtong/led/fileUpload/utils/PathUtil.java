package com.hengtong.led.fileUpload.utils;

import com.hengtong.led.fileUpload.dto.FileUploadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Date : 2019/7/17 11:11
 **/
@Configuration
public class PathUtil {
    /**
     * 获取当前系统的分隔符
     */
    private final static String SEPARATOR = System.getProperty("file.separator");
    /**
     * 上传文件路径
     */
    private static String filePath;

    /**
     * 访问的地址
     */
    private static String servers;


    @Value("${upload.server}")
    public void setServers(String servers) {
        PathUtil.servers = servers;
    }

    @Value("${file.path}")
    public void setAttractionPath(String headImg) {
        PathUtil.filePath = headImg;
    }

    /**
     * 获取文件上传目录
     *
     * @return 上传目录(文件目录)
     */
    private static String getUploadFilePath() {
        return filePath.replace("/", SEPARATOR);
    }

    public static FileUploadResponse uploadFile(MultipartFile multipartFile) {
        return FileUtil.uploadFile(multipartFile, getUploadFilePath(), servers);
    }

    /**
     * @param fileName 文件名
     * @return 文件完整url(ip + 文件目录 + 文件名)
     */
    public static String getFilePath(String fileName) {
        return servers + getUploadFilePath() + fileName;
    }
}
