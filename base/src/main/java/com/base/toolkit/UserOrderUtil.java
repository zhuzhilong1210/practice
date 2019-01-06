package com.base.toolkit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Author: vincent
 * Date: 2018-12-01 16:20:00
 * Comment: 订单工具类
 */

public class UserOrderUtil {

    private static final String ORDER_CODE_PREFIX = "001SO";

    /**
     * 生成随机订单编号
     * @return
     */
    public static String randomCode() {
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        return ORDER_CODE_PREFIX + "-" + currentDate + new Random().nextInt(9999);
    }


}
