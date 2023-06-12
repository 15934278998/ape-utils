package com.ape9527.utils.http;

import java.util.Map;

/**
 * Http请求工具类
 *
 * @author YuanShuai[apeblog@163.com]
 */
public class HttpUtil {
    /**
     * 向http发送post请求
     *
     * @param url          请求地址
     * @param params       请求参数, JSONObject.toString()
     * @param reqEncoding  请求参数编码格式，默认为utf8
     * @param respEncoding 响应解析编码格式，默认为utf8
     * @param header       请求头需要信息, key等于value格式
     * @return 返回信息
     */
    public static String sendPost(String url, String params, String reqEncoding, String respEncoding, String... header) {
        try {
            return X509TrustManagerImpl.httpRequest(url, "POST", params, reqEncoding, respEncoding, header);
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    /**
     * 向http发送get请求
     *
     * @param url          请求地址
     * @param params       请求参数
     * @param reqEncoding  请求参数编码格式，默认为utf8
     * @param respEncoding 响应解析编码格式，默认为utf8
     * @param header       请求头需要信息, key等于value格式
     * @return 返回信息
     */
    public static String sendGet(String url, Map<String, Object> params, String reqEncoding, String respEncoding, String... header) {
        try {
            if (params != null && params.size() > 0) {
                url = url + "?" + X509TrustManagerImpl.getParam(params);
            }
            return X509TrustManagerImpl.httpRequest(url, "GET", null, reqEncoding, respEncoding, header);
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }


}
