package com.base.toolkit;

import com.base.exception.BaseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    /**
     * 任意对象转换为 JSON
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String toJSON(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new BaseException("JSON序列号失败");
        }
    }

    /**
     * JSON 转换为对象
     * @param s
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toBean(String s, Class<T> clazz) {
        try {
            return mapper.readerFor(clazz).readValue(s);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException("JSON序列号失败");
        }
    }

    /**
     * JSON 转换为对象集合
     * @param s
     * @param clazz
     * @param <E>
     * @return
     */
    public static <E> List<E> toBeanList(String s, Class<E> clazz) {
        try {
            return mapper.readValue(s, mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException("JSON序列号失败");
        }
    }

    /**
     * JSON 转换为 HashMap
     * @param s
     * @param keyClass
     * @param valueClass
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> toMap(String s, Class<K> keyClass, Class<V> valueClass) {
        try {
            return mapper.readValue(s, mapper.getTypeFactory().constructMapType(HashMap.class, keyClass, valueClass));
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException("JSON序列号失败");
        }
    }
}