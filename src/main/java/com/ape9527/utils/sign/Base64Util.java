package com.ape9527.utils.sign;

import lombok.extern.slf4j.Slf4j;

import java.util.Base64;

/**
 * Base64工具类
 *
 * @author YuanShuai[apeblog@163.com]
 */
@Slf4j
public class Base64Util {

    /**
     * Base64加密
     *
     * @param str 待加密字符串
     * @return 加密后的字符串
     */
    public static String encryptionToBase64(String str) {
        log.info("加密前: {}",str);
        byte[] bytes = str.getBytes();
        String encode = Base64.getEncoder().encodeToString(bytes);
        log.info("加密后: {}",encode);
        return encode;
    }

    /**
     * Base64解密
     *
     * @param str 待解密字符串
     * @return 解密后的字符串
     */
    public static String decodeByBase64(String str) {
        log.info("解密前: {}",str);
        byte[] bytes = str.getBytes();
        byte[] decodeByte = Base64.getDecoder().decode(bytes);
        String deCode = new String(decodeByte);
        log.info("解密后: {}",deCode);
        return deCode;
    }

}
