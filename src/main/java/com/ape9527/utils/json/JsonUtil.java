package com.ape9527.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Json解析工具类
 *
 * @author YuanShuai[apeblog@163.com]
 */
public class JsonUtil {
    /** 通用的解析器 */
    public static ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 获取JSON字符串
     * @param obj 需要转换的对象
     * @return 转换完成的JSON字符串
     */
    public static String toJSON(Object obj){
        String json = null;
        try {
            json = MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 解析对象
     * @param json 需要解析的JSON字符串
     * @param type 目标类型
     * @param <T> 泛型
     * @return 解析好的对象
     */
    public static <T> T parseObject(String json, Class<T> type){
        Object obj = null;
        try {
            obj = MAPPER.readValue(json, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (T) obj;
    }

    /**
     * 解析列表
     * @param json 需要解析的JSON字符串
     * @param type 目标类型
     * @param <T> 泛型
     * @return 解析好的对象列表
     */
    public static <T> List<T> parseList(String json, Class<T> type) {
        JavaType listType = MAPPER.getTypeFactory().constructParametricType(ArrayList.class, type);
        Object obj = null;
        try {
            obj = MAPPER.readValue(json, listType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (List<T>) obj;
    }
}
