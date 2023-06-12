package com.ape9527.utils.random;

import java.util.Arrays;
import java.util.Random;

/**
 * 生成随机数工具类
 *
 * @author YuanShuai[apeblog@163.com]
 */
public class RandomUtil {

    /**
     * 生成一个[0,end)间的随机整数
     *
     * @param end 区间结束点
     * @return 随机数
     */
    public static int getIntRandom(int end) {
        Random random = new Random();
        return random.nextInt(end);
    }

    /**
     * 生成n个不同的[0,end)间的随机整数
     *
     * @param n 随机数个数
     * @param end 区间结束点
     * @return 随机数数组
     */
    public static Integer[] getArrIntRandom(int n, int end) {
        if (n > end) {
            n = end;
        }
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = getRandomNotArr(arr, end);
        }
        return arr;
    }

    /**
     * 生成一个不存在于ns[]中的[0,end)间的随机整数
     *
     * @param ns 数字数组
     * @param end 区间结束点
     * @return 随机数
     */
    public static int getRandomNotArr(Integer[] ns, int end) {
        int res = getIntRandom(end);
        if (Arrays.asList(ns).contains(res)) {
            res = getRandomNotArr(ns, end);
        }
        return res;
    }

    /**
     * 生产指定长度的数字验证码
     *
     * @param len 验证码长度
     * @return 验证码
     */
    public static String generateVerifyCode(int len) {
        String code = "";
        for (int i = 0; i < len; i++) {
            code += getIntRandom(10);
        }
        return code;
    }

}
