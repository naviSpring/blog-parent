package com.ms.blog.common;

import java.lang.annotation.*;

/**
 * @Author      :Wud
 * @CreateDate  2022/4/2 17:43
 * @desc    redis缓存注解+时效
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {

    long expire() default 1 * 60 * 1000;

    String name() default "";

}
