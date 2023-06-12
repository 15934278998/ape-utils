package com.ape9527.utils.http;

import com.ape9527.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 自定义证书信任管理器及http请求发送
 *
 * @author YuanShuai[apeblog@163.com]
 */
@Slf4j
class X509TrustManagerImpl implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) {
        //Auto-generated method stub
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) {
        //Auto-generated method stub
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        //Auto-generated method stub
        return null;
    }

    /**
     * 发送Http请求
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求类型
     * @param param         请求参数json字符串
     * @param reqEncoding   请求参数编码格式，默认为utf8
     * @param respEncoding  响应解析编码格式，默认为utf8
     * @param header        额外请求头信息
     * @return
     * @throws Exception
     */
    protected static String httpRequest(String requestUrl, String requestMethod, String param,
                                        String reqEncoding, String respEncoding, String... header) throws Exception {
        reqEncoding = reqEncoding != null ? reqEncoding : "utf8";
        respEncoding = respEncoding != null ? respEncoding : "utf8";
        log.info("发送http请求");
        log.info("请求地址: {}", requestUrl);
        log.info("请求方式: {}", requestMethod);
        log.info("请求参数: {}", param);
        log.info("请求头信息: {}", Arrays.toString(header));
        URL url = new URL(requestUrl);
        //之后任何Https协议网站皆能正常访问
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //添加请求头信息
        conn.setRequestMethod(requestMethod);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        conn.setRequestProperty("Accept-Charset", "utf-8");
        conn.setRequestProperty("Content-type", "application/json");
        if (header != null) {
            //添加额外请求头信息
            for (String head : header) {
                String[] split = head.split("=");
                conn.setRequestProperty(split[0], split[1]);
            }
        }
        conn.connect();
        //往服务器端写内容
        if (null != param) {
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(param.getBytes(reqEncoding));
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), respEncoding));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            result.append(line);
        }
        return result != null ? result.toString() : "";
    }

    /**
     * 发送Https请求
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求类型
     * @param param         请求参数json字符串
     * @param reqEncoding   请求参数编码格式，默认为utf8
     * @param respEncoding  响应解析编码格式，默认为utf8
     * @param header        额外请求头信息
     * @return
     * @throws Exception
     */
    protected static String httpsRequest(String requestUrl, String requestMethod, String param,
                                         String reqEncoding, String respEncoding, String... header) throws Exception {
        reqEncoding = reqEncoding != null ? reqEncoding : "utf8";
        respEncoding = respEncoding != null ? respEncoding : "utf8";
        log.info("发送https请求");
        log.info("请求地址: {}", requestUrl);
        log.info("请求方式: {}", requestMethod);
        log.info("请求参数: {}", param);
        log.info("请求头信息: {}", Arrays.toString(header));
        SSLContext sslcontext = SSLContext.getInstance("SSL", "SunJSSE");
        sslcontext.init(null, new TrustManager[]{new X509TrustManagerImpl()}, new java.security.SecureRandom());
        URL url = new URL(requestUrl);
        HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslsession) {
                //这块也不用有啥逻辑，确认结果是true就行
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
        //之后任何Https协议网站皆能正常访问
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        //添加请求头信息
        conn.setRequestMethod(requestMethod);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        conn.setRequestProperty("Accept-Charset", "utf-8");
        conn.setRequestProperty("Content-type", "application/json");
        if (header != null) {
            //添加额外请求头信息
            for (String head : header) {
                String[] split = head.split("=");
                conn.setRequestProperty(split[0], split[1]);
            }
        }
        //必须设置false，否则会自动redirect到重定向后的地址
        conn.setInstanceFollowRedirects(false);
        conn.connect();
        //往服务器端写内容
        if (null != param) {
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(param.getBytes(reqEncoding));
        }
        String result = getReturn(conn, respEncoding);
        return result;
    }

    //请求url获取返回的内容
    protected static String getReturn(HttpsURLConnection connection, String respEncoding) throws Exception {
        StringBuffer buffer = new StringBuffer();
        //将返回的输入流转换成字符串
        try (InputStream inputStream = connection.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, respEncoding);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            String result = buffer.toString();
            return result;
        }
    }

    /**
     * 构建Post请求参数
     * 将map转为 json
     *
     * @param params
     * @return
     */
    protected static String postParam(Map<String, Object> params) {
        return JsonUtil.toJSON(params);
    }

    /**
     * 构建Get请求参数
     * 将map转为 name1=value1&name2=value2 的形式。
     *
     * @param params
     * @return
     */
    protected static String getParam(Map<String, Object> params) {
        return params.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
    }

}
