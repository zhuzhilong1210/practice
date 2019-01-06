package com.base.controller;

import com.base.entity.vo.ResponseData;
import com.base.exception.BaseException;
import com.base.exception.EnumErrMsg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Administrator
 * Date: 2019-01-06 16:04:00
 * Comment:
 */

public class BaseController {
    protected ResponseEntity response(Object data) {
        return ResponseEntity.ok(ResponseData.create(data));
    }

    // 定义exceptionHandler 解决未被controller层吸收的exception
   /* @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    protected ResponseEntity handlerException(HttpServletRequest request, Exception ex) {
        Map<String, String> data = new HashMap<>();
        if (ex instanceof BaseException) {
            BaseException baseException = (BaseException) ex;
            data.put("errCode", baseException.getErrCode());
            data.put("errMsg", baseException.getErrMsg());
        } else {
            data.put("errCode", EnumErrMsg.UNKNOWN.getCode());
            data.put("errMsg", EnumErrMsg.UNKNOWN.getMsg());
        }
        return ResponseEntity.ok(ResponseData.create("FAIL", data));
    }*/
}
