package com.ape9527.utils.random;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子计数工具类
 *
 * @author YuanShuai[apeblog@163.com]
 */
public class AtomicCounterUtil {

    private static AtomicInteger counter = new AtomicInteger(0);

    /**
     * 生成一个唯一的long（一个系统内）
     *
     * @return 随机long
     */
    public static long getAtomicCounter() {
        if (counter.get() > 999999) {
            counter.set(1);
        }
        long time = System.currentTimeMillis();
        long returnValue = time * 100 + counter.incrementAndGet();
        return returnValue;
    }

}
