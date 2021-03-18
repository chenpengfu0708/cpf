package com.hengtong.led.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import com.itextpdf.text.pdf.PdfReader;
import okhttp3.*;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;


@Component
public class HttpUtils {
    /**
     * 请求超时时间
     */
    private static final int TIME_OUT = 120000;

    /**
     * Https请求
     */
    private static final String HTTPS = "https";

    /**
     * Content-Type
     */
    private static final String CONTENT_TYPE = "Content-Type";

    /**
     * 表单提交方式Content-Type
     */
    private static final String FORM_TYPE = "application/x-www-form-urlencoded;charset=UTF-8";

    /**
     * JSON提交方式Content-Type
     */
    private static final String JSON_TYPE = "application/json;charset=UTF-8";

    /**
     * 发送Get请求
     *
     * @param url 请求URL
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    public static Response get(String url) throws IOException {
        return get(url, null);
    }

    /**
     * 发送Get请求
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    public static Response get(String url, Map<String, String> headers) throws IOException {
        if (null == url || url.isEmpty()) {
            throw new RuntimeException("The request URL is blank.");
        }

        // 如果是Https请求
        if (url.startsWith(HTTPS)) {
            getTrust();
        }
        Connection connection = Jsoup.connect(url);
        connection.method(Method.GET);
        connection.timeout(TIME_OUT);
        connection.ignoreHttpErrors(true);
        connection.ignoreContentType(true);
        connection.maxBodySize(0);

        if (null != headers) {
            connection.headers(headers);
        }

        Response response = connection.execute();
        return response;
    }

    /**
     * 发送JSON格式参数POST请求
     *
     * @param url 请求路径
     * @param params JSON格式请求参数
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    public static Response post(String url, String params) throws IOException {
        return doPostRequest(url, null, params);
    }

    /**
     * 发送JSON格式参数POST请求
     *
     * @param url 请求路径
     * @param headers 请求头中设置的参数
     * @param params JSON格式请求参数
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    public static Response post(String url, Map<String, String> headers, String params) throws IOException {
        return doPostRequest(url, headers, params);
    }

    /**
     * 字符串参数post请求
     *
     * @param url 请求URL地址
     * @param paramMap 请求字符串参数集合
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    public static Response post(String url, Map<String, String> paramMap) throws IOException {
        return doPostRequest(url, null, paramMap, null);
    }

    /**
     * 带请求头的普通表单提交方式post请求
     *
     * @param headers 请求头参数
     * @param url 请求URL地址
     * @param paramMap 请求字符串参数集合
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    public static Response post(Map<String, String> headers, String url, Map<String, String> paramMap) throws IOException {
        return doPostRequest(url, headers, paramMap, null);
    }

    /**
     * 带上传文件的post请求
     *
     * @param url 请求URL地址
     * @param paramMap 请求字符串参数集合
     * @param fileMap 请求文件参数集合
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    public static Response post(String url, Map<String, String> paramMap, Map<String, File> fileMap) throws IOException {
        return doPostRequest(url, null, paramMap, fileMap);
    }

    /**
     * 带请求头的上传文件post请求
     *
     * @param url 请求URL地址
     * @param headers 请求头参数
     * @param paramMap 请求字符串参数集合
     * @param fileMap 请求文件参数集合
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    public static Response post(String url, Map<String, String> headers, Map<String, String> paramMap, Map<String, File> fileMap) throws IOException {
        return doPostRequest(url, headers, paramMap, fileMap);
    }

    /**
     * 执行post请求
     *
     * @param url 请求URL地址
     * @param headers 请求头
     * @param jsonParams 请求JSON格式字符串参数
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    private static Response doPostRequest(String url, Map<String, String> headers, String jsonParams) throws IOException {
        if (null == url || url.isEmpty()) {
            throw new RuntimeException("The request URL is blank.");
        }

        // 如果是Https请求
        if (url.startsWith(HTTPS)) {
            getTrust();
        }

        Connection connection = Jsoup.connect(url);
        connection.method(Method.POST);
        connection.timeout(TIME_OUT);
        connection.ignoreHttpErrors(true);
        connection.ignoreContentType(true);
        connection.maxBodySize(0);

        if (null != headers) {
            connection.headers(headers);
        }

        connection.header(CONTENT_TYPE, JSON_TYPE);
        connection.requestBody(jsonParams);

        Response response = connection.execute();
        return response;
    }

    /**
     * 普通表单方式发送POST请求
     *
     * @param url 请求URL地址
     * @param headers 请求头
     * @param paramMap 请求字符串参数集合
     * @param fileMap 请求文件参数集合
     * @return HTTP响应对象
     * @throws IOException 程序异常时抛出，由调用者处理
     */
    private static Response doPostRequest(String url, Map<String, String> headers, Map<String, String> paramMap, Map<String, File> fileMap) throws IOException {
        if (null == url || url.isEmpty()) {
            throw new RuntimeException("The request URL is blank.");
        }

        // 如果是Https请求
        if (url.startsWith(HTTPS)) {
            getTrust();
        }

        Connection connection = Jsoup.connect(url);
        connection.method(Method.POST);
        connection.timeout(TIME_OUT);
        connection.ignoreHttpErrors(true);
        connection.ignoreContentType(true);
        connection.maxBodySize(0);

        if (null != headers) {
            connection.headers(headers);
        }

        // 收集上传文件输入流，最终全部关闭
        List<InputStream> inputStreamList = null;
        try {

            // 添加文件参数
            if (null != fileMap && !fileMap.isEmpty()) {
                inputStreamList = new ArrayList<InputStream>();
                InputStream in = null;
                File file = null;
                for (Entry<String, File> e : fileMap.entrySet()) {
                    file = e.getValue();
                    in = new FileInputStream(file);
                    inputStreamList.add(in);
                    connection.data(e.getKey(), file.getName(), in);
                }
            }

            // 普通表单提交方式
            else {
                connection.header(CONTENT_TYPE, FORM_TYPE);
            }

            // 添加字符串类参数
            if (null != paramMap && !paramMap.isEmpty()) {
                connection.data(paramMap);
            }

            Response response = connection.execute();
            return response;
        }

        // 关闭上传文件的输入流
        finally {
            closeStream(inputStreamList);
        }
    }

