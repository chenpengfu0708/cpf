package com.hengtong.led.OkHttpClient;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class MyOkService {

    private static final String url = "http://101.200.77.25:9009/upload";
    public static final MediaType JSONType = MediaType.parse("multipart/form-data");

    static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(400, TimeUnit.SECONDS)//设置连接超时时间
            .readTimeout(400, TimeUnit.SECONDS)//设置读取超时时间
            .build();

    public <T> T callExcel(MultipartFile file, String type, Class<T> respType) {
        try {
            System.out.println("...");

            RequestBody requestBody = RequestBody.create(JSONType, file.getBytes());
            Request request = new Request.Builder().url(url)
                    .addHeader("type", type)
                    .addHeader("file", file.getBytes().toString())
                    .post(requestBody).build();
            Response response;
            Call call = client.newCall(request);
            try {
                System.out.println("call");
                response = call.execute();
                System.out.println("response = " + response);
                String responseBodyStr = response.body().string();
                return JSON.parseObject(responseBodyStr, respType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
