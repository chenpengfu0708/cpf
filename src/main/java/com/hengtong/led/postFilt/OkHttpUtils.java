package com.hengtong.led.postFilt;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author fu
 */
public class OkHttpUtils {

    private final static int READ_TIMEOUT = 100;

    private final static int CONNECT_TIMEOUT = 60;

    private final static int WRITE_TIMEOUT = 60;

    private static volatile OkHttpClient okHttpClient;

    private OkHttpUtils() {

        okhttp3.OkHttpClient.Builder clientBuilder = new okhttp3.OkHttpClient.Builder();
        //读取超时
        clientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        //连接超时
        clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        //写入超时
        clientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        //自定义连接池最大空闲连接数和等待时间大小，否则默认最大5个空闲连接
        clientBuilder.connectionPool(new ConnectionPool(32, 5, TimeUnit.MINUTES));

        okHttpClient = clientBuilder.build();
    }

    public static OkHttpClient getInstance() {
        if (null == okHttpClient) {
            synchronized (OkHttpUtils.class) {
                if (okHttpClient == null) {
                    new OkHttpUtils();
                    return okHttpClient;
                }
            }
        }
        return okHttpClient;
    }

    /**
     * 获取文件base64格式
     * @param fileUrl
     * @return
     */
    public static String getBase64EncodeByUrl (String fileUrl) {
        try {
            OkHttpClient okHttpClient = OkHttpUtils.getInstance();
            Request request = new Request.Builder().url(fileUrl).get().build();
            Response okResponse = okHttpClient.newCall(request).execute();
            byte[] bytes = okResponse.body().bytes();
            if(ObjectUtil.isNotEmpty(bytes)){
                return Base64.encodeBase64String(bytes);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * xml格式post请求接口调用
     *
     * @param url    接口地址
     * @param xmlStr xml格式请求参数体
     * @return
     */
    public static String postXml(String url, String xmlStr) {
        RequestBody body = RequestBody.create(MediaType.parse("application/xml"), xmlStr);
        Request requestOk = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response;
        try {
            response = getInstance().newCall(requestOk).execute();
            String jsonString = response.body().string();
            if (response.isSuccessful()) {
                return jsonString;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "";
    }

    /**
     * json大写转小写
     *
     * @param jSONArray1 jSONArray1
     * @return JSONObject
     */
    public static JSONObject transToLowerObject(JSONObject jSONArray1) {
        JSONObject jSONArray2 = new JSONObject();
        Iterator it = jSONArray1.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            Object object = jSONArray1.get(key);
            if (object.getClass().toString().endsWith("String")) {
                jSONArray2.put(StrUtil.lowerFirst(key), object);
            } else if (object.getClass().toString().endsWith("JSONObject")) {
                jSONArray2.put(StrUtil.lowerFirst(key), transToLowerObject((JSONObject) object));
            } else if (object.getClass().toString().endsWith("JSONArray")) {
                jSONArray2.put(StrUtil.lowerFirst(key), transToArray(jSONArray1.getJSONArray(key)));
            }
        }
        return jSONArray2;
    }

    /**
     * jsonArray转jsonArray
     *
     * @param jSONArray1 jSONArray1
     * @return JSONArray
     */
    public static JSONArray transToArray(JSONArray jSONArray1) {
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray1.size(); i++) {
            Object jArray = jSONArray1.getJSONObject(i);
            if (jArray.getClass().toString().endsWith("JSONObject")) {
                jSONArray2.add(transToLowerObject((JSONObject) jArray));
            } else if (jArray.getClass().toString().endsWith("JSONArray")) {
                jSONArray2.add(transToArray((JSONArray) jArray));
            }
        }
        return jSONArray2;
    }


    public static void main(String[] args) {

        String responseStr = postJson("http://56.117.241.46:9090/zhongshan-financeplatform-web_cs/rest/bxtypeapi/quitresult", "{\"appkey\":\"financezhonshan_rbtest\",\"applyno\":\"TBaoHanZSRB2020120117105425339\",\"quitdate\":\"2020-12-01 17:26:10\",\"sign\":\"631a05166577f6f0be4e6601045d3a00a2cf60d2ac459ccacd5e52c7e07ce573\",\"status\":1,\"timestamp\":\"2020-12-02 14:59:06\"}");
        System.out.println(responseStr);
//        Map object = XmlUtil.xmlToMap(responseStr);
//        String mapString = JSON.toJSONString(object);
//        JSONObject js = transToLowerObject(JSON.parseObject(mapString));
    }

    /**
     * xml格式post请求接口调用
     *
     * @param url    接口地址
     * @param xmlStr xml格式请求参数体
     * @return
     */
    public static String postJson(String url, String xmlStr) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), xmlStr);
        Request requestOk = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response;
        try {
            response = getInstance().newCall(requestOk).execute();
            String jsonString = response.body().string();
            if (response.isSuccessful()) {
                return jsonString;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "";
    }
}
