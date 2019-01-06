package com.base.entity.vo;

import lombok.Data;

/**
 * Author: Administrator
 * Date: 2019-01-06 16:19:00
 * Comment:
 */
@Data
public class ResponseData<T> {
    /**
     * 状态，SUCCESS 或 FAIL
     */
    public String status;
    /**
     * 返回数据
     * 如果status=SUCCESS， 返回前端需要的数据
     * 如果status=FAIL， 返回固定格式的错误数据，错误码errCode及错误描述errMsg
     */
    private T data;

    public ResponseData(){
        this.status = "SUCCESS";
    }

    public ResponseData(String status, T data){
        this.status = status;
        this.data = data;
    }

    public static <T> ResponseData<T> create(String status, T data){
        ResponseData<T> responseData = new ResponseData<>(status, data);
        return responseData;
    }

    public static <T> ResponseData<T> create(T data){
        ResponseData<T> responseData = new ResponseData<>("SUCCESS", data);
        return responseData;
    }
}
