package com.base.toolkit;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author: LIU ZEJUN
 * @date: 2018-11-28 11:23:00
 * @comment: classUtil
 */
public class ClassUtil {
    @SuppressWarnings("unchecked")
    public static <T> T getInstance(T t) {
        try {
            return (T) t.getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T getInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getModelClass(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        // 得到泛型父类
        //一个泛型类可能有多个泛型形参，比如ClassName<T,K> 这里有两个泛型形参T和K，Class Name<T> 这里只有1个泛型形参T
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Class<T> modelClass;
        if (params.length - 1 < index) {
            modelClass = null;
        } else {
            modelClass = (Class<T>) params[index];
        }
        return modelClass;
    }

}
