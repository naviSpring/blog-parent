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
    TOKEN_ILLEGAL(10003,"非法的符号"),
    NO_PERMISSION(70001,"无访问权限"),
    SESSION_TIME_OUT(90001,"会话超时"),
    NO_LOGIN(90002,"未登录"),
    FILE_UPLOAD_ERROR(20001,"上传失败"),
    VISIT_TOO_FREQUENTLY(20002,"访问过于频繁，请稍候再试")
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
