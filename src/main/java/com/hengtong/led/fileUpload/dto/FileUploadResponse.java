package com.hengtong.led.fileUpload.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class FileUploadResponse {

    private String fileName;

    private String fileUrl;

    public FileUploadResponse() {
    }

    public FileUploadResponse(String fileName, String fileUrl) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }
}
