package com.hengtong.led.OkHttpClient;

import com.alibaba.fastjson.JSON;
import com.hengtong.led.utils.IOUtils;
import com.itextpdf.text.pdf.PdfReader;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
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

    public static void main(String[] args) throws IOException {
        InputStream inputStream = null;
        try {
            File f = new File("C:\\Users\\fu\\Desktop\\mydzfp.pdf");
            FileInputStream inputFile = new FileInputStream(f);
            byte[] buffer = new byte[(int) f.length()];
            inputFile.read(buffer);
            inputFile.close();
            String base64 = new BASE64Encoder().encode(buffer);
            inputStream = BaseToInputStream(base64);
            if (checkPdfCompleteness(inputStream)) {
                System.out.println("完好的文件！");
            } else {
                System.out.println("损坏的文件！");
            }
        } catch (IOException e) {
        } finally {
            inputStream.close();
        }
    }

    /**
     * base64转inputStream
     * @param base64string
     * @return
     */
    private static InputStream BaseToInputStream(String base64string){
        ByteArrayInputStream stream = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes1 = decoder.decodeBuffer(base64string);
            stream = new ByteArrayInputStream(bytes1);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return stream;
    }

    public static boolean checkPdfCompleteness(InputStream in){
        boolean result = false;
        PdfReader pdfReader = null;
        try {
            pdfReader = new PdfReader(IOUtils.toBufferedInputStream(in));
            result = !pdfReader.isTampered();
        } catch (IOException e) {
            //needless
        } finally {
            IOUtils.close(pdfReader);
        }
        return result;
    }

}
