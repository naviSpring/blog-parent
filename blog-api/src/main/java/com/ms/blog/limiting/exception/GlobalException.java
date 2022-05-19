package com.ms.blog.limiting.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName com.ms.blog.limiting.exception
 * @className GlobalException
 * @Author :Wud
 * @CreateDate 2022/5/16 11:32
 * @Desc
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ServiceException.class)
    public Map<String,Object> serviceException(ServiceException e) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", 500);
        map.put("message", e.getMessage());
        return map;
    }

}
