package com.ms.blog.enums;

import lombok.AllArgsConstructor;

/**
 * @author STEEZ
 * @version 1.0
 * @date 2022/4/25 22:03
 */
@AllArgsConstructor
public enum ErrorCode {
    SYSTEM_ERROR(-999,"系统异常"),
    PARAMS_ERROR(10001,"参数有误"),
    ACCOUNT_PWD_NOT_EXIST(10002,"用户名或密码不存在"),
    NO_PERMISSION(70001,"无访问权限"),
    SESSION_TIME_OUT(90001,"会话超时"),
    NO_LOGIN(90002,"未登录"),
    ;

//    ErrorCode(int code, String msg) {
//        this.code = code;
//        this.msg = msg;
//    }

    private int code;

    private String msg;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
