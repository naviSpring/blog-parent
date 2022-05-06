package com.ms.blog.handler;

import com.ms.blog.common.ServerResponse;
import com.ms.blog.enums.ErrorCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author STEEZ
 * @version 1.0
 * @date 2022/4/25 21:53
 */
//对加了controller注解的方法拦截进行aop的实现
//@ControllerAdvice
@RestControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(Exception.class)
//    @ResponseBody
    public ServerResponse doException(Exception e){
        e.printStackTrace();
        return ServerResponse.Custom(ErrorCode.SYSTEM_ERROR.getCode(),ErrorCode.SYSTEM_ERROR.getMsg(),null);
    }

}
