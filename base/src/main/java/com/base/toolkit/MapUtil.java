package com.base.toolkit;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: vincent
 * Date: 2018-12-04 15:51:00
 * Comment:
 */

public class MapUtil {

    public static Map<String, String> convertToStringMap(Map<String, Object> sourceMap) {
        Map<String, String> resultMap = new HashMap<>();
        sourceMap.forEach((key, value) -> resultMap.put(key, value.toString()));
        return resultMap;
    }

}
