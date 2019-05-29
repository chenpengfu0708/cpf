package com.hengtong.led.utils;

import org.springframework.util.Base64Utils;


public class Base64Util {
    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
	public static byte[] decryptBASE64(byte[] key) throws Exception {
        return Base64Utils.decode(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return new String(Base64Utils.encode(key));
    }
}
