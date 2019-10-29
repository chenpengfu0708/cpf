package com.hengtong.led.fileUpload.service;

import com.hengtong.led.fileUpload.dto.FileUploadResponse;

import com.hengtong.led.fileUpload.utils.PathUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {


    /**
     * 文件上传
     */
    public FileUploadResponse upload(MultipartFile file) {
        return PathUtil.uploadFile(file);
    }

}
