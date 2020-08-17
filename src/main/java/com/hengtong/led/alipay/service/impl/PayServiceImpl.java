package com.hengtong.led.alipay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePayRequest;

import com.hengtong.led.alipay.config.PayConfig;
import com.hengtong.led.alipay.domain.Orders;
import com.hengtong.led.alipay.service.PayService;
import org.springframework.stereotype.Service;

/**
 * @author column
 */
@Service
public class PayServiceImpl implements PayService {


    private String privateKey = PayConfig.merchant_private_key;
    private String publicKey = PayConfig.alipay_public_key;
    private String appid = PayConfig.app_id;
    private String gatewayUrl = PayConfig.gatewayUrl;
    private String charset = PayConfig.charset;
    private String sign_type = PayConfig.sign_type;
    //private String authCode;

    @Override
    public String pay(Orders order) {
        AlipayClient alipayClient = new DefaultAlipayClient(
                gatewayUrl,
                appid,
                privateKey,
                "json",
                charset,
                publicKey,
                sign_type);

        AlipayTradePayRequest request = new AlipayTradePayRequest();
        AlipayTradePayModel model = new AlipayTradePayModel();

        String out_trade_no = order.getOrderno();
        String total_amount = order.getAmount().toString();
        String subject = order.getSubject();
        String body = order.getBody();

        request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        try {
            //调用API接口，发送支付请求
            return alipayClient.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return "";
    }
}
