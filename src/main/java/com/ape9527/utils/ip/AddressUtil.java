package com.ape9527.utils.ip;

import com.alibaba.fastjson.JSONObject;
import com.ape9527.utils.http.HttpUtil;
import com.ape9527.utils.string.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取地址工具类
 *
 * @author YuanShuai[apeblog@163.com]
 */
@Slf4j
public class AddressUtil {
    /**
     * IP地址查询
     */
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    /**
     * 未知地址
     */
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip)
    {
        String address = UNKNOWN;
        // 内网不查询
        if (IpUtil.internalIp(ip))
        {
            return "内网IP";
        }
        try
        {
            Map<String,Object> param = new HashMap<>();
            param.put("ip",ip);
            param.put("json","true");
            String rspStr = HttpUtil.sendGet(IP_URL, param,"utf8","GBK");
            if (StringUtil.isEmpty(rspStr))
            {
                log.error("获取地理位置异常 {}", ip);
                return UNKNOWN;
            }
            JSONObject obj = JSONObject.parseObject(rspStr);
            String region = obj.getString("pro");
            String city = obj.getString("city");
            return String.format("%s %s", region, city);
        }
        catch (Exception e)
        {
            log.error("获取地理位置异常 {}", ip);
        }
        return address;
    }
}
