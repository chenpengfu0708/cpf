package com.hengtong.led.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class Md5Util {


    /**
     * md5加密
     */
    public static String md5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public static void main(String[] args) {
        String a = "761f56b84529ec6c38192608d304cb05006da3aaf7c6793b74fea4f1f52b3e8ablock_name深施工（EPC）工程总承包（标段五）-(二次)-十二block_number811111181616518699-012btc_id223creditor谢谢谢谢有限公司enter_institution_type4guarantee_end_date2021-02-28methodinsurer_apply_guarantee_resultnoteorder_do_type1order_no1813037628price240principal测试焕换一有限公司real_pay240status1timestamp20201109162639761f56b84529ec6c38192608d304cb05006da3aaf7c6793b74fea4f1f52b3e8a";
        byte[] c = a.getBytes();
        System.out.println(c.toString());
        System.out.println(md5(a).toUpperCase());
    }
}
