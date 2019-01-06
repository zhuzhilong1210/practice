package com.base.toolkit;

import org.springframework.beans.BeanUtils;

/**
 * @author: LIU ZEJUN
 * @date: 2018-11-27 19:20:00
 * @comment: beanUtils
 */
public class BeanUtil {

    public static <T> T copy(Object source, T target, String... ignoreProperties) {
        BeanUtils.copyProperties(source, target, ignoreProperties);
        return target;
    }
}
