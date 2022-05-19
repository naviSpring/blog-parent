package com.ms.blog.limiting.exception;

import com.ms.blog.enums.ErrorCode;
import lombok.Data;

/**
 * @PackageName com.ms.blog.limiting.exception
 * @className ServiceException
 * @Author :Wud
 * @CreateDate 2022/5/16 11:38
 * @Desc
 */
@Data
public class ServiceException extends RuntimeException {

    private int code;
    private String msg;

    public ServiceException(ErrorCode errorCode) {
        super();
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public ServiceException(int code,String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }


}
