package com.ape9527.utils.random;


import java.util.UUID;

/**
 * UUID工具类
 *
 * @author YuanShuai[apeblog@163.com]
 */
public class UuidUtil {
    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID() {
        return randomUUID().replace("-","");
    }

}
