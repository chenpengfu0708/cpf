package com.hengtong.led.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MinioService {

    @Autowired
    private MinioConfig minioConfig;


    public String uploadToMinio(MultipartFile file) {
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient = new MinioClient(minioConfig.getUrl(), minioConfig.getPost(), minioConfig.getAccessKey(),
                    minioConfig.getSecretKey());

            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists("test");
            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket("test");
            }
            // 使用putObject上传一个文件到存储桶中。
            minioClient.putObject("test",file.getOriginalFilename(), file.getInputStream(), file.getContentType());
            System.out.println("successfully...");
            return minioClient.getObjectUrl("test", file.getOriginalFilename());
        } catch(Exception e) {
            System.out.println("Error occurred: " + e);
        }
        return "";
    }

}
