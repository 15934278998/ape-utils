package com.ape9527.utils.http;


import java.util.Map;

/**
 * Https请求工具类
 *
 * @author YuanShuai[apeblog@163.com]
 */
public class HttpsUtil {

    /**
     * 向https发送post请求
     *
     * @param url          请求地址
     * @param params       请求参数, JSONObject.toString()
     * @param reqEncoding  请求参数编码格式，默认为utf8
     * @param respEncoding 响应解析编码格式，默认为utf8
     * @param header       请求头需要信息, key等于value格式
     * @return 返回信息
     */
    public static String sendSslPost(String url, String params, String reqEncoding, String respEncoding, String... header) {
        try {
            return X509TrustManagerImpl.httpsRequest(url, "POST", params, reqEncoding, respEncoding, header);
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    /**
     * 向https发送get请求
     *
     * @param url          请求地址
     * @param params       请求参数
     * @param reqEncoding  请求参数编码格式，默认为utf8
     * @param respEncoding 响应解析编码格式，默认为utf8
     * @param header       请求头需要信息, key等于value格式
     * @return 返回信息
     */
    public static String sendSslGet(String url, Map<String, Object> params, String reqEncoding, String respEncoding, String... header) {
        try {
            if (params != null && params.size() > 0) {
                url = url + "?" + X509TrustManagerImpl.getParam(params);
            }
            return X509TrustManagerImpl.httpsRequest(url, "GET", null, reqEncoding, respEncoding, header);
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
