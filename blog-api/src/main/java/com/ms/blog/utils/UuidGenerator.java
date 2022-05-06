package com.ms.blog.utils;

import java.util.UUID;

/**
 * @author 量能授官 on 2020-03-08 20:09
 **/
public class UuidGenerator {
    /**
     * 生成36位的UUID
     *
     * @return
     */
    public static String generate36UUID(){
        return UUID.randomUUID().toString().toUpperCase();
    }
}
