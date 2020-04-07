package com.hengtong.led.exception;

import com.hengtong.led.CommonErrorCode;

/**
 * 统一异常定义
 * 
 * @author deng.huaiyu
 *
 */
public class BusinessRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int errorCode = -1;

	public BusinessRuntimeException(int errorCode,String msg) {
		super(msg);
		this.errorCode = errorCode;
	}

	public BusinessRuntimeException(int errorCode,String msg, Throwable e) {
		super(msg, e);
		this.errorCode = errorCode;
	}

	public BusinessRuntimeException(CommonErrorCode errorCode) {
		this(errorCode.getCode(), errorCode.getMessage());
	}

	public int getErrorCode() {
		return errorCode;
	}
}
