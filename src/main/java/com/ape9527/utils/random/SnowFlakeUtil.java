package com.ape9527.utils.random;


/**
 * 雪花算法工具类
 *
 * @author YuanShuai[apeblog@163.com]
 */
public class SnowFlakeUtil {

    /**
     * 起始的时间戳
     */
    private final static long START_STAMP = 977932800000L;

    /**
     * 每一部分占用的位数
     * 序列号，机器标识，数据中心标识
     */
    private final static long SEQUENCE_BIT = 12L;
    private final static long MACHINE_BIT = 5L;
    private final static long DATACENTER_BIT = 5L;

    /**
     * 每一部分的最大值
     * 序列号，机器标识，数据中心标识
     */
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);
    private final static long MAX_MACHINE = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_DATACENTER = -1L ^ (-1L << DATACENTER_BIT);

    /**
     * 每一部分向左的位移
     * 机器标识，数据中心标识，时间戳
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    /**
     * 序列号，上一次时间戳
     */
    private static long sequence = 0L;
    private static long lastStamp = -1L;

    /**
     * 产生下一个ID
     * @param datacenterId 数据中心标识
     * @param machineId 机器标识
     * @return ID
     */
    public static synchronized long nextId(long datacenterId,long machineId) {
        if (machineId > MAX_MACHINE || machineId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", MAX_MACHINE));
        }
        if (datacenterId > MAX_DATACENTER || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", MAX_DATACENTER));
        }
        // 获取当前时间戳
        long currStamp = getNewStamp();
        // 如果当前时间戳小于上次时间戳就抛出异常
        if (currStamp < lastStamp) {
            throw new IllegalArgumentException("The current timestamp is smaller than the last timestamp.");
        }
        // 相同毫秒值，序列化自增
        if (currStamp == lastStamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0L) {
                currStamp = getNextMill();
            }
        } else {
            sequence = 0L;
        }
        lastStamp = currStamp;
        return (currStamp - START_STAMP) << TIMESTAMP_LEFT
                | datacenterId << DATACENTER_LEFT
                | machineId << MACHINE_LEFT
                | sequence;
    }

    public static long nextDefId() {
        return nextId(0L, 0L);
    }

    public static String nextDefStringId(){
        return String.valueOf(nextDefId());
    }

    private static long getNextMill() {
        long mill = getNewStamp();
        while (mill <= lastStamp) {
            mill = getNewStamp();
        }
        return mill;
    }

    private static long getNewStamp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        for (int i = 0; i <8; i++) {
            System.out.println(nextDefStringId());
        }
    }


}
