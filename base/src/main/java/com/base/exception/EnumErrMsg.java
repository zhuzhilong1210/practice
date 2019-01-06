package com.base.exception;

public enum EnumErrMsg {
    SUCCESS("0", "SUCCESS"),
    SYSTEM("1", "系统错误"),
    UNKNOWN("2", "未知错误"),
    EMPTY("3", "返回值为空"),
    DB_EXCEPTION("4", "数据库错误"),
    OPERATION_INVALID("5", "非法操作"),


    DATA_IS_EMPTY("100001", "数据为空"),
    ;

    private String code;
    private String msg;

    EnumErrMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
