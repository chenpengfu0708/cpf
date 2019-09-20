package com.hengtong.led.excetion;

/**
 * 错误码
 *
 * @author deng.huaiyu
 */
public class CommonErrorCode {
    public static final CommonErrorCode UNKOWN = new CommonErrorCode(100, "系统内部错误");
    public static final CommonErrorCode PRAMERROR = new CommonErrorCode(101, "参数错误");
    public static final CommonErrorCode METHOD_NAMED_ERROR = new CommonErrorCode(100002, "方法命名有误");

    public static final CommonErrorCode ENCLOSURE_INFO_NULL = new CommonErrorCode(1004, "上传图片为空");
    public static final CommonErrorCode FILE_FORMAT_ERROR = new CommonErrorCode(1005, "上传类型错误(类型必须为：jpeg|jpg|png)");
    public static final CommonErrorCode FILE_SIZE_ERROR = new CommonErrorCode(1006, "文件大小不能超过5M");


    public CommonErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
