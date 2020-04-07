package com.hengtong.led;

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

    public static final CommonErrorCode FILE_IS_NULL = new CommonErrorCode(1007, "上传文件为空");

    public static final CommonErrorCode DATA_REPORT_ERROR = new CommonErrorCode(1008, "文件上传错误");

    public static final CommonErrorCode FILE_EXPORT_ERROR = new CommonErrorCode(1008, "文件导出错误");

    public static final CommonErrorCode USER_MUST_LOGNIN = new CommonErrorCode(1009, "用户还没有登录，请登录");

    public static final CommonErrorCode SERVICE_TYPE_ERROR = new CommonErrorCode(1010, "业务类型错误");


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

    @Override
    public String toString() {
        return "CommonErrorCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