    /**
     * 关流
     *
     * @param streamList 流集合
     */
    private static void closeStream(List<? extends Closeable> streamList) {
        if (null != streamList) {
            for (Closeable stream : streamList) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取服务器信任
     */
    private static void getTrust() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[] { new X509TrustManager() {

                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            } }, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**=================================xiadanyi===================================*/

    /**
     * xiadanyi post
     * @param url
     * @param requestData
     * @param proxy
     */
    public static String post(String url, Map<String, String> requestData, Proxy proxy) throws IOException {
        OkHttpClient.Builder builder_temp = new OkHttpClient.Builder();
        //设置代理
        if(proxy!=null){
            builder_temp.proxy(proxy);
        }
        OkHttpClient client = builder_temp.build();;
        FormBody.Builder builder = new FormBody.Builder();
        for (Entry<String, String> entry : requestData.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder().url(url).post(body).build();
        Call call = client.newCall(request);
        okhttp3.Response response = call.execute();
        return response.body().string();
    }

    public static String postWithFile(String url, Map<String, String> requestData,File file, boolean isDzfp) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        MultipartBody.Builder builder = new MultipartBody.Builder("AaB03x");
        for (Entry<String, String> entry : requestData.entrySet()) {
            builder.addFormDataPart(entry.getKey(), (String) entry.getValue());
        }
        if (isDzfp) {
            builder.addFormDataPart("invoice_pdf", file.getName(),
                    RequestBody.create(MediaType.parse("application/pdf"), file));
        } else {
            builder.addFormDataPart("guarantee_pdf", file.getName(),
                    RequestBody.create(MediaType.parse("application/pdf"), file));
        }
        RequestBody body = builder.setType(MultipartBody.FORM).build();

        Request request = new Request.Builder().url(url).post(body).build();

        Call call = client.newCall(request);
        okhttp3.Response response = call.execute();
        return response.body().string();
    }


    /**
     * 配置代理
     * @return
     */
    public static Proxy getDevProxy() {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress("56.1......ip地址", 8088));
    }


    public static Map downloadFile(String downloadUrl,File file,Integer type) throws Exception {
        Map map = new HashMap();
        URL url = new URL(downloadUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为60秒
        conn.setConnectTimeout(60*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        InputStream inputStream = conn.getInputStream();
        //转换成file
        if(type==1){
            inputStreamToFile(inputStream,file);
        }else{
            String md5SHA256 = md5HashCode32(inputStream);
            map.put("md5SHA256",md5SHA256);
        }
        return map;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) throws Exception{
        try {
            ByteArrayOutputStream baos = cloneInputStream(ins);
            // 打开两个新的输入流
            InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());
            InputStream stream2 = new ByteArrayInputStream(baos.toByteArray());
            if (!checkPdfCompleteness(stream2)) {
                ins.close();
                stream1.close();
                stream2.close();
                //如果为损坏文件
                throw new Exception("下载文件转换失败，损坏的文件！");
            }
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = stream1.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
            stream1.close();
            stream2.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static ByteArrayOutputStream cloneInputStream(InputStream input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * java计算文件32位md5值
     * @param fis 输入流
     * @return
     */
    public static String md5HashCode32(InputStream fis) {
        try {
            //拿到一个MD5转换器,如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //分多次将一个文件读入，对于大型文件而言，比较推荐这种方式，占用内存比较少。
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            fis.close();
            //转换并返回包含16个元素字节数组,返回数值范围为-128到127
            byte[] md5Bytes  = md.digest();
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;//解释参见最下方
                if (val < 16) {
                    /**
                     * 如果小于16，那么val值的16进制形式必然为一位，
                     * 因为十进制0,1...9,10,11,12,13,14,15 对应的 16进制为 0,1...9,a,b,c,d,e,f;
                     * 此处高位补0。
                     */
                    hexValue.append("0");
                }
                //这里借助了Integer类的方法实现16进制的转换
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 检测文件是否完好
     * @param in
     * @return  false：损坏  true：完好
     */
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
