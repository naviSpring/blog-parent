package com.ms.blog.exception;

import lombok.Data;

/**
 * @PackageName com.ms.blog.exception
 * @className RsaException
 * @Author :Wud
 * @CreateDate 2022/6/9 10:48
 * @Desc
 */
@Data
public class RsaException  extends RuntimeException{

    private final String message;

    public RsaException(String message) {
        this.message = message;
    }


}
