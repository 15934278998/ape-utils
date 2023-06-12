package com.ape9527.utils.sign;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * MD5工具类
 *
 * @author YuanShuai[apeblog@163.com]
 */
@Slf4j
public class Md5Util {

    private static byte[] md5(String s) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        } catch (Exception e) {
            log.error("MD5错误: {}", e.getMessage());
        }
        return null;
    }

    private static final String toHex(byte hash[]) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    /**
     * MD5加密
     * @param str 待加密字符串
     * @return 加密后的字符串
     */
    public static String hash(String str) {
        try {
            return new String(toHex(md5(str)).getBytes("UTF-8"), "UTF-8");
        } catch (Exception e) {
            log.error("不支持的字符集: {}", e.getMessage());
            return str;
        }
    }

}
