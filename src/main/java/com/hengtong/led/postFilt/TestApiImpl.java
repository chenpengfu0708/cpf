package com.hengtong.led.postFilt;

import okhttp3.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @author fu
 */
@RestController
public class TestApiImpl implements TestApi {

    OkHttpClient client = new OkHttpClient.Builder().build();

    @Override
    public void importFile(MultipartFile file) {
        try {
            String res = postFile("http://192.168.3.60:8888/v1/file/my/testFile", file);
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String postWithFile(String url, Map<String, String> requestData) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        MultipartBody.Builder builder = new MultipartBody.Builder("AaB03x");

        for (Map.Entry<String, String> entry : requestData.entrySet()) {

            if (entry.getValue().indexOf("@") == 0) {
                String filePath = entry.getValue().substring(1);
                File file = new File(filePath);
                builder.addFormDataPart(entry.getKey(), file.getName(),
                        RequestBody.create(getMimeType(filePath), file));
            } else {
                builder.addFormDataPart(entry.getKey(), (String) entry.getValue());
            }
        }

        RequestBody body = builder.setType(MultipartBody.FORM).build();

        Request request = new Request.Builder().url(url).post(body).build();

        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }

    public String postFile(String url, MultipartFile file) throws Exception {
        OkHttpClient client = new OkHttpClient.Builder().build();
        MultipartBody.Builder builder = new MultipartBody.Builder("AaB03x");
        File file1 = MyFileUtil.multipartFileToFile(file);
        builder.addFormDataPart("file", file1.getName(),
                RequestBody.create(MediaType.parse(file.getContentType()), file1));
        builder.addFormDataPart("sign", "123");
        RequestBody body = builder.setType(MultipartBody.FORM).build();
        Request request = new Request.Builder().url(url).post(body).build();
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }

    public static MediaType getMimeType(String file) {
        Path path = Paths.get(file);
        try {
            String contentType = Files.probeContentType(path);
            return MediaType.parse(contentType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
